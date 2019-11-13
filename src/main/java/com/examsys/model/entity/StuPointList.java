package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class StuPointList {
    private Integer id;

    private String paperCode;

    private Double objectiveGrade;

    private Double subjectiveGrade;

    private Double paperTotalPoint;

    private Double studentTotalPoint;

    private String examName;

    private Integer examId;

    private String beginTime;

    private String endTime;

    private Long duration;

}
