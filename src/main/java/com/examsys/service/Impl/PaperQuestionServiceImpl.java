package com.examsys.service.Impl;

import com.examsys.dao.PaperQuestionMapper;
import com.examsys.dao.QuestioninfoMapper;
import com.examsys.model.PaperQuestion;
import com.examsys.model.Questioninfo;
import com.examsys.model.entity.ResponseEntity;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@Service
public class PaperQuestionServiceImpl {


    @Autowired
    PaperQuestionMapper paperQuestionMapper;


    public ResponseEntity addNewPaper(List<PaperQuestion> paperQuestionList){
        ResponseEntity responseEntity=new ResponseEntity();
        int length=paperQuestionList.size();
        for(int i=0;i<length;i++){
            PaperQuestion paperQuestion=paperQuestionList.get(i);
            int res=paperQuestionMapper.insert(paperQuestion);
            if(res<0){
                responseEntity.setStatus(-1);
                return responseEntity;
            }else{

            }
        }
        responseEntity.setStatus(200);
        return responseEntity;
    }

    public Map<String,Object> handleNewPaper(List<Map<String,Object>> map){
        Map<String,Object> mapRes=new HashMap<String, Object>();
        List<Questioninfo> questioninfoList=new ArrayList<>();
        List<PaperQuestion> paperQuestionList=new ArrayList<>();
        Calendar cal=Calendar.getInstance();
        String paper_code=""+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+cal.get(Calendar.DATE)+cal.get(Calendar.HOUR_OF_DAY)
                +cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);

        for(int i=0;i<map.size();i++){
            Questioninfo questioninfo=new Questioninfo();
            PaperQuestion paperQuestion=new PaperQuestion();
            Map<String,Object> map1=map.get(i);

            questioninfo.setType(String.valueOf(map1.get("type")));
            questioninfo.setContent(String.valueOf(map1.get("content")));
            ArrayList<String> options=(ArrayList<String>)map1.get("option_list");
            ArrayList<String> answers=(ArrayList<String>)map1.get("answer_list");
            String option="";
            String answer="";
            if(questioninfo.getType().equals("multi")){
                for (int j=0;j<options.size();j++){
                    option=option+options.get(j)+"#";
                }
                for (int j=0;j<answers.size();j++){
                    answer=answer+answers.get(j)+"#";
                }
            }else if(questioninfo.getType().equals("single")){

                for (int j=0;j<options.size();j++){
                    option=option+options.get(j)+"#";
                }
                answer=answer+answers.get(0);
            }else if(questioninfo.getType().equals("judge")){
                for (int j=0;j<options.size();j++){
                    option=option+options.get(j)+"#";
                }
                answer=answer+answers.get(0);
            }else{

            }

            questioninfo.setAnswer(answer);
            questioninfo.setOptions(option);

            double score=(double) Integer.parseInt(String.valueOf(map1.get("score")));
            paperQuestion.setScore(score);
            paperQuestion.setAnswer(answer);
            paperQuestion.setPaperCode(paper_code);

            questioninfoList.add(questioninfo);
            paperQuestionList.add(paperQuestion);
        }

        mapRes.put("questionInfo",questioninfoList);
        mapRes.put("paperQuestion",paperQuestionList);

        return mapRes;
    }

}
