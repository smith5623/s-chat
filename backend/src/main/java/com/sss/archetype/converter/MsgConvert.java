package com.sss.archetype.converter;

import com.sss.archetype.dto.MsgDTO;
import com.sss.archetype.entity.Msg;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MsgConvert implements Converter<MsgDTO, Msg> {
    @Override
    public Msg doForward(MsgDTO dto) {
        Msg entity = new Msg();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(dto.getId());
        return entity;
    }

    @Override
    public MsgDTO doBackward(Msg entity) {
        MsgDTO dto = new MsgDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getId());
        return dto;
    }
}