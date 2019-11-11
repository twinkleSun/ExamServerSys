package com.examsys.util;

import com.alibaba.fastjson.JSONObject;
import com.examsys.model.QuestionLibrary;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.util.error.ErrorMsgEnum;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@Component
@Repository
public class ExcelAnalysisUtil {



//    public Map<String,Object> addNewPaper(MultipartFile multipartFile)throws IllegalStateException, IOException {
//
//        Map<String,Object> map=new HashMap<String, Object>();
//        InputStream inputStream = multipartFile.getInputStream();
//
//        try{
//            Workbook wb = WorkbookFactory.create(inputStream);
//            Sheet sheet = wb.getSheetAt(0);
//            //获取最大行
//            int maxRowNum = sheet.getPhysicalNumberOfRows();
//
//            List<Questioninfo> questioninfoList=new ArrayList<>();
//            List<PaperQuestion> paperQuestionList=new ArrayList<>();
//            //从第四行开始解析，前三行为提示信息
//
//            Calendar cal=Calendar.getInstance();
//            String paper_code=""+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+cal.get(Calendar.DATE)+cal.get(Calendar.HOUR_OF_DAY)
//                    +cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
//
//            for(int i=3;i<maxRowNum;i++){
//
//                Questioninfo questioninfo=new Questioninfo();
//                PaperQuestion paperQuestion=new PaperQuestion();
//
//                Row row=sheet.getRow(i);
//                Cell contentCell=row.getCell(0);//题干
//                Cell typeCell= row.getCell(1);//类型
//                Cell pointCell= row.getCell(2);//分值
//                Cell answerCell=row.getCell(3);//参考答案
//                Cell optionCell=row.getCell(4);//选项
//
//                questioninfo.setContent(contentCell.getStringCellValue());//题干
//
//                if(typeCell.getStringCellValue().equals("单选题")){
//                    questioninfo.setType("single");
//                }else if(typeCell.getStringCellValue().equals("多选题")){
//                    questioninfo.setType("multi");
//                }else if(typeCell.getStringCellValue().equals("判断题")){
//                    questioninfo.setType("judge");
//                }else {
//                    questioninfo.setType("zhuguan");
//                }
//                questioninfo.setAnswer(answerCell.getStringCellValue());
//                questioninfo.setOptions(optionCell.getStringCellValue());
//                questioninfoList.add(questioninfo);
//
//                paperQuestion.setScore(pointCell.getNumericCellValue());
//                paperQuestion.setAnswer(answerCell.getStringCellValue());
//                paperQuestion.setPaperCode(paper_code);
//                paperQuestionList.add(paperQuestion);
//            }
//            map.put("questionInfo",questioninfoList);
//            map.put("paperQuestion",paperQuestionList);
//
//        }catch (Exception e) {
//
//        }
//
//        return map;
//    }
//
//
//

    /**
     * 处理试题数据
     * @param multipartFile
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public ResponseEntity addQuestions(MultipartFile multipartFile)throws IllegalStateException, IOException {
        InputStream inputStream = multipartFile.getInputStream();
        List<QuestionLibrary> questionsList=new ArrayList<>();
        try{
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行
            int maxRowNum = sheet.getPhysicalNumberOfRows();

            //从第四行开始解析，前三行为提示信息
            for(int i = 4; i<maxRowNum; i++){
                QuestionLibrary question=new QuestionLibrary();
                Row row=sheet.getRow(i);
                Cell contentCell=row.getCell(0);//题干
                Cell typeCell= row.getCell(1);//类型
                Cell answerCell=row.getCell(2);//参考答案
                Cell optionCell=row.getCell(3);//选项
                Cell descriptionCell=row.getCell(4);//描述

                if(answerCell.getCellType() == 0){
                    return new ResponseEntity(ErrorMsgEnum.ANSWER_FORMAT_INCORRECT);
                }

                if(contentCell.getStringCellValue() == null || contentCell.getStringCellValue() == "") {
                    return new ResponseEntity(ErrorMsgEnum.CONTENT_CAN_NOT_BE_NULL);
                }else {
                    question.setContent(contentCell.getStringCellValue());//题干
                }

                question.setDescription(descriptionCell.getStringCellValue());

                if(typeCell.getStringCellValue().equals("主观题")){
                    question.setType("subjective");
                    List<Map<String,Object>> subTmp= new ArrayList<>();
                    question.setOptions(JSONObject.toJSONString(subTmp));

                    Map<String,Object> subMap =new HashMap<>();
                    subMap.put("id",0);
                    subMap.put("content",answerCell.getStringCellValue());
                    subTmp.add(subMap);

                    question.setAnswer(JSONObject.toJSONString(subTmp));
                }else{


                    if(optionCell.getStringCellValue() == null || optionCell.getStringCellValue() ==""){
                        return new ResponseEntity(ErrorMsgEnum.OPTION_CAN_NOT_BE_NULL);
                    }

                    //options形如:1:测试选项1##2：测试选项2###3:测试选项3
                    String optionsStr = optionCell.getStringCellValue();

                    //String[] baseIndex ={"0","1","2","3","4","5","6","7","8","9"};
                    String baseIndex = "0123456789";
                    String[] tmpOption = new String[10];

                    List<Map<String,Object>> optionMapList =new ArrayList<>();
                    String[] optionsArr = optionsStr.split("##");

                    for(int j=0;j<optionsArr.length;j++){
                        if(optionsArr[j].length()<3){
                            return new ResponseEntity(ErrorMsgEnum.OPTION_IS_TOO_SHORT);
                        }
                        String indexStr = optionsArr[j].substring(0,1);
                        if(baseIndex.indexOf(indexStr)<0){
                            return new ResponseEntity(ErrorMsgEnum.INDEX_FORMAT_NOT_CORRECT);
                        }

                        int index = Integer.valueOf(indexStr);
                        if(index>9 || index > tmpOption.length-1){
                            return new ResponseEntity(ErrorMsgEnum.INDEX_FORMAT_NOT_CORRECT);
                        }
                        String option = optionsArr[j].substring(2,optionsArr[j].length());
                        tmpOption[index] = option;
                        Map<String,Object> optionMap =new HashMap<>();
                        optionMap.put("id",index);
                        optionMap.put("content",option);
                        optionMapList.add(optionMap);
                    }

                    question.setOptions(JSONObject.toJSONString(optionMapList));

                    if(typeCell.getStringCellValue().equals("单选题")){
                        question.setType("single");
                    }else if(typeCell.getStringCellValue().equals("多选题")){
                        question.setType("multi");
                    }else if(typeCell.getStringCellValue().equals("判断题")){
                        question.setType("judge");
                    }


                    //answer形如：0#1#2
                    String answerStr = answerCell.getStringCellValue();
                    if(answerStr == null || answerStr ==""){
                        return new ResponseEntity(ErrorMsgEnum.ANSWER_CAN_NOT_BE_NULL);
                    }
                    if(typeCell.getStringCellValue().equals("单选题") || typeCell.getStringCellValue().equals("判断题")){
                        answerStr = answerStr.substring(0,1);

                        if(baseIndex.indexOf(answerStr)<0 || answerStr.length()>2){
                            return new ResponseEntity(ErrorMsgEnum.ANSWER_INDEX_FORMAT_INCORRECT);
                        }

                        int ansIndex = Integer.valueOf(answerStr);
                        if(ansIndex>9 || ansIndex > tmpOption.length-1){
                            return new ResponseEntity(ErrorMsgEnum.ANSWER_INDEX_FORMAT_INCORRECT);
                        }

                        List<Map<String,Object>> ansMapList =new ArrayList<>();
                        Map<String,Object> ansMap =new HashMap<>();
                        ansMap.put("id",ansIndex);
                        ansMap.put("content",tmpOption[ansIndex]);
                        ansMapList.add(ansMap);
                        question.setAnswer(JSONObject.toJSONString(ansMapList));

                    }else{

                        //多选题
                        if(answerStr.indexOf("#") < 0){
                            return new ResponseEntity(ErrorMsgEnum.ANSWER_FORMAT_INCORRECT);
                        }
                        String[] ansArr = answerStr.split("#");

                        List<Map<String,Object>> ansMapList = new ArrayList<>();
                        for(int k=0;k<ansArr.length;k++){
                            if(baseIndex.indexOf(ansArr[k])<0){
                                return new ResponseEntity(ErrorMsgEnum.ANSWER_INDEX_FORMAT_INCORRECT);
                            }

                            int answerIndex = Integer.valueOf(ansArr[k]);
                            if(answerIndex > 9 || answerIndex>tmpOption.length-1){
                                return new ResponseEntity(ErrorMsgEnum.ANSWER_INDEX_FORMAT_INCORRECT);
                            }

                            Map<String,Object> answerMap =new HashMap<>();
                            answerMap.put("content",tmpOption[answerIndex]);
                            answerMap.put("id",answerIndex);
                            ansMapList.add(answerMap);
                        }
                        question.setAnswer(JSONObject.toJSONString(ansMapList));
                    }
                }
                questionsList.add(question);
            }

        }catch (Exception e) {

            return new ResponseEntity(ErrorMsgEnum.EXCEPTION_ERROR);
        }
        return new ResponseEntity(200,"数据处理成功",questionsList);
    }
}
