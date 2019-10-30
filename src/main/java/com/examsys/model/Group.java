package com.examsys.model;

public class Group {
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group(){

    }

    public Group(int gId,String gName){
        id = gId;
        name = gName;
    }
}