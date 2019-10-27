package com.examsys.service.Impl;

import com.alibaba.fastjson.JSON;
import com.examsys.dao.KnowledgeMapper;
import com.examsys.dao.QuesKnowledgeMapper;
import com.examsys.dao.QuestionLibraryMapper;
import com.examsys.model.Knowledge;
import com.examsys.model.QuesKnowledge;
import com.examsys.model.QuestionLibrary;
import com.examsys.model.TestPaperDetail;
import com.examsys.model.entity.QuesKnowNameEntity;
import com.examsys.model.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@Service
@Repository
public class QuestionLibraryServiceImpl {

    @Autowired
    QuestionLibraryMapper questionLibraryMapper;

    @Autowired
    KnowledgeMapper knowledgeMapper;

    @Autowired
    QuesKnowledgeMapper quesKnowledgeMapper;

    /**
     * 处理题库数据
     * @param map
     * @return
     */
    public  List<QuestionLibrary> handleNewQuestions(List<Map<String,Object>> map){
        List<QuestionLibrary> questioninfoList=new ArrayList<>();
        for(int i=0;i<map.size();i++){
            QuestionLibrary questionList=new QuestionLibrary();
            Map<String,Object> map1=map.get(i);
            questionList.setType(String.valueOf(map1.get("type")));
            questionList.setContent(String.valueOf(map1.get("content")));
            questionList.setAnswer(JSON.toJSONString(map1.get("answer_list")));
            if(map1.get("option_list") ==null || map1.get("option_list")==""){
                questionList.setOptions("");
            }else {
                questionList.setOptions(JSON.toJSONString(map1.get("option_list")));
            }

            if( map1.get("description") == null || map1.get("description")== ""){
                questionList.setDescription("");
            }else {
                questionList.setDescription(String.valueOf(map1.get("description")));
            }

            questioninfoList.add(questionList);
        }

        return questioninfoList;
    }



    public ResponseEntity addSingleQuestion(Map<String,Object> map){

        ResponseEntity responseEntity = new ResponseEntity();

        int quesId;
        QuestionLibrary question=new QuestionLibrary();
        question.setType(String.valueOf(map.get("type")));
        question.setContent(String.valueOf(map.get("content")));
        question.setAnswer(JSON.toJSONString(map.get("answer_list")));
        if(map.get("option_list") ==null || map.get("option_list")==""){
            question.setOptions("");
        }else {
            question.setOptions(JSON.toJSONString(map.get("option_list")));
        }

        if( map.get("description") == null || map.get("description")== ""){
            question.setDescription("");
        }else {
            question.setDescription(String.valueOf(map.get("description")));
        }

        if(map.get("id")== null || map.get("id")==""){
            QuestionLibrary questionAlready =questionLibraryMapper.selectByQuestion(question);
            if(questionAlready != null){
                responseEntity.setStatus(-1);
                responseEntity.setMsg("该题已存在");
                return responseEntity;
            }else{
                int tmp= questionLibraryMapper.insert(question);
                quesId = question.getId();
                if(tmp<0){
                    throw new RuntimeException("数据库错误");
                }
            }
        }else{
            question.setId(Integer.valueOf(map.get("id").toString()));
            quesId = question.getId();
            QuestionLibrary questionAlready =questionLibraryMapper.selectByQuestion(question);
            if(questionAlready !=null &&  questionAlready.getId() != question.getId()){
                responseEntity.setStatus(-1);
                responseEntity.setMsg("相同的content/options记录已经存在，不可如此修改");
                return responseEntity;
            }else{
                int tmp= questionLibraryMapper.updateByPK(question);
                if(tmp<0){
                    throw new RuntimeException("数据库错误");
                }
            }
        }

        if(map.get("knowledge_list") ==null || map.get("knowledge_list")==""){

        }else{
            List<String> knameList = (List<String>)map.get("knowledge_list");
            int dres = quesKnowledgeMapper.deleteByQuesId(quesId);
            if(dres<0){
                throw new RuntimeException("数据库错误");
            }

            for(int i=0;i<knameList.size();i++){
                String kname = knameList.get(i);
                Knowledge tmpKnow = new Knowledge();
                tmpKnow.setName(kname);
                Knowledge knowledge = knowledgeMapper.selectByKnowledge(tmpKnow);
                int kId = knowledge.getId();

                QuesKnowledge quesKnowledge = new QuesKnowledge();
                quesKnowledge.setKnowledgeId(kId);
                quesKnowledge.setQuestionId(quesId);
                int res = quesKnowledgeMapper.insert(quesKnowledge);
                if(res <0){
                    throw new RuntimeException("数据库错误");
                }


            }
        }


        responseEntity.setMsg("操作成功");
        responseEntity.setStatus(200);

        return responseEntity;
    }


    /**
     * 添加题目列表
     * @param questionList
     * @return
     */
    public ResponseEntity addNewQuestions(List<QuestionLibrary> questionList){
        int length=questionList.size();
        ResponseEntity responseEntity=new ResponseEntity();
        List<QuestionLibrary> questionLibraryList=new ArrayList<>();
        int flag=0;
        for(int i=0;i<length;i++){
            QuestionLibrary question=questionList.get(i);
            QuestionLibrary questionAlready =questionLibraryMapper.selectByQuestion(question);
            if(questionAlready != null){
                questionLibraryList.add(questionAlready);
            }else{
                int tmp= questionLibraryMapper.insert(question);
                if(tmp<0){
                    flag=1;
                    questionLibraryList.add(question);
                }
            }

        }
        if(flag!=0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("部分题目添加失败，添加失败的题目见data,存在ID则为该题题库中已经存在");
            responseEntity.setData(questionLibraryList);
        }else{
            responseEntity.setStatus(200);
            responseEntity.setMsg("添加成功");
        }
        return responseEntity;
    }

//    /**
//     * 添加试卷的试题
//     * @param questionList
//     * @param testPaperList
//     * @return
//     */
//    public List<TestPaperDetail> addNewQuestions( List<TestPaperDetail> testPaperList){
//        //int length=questionList.size();
//        for(int i=0;i<length;i++){
//            QuestionLibrary question=questionList.get(i);
//            QuestionLibrary questionAlready =questionLibraryMapper.selectByQuestion(question);
//            if(questionAlready!=null){
//                testPaperList.get(i).setQuestionId(questionAlready.getId());
//            }else{
//                int tmp= questionLibraryMapper.insert(question);
//                if(tmp<0){
//                    throw new RuntimeException("数据库错误");
//                }else {
//
//                    testPaperList.get(i).setQuestionId(question.getId());
//                }
//            }
//
//        }
//        return testPaperList;
//    }


    public ResponseEntity getAllQuestion(){
        ResponseEntity responseEntity=new ResponseEntity();
        List<QuesKnowNameEntity> questionList = questionLibraryMapper.selectAllWithKnowledgeName();

        if(questionList == null || questionList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("没有题目信息");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("获取题目成功");
            responseEntity.setData(questionList);
        }

        return responseEntity;
    }


    public ResponseEntity getQuestionsByFilter(Map<String,Object> map){
        ResponseEntity responseEntity=new ResponseEntity();

        List<String> ques_name_filter=(List<String>)map.get("ques_name_filter");
        List<String> ques_type_filter=(List<String>)map.get("ques_type_filter");
        List<String> ques_knowledge_filter=(List<String>)map.get("ques_knowledge_filter");
        String ques_name = "";
        String ques_type = "";
        String ques_knowledge = "";

        for(int i=0;i<ques_name_filter.size();i++){
            ques_name=ques_name+ques_name_filter.get(i)+"|";
        }
        if(ques_name!=""){
            ques_name=ques_name.substring(0,ques_name.length()-1);
        }


        for(int i=0;i<ques_type_filter.size();i++){
            ques_type=ques_type+ques_type_filter.get(i)+"|";
        }
        if(ques_type!=""){
            ques_type=ques_type.substring(0,ques_type.length()-1);
        }


        for(int i=0;i<ques_knowledge_filter.size();i++){
            ques_knowledge=ques_knowledge+ques_knowledge_filter.get(i)+"|";
        }
        if(ques_knowledge!=""){
            ques_knowledge=ques_knowledge.substring(0,ques_knowledge.length()-1);
        }


        List<QuesKnowNameEntity> questionList = questionLibraryMapper.selectByFilter(ques_name,ques_type,ques_knowledge);

        if(questionList == null || questionList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("不存在满足条件的题目");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("获取成功");
            responseEntity.setData(questionList);
        }

        return responseEntity;
    }
}
