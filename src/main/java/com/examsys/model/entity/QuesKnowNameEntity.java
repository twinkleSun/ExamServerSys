package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by twinkleStar on 2019/10/22.
 */
@Data
@Setter
@Getter
public class QuesKnowNameEntity {

    private Integer id;

    private String content;

    private String options;

    private String answer;

    private String type;

    private String description;

    private List<String> knowledge;
}
