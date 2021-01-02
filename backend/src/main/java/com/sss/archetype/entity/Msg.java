package com.sss.archetype.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class Msg extends BaseEntity<java.lang.String> {
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
    private Date createTime;

}