package com.sss.archetype.dao;

import com.sss.archetype.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User,java.lang.String> {
}