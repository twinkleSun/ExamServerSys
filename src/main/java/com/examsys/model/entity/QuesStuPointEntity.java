package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Setter
@Getter
public class QuesStuPointEntity {

    private String paperCode;

    private String categoryContent;

    private List<QuestionAns> questionList;




}
