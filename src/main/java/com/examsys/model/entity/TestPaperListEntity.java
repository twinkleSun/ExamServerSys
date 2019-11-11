package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Setter
@Getter
public class TestPaperListEntity {

    private String paperCode;

    private String title;

    private String paperDescription;

    private String createTime;

    private String lastModifiedTime;

    private Integer createUserId;

    private Long leftTime;

    private List<TestPaperEntity> categoryList;
}
