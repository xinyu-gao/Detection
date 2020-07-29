package com.node.detection.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author xinyu
 */
@Data
public class HttpResult implements Serializable {

    /**
     * HTTP 状态码
     */
    private int status;
    /**
     * 结果消息
     */
    private String message;
    /**
     * 结果数据
     */
    private Object data;

    /**
     * 发生错误，状态码400，显示错误的相关信息，例如：
     * {
     * "status": 400,
     * "message": "username is already exist",
     * "data": null
     * }
     *
     * @param message 错误信息
     * @return 整合后的数据结果
     */
    public static HttpResult error(String message) {
        return new HttpResult()
                .setStatus(400)
                .setMessage(message);
    }

    /**
     * 发生错误，状态码自定义，显示错误的相关信息，例如：
     * {
     * "status": 400,
     * "message": "username is already exist",
     * "data": null
     * }
     *
     * @param status  HTTP 状态码
     * @param message 错误信息
     * @return 整合后的数据结果
     */
    public static HttpResult error(int status, String message) {
        return new HttpResult()
                .setStatus(status)
                .setMessage(message);
    }

    /**
     * 成功处理了请求， message 默认为 success, 例如：
     * {
     * "status": 200,
     * "message": "success",
     * "data": {
     * ...
     * }
     * }
     *
     * @param data 返回的数据data
     * @return 整合后的数据结果
     */
    public static HttpResult ok(Object data) {
        return new HttpResult()
                .setStatus(200)
                .setMessage("success")
                .setData(data);
    }

    public static void responseOk(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(JSONObject.toJSONString(ok(data)));
    }

    public static void responseError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(JSONObject.toJSONString(error(message)));
    }

    public HttpResult setStatus(int status) {
        this.status = status;
        return this;
    }


    public HttpResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public HttpResult setData(Object data) {
        this.data = data;
        return this;
    }

}
