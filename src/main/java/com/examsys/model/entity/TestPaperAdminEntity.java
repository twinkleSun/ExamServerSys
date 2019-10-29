package com.examsys.model.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TestPaperAdminEntity {

    private Integer id;

    private String paperCode;

    private String createTime;

    private String lastModifiedTime;

    private Integer createUserId;

    private String title;

    private String description;

    private String userName;

    private Integer userId;
}
