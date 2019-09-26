package com.examsys.controller;

import com.examsys.model.PaperQuestion;
import com.examsys.model.Questioninfo;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.PaperQuestionServiceImpl;
import com.examsys.service.Impl.QuestioninfoServiceImpl;
import com.examsys.util.ExcelAnalysisUtil;
import com.examsys.util.ExcelTemplateUtil;
import com.examsys.util.OtherUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@RestController
@RequestMapping("/question")
public class QuestioninfoController {


    @Autowired
    ExcelAnalysisUtil excelAnalysisUtil;

    @Autowired
    QuestioninfoServiceImpl questioninfoService;

    @Autowired
    ExcelTemplateUtil excelUtil;
    @Autowired
    OtherUtil otherUtil;


    @PostMapping(value = "/excel")
    @Transactional
    public ResponseEntity addNewQuestionByExcel(
            @RequestParam( value="files[]",required=false)MultipartFile[] multipartFiles)throws IllegalStateException, IOException {
        MultipartFile file=multipartFiles[0];
        List<Questioninfo> questioninfoList=excelAnalysisUtil.addNewQuestion(file);
        ResponseEntity responseEntity=questioninfoService.addNewQuestions(questioninfoList);
        return responseEntity;
    }

    @PostMapping(value = "/new")
    @Transactional
    public ResponseEntity addNewPaperByFront(@RequestBody List<Map<String,Object>> mapRes) {
        List<Questioninfo> questioninfoList=questioninfoService.handleNewQuestions(mapRes);
        ResponseEntity responseEntity=questioninfoService.addNewQuestions(questioninfoList);
        return responseEntity;
    }


    @GetMapping("/template")
    public void userTemplate(HttpServletResponse response) throws Exception {
        String fileName = "添加题库模板.xls";
        HSSFWorkbook wb=excelUtil.addQuestionsTemplate(); //调用excelUtil生成excel
        try {
            otherUtil.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
