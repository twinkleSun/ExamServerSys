package com.examsys.controller;


import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.StudentPointDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/spi")
public class StudentPointDetailController {

    @Autowired
    StudentPointDetailServiceImpl studentPointDetailService;


    /**
     * 老师获取主观题内容，方便进行判题
     * @param map
     * @return
     */
    @PostMapping(value = "/subanswers")
    @ResponseBody
    public ResponseEntity getAnswers(@RequestBody Map<String,Integer> map) {
        ResponseEntity responseEntity = studentPointDetailService.getAllStudentAnswers(map.get("exam_id"));
        return responseEntity;
    }

    /**
     * 老师判题接口
     * @param map
     * @return
     */
    @PostMapping(value = "/techsub")
    @ResponseBody
    public ResponseEntity TechSub(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = studentPointDetailService.addTeacherPoint(map);
        return responseEntity;
    }

}
