package com.examsys.util;

import com.examsys.model.PaperQuestion;
import com.examsys.model.Questioninfo;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.service.Impl.PaperQuestionServiceImpl;
import com.examsys.service.Impl.QuestioninfoServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@Component
public class ExcelAnalysisUtil {



    public Map<String,Object> addNewPaper(MultipartFile multipartFile)throws IllegalStateException, IOException {

        Map<String,Object> map=new HashMap<String, Object>();
        InputStream inputStream = multipartFile.getInputStream();

        try{
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行
            int maxRowNum = sheet.getPhysicalNumberOfRows();

            List<Questioninfo> questioninfoList=new ArrayList<>();
            List<PaperQuestion> paperQuestionList=new ArrayList<>();
            //从第四行开始解析，前三行为提示信息

            Calendar cal=Calendar.getInstance();
            String paper_code=""+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+cal.get(Calendar.DATE)+cal.get(Calendar.HOUR_OF_DAY)
                    +cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);

            for(int i=3;i<maxRowNum;i++){

                Questioninfo questioninfo=new Questioninfo();
                PaperQuestion paperQuestion=new PaperQuestion();

                Row row=sheet.getRow(i);
                Cell contentCell=row.getCell(0);//题干
                Cell typeCell= row.getCell(1);//类型
                Cell pointCell= row.getCell(2);//分值
                Cell answerCell=row.getCell(3);//参考答案
                Cell optionCell=row.getCell(4);//选项

                questioninfo.setContent(contentCell.getStringCellValue());//题干

                if(typeCell.getStringCellValue().equals("单选题")){
                    questioninfo.setType("single");
                }else if(typeCell.getStringCellValue().equals("多选题")){
                    questioninfo.setType("multi");
                }else if(typeCell.getStringCellValue().equals("判断题")){
                    questioninfo.setType("judge");
                }else {
                    questioninfo.setType("zhuguan");
                }
                questioninfo.setAnswer(answerCell.getStringCellValue());
                questioninfo.setOptions(optionCell.getStringCellValue());
                questioninfoList.add(questioninfo);

                paperQuestion.setScore(pointCell.getNumericCellValue());
                paperQuestion.setAnswer(answerCell.getStringCellValue());
                paperQuestion.setPaperCode(paper_code);
                paperQuestionList.add(paperQuestion);
            }
            map.put("questionInfo",questioninfoList);
            map.put("paperQuestion",paperQuestionList);

        }catch (Exception e) {

        }

        return map;
    }



    public List<Questioninfo> addNewQuestion(MultipartFile multipartFile)throws IllegalStateException, IOException {

        Map<String,Object> map=new HashMap<String, Object>();
        InputStream inputStream = multipartFile.getInputStream();
        List<Questioninfo> questioninfoList=new ArrayList<>();
        try{
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行
            int maxRowNum = sheet.getPhysicalNumberOfRows();

            //从第四行开始解析，前三行为提示信息

            for(int i=3;i<maxRowNum;i++){

                Questioninfo questioninfo=new Questioninfo();
                PaperQuestion paperQuestion=new PaperQuestion();

                Row row=sheet.getRow(i);
                Cell contentCell=row.getCell(0);//题干
                Cell typeCell= row.getCell(1);//类型
                Cell answerCell=row.getCell(2);//参考答案
                Cell optionCell=row.getCell(3);//选项

                questioninfo.setContent(contentCell.getStringCellValue());//题干

                if(typeCell.getStringCellValue().equals("单选题")){
                    questioninfo.setType("single");
                }else if(typeCell.getStringCellValue().equals("多选题")){
                    questioninfo.setType("multi");
                }else if(typeCell.getStringCellValue().equals("判断题")){
                    questioninfo.setType("judge");
                }else {
                    questioninfo.setType("zhuguan");
                }
                questioninfo.setAnswer(answerCell.getStringCellValue());
                questioninfo.setOptions(optionCell.getStringCellValue());
                questioninfoList.add(questioninfo);

            }


        }catch (Exception e) {

        }

        return questioninfoList;
    }
}
