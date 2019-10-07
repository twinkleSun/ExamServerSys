package com.examsys.controller;


import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.ExamGroupServiceImpl;
import com.examsys.service.Impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/epi")
public class ExamGroupController {

    @Autowired
    ExamGroupServiceImpl examGroupService;

    @PostMapping(value = "/user/examlist")
    public ResponseEntity getExamListByUser(@RequestBody Map<String,Integer> map) {
        ResponseEntity responseEntity = examGroupService.getExamListByStudent(map.get("user_id"));
        return responseEntity;
    }
}
