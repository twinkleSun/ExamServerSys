package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.IExamInfoService;
import com.examsys.service.IGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/examinfo")
public class ExamInfoController {
    @Autowired
    IExamInfoService iExamInfoService;

    @PostMapping(value = "/check-exam")
    public ResponseEntity getGroupUser(@RequestBody Map<String,Integer> map) {
        ResponseEntity responseEntity = iExamInfoService.checkExamByStudent(map.get("id"));
        return responseEntity;
    }
}

