package com.examsys.controller;

import com.examsys.model.QuestionLibrary;
import com.examsys.model.TestPaperDetail;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.TestPaperServiceImpl;
import com.examsys.service.Impl.QuestionLibraryServiceImpl;
import com.examsys.util.ExcelAnalysisUtil;
import com.examsys.util.ExcelTemplateUtil;
import com.examsys.util.OtherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@RestController
@RequestMapping("/paper")
public class TestPaperController {

    @Autowired
    ExcelAnalysisUtil excelAnalysisUtil;
    @Autowired
    TestPaperServiceImpl testPaperService;
    @Autowired
    QuestionLibraryServiceImpl questionLibraryService;

    @Autowired
    ExcelTemplateUtil excelUtil;
    @Autowired
    OtherUtil otherUtil;

//    /**
//     * 通过上传模板excel的方式批量添加试题
//     * todo:暂不可用
//     * @param multipartFiles
//     * @return
//     */
//    @PostMapping(value = "/excel")
//    @Transactional
//    public ResponseEntity addNewPaperByExcel(
//            @RequestParam( value="files[]",required=false)MultipartFile[] multipartFiles)throws IllegalStateException, IOException {
//        MultipartFile file=multipartFiles[0];
//        Map<String,Object> map=excelAnalysisUtil.addNewPaper(file);
//        List<QuestionLibrary> questioninfoList=(List<QuestionLibrary>)map.get("questionInfo");
//        List<TestPaper> paperQuestionList=(List<TestPaper>)map.get("paperQuestion");
//        List<TestPaper> paperQuestion=questionLibraryService.addNewQuestions(questioninfoList,paperQuestionList);
//        ResponseEntity responseEntity=testPaperService.addNewPaper(paperQuestion);
//        return responseEntity;
//    }





//    /**
//     * 下载批量导入的excel模板
//     * @param response
//     * @throws Exception
//     */
//    @GetMapping("/template")
//    public void userTemplate(HttpServletResponse response) throws Exception {
//        String fileName = "添加试卷模板.xls";
//        HSSFWorkbook wb=excelUtil.addPaperTemplate(); //调用excelUtil生成excel
//        try {
//            otherUtil.setResponseHeader(response, fileName,wb);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 添加试卷
     * @param mapRes
     * @return
     */
    @PostMapping(value = "/new")
    @Transactional
    public ResponseEntity addNewPaperByFront(@RequestBody Map<String,Object> mapRes) {
        List<TestPaperDetail> testPaperList = testPaperService.handleNewPaper(mapRes);

        if(mapRes.get("paper_code") == null || mapRes.get("paper_code") == "" ){

        }else{
            testPaperService.deletePaper(testPaperList);
        }
        ResponseEntity responseEntity=testPaperService.addNewPaper(testPaperList);
        return responseEntity;


    }


    /**
     * 获取所有试卷
     * @return
     */
    @GetMapping(value = "/all")
    public ResponseEntity getAllPapers() {
        ResponseEntity responseEntity=testPaperService.getAllPaperList();
        return responseEntity;
    }

    /**
     * 获取所有试卷
     * @return
     */
    @PostMapping(value = "/adminpaper")
    public ResponseEntity getAllPapersByAdmin(@RequestBody Map<String,Object> mapRes) {
        ResponseEntity responseEntity=testPaperService.getPaperListByAdmin(mapRes);
        return responseEntity;
    }


    /**
     * 获取单个试卷详情
     * @param map
     * @return
     */
    @PostMapping(value = "/single")
    @Transactional
    public ResponseEntity getSinglePaper(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity=testPaperService.getTestPaperDetail(map.get("paper_code").toString());
        return responseEntity;

    }

    /**
     * 获取参与考试的考生
     * @param map
     * @return
     */
    @PostMapping(value = "/student")
    @Transactional
    public ResponseEntity getStudents(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity=testPaperService.getStudent((Integer) map.get("exam_id"));
        return responseEntity;

    }


    @DeleteMapping(value = "/del")
    public ResponseEntity delTestPaper(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity=testPaperService.delByPaperCode(map);
        return responseEntity;

    }

}
