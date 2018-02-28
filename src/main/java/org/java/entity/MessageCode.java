package org.java.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by zhengjun.jing on 7/13/2017.
 */
public enum MessageCode {

	/**============Marker管理 Message Code=====================*/
    /** 前缀 0012*/
    COMMON_SUCCESS("0000_0","执行成功"),
    COMMON_FAILURE("0000_1", "执行失败"),
    COMMON_NO_AUTHORIZED("0000_2","没有权限执行"),
    COMMON_NO_DATA("0000_3","查询不到对应数据"),
    COMMON_PARAMETER_ERROR("0000_4","参数错误"),
    COMMON_UNKNOWN_ERROR("0000_11","未知异常"),
    COMMON_DB_ERROR("0000_12","数据库操作异常"),
    COMMON_API_ERROR("0000_13","操作异常");

    //Message 编码
    private String code;
    //Message 描叙
    private String message;

    MessageCode(String code,String message){
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }


    @JsonCreator
    public static MessageCode getStatusCode(String status) {
        for (MessageCode unit : MessageCode.values()) {
            if (unit.getCode().equals(status)) {
                return unit;
            }
        }

        return null;
    }


    @Override
    public String toString() {
        return "{code:'" + code + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }
}
