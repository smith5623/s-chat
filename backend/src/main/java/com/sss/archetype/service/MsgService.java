package com.sss.archetype.service;

import com.sss.archetype.converter.Converter;
import com.sss.archetype.dao.MsgMapper;
import com.sss.archetype.dto.MsgDTO;
import com.sss.archetype.entity.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MsgService extends BaseService<MsgDTO, Msg, java.lang.String,MsgMapper> {
    @Autowired
    private Converter<MsgDTO,Msg> converter;

    @Override
    public Msg doForward(MsgDTO dto) {
        return converter.doForward(dto);
    }

    @Override
    public MsgDTO doBackward(Msg entity) {
        return converter.doBackward(entity);
    }
}
