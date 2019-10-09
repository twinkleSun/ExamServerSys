package com.examsys.service.Impl;

import com.alibaba.fastjson.JSON;
import com.examsys.dao.TestPaperDetailMapper;
import com.examsys.dao.TestPaperMapper;
import com.examsys.model.QuestionLibrary;
import com.examsys.model.TestPaper;
import com.examsys.model.TestPaperDetail;
import com.examsys.model.entity.ResponseEntity;
import com.examsys.model.entity.TestPaperListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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

    /**
     * 处理添加试卷的数据
     * @param TestpaperMap
     * @return
     */
    public Map<String,Object> handleNewPaper(Map<String,Object> TestpaperMap){
        Map<String,Object> mapRes=new HashMap<>();
        List<QuestionLibrary> questionList=new ArrayList<>();
        List<TestPaperDetail> testPaperList=new ArrayList<>();
        TestPaper testPaper=new TestPaper();
        Date now = new Date();
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        testPaper.setCreateTime(dateFormatTime.format(now));

        SimpleDateFormat dateFormatCode = new SimpleDateFormat("yyyyMMddHHmmss");

        String paper_code=dateFormatCode.format(now);
//        Calendar cal=Calendar.getInstance();
//        String paper_code=""+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+cal.get(Calendar.DATE)+cal.get(Calendar.HOUR_OF_DAY)
//                +cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);

        List<Map<String,Object>> map=(List<Map<String,Object>>)TestpaperMap.get("question_list");
        testPaper.setPaperCode(paper_code);
        testPaper.setDescription(String.valueOf(TestpaperMap.get("description")));
        testPaper.setTitle(String.valueOf(TestpaperMap.get("title")));
        testPaper.setCreateUserId(Integer.valueOf(TestpaperMap.get("user_id").toString()));

        TestPaper testPaperAlready=testPaperMapper.selectByPaperCode(paper_code);
        if(testPaperAlready!=null){
            throw new RuntimeException("试卷已存在");
        }else{
            int res=testPaperMapper.insert(testPaper);
            if(res<0){
                throw new RuntimeException("数据库错误");
            }
        }

        for(int i=0;i<map.size();i++){
            QuestionLibrary question=new QuestionLibrary();
            TestPaperDetail testpaper=new TestPaperDetail();
            Map<String,Object> map1=map.get(i);

            question.setType(String.valueOf(map1.get("type")));
            question.setContent(String.valueOf(map1.get("content")));

            question.setDescription(String.valueOf(map1.get("description")));
            question.setOptions(JSON.toJSONString(map1.get("option_list")));
            question.setAnswer(JSON.toJSONString(map1.get("answer_list")));


            double score=(double) Integer.parseInt(String.valueOf(map1.get("score")));
            testpaper.setScore(score);
            testpaper.setDefAnswer(map1.get("answer_list").toString());
            testpaper.setPaperCode(paper_code);
            testpaper.setMustOrNot(Integer.valueOf(map1.get("must_or_not").toString()));
            testpaper.setCategoryContent(String.valueOf(map1.get("category_content")));

            questionList.add(question);
            testPaperList.add(testpaper);
        }

        mapRes.put("questionList",questionList);
        mapRes.put("testPaperList",testPaperList);

        return mapRes;
    }


    /**
     * 添加试卷
     * @param testPaperList
     * @return
     */
    public ResponseEntity addNewPaper(List<TestPaperDetail> testPaperList){
        ResponseEntity responseEntity=new ResponseEntity();
        int length=testPaperList.size();

        for(int i=0;i<length;i++){
            TestPaperDetail testPaper=testPaperList.get(i);
            int res=testPaperDetailMapper.insert(testPaper);
            if(res<0){
                responseEntity.setStatus(-1);
                return responseEntity;
            }
        }
        responseEntity.setStatus(200);
        return responseEntity;
    }


    /**
     * 获取单个试卷详情
     * @return
     */
    public ResponseEntity getTestPaperDetail(String paper_code){
        TestPaperListEntity testPaperList = testPaperDetailMapper.selectPapers(paper_code);
        ResponseEntity responseEntity=new ResponseEntity();
        if(testPaperList==null){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("查询失败");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setData(testPaperList);
        }
        return responseEntity;
    }


    /**
     * 获取试卷列表
     * @return
     */
    public ResponseEntity getAllPaperList(){
        List<TestPaper> testPaperList = testPaperMapper.selectAll();
        ResponseEntity responseEntity=new ResponseEntity();
        if(testPaperList==null | testPaperList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("不存在试卷");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setData(testPaperList);
        }
        return responseEntity;
    }



    /**
     * 获取试卷列表,
     * @return
     */
    public ResponseEntity getPaperListByAdmin(Map<String,Object> mapRes){
        List<TestPaper> testPaperList = testPaperMapper.selectByAdminId(Integer.valueOf(mapRes.get("admin_id").toString()));
        ResponseEntity responseEntity=new ResponseEntity();
        if(testPaperList==null | testPaperList.size()==0){
            responseEntity.setStatus(-1);
            responseEntity.setMsg("该管理员名下没有试卷");
        }else {
            responseEntity.setStatus(200);
            responseEntity.setData(testPaperList);
        }
        return responseEntity;
    }

}
