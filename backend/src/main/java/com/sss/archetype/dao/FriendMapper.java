package com.sss.archetype.dao;

import com.sss.archetype.entity.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FriendMapper extends BaseMapper<Friend,java.lang.String> {
}