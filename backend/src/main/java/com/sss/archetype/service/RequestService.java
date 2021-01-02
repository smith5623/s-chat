package com.sss.archetype.service;

import com.sss.archetype.converter.Converter;
import com.sss.archetype.dao.RequestMapper;
import com.sss.archetype.dto.RequestDTO;
import com.sss.archetype.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestService extends BaseService<RequestDTO, Request, java.lang.String,RequestMapper> {
    @Autowired
    private Converter<RequestDTO,Request> converter;

    @Override
    public Request doForward(RequestDTO dto) {
        return converter.doForward(dto);
    }

    @Override
    public RequestDTO doBackward(Request entity) {
        return converter.doBackward(entity);
    }
}
