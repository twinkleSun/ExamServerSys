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
    GROUP_ASSISTANT_WITH_EXAM(100010,"该组已和考试关联，不可删除。删除需要先解除考试关联关系!"),
    GROUP_ASSISTANT_EXAM_BEGIN(100011,"该组已和考试关联，且考试已经开始或者结束，不可修改!"),


    /**
     * 2开头为知识点/题库类错误码
     */
    NO_QUESTIONS_IN_DATABASE(200001,"数据库中没有题目，请新建"),
    NO_KNOWLEDGE_IN_DATABASE(200002,"数据库中没有知识点，请新建"),
    QUESTION_LIST_IS_NULL(200003,"没有选择题目，请重新选择"),
    NO_QUESTIONS_ASSISTANT_WITH_KNOWLEDGE(200004,"该知识点下没有题目"),
    NO_QUESTIONS_FILTER(200005,"不存在满足条件的题目"),
    QUESTION_ALREADY_EXIST(200006,"相同的题目已存在,请重新编辑"),
    CONTENT_OPTION_IS_DUPLICATE(200007,"相同的题目已存在，请重新修改（题目和选项相同则视为相同）"),
    KNOWLEDGE_ALREADY_EXIST(200008,"相同的知识点已存在，请重新编辑"),
    SAME_KNOWLEDGE_EXIST(200009,"相同的知识点已存在，请重新修改"),
    QUESTION_ASSISTANT_WITH_PAPER(200010,"有部分题目关联了试卷，不可以删除，题目ID见data"),


    /**
     * 3开头为试卷相关错误码
     */
    NO_PAPERS_EXIST(300001,"不存在试卷"),
    PAPER_ASSISTANT_WITH_EXAM(300002,"该试卷已和考试关联，不可以删除。被关联的试卷ID见data"),
    ADMIN_HAS_NO_PAPERS(300003,"该管理员名下没有试卷"),


    /**
     * 4开头为考试相关错误码
     */
    NOT_CHOOSE_PAPER(400001,"没有选择试卷，请选择试卷后再提交"),
    END_EARLY_THAN_BEGIN(400002,"开始日期在结束日期之后,请重新提交"),
    INCORRECT_DATA_FORMAT(400003,"日期格式错误，请重新编辑"),
    BEGIN_CAN_NOT_DELETE(400004,"已完成或进行中的考试信息不允许修改"),
    EXAM_HAS_NO_GROUP(400005,"未添加组,请重新编辑"),
    STUDENT_HAS_NO_EXAM(400006,"该考生没有考试"),
    EXAM_HAS_NO_STUDENT(400007,"该场考试还没考生，请添加"),
    NO_EXAM_LIST(400008,"没有任何考试场次"),
    EXAM_BEGINS_CAN_NOT_DELETE(400009,"考试已结束或正在继续进行中,不得删除")
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
