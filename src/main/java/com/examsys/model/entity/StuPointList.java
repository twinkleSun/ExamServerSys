package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class StuPointList {

    private Integer id;

    private String examName;

    private String paperCode;

    private String beginTime;

    private String endTime;

    private Long duration;

    private String status;

    private Double objectiveGrade;

    private Double subjectiveGrade;

    private Double paperTotalPoint;

    private Double studentTotalPoint;

    private Integer endFlag;



}
