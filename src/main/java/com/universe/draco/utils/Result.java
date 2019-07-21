package com.universe.draco.utils;

import com.alibaba.fastjson.JSONObject;
import com.universe.draco.sys.entity.SysUser;
import com.universe.draco.vo.ResultVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Result
 * @Description:
 * @Author: Liu Xiaonan
 * @data： 2019/7/10 20:56
 */
public class Result {

    // 成功
    public static String SUCCESS = "200";
    // 失败
    public static String ERROR = "500";
    // 请求未认证，跳转登录页
    public static String UNAUTHORIZED = "401";

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

    public static String successLogin(String token, String msg, SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", SUCCESS);
        map.put("msg", msg);
        map.put("token", token);
        map.put("data", user);
        return JSONObject.toJSONString(map);
    }
}
