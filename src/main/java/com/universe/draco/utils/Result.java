package com.universe.draco.utils;

import com.alibaba.fastjson.JSONObject;
import com.universe.draco.vo.ResultVO;

/**
 * @ClassName: Result
 * @Description:
 * @Author: Liu Xiaonan
 * @data： 2019/7/10 20:56
 */
public class Result {

    //成功
    private static String SUCCESS = "1";
    //失败
    private static String ERROR = "0";

    public static String success() {
        ResultVO vo = new ResultVO();
        vo.setCode(SUCCESS);
        return JSONObject.toJSONString(vo);
    }

    public static String success(String msg) {
        ResultVO vo = new ResultVO();
        vo.setCode(SUCCESS);
        vo.setMsg(msg);
        return JSONObject.toJSONString(vo);
    }

    public static String success(String msg, Object data) {
        ResultVO vo = new ResultVO();
        vo.setCode(SUCCESS);
        vo.setMsg(msg);
        vo.setData(data);
        return JSONObject.toJSONString(vo);
    }

    public static String error() {
        ResultVO vo = new ResultVO();
        vo.setCode(ERROR);
        return JSONObject.toJSONString(vo);
    }

    public static String error(String msg) {
        ResultVO vo = new ResultVO();
        vo.setCode(ERROR);
        vo.setMsg(msg);
        return JSONObject.toJSONString(vo);
    }

//    public static String error(String msg, Object data) {
//        ResultVO vo = new ResultVO();
//        vo.setCode(ERROR);
//        vo.setMsg(msg);
//        vo.setData(data);
//        return JSONObject.toJSONString(vo);
//    }

    public static String error(String code, String msg) {
        ResultVO vo = new ResultVO();
        vo.setCode(code);
        vo.setMsg(msg);
        return JSONObject.toJSONString(vo);
    }
}
