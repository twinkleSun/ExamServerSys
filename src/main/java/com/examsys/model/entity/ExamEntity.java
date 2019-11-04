package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Data
@Setter
@Getter
public class ExamEntity {

    private Integer id;

    private String examName;

    private String paperCode;

    private String beginTime;

    private String endTime;

    private String duration;

    private String status;

    private Map<String,Object> paper_info;
}
