package com.sss.archetype.dao;

import com.sss.archetype.entity.Request;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RequestMapper extends BaseMapper<Request,java.lang.String> {
}