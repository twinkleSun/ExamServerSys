package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class PaperAndStuAnsPointEntity{

    private String paperCode;

    private String title;

    private String paperDescription;

    private String createTime;

    private String lastModifiedTime;

    private Integer createUserId;

    private Double objectiveGrade;

    private Integer objectiveStatus;

    private Double subjectiveGrade;

    private Integer subjectiveStatus;

    private Double paperTotalPoint;

    private Double studentTotalPoint;

    private Integer endFlag;

    private List<TestPaperEntity> categoryList;
}
