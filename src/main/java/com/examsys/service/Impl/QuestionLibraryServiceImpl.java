package com.examsys.service.Impl;

import com.alibaba.fastjson.JSON;
import com.examsys.dao.KnowledgeMapper;
import com.examsys.dao.QuesKnowledgeMapper;
import com.examsys.dao.QuestionLibraryMapper;
import com.examsys.dao.TestPaperDetailMapper;
import com.examsys.model.Knowledge;
import com.examsys.model.QuesKnowledge;
import com.examsys.model.QuestionLibrary;
import com.examsys.model.TestPaperDetail;
import com.examsys.model.entity.QuesKnowNameEntity;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.util.error.ErrorMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.CriteriaBuilder;
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

    @Autowired
    TestPaperDetailMapper testPaperDetailMapper;

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


    /**
     * 添加/编辑单个题目
     * @param map
     * @return
     */
    @Transactional
    public ResponseEntity addOrUpdateQuestion(Map<String,Object> map){

        int quesId;
        QuestionLibrary question=new QuestionLibrary();
        question.setType(String.valueOf(map.get("type")));
        question.setContent(String.valueOf(map.get("content")));
        question.setAnswer(JSON.toJSONString(map.get("answer_list")));
        if(map.get("option_list") == null || map.get("option_list") ==""){
            question.setOptions("");
        }else {
            question.setOptions(JSON.toJSONString(map.get("option_list")));
        }

        if( map.get("description") == null || map.get("description")== ""){
            question.setDescription("");
        }else {
            question.setDescription(String.valueOf(map.get("description")));
        }

        //开始处理题目
        if(map.get("id") == null || map.get("id") == ""){
            QuestionLibrary questionDB =questionLibraryMapper.selectByQuestion(question);
            if(questionDB != null){
                return new ResponseEntity(ErrorMsgEnum.QUESTION_ALREADY_EXIST);
            }else{
                int tmp= questionLibraryMapper.insert(question);
                quesId = question.getId();
                if(tmp<0){
                    return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
                }
            }
        }else{
            question.setId(Integer.valueOf(map.get("id").toString()));
            quesId = question.getId();
            QuestionLibrary questionDB =questionLibraryMapper.selectByQuestion(question);
            if(questionDB !=null &&  questionDB.getId() != question.getId()){
                return new ResponseEntity(ErrorMsgEnum.CONTENT_OPTION_IS_DUPLICATE);
            }else{
                int tmp= questionLibraryMapper.updateByPK(question);
                if(tmp<0){
                    return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
                }
            }
        }

        //开始处理知识点
        if(map.get("knowledge_list") == null || map.get("knowledge_list")==""){
            //知识点为空不处理
        }else{
            List<String> knameList = (List<String>)map.get("knowledge_list");
            int dres = quesKnowledgeMapper.deleteByQuesId(quesId);
            if(dres < 0){
                return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
            }

            for(int i=0;i<knameList.size();i++){

                Knowledge tmpKnow = new Knowledge();
                tmpKnow.setName(knameList.get(i));
                Knowledge knowledge = knowledgeMapper.selectByKnowledge(tmpKnow);
                int kId = knowledge.getId();

                QuesKnowledge quesKnowledge = new QuesKnowledge();
                quesKnowledge.setKnowledgeId(kId);
                quesKnowledge.setQuestionId(quesId);
                int res = quesKnowledgeMapper.insert(quesKnowledge);
                if(res <0){
                    return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
                }
            }
        }

        return new ResponseEntity(200,"编辑/更新成功");
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


    /**
     * 获取所有题目
     * @return
     */
    public ResponseEntity getAllQuestion(){
        List<QuesKnowNameEntity> questionList = questionLibraryMapper.selectAllWithKnowledgeName();
        if(questionList == null || questionList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.NO_QUESTIONS_IN_DATABASE);
        }else {
            return new ResponseEntity(200,"获取题目成功",questionList);
        }
    }


    /**
     * 根据过滤条件获取题目
     * @param map
     * @return
     */
    public ResponseEntity getQuestionsByFilter(Map<String,Object> map){

        List<String> ques_name_filter=(List<String>)map.get("ques_name_filter");
        List<String> ques_type_filter=(List<String>)map.get("ques_type_filter");
        List<String> ques_knowledge_filter=(List<String>)map.get("ques_knowledge_filter");

        String ques_name = "";
        String ques_type = "";
        String ques_knowledge = "";

        for(int i=0;i<ques_name_filter.size();i++){
            ques_name=ques_name+ques_name_filter.get(i)+"|";
        }
        if(ques_name != ""){
            ques_name=ques_name.substring(0,ques_name.length()-1);
        }

        for(int i=0;i<ques_type_filter.size();i++){
            ques_type=ques_type+ques_type_filter.get(i)+"|";
        }
        if(ques_type != ""){
            ques_type=ques_type.substring(0,ques_type.length()-1);
        }


        for(int i=0;i<ques_knowledge_filter.size();i++){
            ques_knowledge = ques_knowledge+ques_knowledge_filter.get(i)+"|";
        }
        if(ques_knowledge != ""){
            ques_knowledge = ques_knowledge.substring(0,ques_knowledge.length()-1);
        }

        List<QuesKnowNameEntity> questionList = questionLibraryMapper.selectByFilter(ques_name,ques_type,ques_knowledge);
        if(questionList == null || questionList.size() == 0){
            return new ResponseEntity(ErrorMsgEnum.NO_QUESTIONS_FILTER);
        }else {
            return new ResponseEntity(200,"查询成功",questionList);
        }
    }


    /**
     * 删除若干题目,失败回滚
     * @param map
     * @return
     */
    @Transactional
    public ResponseEntity delQues(Map<String,Object> map){
        ArrayList<Integer> quesIds = (ArrayList<Integer>)map.get("question_id");
        for(int i=0; i<quesIds.size(); i++){
            List<TestPaperDetail> testPaperDetailsDB = testPaperDetailMapper.selectByQuesId(quesIds.get(i));
            if(testPaperDetailsDB == null || testPaperDetailsDB.size()==0){
                quesKnowledgeMapper.deleteByQuesId(quesIds.get(i));
                questionLibraryMapper.deleteByPrimaryKey(quesIds.get(i));
            }else{
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResponseEntity(ErrorMsgEnum.QUESTION_ASSISTANT_WITH_PAPER,testPaperDetailsDB.get(0).getQuestionId());
            }
        }

        return new ResponseEntity(200,"删除成功");
    }
}
