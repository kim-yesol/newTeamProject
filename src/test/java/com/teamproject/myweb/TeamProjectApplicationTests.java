package com.teamproject.myweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teamproject.myweb.command.MessageVO;
import com.teamproject.myweb.message.MessageMapper;

@SpringBootTest
class TeamProjectApplicationTests {
	
	@Autowired
	private MessageMapper messageMapper;

	@Test
	void contextLoads() {
		
		for(int i = 1; i < 50; i++) {
			
			MessageVO vo = MessageVO.builder().mreceiver("세션에아이디")
					.msender("세션에아이디")
					.mcontent("test" + i)
					.build();
			
			messageMapper.write(vo);
			
					
		}
	}

}
