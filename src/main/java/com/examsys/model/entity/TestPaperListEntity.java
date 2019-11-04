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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPaperDescription() {
        return paperDescription;
    }

    public void setPaperDescription(String paperDescription) {
        this.paperDescription = paperDescription;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(String lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }


    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }


    public List<TestPaperEntity> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<TestPaperEntity> categoryList) {
        this.categoryList = categoryList;
    }








}
