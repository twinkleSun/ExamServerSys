package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/3.
 */
@Data
@Setter
@Getter
public class GroupUserEntity {
    private int group_id;
    private String group_name;
    private List<Map<String,Object>> students;

}
