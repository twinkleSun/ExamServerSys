package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by twinkleStar on 2019/11/5.
 */
@Data
@Setter
@Getter
public class QuestionAns {

    private int ques_id;

    private String content;

    private String options;

    private String type;

    private Double score;

    private String student_answer;

    private Double student_point;

    private String description;

}
