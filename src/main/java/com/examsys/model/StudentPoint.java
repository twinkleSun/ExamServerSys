package com.examsys.model;

public class StudentPoint {
    private Integer id;

    private String paperCode;

    private Integer studentId;

    private Double objectiveGrade;

    private Double subjectiveGrade;

    private Double extraPoint;

    private Double paperTotalPoint;

    private Double studentTotalPoint;

    private Integer objectiveStatus;

    private Integer subjectiveStatus;

    private Integer examId;



    private Integer endFlag;

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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Double getObjectiveGrade() {
        return objectiveGrade;
    }

    public void setObjectiveGrade(Double objectiveGrade) {
        this.objectiveGrade = objectiveGrade;
    }

    public Double getSubjectiveGrade() {
        return subjectiveGrade;
    }

    public void setSubjectiveGrade(Double subjectiveGrade) {
        this.subjectiveGrade = subjectiveGrade;
    }

    public Double getExtraPoint() {
        return extraPoint;
    }

    public void setExtraPoint(Double extraPoint) {
        this.extraPoint = extraPoint;
    }

    public Double getPaperTotalPoint() {
        return paperTotalPoint;
    }

    public void setPaperTotalPoint(Double paperTotalPoint) {
        this.paperTotalPoint = paperTotalPoint;
    }

    public Double getStudentTotalPoint() {
        return studentTotalPoint;
    }

    public void setStudentTotalPoint(Double studentTotalPoint) {
        this.studentTotalPoint = studentTotalPoint;
    }


    public Integer getObjectiveStatus() {
        return objectiveStatus;
    }

    public void setObjectiveStatus(Integer objectiveStatus) {
        this.objectiveStatus = objectiveStatus;
    }

    public Integer getSubjectiveStatus() {
        return subjectiveStatus;
    }

    public void setSubjectiveStatus(Integer subjectiveStatus) {
        this.subjectiveStatus = subjectiveStatus;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(Integer endFlag) {
        this.endFlag = endFlag;
    }
}