package com.examsys.service.Impl;

import com.examsys.dao.StudentPointDetailMapper;
import com.examsys.dao.StudentPointMapper;
import com.examsys.model.StudentPoint;
import com.examsys.model.StudentPointDetail;
import com.examsys.model.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Repository
public class StudentPointServiceImpl {

    @Autowired
    StudentPointDetailMapper studentPointDetailMapper;
    @Autowired
    StudentPointMapper studentPointMapper;

    //todo:缺少transcation
    public ResponseEntity addStuAns(Map<String,Object> map){
        ResponseEntity responseEntity = new ResponseEntity();
        String paper_code = String.valueOf(map.get("paper_code"));
        int exam_id = Integer.valueOf(map.get("exam_id").toString());
        int student_id = Integer.valueOf(map.get("student_id").toString());
        List<Map<String,Object>> paper_status = (List<Map<String,Object>>)map.get("paper_status");
        double def_total_point = 0.0;
        for(int i=0;i<paper_status.size();i++){

            Map<String,Object> stuQues = paper_status.get(i);
            int id = Integer.valueOf(stuQues.get("id").toString());//question的ID
            String content = String.valueOf(stuQues.get("content"));
            String type = String.valueOf(stuQues.get("type"));
            String description = String.valueOf(stuQues.get("description"));
            double total_point = Double.valueOf(stuQues.get("total_point").toString());
            String student_answer = String.valueOf(stuQues.get("student_answer"));
            int mustOrNot = Integer.valueOf(stuQues.get("mustOrNot").toString());

            StudentPointDetail studentPointDetail = new StudentPointDetail();
            studentPointDetail.setQuestionId(id);
            studentPointDetail.setStudentId(student_id);
            studentPointDetail.setPaperCode(paper_code);
            studentPointDetail.setExamId(exam_id);
            studentPointDetail.setQuestionStatus(0);
            studentPointDetail.setDefPoint(total_point);
            studentPointDetail.setStudentAnswer(student_answer);
            def_total_point = def_total_point + total_point;

            StudentPointDetail alreadyStuRecord = studentPointDetailMapper.selectByIds(studentPointDetail);
            if (alreadyStuRecord != null){
                throw new RuntimeException("系统错误");
            }else {
                int res = studentPointDetailMapper.insert(studentPointDetail);
                if(res<0){
                    throw new RuntimeException("数据库错误");
                }
            }


        }
        StudentPoint studentPoint = new StudentPoint();
        studentPoint.setStudentId(student_id);
        studentPoint.setPaperTotalPoint(def_total_point);
        studentPoint.setExamId(exam_id);
        studentPoint.setSubjectiveStatus(0);
        studentPoint.setObjectiveStatus(0);
        studentPoint.setPaperCode(paper_code);

        StudentPoint alreadyStuRecord = studentPointMapper.selectByIds(studentPoint);
        if(alreadyStuRecord != null){
            throw new RuntimeException("系统错误");
        }else{
            int res =studentPointMapper.insert(studentPoint);
            if(res<0){
                throw new RuntimeException("数据库错误");
            }else {
                responseEntity.setMsg("考试结束");
                responseEntity.setStatus(200);
            }
        }

        return responseEntity;
    }
}
