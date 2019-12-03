package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.ExamServiceImpl;
import com.examsys.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;


@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamServiceImpl examService;

    /**
     * 新建/修改考试
     * @return
     */
    @PostMapping(value = "/new")
    public ResponseEntity addOrUpdateExam(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = examService.addOrUpdateExam(map);
        return responseEntity;
    }


    /**
     * 给考试额外添加组
     * @return
     */
    @PostMapping(value = "/group")
    public ResponseEntity addNewExamGroup(@RequestBody Map<String,Object> map) {
        ArrayList<Integer> groupIds=(ArrayList<Integer>)map.get("group_ids");
        int examId=Integer.valueOf(map.get("exam_id").toString());
        ResponseEntity responseEntity = examService.addExamGroup(examId,groupIds);
        return responseEntity;
    }


    /**
     * 管理员手动结束考试
     * @param map
     * @return
     */
    @PostMapping(value = "/techend")
    public ResponseEntity endExam(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = examService.endExam(Integer.valueOf( map.get("exam_id").toString()));
        return responseEntity;
    }


    /**
     * 批量删除考试
     * @param map
     * @return
     */
    @DeleteMapping(value = "/del")
    @Transactional
    public ResponseEntity delExams(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = examService.delExams(map);
        if(responseEntity.getStatus()!=200){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseEntity;
    }

}

