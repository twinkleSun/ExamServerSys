package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
@Getter
@Setter
public class StuObjQuesEntity {

    private Integer sdId;

    private Integer questionId;

    private Double defPoint;

    private String studentAnswer;

    private Double studentPoint;

    private Integer questionStatus;


    private String defAns;

    private String quesType;

}
