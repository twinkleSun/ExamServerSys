package com.examsys.model.entity;

import java.util.List;
import java.util.Map;

public class TestPaperEntity {

    private String paperCode;

    private String categoryContent;

    private List<Map<String,Object>> questionList;

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getCategoryContent() {
        return categoryContent;
    }

    public void setCategoryContent(String categoryContent) {
        this.categoryContent = categoryContent;
    }

    public List<Map<String, Object>> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Map<String, Object>> questionList) {
        this.questionList = questionList;
    }






}
