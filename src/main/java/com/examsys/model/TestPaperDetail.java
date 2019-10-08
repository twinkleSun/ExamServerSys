package com.examsys.model;

public class TestPaperDetail {
    private Integer id;

    private String paperCode;

    private Integer questionId;

    private Integer mustOrNot;

    private Double score;

    private String defAnswer;

    private String categoryContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getMustOrNot() {
        return mustOrNot;
    }

    public void setMustOrNot(Integer mustOrNot) {
        this.mustOrNot = mustOrNot;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDefAnswer() {
        return defAnswer;
    }

    public void setDefAnswer(String defAnswer) {
        this.defAnswer = defAnswer;
    }

    public String getCategoryContent() {
        return categoryContent;
    }

    public void setCategoryContent(String categoryContent) {
        this.categoryContent = categoryContent;
    }
}