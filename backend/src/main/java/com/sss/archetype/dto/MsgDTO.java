package com.sss.archetype.dto;

import lombok.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class MsgDTO extends BaseDTO<java.lang.String> {
    /**
     * 
     */
    private String sendUserId;

    /**
     * 
     */
    private String acceptUserId;

    /**
     * 
     */
    private String msg;

    /**
     * 消息是否签收状态
1：签收
0：未签收

     */
    private Integer signFlag;

    /**
     * 发送请求的事件
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private Date createTime;

}