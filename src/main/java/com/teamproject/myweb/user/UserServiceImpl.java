package com.teamproject.myweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamproject.myweb.command.ExamineVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public int examine(ExamineVO vo) {
		return userMapper.examine(vo);
	}
	


}
