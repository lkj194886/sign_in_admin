package com.sign_in.code.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

/**
 * @Classname Result
 * @Description 响应实体类
 * @Date 2020/6/26 18:44
 * @Created by wgg
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "响应实体类")
public class Result<T> {
    @ApiModelProperty(value = "code")
    private int code;
    @ApiModelProperty(value = "msg")
    private String msg;
    @ApiModelProperty(value = "data")
    private T data;

    public Result(){

    }


    public Result(int code, String msg, T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
