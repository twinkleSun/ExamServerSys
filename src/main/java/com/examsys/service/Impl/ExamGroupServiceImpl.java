package com.examsys.service.Impl;

import com.examsys.dao.ExamMapper;
import com.examsys.model.Exam;
import com.examsys.model.entity.ResponseEntity;
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
     * 根据用户ID查询考试列表
     * @param userId
     * @return
     */
    public ResponseEntity getExamListByStudent(int userId) {
        List<Exam> examList=examMapper.selectExamByUserId(userId);
        ResponseEntity responseEntity=new ResponseEntity();


        if(examList==null || examList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("没有考试列表信息");
            return responseEntity;
        }

        List<Exam> examNotEnd = new ArrayList<>();
        int len = examList.size();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = simpleDateFormat.format(new java.util.Date());
        for(int i = 0; i < len; i++) {
            Exam exam = examList.get(i);
            if(exam.getStatus().equals("未开始")) {
                if(current.compareTo(exam.getBeginTime()) <= 0)  {
                    exam.setStatus("未开始");
                } else if(current.compareTo(exam.getBeginTime()) > 0 && current.compareTo(exam.getEndTime()) <= 0){
                    exam.setStatus("进行中");
                } else if(current.compareTo(exam.getEndTime()) > 0) {
                    exam.setStatus("已结束");
                }


                if(exam.getStatus()!="已结束"){
                    examNotEnd.add(exam);
                }
            }

            int res = examMapper.updateExamStatus(exam);
        }
        responseEntity.setStatus(200);
        responseEntity.setMsg("查询成功");
        responseEntity.setData(examNotEnd);

        return responseEntity;
    }



    public ResponseEntity getExamListByAdmin() {
        List<Exam> exams=examMapper.selectAll();
        ResponseEntity responseEntity=new ResponseEntity();

        if(exams==null || exams.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("没有考试列表");
            return responseEntity;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = simpleDateFormat.format(new java.util.Date());
        for(int i = 0; i < exams.size(); i++) {
            Exam exam = exams.get(i);
            if(exam.getStatus().equals("未开始") || exam.getStatus().equals("0")) {
                if(curTime.compareTo(exam.getBeginTime()) <= 0)  {
                    exam.setStatus("未开始");
                } else if(curTime.compareTo(exam.getBeginTime()) > 0 && curTime.compareTo(exam.getEndTime()) <= 0){
                    exam.setStatus("进行中");
                } else if(curTime.compareTo(exam.getEndTime()) > 0) {
                    exam.setStatus("已结束");
                }
            }

            int res = examMapper.updateExamStatus(exam);
        }


        responseEntity.setStatus(200);
        responseEntity.setMsg("查询成功");
        responseEntity.setData(exams);

        return responseEntity;
    }
}
