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

    result_not_exist(2,"记录不存在")

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
