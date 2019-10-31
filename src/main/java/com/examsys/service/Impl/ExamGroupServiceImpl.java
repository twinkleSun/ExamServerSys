package com.examsys.service.Impl;

import com.examsys.dao.ExamMapper;
import com.examsys.model.Exam;
import com.examsys.model.entity.ResponseEntity;
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
     * 考生查询还未开始或进行中的考试
     * @param userId
     * @return
     */
    public ResponseEntity getExamListByStudent(int userId) {
        List<Exam> examList = examMapper.selectExamByUserId(userId);
        if(examList==null || examList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.STUDENT_HAS_NO_EXAM);
        }

        List<Exam> examNotEnd = new ArrayList<>();
        for(int i = 0; i < examList.size(); i++) {
            Exam exam = examList.get(i);
            if(exam.getStatus().equals("未开始")) {
                exam = handleExamStatus(exam);
                if(!exam.getStatus().equals("已结束")){
                    examNotEnd.add(exam);
                }
            }
            examMapper.updateExamStatus(exam);
        }
        return new ResponseEntity(200,"查询成功",examNotEnd);
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
            exam.setStatus("已结束");
        }
        return exam;
    }


    /**
     * 管理员获取考试列表
     * @return
     */
    public ResponseEntity getExamListByAdmin() {
        List<Exam> examList = examMapper.selectAll();
        if(examList == null || examList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.NO_EXAM_LIST);
        }

        for(int i = 0; i < examList.size(); i++) {
            Exam exam = examList.get(i);
            if(exam.getStatus().equals("未开始") || exam.getStatus().equals("0")) {
                exam = handleExamStatus(exam);
            }
            examMapper.updateExamStatus(exam);
        }
        return new ResponseEntity(200,"查询成功",examList);
    }
}
