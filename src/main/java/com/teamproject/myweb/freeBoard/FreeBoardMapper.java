package com.teamproject.myweb.freeBoard;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.teamproject.myweb.command.freeBoardVO;
import com.teamproject.myweb.util.Criteria;
import com.teamproject.myweb.util.PageVO;

@Mapper
public interface FreeBoardMapper {

	public ArrayList<freeBoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public int regist(freeBoardVO vo);
	public freeBoardVO getDetail(int free_bno);
	public int update(freeBoardVO vo);
	public int delete(int free_bno);
}
