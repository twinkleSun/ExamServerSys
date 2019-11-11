package com.examsys.controller;

import com.examsys.model.TestPaperDetail;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.TestPaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
    TestPaperServiceImpl testPaperService;

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
    public ResponseEntity addPaper(@RequestBody Map<String,Object> mapRes) {
        //添加试卷，并处理试卷-题目关联关系

        ResponseEntity tmpResponse  = testPaperService.handleNewPaper(mapRes);

        if(tmpResponse.getStatus() != 200){
            return tmpResponse;
        }

        List<TestPaperDetail> testPaperList = (List<TestPaperDetail>)tmpResponse.getData();
        if(mapRes.get("paper_code") == null || mapRes.get("paper_code") == "" ){
            //没有paper_code则为新建
        }else{
            testPaperService.deletePaperDetail(testPaperList);
        }

        //添加试卷-题目关联关系
        ResponseEntity responseEntity = testPaperService.addTestPaperDetail(testPaperList);
        return responseEntity;
    }


    /**
     * 获取所有试卷
     * @return
     */
    @GetMapping(value = "/all")
    public ResponseEntity getAllPapers() {
        ResponseEntity responseEntity = testPaperService.getPapersList();
        return responseEntity;
    }

    /**
     * 管理员查看自己创建的试卷
     * @return
     */
    @PostMapping(value = "/adminpaper")
    public ResponseEntity getPapersByAdmin(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = testPaperService.getPaperListByAdmin(map);
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
        ResponseEntity responseEntity = testPaperService.getTestPaperDetail(map.get("paper_code").toString());
        return responseEntity;
    }


    /**
     * 获取某场考试参与考试的考生
     * @param map
     * @return
     */
    @PostMapping(value = "/student")
    public ResponseEntity getStudentsByExam(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = testPaperService.getStudent((Integer)map.get("exam_id"));
        return responseEntity;
    }


    /**
     * 管理员删除试卷
     * @param map
     * @return
     */
    @DeleteMapping(value = "/del")
    @Transactional
    public ResponseEntity delTestPaper(@RequestBody Map<String,Object> map) {
        ResponseEntity responseEntity = testPaperService.delByPaperCode(map);
        if(responseEntity.getStatus()!=200){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseEntity;
    }

}
