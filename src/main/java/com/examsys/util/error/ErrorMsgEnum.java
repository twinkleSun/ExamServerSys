package com.examsys.util.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by twinkleStar on 2019/10/30.
 */

@Getter
@AllArgsConstructor
public enum ErrorMsgEnum {
    /**
     * 错误类型
     */
    OBJECT_NOT_FOUND(0,"对象不存在"),

    INVALID_PARAMS(1,"参数不正确"),

    result_not_exist(2,"记录不存在"),

    /**
     * 数据库错误，一般不会出现
     */
    DATABASE_ERROR(0,"数据库错误"),

    /**
     * 1开头为用户类错误码
     */
    USER_NOT_EXIT(100001,"用户不存在"),
    USERNAME_OR_PASSWORD_INCORRECT(100002,"用户名或密码错误"),
    NO_GROUP_NO_USER(100003,"不存在任何组信息"),
    NO_USER_NO_GROUP(100004,"不存在任何考生用户数据，请新建！"),
    GROUP_ALREADY_EXIST(100005,"组名已存在，不可重复"),
    USER_BELONGS_NO_GROUP(100006,"该考生还未添加进任何组"),
    GROUP_CONTAINS_STUDENT_ALREADY(100007,"该组已经包含此学生"),
    USER_ALREADY_EXIST(100008,"该用户已存在，注意用户名不可以重复"),
    USER_ALREADY_EXIST_SAME(100009,"数据库中已存在相同用户名的账号，不可以如此修改"),
    GROUP_ASSISTANT_WITH_EXAM(100010,"该组已和考试关联，不可删除。删除需要先解除考试关联关系!");

    /**
     * 2开头为
     */
    ;

    /**
     * 错误码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;
}
