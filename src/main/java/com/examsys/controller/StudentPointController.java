package com.examsys.controller;

import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.StudentPointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/spi")
public class StudentPointController {

    @Autowired
    StudentPointServiceImpl studentPointService;


    /**
     * 学生考试提交答卷，过程中可临时保存
     * @param map
     * @return
     */
    @PostMapping(value = "/do")
    @Transactional
    public ResponseEntity doPaper(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = studentPointService.addStuAns(map);
        return responseEntity;
    }


    /**
     * 老师获取主观题内容，方便进行判题
     * @param map
     * @return
     */
    @PostMapping(value = "/subanswers")
    public ResponseEntity getStuAnsOfExam(@RequestBody Map<String,Integer> map) {
        ResponseEntity responseEntity = studentPointService.getStuAnsOfExam(map.get("exam_id"));
        return responseEntity;
    }


    /**
     * 老师主观题判题
     * @param map
     * @return
     */
    @PostMapping(value = "/techsub")
    public ResponseEntity addTechSubPoint(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = studentPointService.addTechSubPoint(map);
        return responseEntity;
    }


    /**
     * 学生获取考试试题
     * @param map
     * @return
     */
    @PostMapping(value = "/stupaper")
    public ResponseEntity getExamPaper(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = studentPointService.getStuExamPaper(map);
        return responseEntity;

    }


    /**
     * 开始客观题判题
     * @param map
     * @return
     */
    @PostMapping(value = "/startobj")
    public ResponseEntity startObjJudge(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = studentPointService.stuObjQuesJudge(Integer.valueOf(map.get("exam_id").toString()));
        return responseEntity;
    }


    /**
     * 管理员获取考生成绩表
     * @param map
     * @return
     */
    @PostMapping(value = "/stupoint")
    public ResponseEntity selectAllByExamId(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = studentPointService.selectAllByExamId(Integer.valueOf(map.get("exam_id").toString()));
        return responseEntity;
    }



}
