package com.universe.draco.vo;

/**
 * 封装返回信息用
 */
public class ResultVO {

    private String code; //请求结果编码，成功或失败
    private String msg; //请求结果信息
    private Object data; //数据信息

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
}
