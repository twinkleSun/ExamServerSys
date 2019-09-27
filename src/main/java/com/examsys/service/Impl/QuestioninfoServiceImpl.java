package com.examsys.service.Impl;

import com.examsys.dao.QuestioninfoMapper;
import com.examsys.model.PaperQuestion;
import com.examsys.model.Questioninfo;
import com.examsys.model.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@Service
public class QuestioninfoServiceImpl {

    @Autowired
    QuestioninfoMapper questioninfoMapper;

    public List<PaperQuestion> addNewQuestions(List<Questioninfo> questioninfoList, List<PaperQuestion> paperQuestionList){
        int length=questioninfoList.size();
        for(int i=0;i<length;i++){
            Questioninfo questioninfo=questioninfoList.get(i);
            //todo:先查询该题在不在题库内,题目内容和题目类型一致即可。
            int tmp= questioninfoMapper.insert(questioninfo);
            if(tmp<0){
            }else {
                paperQuestionList.get(i).setQuestionId(questioninfo.getId());
            }
        }
        return paperQuestionList;
    }

    public ResponseEntity addNewQuestions(List<Questioninfo> questioninfoList){
        int length=questioninfoList.size();
        ResponseEntity responseEntity=new ResponseEntity();
        for(int i=0;i<length;i++){
            Questioninfo questioninfo=questioninfoList.get(i);
            //todo:先查询该题在不在题库内,题目内容和题目类型一致即可。
            int tmp= questioninfoMapper.insert(questioninfo);
            if(tmp<0){
                responseEntity.setStatus(-1);
            }else {

            }
        }
        responseEntity.setStatus(200);
        return responseEntity;
    }

    public  List<Questioninfo> handleNewQuestions(List<Map<String,Object>> map){
        List<Questioninfo> questioninfoList=new ArrayList<>();
        for(int i=0;i<map.size();i++){
            Questioninfo questioninfo=new Questioninfo();
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
            questioninfoList.add(questioninfo);
        }

        return questioninfoList;
    }

    public ResponseEntity getAllQuestionInfo(){
        ResponseEntity responseEntity=new ResponseEntity();
        List<Questioninfo> questioninfoList = questioninfoMapper.selectAll();

        if(questioninfoList == null){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("没有题目信息");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("获取题目成功");
            responseEntity.setData(questioninfoList);
        }

        return responseEntity;
    }
}
