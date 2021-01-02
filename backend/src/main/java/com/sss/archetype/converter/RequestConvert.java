package com.sss.archetype.converter;

import com.sss.archetype.dto.RequestDTO;
import com.sss.archetype.entity.Request;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RequestConvert implements Converter<RequestDTO, Request> {
    @Override
    public Request doForward(RequestDTO dto) {
        Request entity = new Request();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(dto.getId());
        return entity;
    }

    @Override
    public RequestDTO doBackward(Request entity) {
        RequestDTO dto = new RequestDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getId());
        return dto;
    }
}