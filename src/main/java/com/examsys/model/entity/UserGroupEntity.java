package com.examsys.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by twinkleStar on 2019/9/15.
 */
@Data
@Setter
@Getter
public class UserGroupEntity {
    private int id;
    private String userName;
    private String password;
    private String userType;
    private List<Map<String,Object>> group_list;
}
