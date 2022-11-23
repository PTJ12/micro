package xpit.top.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import xpit.top.enums.HttpCodeEnum;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Result() {
        this.code = HttpCodeEnum.SUCCESS.getCode();
        this.msg = HttpCodeEnum.SUCCESS.getMsg();
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result errorResult(int code, String msg) {
        Result result = new Result();
        return result.error(code, msg);
    }
    public static Result okResult() {
        Result result = new Result();
        return result;
    }
    public static Result okResult(int code, String msg) {
        Result result = new Result();
        return result.ok(code, null, msg);
    }

    public static Result okResult(Object data) {
        Result result = setHttpCodeEnum(HttpCodeEnum.SUCCESS, HttpCodeEnum.SUCCESS.getMsg());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static Result errorResult(HttpCodeEnum enums){
        return setHttpCodeEnum(enums,enums.getMsg());
    }

    public static Result errorResult(HttpCodeEnum enums, String msg){
        return setHttpCodeEnum(enums,msg);
    }

    public static Result setHttpCodeEnum(HttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getMsg());
    }

    private static Result setHttpCodeEnum(HttpCodeEnum enums, String msg){
        return okResult(enums.getCode(),msg);
    }

    public Result<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public Result<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public Result<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

}