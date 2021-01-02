package com.sss.archetype.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class Request extends BaseEntity<java.lang.String> {
    /**
     * 
     */
    private String sendUserId;

    /**
     * 
     */
    private String acceptUserId;

    /**
     * 发送请求的事件
     */
    private Date requestDateTime;

}