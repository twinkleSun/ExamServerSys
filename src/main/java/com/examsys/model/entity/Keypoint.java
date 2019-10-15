package com.examsys.model.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Keypoint {

    private String title;

    private String key;

    private String isLeaf = "true";

}
