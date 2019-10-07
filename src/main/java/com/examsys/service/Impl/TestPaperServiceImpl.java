package com.examsys.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.examsys.dao.TestPaperMapper;
import com.examsys.model.QuestionLibrary;
import com.examsys.model.TestPaper;
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
public class TestPaperServiceImpl {

    @Autowired
    TestPaperMapper testPaperMapper;

    /**
     * 处理添加试卷的数据
     * @param map
     * @return
     */
    public Map<String,Object> handleNewPaper(List<Map<String,Object>> map){
        Map<String,Object> mapRes=new HashMap<>();
        List<QuestionLibrary> questionList=new ArrayList<>();
        List<TestPaper> testPaperList=new ArrayList<>();
        Calendar cal=Calendar.getInstance();
        String paper_code=""+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+cal.get(Calendar.DATE)+cal.get(Calendar.HOUR_OF_DAY)
                +cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);

        for(int i=0;i<map.size();i++){
            QuestionLibrary question=new QuestionLibrary();
            TestPaper testpaper=new TestPaper();
            Map<String,Object> map1=map.get(i);

            question.setType(String.valueOf(map1.get("type")));
            question.setContent(String.valueOf(map1.get("content")));

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
    public ResponseEntity addNewPaper(List<TestPaper> testPaperList){
        ResponseEntity responseEntity=new ResponseEntity();
        int length=testPaperList.size();

        for(int i=0;i<length;i++){
            TestPaper testPaper=testPaperList.get(i);
            int res=testPaperMapper.insert(testPaper);
            if(res<0){
                responseEntity.setStatus(-1);
                return responseEntity;
            }
        }
        responseEntity.setStatus(200);
        return responseEntity;
    }


    public ResponseEntity getAllPapers(){
        List<TestPaper> testPaperList = testPaperMapper.selectPapers();
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

}
