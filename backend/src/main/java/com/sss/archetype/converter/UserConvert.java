package com.sss.archetype.converter;

import com.sss.archetype.dto.UserDTO;
import com.sss.archetype.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserConvert implements Converter<UserDTO, User> {
    @Override
    public User doForward(UserDTO dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(dto.getId());
        return entity;
    }

    @Override
    public UserDTO doBackward(User entity) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setId(entity.getId());
        return dto;
    }
}