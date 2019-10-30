package com.examsys.service.Impl;

import com.examsys.dao.ExamGroupMapper;
import com.examsys.dao.ExamMapper;
import com.examsys.model.Exam;
import com.examsys.model.ExamGroup;
import com.examsys.model.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class ExamServiceImpl{


    @Autowired
    ExamMapper examMapper;
    @Autowired
    ExamGroupMapper examGroupMapper;


    public ResponseEntity addNewExam(Map<String,Object> map){
        ResponseEntity responseEntity=new ResponseEntity();


        Exam exam=new Exam();
        exam.setExamName(String.valueOf(map.get("exam_name")));

        if (map.get("paper_code") == null || map.get("paper_code") == "") {

            responseEntity.setStatus(-1);
            responseEntity.setMsg("请选择试卷");
            return responseEntity;
        }
        exam.setPaperCode(String.valueOf(map.get("paper_code")));
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        exam.setBeginTime(String.valueOf(map.get("begin_time")));
        exam.setEndTime(String.valueOf(map.get("end_time")));
        exam.setDuration(String.valueOf(map.get("duration")));

        try{
            Date dtBegin = dateFormatTime.parse(exam.getBeginTime());
            Date dtEnd = dateFormatTime.parse(exam.getEndTime());

            if(dtBegin.getTime() >= dtEnd.getTime()){
                throw new RuntimeException("开始日期在结束日期之后,请重新提交");
            }
        }catch (Exception e){
            throw new RuntimeException("日期格式错误，请重新提交");

        }

        int examId;
        if(map.get("id") == null ||map.get("id") ==""){
            //新建
            int res=examMapper.insert(exam);
            exam.setStatus("未开始");
            examId=exam.getId();
        }else{
            examId = Integer.valueOf(String.valueOf(map.get("id")));
            exam.setId(examId);

            String status = String.valueOf(map.get("status"));
            if(status.equals("未开始")){
                //xiugai
                int res= examMapper.updateByPrimaryKey(exam);

                int res2 =examGroupMapper.deleteByExamId(examId);
            }else {
                responseEntity.setMsg("已完成或进行中的考试信息不允许修改");
                responseEntity.setStatus(-1);
                return responseEntity;
            }

        }


        if(map.get("group_ids") == null || map.get("group_ids") == ""){
            responseEntity.setMsg("未添加组");
            responseEntity.setStatus(-1);
            return responseEntity;
        }else {
            ArrayList<Integer> groupIds=(ArrayList<Integer>)map.get("group_ids");
            responseEntity=addExamGroup(examId,groupIds);
            return responseEntity;
        }


    }



    public ResponseEntity addExamGroup(int examId,ArrayList<Integer> groupIds){
        for(int i=0;i<groupIds.size();i++){

            ExamGroup examGroup=new ExamGroup();
            examGroup.setExamId(examId);
            examGroup.setGroupId(groupIds.get(i));
            ExamGroup examGroup1=examGroupMapper.selectByExamGroup(examGroup);
            if(examGroup1!=null){

            }else{
                int res2=examGroupMapper.insert(examGroup);
            }
        }
        ResponseEntity responseEntity=new ResponseEntity();
        responseEntity.setStatus(200);
        responseEntity.setMsg("操作成功");
        return responseEntity;
    }

    public ResponseEntity endExam(int examId){
        ResponseEntity responseEntity = new ResponseEntity();
        Exam exam = new Exam();
        exam.setStatus("已结束");
        exam.setId(examId);
        int res = examMapper.updateExamStatus(exam);
        if(res<0){
            throw new RuntimeException("数据库错误");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("更新成功");
        }
        return responseEntity;
    }

    public ResponseEntity delExam(Map<String,Object> map){
        ResponseEntity responseEntity = new ResponseEntity();
        List<Integer> examIds = (List<Integer>)map.get("exam_id");
        for(int i=0;i<examIds.size();i++){

            int examId = examIds.get(i);
            Exam exam = examMapper.selectByPrimaryKey(examId);
            if(!exam.getStatus().equals("未开始")){
                responseEntity.setStatus(-1);
                responseEntity.setMsg("考试已结束或正在继续进行中，不得删除");
                return responseEntity;
            }else{

                int res2 = examGroupMapper.deleteByExamId(examId);

                int res = examMapper.deleteByPrimaryKey(examId);


            }
        }

        responseEntity.setMsg("删除成功");
        responseEntity.setStatus(200);
        return responseEntity;
    }

}
