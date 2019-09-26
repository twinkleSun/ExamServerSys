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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@RestController
@RequestMapping("/paper")
public class PaperQuestionController {

    @Autowired
    ExcelAnalysisUtil excelAnalysisUtil;
    @Autowired
    PaperQuestionServiceImpl paperQuestionService;
    @Autowired
    QuestioninfoServiceImpl questioninfoService;

    @Autowired
    ExcelTemplateUtil excelUtil;
    @Autowired
    OtherUtil otherUtil;

    /**
     * 通过上传模板excel的方式批量添加用户
     * @param multipartFiles
     * @return
     */
    @PostMapping(value = "/excel")
    @Transactional
    public ResponseEntity addNewPaperByExcel(
            @RequestParam( value="files[]",required=false)MultipartFile[] multipartFiles)throws IllegalStateException, IOException {
        MultipartFile file=multipartFiles[0];

        Map<String,Object> map=excelAnalysisUtil.addNewPaper(file);
        List<Questioninfo> questioninfoList=(List<Questioninfo>)map.get("questionInfo");
        List<PaperQuestion> paperQuestionList=(List<PaperQuestion>)map.get("paperQuestion");
        List<PaperQuestion> paperQuestion=questioninfoService.addNewQuestions(questioninfoList,paperQuestionList);
        ResponseEntity responseEntity=paperQuestionService.addNewPaper(paperQuestion);
        return responseEntity;
    }

    @PostMapping(value = "/new")
    @Transactional
    public ResponseEntity addNewPaperByFront(@RequestBody List<Map<String,Object>> mapRes) {

        Map<String,Object> map=paperQuestionService.handleNewPaper(mapRes);
        List<Questioninfo> questioninfoList=(List<Questioninfo>)map.get("questionInfo");
        List<PaperQuestion> paperQuestionList=(List<PaperQuestion>)map.get("paperQuestion");
        List<PaperQuestion> paperQuestion=questioninfoService.addNewQuestions(questioninfoList,paperQuestionList);
        ResponseEntity responseEntity=paperQuestionService.addNewPaper(paperQuestion);
        return responseEntity;

    }



    /**
     * 下载批量导入的excel模板
     * @param response
     * @throws Exception
     */
    @GetMapping("/template")
    public void userTemplate(HttpServletResponse response) throws Exception {
        String fileName = "添加试卷模板.xls";
        HSSFWorkbook wb=excelUtil.addPaperTemplate(); //调用excelUtil生成excel
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
