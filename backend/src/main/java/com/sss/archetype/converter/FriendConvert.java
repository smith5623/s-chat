package com.sss.archetype.converter;

import com.sss.archetype.dto.FriendDTO;
import com.sss.archetype.entity.Friend;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FriendConvert implements Converter<FriendDTO, Friend> {
    @Override
    public Friend doForward(FriendDTO dto) {
        Friend entity = new Friend();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(dto.getId());
        return entity;
    }

    @Override
    public FriendDTO doBackward(Friend entity) {
        FriendDTO dto = new FriendDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getId());
        return dto;
    }
}