package com.examsys.service.Impl;

import com.examsys.dao.ExamMapper;
import com.examsys.model.Exam;
import com.examsys.model.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
        } else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(examList);
        }
        return responseEntity;
    }
}
