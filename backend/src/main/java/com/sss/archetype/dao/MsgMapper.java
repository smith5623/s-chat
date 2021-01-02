package com.sss.archetype.dao;

import com.sss.archetype.entity.Msg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MsgMapper extends BaseMapper<Msg,java.lang.String> {
}