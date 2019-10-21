package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamServiceImpl examService;

    /**
     * 生成考试
     * @return
     */
    @PostMapping(value = "/new")
    public ResponseEntity addNewExam(@RequestBody Map<String,Object> mapRes) {
        ResponseEntity responseEntity=examService.addNewExam(mapRes);
        return responseEntity;
    }

    /**
     * 给考试单独添加组
     * @return
     */
    @PostMapping(value = "/group")
    public ResponseEntity addNewExamGroup(@RequestBody Map<String,Object> map) {
        ArrayList<Integer> groupIds=(ArrayList<Integer>)map.get("group_ids");
        int examId=Integer.valueOf(map.get("exam_id").toString());
        ResponseEntity responseEntity=examService.addExamGroup(examId,groupIds);
        return responseEntity;
    }

    @PostMapping(value = "/techend")
    @ResponseBody
    public ResponseEntity endExam(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = examService.endExam(Integer.valueOf(map.get("exam_id").toString()));
        return responseEntity;
    }

}

