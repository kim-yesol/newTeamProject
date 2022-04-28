package com.teamproject.myweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teamproject.myweb.command.freeBoardVO;
import com.teamproject.myweb.freeBoard.FreeBoardMapper;



@SpringBootTest
class TeamProjectApplicationTests {

	@Autowired
	private FreeBoardMapper freeBoardMapper;
	
	@Test
	void contextLoads() {
		
		for(int i = 1; i < 150; i++) {
			
			freeBoardVO vo = freeBoardVO.builder().free_title("test" + i)
								 .free_content("test" + i)
								 .free_writer("test" + i)
								 .build();
			
			freeBoardMapper.regist(vo);
		}
	}

}
