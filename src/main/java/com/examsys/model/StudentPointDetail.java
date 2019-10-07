package com.examsys.model;

public class StudentPointDetail {
    private Integer id;

    private String paperCode;

    private Integer questionId;

    private Integer studentId;

    private String defAnswer;

    private String studentAnswer;

    private Double studentPoint;

    private Integer questionStatus;

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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getDefAnswer() {
        return defAnswer;
    }

    public void setDefAnswer(String defAnswer) {
        this.defAnswer = defAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public Double getStudentPoint() {
        return studentPoint;
    }

    public void setStudentPoint(Double studentPoint) {
        this.studentPoint = studentPoint;
    }

    public Integer getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(Integer questionStatus) {
        this.questionStatus = questionStatus;
    }
}