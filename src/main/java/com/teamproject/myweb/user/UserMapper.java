package com.teamproject.myweb.user;

import org.apache.ibatis.annotations.Mapper;

import com.teamproject.myweb.command.ExamineVO;

@Mapper
public interface UserMapper {
	
	public int examine(ExamineVO vo);

}
