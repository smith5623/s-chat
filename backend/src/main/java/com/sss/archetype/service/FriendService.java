package com.sss.archetype.service;

import com.sss.archetype.converter.Converter;
import com.sss.archetype.dao.FriendMapper;
import com.sss.archetype.dto.FriendDTO;
import com.sss.archetype.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService extends BaseService<FriendDTO, Friend, java.lang.String,FriendMapper> {
    @Autowired
    private Converter<FriendDTO,Friend> converter;

    @Override
    public Friend doForward(FriendDTO dto) {
        return converter.doForward(dto);
    }

    @Override
    public FriendDTO doBackward(Friend entity) {
        return converter.doBackward(entity);
    }
}
