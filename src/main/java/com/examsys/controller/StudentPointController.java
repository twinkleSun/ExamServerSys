package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.StudentPointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/spi")
public class StudentPointController {

    @Autowired
    StudentPointServiceImpl studentPointService;

    /**
     * 学生考试提交答卷
     * @param map
     * @return
     */
    @PostMapping(value = "/do")
    @ResponseBody
    public ResponseEntity doPaper(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = studentPointService.addStuAns(map);
        return responseEntity;
    }


}
