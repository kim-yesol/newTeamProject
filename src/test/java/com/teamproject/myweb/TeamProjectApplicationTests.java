package com.teamproject.myweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teamproject.myweb.Service.boardService;
import com.teamproject.myweb.command.reviewVO;

@SpringBootTest
class TeamProjectApplicationTests {
	
	@Autowired
	boardService boardservice;
	
	@Test
	void contextLoads() {
		for( int i = 0 ; i < 200 ; i++) {
		reviewVO vo =reviewVO.builder().review_category(""+i)
							  .review_content("내용" + i)
							  .review_lat(""+i)
							  .review_lng(""+i)
							  .review_title("제목"+i)
							  .review_writer("저자" + i)
							  .build();
		}
	}

}
