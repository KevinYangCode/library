package xyz.jianzha.library.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应数据格式的处理
 *
 * @author Y_Kevin
 * @date 2020-01-05 00:39
 */
@Data
public class ResponseData implements Serializable {

    /**
     * 成功的状态码，默认：0
     */
    private Integer code;

    /**
     * 状态信息的字段名称，默认：msg
     */
    private String msg = "";

    /**
     * 数据总数的字段名称，默认：count
     */
    private long count;

    /**
     * 数据列表的字段名称，默认：data
     */
    private Object data;

    public ResponseData(int code) {
        this.code = code;
    }

    /**
     * 成功，默认true
     *
     * @param object 存放数据
     * @param msg    消息
     * @return json
     */
    public static ResponseData success(Object object, String msg) {
        ResponseData result = new ResponseData(0);
        result.data = object;
        result.msg = msg;
        return result;
    }

    /**
     * 成功，默认true
     *
     * @param object 存放数据
     * @param count  数据总数
     * @param msg    消息
     * @return json
     */
    public static ResponseData success(Object object, long count, String msg) {
        ResponseData result = new ResponseData(0);
        result.data = object;
        result.count = count;
        result.msg = msg;
        return result;
    }

    /**
     * 成功，默认true
     *
     * @param object 存放数据
     * @return json
     */
    public static ResponseData success(Object object) {
        ResponseData result = new ResponseData(0);
        result.data = object;
        return result;
    }

    /**
     * 成功，默认true
     *
     * @return json
     */
    public static ResponseData success() {
        return new ResponseData(0);
    }

    /**
     * 失败，默认false
     *
     * @param msg 消息
     * @return json
     */
    public static ResponseData fail(String msg) {
        ResponseData result = new ResponseData(-1);
        result.msg = msg;
        return result;
    }
}
