package com.examsys.model.entity;

import com.examsys.util.error.ErrorMsgEnum;

/**
 * Created by twinkleStar on 2018/12/9.
 */

//封装controller返回值
public class ResponseEntity {
    private int status;
    private String msg;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public ResponseEntity(){

    }

    public ResponseEntity(ErrorMsgEnum type){
        msg = type.getMsg();
        status = type.getCode();
    }


    public ResponseEntity(ErrorMsgEnum etype, Object obj){
        msg = etype.getMsg();
        status = etype.getCode();
        data = obj;
    }

    public ResponseEntity(int code,String info){
        status = code;
        msg = info;
    }

    public ResponseEntity(int code,String info,Object obj){
        status = code;
        msg = info;
        data = obj;
    }


}
