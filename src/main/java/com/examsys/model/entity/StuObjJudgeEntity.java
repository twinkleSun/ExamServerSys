package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class StuObjJudgeEntity {

    private Integer spId;

    private String paperCode;

    private Double objectiveGrade;

    private Double subjectiveGrade;

    private Double extraPoint;

    private Double studentTotalPoint;

    private Integer objectiveStatus;

    private int stuId;

    private int examId;

    private List<StuObjQuesEntity> stuQues;
}
