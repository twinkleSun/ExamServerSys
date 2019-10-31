package com.examsys.service.Impl;

import com.examsys.dao.KnowledgeMapper;
import com.examsys.dao.QuesKnowledgeMapper;
import com.examsys.dao.QuestionLibraryMapper;
import com.examsys.model.Knowledge;
import com.examsys.model.QuesKnowledge;
import com.examsys.model.QuestionLibrary;
import com.examsys.model.entity.Keypoint;
import com.examsys.model.entity.QuesKnowEntity;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.util.error.ErrorMsgEnum;
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
     * 添加/编辑知识点
     * @param map
     * @return
     */
    public ResponseEntity addOrUpdateKnowledge(Map<String,Object> map){
        Knowledge knowledge=new Knowledge();
        knowledge.setName(String.valueOf(map.get("name")));
        knowledge.setDescription(String.valueOf(map.get("description")));
        knowledge.setLevel(1);
        knowledge.setParentId(0);

        //不存在则添加
        if(map.get("id") == null || map.get("id") == ""){
            Knowledge knowledgeDB = knowledgeMapper.selectByKnowledge(knowledge);
            if(knowledgeDB != null){
              return new ResponseEntity(ErrorMsgEnum.KNOWLEDGE_ALREADY_EXIST);
            }else{
                knowledgeMapper.insert(knowledge);
            }
        }else{
            knowledge.setId(Integer.valueOf(String.valueOf(map.get("id"))));
            Knowledge knowledgeDB = knowledgeMapper.selectByKnowledge(knowledge);
            if(knowledgeDB != null){
                return new ResponseEntity(ErrorMsgEnum.SAME_KNOWLEDGE_EXIST);
            }else{
                knowledgeMapper.updateByPrimaryKey(knowledge);
            }
        }
        return new ResponseEntity(200,"更新/编辑成功");
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


    /**
     * 获取所有知识点
     * @return
     */
    public ResponseEntity getAllKnowledge(){
        List<Keypoint> knowledgeList = knowledgeMapper.selectAllKeyPoint();
        if(knowledgeList == null || knowledgeList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.NO_KNOWLEDGE_IN_DATABASE);
        }else {
            return new ResponseEntity(200,"获取知识点成功",knowledgeList);
        }
    }


    /**
     * 给多个题目添加知识点
     * 处理数据
     * @param mapList
     * @return
     */
    public List<QuesKnowEntity> handleQuesAndKnow(List<Map<String,Object>> mapList){
        List<QuesKnowEntity> QuesKnowList=new ArrayList<>();

        for(int i=0; i<mapList.size(); i++){
            Map<String,Object> map=mapList.get(i);

            QuesKnowEntity quesknowEntity=new QuesKnowEntity();
            quesknowEntity.setK_id((Integer) map.get("k_id"));
            quesknowEntity.setk_name(String.valueOf(map.get("k_name")));

            List<Map<String,Object>> ques_list =  (List<Map<String, Object>>) map.get("question_list");
            if(ques_list.size() == 0 || ques_list == null) {
                quesknowEntity.setQues_list(null);
            } else {
                quesknowEntity.setQues_list(questionLibraryService.handleNewQuestions(ques_list));
            }
            QuesKnowList.add(quesknowEntity);
        }
        return QuesKnowList;
    }


    /**
     * 给多个题目添加知识点
     * @param QuesKnowList
     * @return
     */
    public ResponseEntity addQuesAndKnow(List<QuesKnowEntity> QuesKnowList){
        for(int i = 0; i < QuesKnowList.size(); i++) {
            QuesKnowEntity quesKnowEntity = QuesKnowList.get(i);
            List<QuestionLibrary> questionLibraryList = quesKnowEntity.getQues_list();
            if(questionLibraryList == null || questionLibraryList.size() == 0) {
                return new ResponseEntity(ErrorMsgEnum.QUESTION_LIST_IS_NULL);
            }

            for(int j = 0; j < questionLibraryList.size(); j++) {
                QuestionLibrary questionLibrary = questionLibraryList.get(j);

                QuesKnowledge quesKnowledge = new QuesKnowledge();
                quesKnowledge.setKnowledgeId(quesKnowEntity.getK_id());

                QuestionLibrary questionLibraryDB = questionLibraryMapper.selectByQuestion(questionLibrary);
                if(questionLibraryDB == null) {
                    //如果题目不存在，则先插入
                    questionLibraryMapper.insert(questionLibrary);
                    quesKnowledge.setQuestionId(questionLibrary.getId());
                }else {
                    quesKnowledge.setQuestionId(questionLibraryDB.getId());
                }

                int temp = quesKnowledgeMapper.insertNotExist(quesKnowledge);
                if(temp<0){
                    return new ResponseEntity(ErrorMsgEnum.DATABASE_ERROR);
                }
            }
        }
        return new ResponseEntity(200,"添加成功");
    }


    /**
     * 根据知识点获取题目
     * @param map
     * @return
     */
    public ResponseEntity getQuesByKonw(Map<String,Object> map) {
        List<QuestionLibrary> questionLibraryList=questionLibraryMapper.selectQuesByKnow((Integer) map.get("k_id"));

        if(questionLibraryList==null || questionLibraryList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.NO_QUESTIONS_ASSISTANT_WITH_KNOWLEDGE);
        }else {
            return new ResponseEntity(200,"查询成功",questionLibraryList);
        }
    }

    /**
     * 批量删除知识点
     * @param map
     * @return
     */
    public ResponseEntity delKnows(Map<String,Object> map){
        ArrayList<Integer> knowIds = (ArrayList<Integer>)map.get("id");
        for(int i=0; i<knowIds.size(); i++){
            int knowId = knowIds.get(i);
            quesKnowledgeMapper.deleteByKid(knowId);
            knowledgeMapper.deleteByPrimaryKey(knowId);
        }
        return new ResponseEntity(200,"删除成功");
    }
}
