package com.examsys.controller;

import com.examsys.model.QuestionLibrary;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.QuestionLibraryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


//    @PostMapping(value = "/excel")
//    @Transactional
//    public ResponseEntity addNewQuestionByExcel(
//            @RequestParam( value="files[]",required=false)MultipartFile[] multipartFiles)throws IllegalStateException, IOException {
//        MultipartFile file=multipartFiles[0];
//        List<QuestionLibrary> questioninfoList=excelAnalysisUtil.addNewQuestion(file);
//        ResponseEntity responseEntity=questioninfoService.addNewQuestions(questioninfoList);
//        return responseEntity;
//    }




//    @GetMapping("/template")
//    public void userTemplate(HttpServletResponse response) throws Exception {
//        String fileName = "添加题库模板.xls";
//        HSSFWorkbook wb=excelUtil.addQuestionsTemplate(); //调用excelUtil生成excel
//        try {
//            otherUtil.setResponseHeader(response, fileName);
//            OutputStream os = response.getOutputStream();
//            wb.write(os);
//            os.flush();
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
