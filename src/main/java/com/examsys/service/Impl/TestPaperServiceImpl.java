package com.examsys.service.Impl;

import com.alibaba.fastjson.JSON;
import com.examsys.dao.*;
import com.examsys.model.*;
import com.examsys.model.entity.GroupUserEntity;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.model.entity.TestPaperAdminEntity;
import com.examsys.model.entity.TestPaperListEntity;
import com.examsys.util.error.ErrorMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@Service
@Repository
public class TestPaperServiceImpl {

    @Autowired
    TestPaperDetailMapper testPaperDetailMapper;

    @Autowired
    TestPaperMapper testPaperMapper;

    @Autowired
    GroupUserMapper groupUserMapper;

    @Autowired
    ExamMapper examMapper;

    @Autowired
    UserMapper userMapper;


    /**
     * 处理添加试卷的数据
     * @param paperMap
     * @return
     */
    public ResponseEntity handleNewPaper(Map<String,Object> paperMap){
        TestPaper testPaper=new TestPaper();

        Date now = new Date();
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormatCode = new SimpleDateFormat("yyyyMMddHHmmss");

        testPaper.setDescription(String.valueOf(paperMap.get("description")));
        testPaper.setTitle(String.valueOf(paperMap.get("title")));
        testPaper.setCreateUserId(Integer.valueOf(paperMap.get("user_id").toString()));

        User userDB = userMapper.selectByUid(Integer.valueOf(paperMap.get("user_id").toString()));
        if(userDB == null){
            return new ResponseEntity(ErrorMsgEnum.USER_NOT_EXIT);
        }

        if(userDB.getRole().equals("admin")){
            testPaper.setCreateUserId(Integer.valueOf(paperMap.get("user_id").toString()));
        }else{
            return new ResponseEntity(ErrorMsgEnum.CREATOR_NOT_ADMIN);
        }
        String paper_code;
        if (paperMap.get("paper_code") == null || paperMap.get("paper_code") == "") {
            //没有paper_code则新建
            testPaper.setCreateTime(dateFormatTime.format(now));
            paper_code = dateFormatCode.format(now);
            testPaper.setPaperCode(paper_code);
            testPaperMapper.insert(testPaper);
        }else {
            //修改试卷
            paper_code = String.valueOf(paperMap.get("paper_code"));
            testPaper.setPaperCode(paper_code);
            testPaper.setLastModifiedTime(dateFormatTime.format(now));
            testPaperMapper.updateByPaperCode(testPaper);

        }

        //添加试卷-题目关联关系
        List<TestPaperDetail> testPaperList=new ArrayList<>();
        List<Map<String,Object>> questionList=(List<Map<String,Object>>)paperMap.get("question_list");
        for(int i=0; i<questionList.size(); i++){
            TestPaperDetail testPaperDetail=new TestPaperDetail();

            Map<String,Object> question=questionList.get(i);

            double score = Double.parseDouble(String.valueOf(question.get("score")));
            testPaperDetail.setScore(score);

            testPaperDetail.setDefAnswer(JSON.toJSONString(question.get("answer_list")));
            testPaperDetail.setPaperCode(paper_code);
            testPaperDetail.setMustOrNot(Integer.valueOf(question.get("must_or_not").toString()));
            testPaperDetail.setCategoryContent(String.valueOf(question.get("category_content")));
            testPaperDetail.setQuestionId(Integer.valueOf(question.get("id").toString()));
            testPaperList.add(testPaperDetail);
        }

        return new ResponseEntity(200,"数据处理成功",testPaperList);
    }


    /**
     * 添加试卷
     * @param testPaperDetailList
     * @return
     */
    public ResponseEntity addTestPaperDetail(List<TestPaperDetail> testPaperDetailList){
        for(int i=0; i<testPaperDetailList.size(); i++){
            TestPaperDetail testPaper = testPaperDetailList.get(i);
            testPaperDetailMapper.insert(testPaper);
        }
        return new ResponseEntity(200,"添加试卷成功");
    }


    /**
     * 删除试卷-题目关系
     * @param testPaperList
     */
    public void deletePaperDetail(List<TestPaperDetail> testPaperList){
        String paperCode = testPaperList.get(0).getPaperCode();
        int res = testPaperDetailMapper.deleteByPaperCode(paperCode);
        if(res<0){
            throw new RuntimeException("数据库错误");
        }
    }


    /**
     * 获取单个试卷详情
     * @return
     */
    public ResponseEntity getTestPaperDetail(String paper_code){
        TestPaperListEntity testPaperList = testPaperDetailMapper.selectPapers(paper_code);
        return new ResponseEntity(200,"查询成功",testPaperList);
    }


    /**
     * 获取试卷列表
     * @return
     */
    public ResponseEntity getPapersList(){
        List<TestPaperAdminEntity>  testPaperList = testPaperMapper.selectAllWithAdmin();
        if(testPaperList==null | testPaperList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.NO_PAPERS_EXIST);
        } else {
            return new ResponseEntity(200,"查询成功",testPaperList);
        }

    }


    /**
     * 管理员查看自己创建的试卷
     * @return
     */
    public ResponseEntity getPaperListByAdmin(Map<String,Object> map){
        List<TestPaper> testPaperList = testPaperMapper.selectByAdminId(Integer.valueOf(map.get("admin_id").toString()));

        if(testPaperList==null | testPaperList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.ADMIN_HAS_NO_PAPERS);
        } else {
            return new ResponseEntity(200,"查询成功",testPaperList);
        }

    }


    /**
     * 获取某场考试的考生列表
     * @return
     */
    public ResponseEntity getStudent(int exam_id){
        List<GroupUserEntity> studentList = groupUserMapper.selectStudent(exam_id);

        if(studentList==null || studentList.size()==0){
            return new ResponseEntity(ErrorMsgEnum.EXAM_HAS_NO_STUDENT);
        } else {
            return new ResponseEntity(200,"查询成功",studentList);
        }
    }


    /**
     * 管理员删除试卷
     * @param map
     * @return
     */
    @Transactional
    public ResponseEntity delByPaperCode(Map<String,Object> map){
        List<String> paperCodes = (List<String>)map.get("paper_code");

        for(int i=0;i<paperCodes.size();i++){
            String paperCode = paperCodes.get(i);
            List<Exam> examListDB = examMapper.selectByPaperCode(paperCode);
            if(examListDB == null || examListDB.size()==0){
                testPaperMapper.deleteByPaperCode(paperCode);
                testPaperDetailMapper.deleteByPaperCode(paperCode);
            }else{
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResponseEntity(ErrorMsgEnum.PAPER_ASSISTANT_WITH_EXAM,paperCode);
            }
        }
        return new ResponseEntity(200,"删除成功");
    }

}
