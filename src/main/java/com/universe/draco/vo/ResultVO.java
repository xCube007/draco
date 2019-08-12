package com.universe.draco.vo;

/**
 * 封装返回信息用
 * @author Liu Xiaonan
 */
public class ResultVO {

    /**
     * 请求结果编码，成功或失败
     */
    private String code;
    /**
     * 请求结果信息
     */
    private String msg;
    /**
     * 数据信息
     */
    private Object data;

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

    @Override
    public String toString() {
        return "ResultVO{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
