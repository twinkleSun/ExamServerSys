package com.examsys.service.Impl;

import com.examsys.dao.ExamMapper;
import com.examsys.model.Exam;
import com.examsys.model.entity.ExamEntity;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.model.entity.StuPointList;
import com.examsys.util.error.ErrorMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class ExamGroupServiceImpl {

    @Autowired
    ExamMapper examMapper;


    /**
     * 考生查询考试
     * @param userId
     * @return
     */
    public ResponseEntity getExamListByStudent(int userId) {
        List<StuPointList> examList = examMapper.selectExamByUserId(userId);
        if(examList==null || examList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.STUDENT_HAS_NO_EXAM);
        }

        List<StuPointList> stuPointLists = new ArrayList<>();
        for(int i = 0; i < examList.size(); i++) {
            StuPointList stuPointList = examList.get(i);

            Exam exam = new Exam();
            exam.setId(stuPointList.getId());
            exam.setStatus(stuPointList.getStatus());
            exam.setBeginTime(stuPointList.getBeginTime());
            exam.setDuration(stuPointList.getDuration());
            exam.setEndTime(stuPointList.getEndTime());
            exam.setExamName(stuPointList.getExamName());
            exam.setPaperCode(stuPointList.getPaperCode());

            if(!exam.getStatus().equals("已结束")) {
                exam = handleExamStatus(exam);
                if(!exam.getStatus().equals("已结束") && !exam.getStatus().equals("判题中")){
                    stuPointLists.add(stuPointList);
                }
            }
            examMapper.updateExamStatus(exam);
        }
        return new ResponseEntity(200,"查询成功",stuPointLists);
    }


    /**
     * 管理员获取考试列表
     * @return
     */
    public ResponseEntity getExamListByAdmin() {
        List<ExamEntity> examList = examMapper.selectWithPaper();
        if(examList == null || examList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.NO_EXAM_LIST);
        }

        for(int i = 0; i < examList.size(); i++) {
            ExamEntity examEntity = examList.get(i);
            if(!examEntity.getStatus().equals("已结束")) {
                examEntity = handleExamEntity(examEntity);
            }
            Exam examDB = new Exam();
            examDB.setId(examEntity.getId());
            examDB.setStatus(examEntity.getStatus());
            examMapper.updateExamStatus(examDB);
        }
        return new ResponseEntity(200,"查询成功",examList);
    }


    /**
     * 处理考试状态
     * @param exam
     * @return
     */
    public Exam handleExamStatus(Exam exam){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = simpleDateFormat.format(new java.util.Date());

        if(current.compareTo(exam.getBeginTime()) <= 0)  {
            exam.setStatus("未开始");
        } else if(current.compareTo(exam.getBeginTime()) > 0 && current.compareTo(exam.getEndTime()) <= 0){
            exam.setStatus("进行中");
        } else if(current.compareTo(exam.getEndTime()) > 0) {
            exam.setStatus("判卷中");
        }
        return exam;
    }



    /**
     * 处理考试状态
     * @param examEntity
     * @return
     */
    public ExamEntity handleExamEntity(ExamEntity examEntity){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = simpleDateFormat.format(new java.util.Date());

        if(current.compareTo(examEntity.getBeginTime()) <= 0)  {
            examEntity.setStatus("未开始");
        } else if(current.compareTo(examEntity.getBeginTime()) > 0 && current.compareTo(examEntity.getEndTime()) <= 0){
            examEntity.setStatus("进行中");
        } else if(current.compareTo(examEntity.getEndTime()) > 0) {
            examEntity.setStatus("判卷中");
        }
        return examEntity;
    }

}
