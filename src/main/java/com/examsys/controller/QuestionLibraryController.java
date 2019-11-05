package com.examsys.controller;

import com.examsys.model.QuestionLibrary;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.QuestionLibraryServiceImpl;
import com.examsys.util.ExcelAnalysisUtil;
import com.examsys.util.ExcelTemplateUtil;
import com.examsys.util.OtherUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
public class QuestionLibraryController {

    @Autowired
    QuestionLibraryServiceImpl questionLibraryService;
    @Autowired
    ExcelAnalysisUtil excelAnalysisUtil;
    @Autowired
    ExcelTemplateUtil excelTemplateUtil;
    @Autowired
    OtherUtil otherUtil;


    /**
     * 批量添加题目
     * @param mapRes
     * @return
     */
    @PostMapping(value = "/multi")
    @Transactional
    public ResponseEntity addQusetions(@RequestBody List<Map<String,Object>> mapRes) {
        List<QuestionLibrary> questionList=questionLibraryService.handleNewQuestions(mapRes);
        ResponseEntity responseEntity=questionLibraryService.addNewQuestions(questionList);
        return responseEntity;
    }


    /**
     * 添加/编辑题目
     * @param mapRes
     * @return
     */
    @PostMapping(value = "/single")
    @Transactional
    public ResponseEntity adOrUpdateQusetion(@RequestBody Map<String,Object> mapRes) {
        ResponseEntity responseEntity = questionLibraryService.addOrUpdateQuestion(mapRes);
        return responseEntity;
    }


    /**
     * 获取所有题目
     * @return
     */
    @GetMapping(value = "/all")
    public ResponseEntity getQuestions() {
        ResponseEntity responseEntity=questionLibraryService.getAllQuestion();
        return responseEntity;
    }


    /**
     * 根据过滤条件获取题目
     * @return
     */
    @PostMapping(value = "/filter")
    public ResponseEntity getQuestionsByFlitEr(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity=questionLibraryService.getQuestionsByFilter(map);
        return responseEntity;
    }

    /**
     * 删除若干题目
     * @param map
     * @return
     */
    @DeleteMapping(value = "/del")
    @Transactional
    public ResponseEntity delQues(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = questionLibraryService.delQues(map);
        if(responseEntity.getStatus() != 200){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseEntity;
    }


    /**
     * excel批量上传题目
     * @param multipartFiles
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @PostMapping(value = "/excel")
    @Transactional
    public ResponseEntity addQuestionsByExcel(
            @RequestParam( value="files[]",required=false)MultipartFile[] multipartFiles)throws IllegalStateException, IOException {
        MultipartFile file=multipartFiles[0];
        //处理数据
        ResponseEntity tmpResponse = excelAnalysisUtil.addQuestions(file);
        if(tmpResponse.getStatus() != 200){
            return tmpResponse;
        }
        ResponseEntity responseEntity = questionLibraryService.addNewQuestions((List<QuestionLibrary>)tmpResponse.getData());
        if(responseEntity.getStatus()!=200){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseEntity;
    }



    /**
     * 获取题目excel模板
     * @param response
     * @throws Exception
     */
    @GetMapping("/template")
    public void userTemplate(HttpServletResponse response) throws Exception {
        String fileName = "添加题库模板.xls";
        HSSFWorkbook wb = excelTemplateUtil.addQuestionsTemplate(); //调用excelUtil生成excel
        try {
            otherUtil.setResponseHeader(response, fileName,wb);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
