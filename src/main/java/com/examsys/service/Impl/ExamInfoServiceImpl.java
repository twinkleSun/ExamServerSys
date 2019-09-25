package com.examsys.service.Impl;

import com.examsys.dao.ExaminfoMapper;
import com.examsys.model.Examinfo;
import com.examsys.model.entity.GroupUserInfo;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.IExamInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class ExamInfoServiceImpl implements IExamInfoService {
    @Autowired
    ExaminfoMapper examinfoMapper;

    @Override
    public ResponseEntity checkExamByStudent(int userId) {
        List<Examinfo> ExamInfoList=examinfoMapper.selectExamById(userId);
        ResponseEntity responseEntity=new ResponseEntity();
        if(ExamInfoList==null || ExamInfoList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("没有考试列表信息");
        } else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(ExamInfoList);
        }

        return responseEntity;
    }
}
