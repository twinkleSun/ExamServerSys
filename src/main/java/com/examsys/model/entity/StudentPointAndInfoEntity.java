package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class StudentPointAndInfoEntity {
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

    private String inTime;

    private Long leftTime;

    private String studentName;
}