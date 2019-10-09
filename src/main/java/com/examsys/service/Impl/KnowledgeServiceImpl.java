package com.examsys.service.Impl;

import com.alibaba.fastjson.JSON;
import com.examsys.dao.KnowledgeMapper;
import com.examsys.dao.QuesKnowledgeMapper;
import com.examsys.dao.QuestionLibraryMapper;
import com.examsys.model.Knowledge;
import com.examsys.model.QuesKnowledge;
import com.examsys.model.QuestionLibrary;
import com.examsys.model.entity.QuesKnowEntity;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.model.entity.UserGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class KnowledgeServiceImpl {

    @Autowired
    KnowledgeMapper knowledgeMapper;

    @Autowired
    QuestionLibraryMapper questionLibraryMapper;

    @Autowired
    QuestionLibraryServiceImpl questionLibraryService;

    @Autowired
    QuesKnowledgeMapper quesKnowledgeMapper;

    /**
     * 处理数据
     * @param map
     * @return
     */
    public List<Knowledge> handleNewKnowledge(List<Map<String,Object>> map){
        List<Knowledge> knowledgeList=new ArrayList<>();
        for(int i=0;i<map.size();i++){
            Knowledge knowledge=new Knowledge();
            Map<String,Object> map1=map.get(i);
            knowledge.setName(String.valueOf(map1.get("name")));
            knowledge.setDescription(String.valueOf(map1.get("description")));
            knowledge.setLevel(1);
            knowledge.setParentId(0);
            knowledgeList.add(knowledge);
        }

        return knowledgeList;
    }

    /**
     * 添加知识点
     * @param knowledgeList
     * @return
     */
    public ResponseEntity addNewKnowledge(List<Knowledge> knowledgeList){
        int length=knowledgeList.size();
        ResponseEntity responseEntity=new ResponseEntity();
        List<Knowledge> KnowledgeList=new ArrayList<>();
        int flag=0;
        for(int i=0;i<length;i++){
            Knowledge knowledge=knowledgeList.get(i);
            Knowledge knowledgeAlready = knowledgeMapper.selectByKnowledge(knowledge);
            if(knowledgeAlready!=null){
                KnowledgeList.add(knowledgeAlready);
            }else{
                int tmp= knowledgeMapper.insert(knowledge);
                if(tmp<0){
                    flag=1;
                    KnowledgeList.add(knowledge);
                }
            }

        }

        if(flag!=0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("部分知识点添加失败，添加失败的知识点见data,存在ID则为该知识点已经存在");
            responseEntity.setData(KnowledgeList);
        }else{
            responseEntity.setStatus(200);
            responseEntity.setMsg("添加成功");
        }
        return responseEntity;
    }

    public ResponseEntity getAllKnowledge(){
        ResponseEntity responseEntity=new ResponseEntity();
        List<Knowledge> knowledgeList = knowledgeMapper.selectAll();

        if(knowledgeList == null || knowledgeList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("没有知识点信息");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("获取知识点成功");
            responseEntity.setData(knowledgeList);
        }

        return responseEntity;
    }

    /**
     * 处理数据
     * @param map
     * @return
     */
    public List<QuesKnowEntity> handleQuesAndKnow(List<Map<String,Object>> map){
        List<QuesKnowEntity> QuesKnowList=new ArrayList<>();

        for(int i=0;i<map.size();i++){
            QuesKnowEntity quesknowEntity=new QuesKnowEntity();
            List<Map<String,Object>> ques_list = new ArrayList<>();

            Map<String,Object> map1=map.get(i);
            quesknowEntity.setK_id((Integer) map1.get("k_id"));
            quesknowEntity.setk_name(String.valueOf(map1.get("k_name")));
            ques_list = (List<Map<String, Object>>) map1.get("question_list");
            if(ques_list.size() == 0 || ques_list == null) {
                quesknowEntity.setQues_list(null);
            } else {
                quesknowEntity.setQues_list(questionLibraryService.handleNewQuestions(ques_list));
            }
            QuesKnowList.add(quesknowEntity);
        }

        return QuesKnowList;
    }

    public ResponseEntity addQuesAndKnow(List<QuesKnowEntity> QuesKnowList){
        ResponseEntity responseEntity=new ResponseEntity();

        if(QuesKnowList == null || QuesKnowList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("没有知识点需要添加");
            return responseEntity;
        }

        int len = QuesKnowList.size();
        int flag = 0;
        for(int i = 0; i < len; i++) {
            QuesKnowEntity quesKnowEntity = QuesKnowList.get(i);
            List<QuestionLibrary> questionLibraryList = quesKnowEntity.getQues_list();
            if(questionLibraryList == null || questionLibraryList.size() == 0) {
                continue;
            }
            int quesLen = questionLibraryList.size();
            for(int j = 0; j < quesLen; j++) {
                QuestionLibrary questionLibrary = questionLibraryList.get(j);
                QuestionLibrary questionLibraryAlready = questionLibraryMapper.selectByQuestion(questionLibrary);
                if(questionLibraryAlready == null) {
                    questionLibraryMapper.insert(questionLibrary);
                }
                QuesKnowledge quesKnowledge = new QuesKnowledge();
                quesKnowledge.setKnowledgeId(quesKnowEntity.getK_id());
                QuestionLibrary quesId = questionLibraryMapper.getId(questionLibrary);
                quesKnowledge.setQuestionId(quesId.getId());
                int temp = quesKnowledgeMapper.insertNotExist(quesKnowledge);
                if(temp<0){
                    flag=1;
                }
            }
        }
        if(flag!=0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("部分知识点添加失败，添加失败的知识点见data,存在ID则为该知识点已经存在");
        }else{
            responseEntity.setStatus(200);
            responseEntity.setMsg("添加成功");
        }
        return responseEntity;
    }

    public ResponseEntity getQuesByKonw(Map<String,Object> map) {
        List<QuestionLibrary> questionLibraryList=questionLibraryMapper.selectQuesByKnow((Integer) map.get("k_id"));
        ResponseEntity responseEntity=new ResponseEntity();
        if(questionLibraryList==null || questionLibraryList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("没有题目信息");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setMsg("查询成功");
            responseEntity.setData(questionLibraryList);
        }

        return responseEntity;
    }
}
