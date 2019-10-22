package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
public class StudentAnswersDetail {

    private int exam_id;

    private String paper_code;

    private List<Map<String,Object>> student_answers_detail;


}
