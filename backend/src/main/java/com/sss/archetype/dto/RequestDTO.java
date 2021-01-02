package com.sss.archetype.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sss.archetype.dto.BaseDTO;

@Data
public class RequestDTO extends BaseDTO<String> {
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private Date requestDateTime;

}