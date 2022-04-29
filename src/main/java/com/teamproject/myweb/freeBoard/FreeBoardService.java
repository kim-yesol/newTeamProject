package com.teamproject.myweb.freeBoard;

import java.util.ArrayList;

import com.teamproject.myweb.command.freeBoardVO;
import com.teamproject.myweb.util.Criteria;
import com.teamproject.myweb.util.PageVO;

public interface FreeBoardService {

	public ArrayList<freeBoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public int regist(freeBoardVO vo);
	public freeBoardVO getDetail(int free_bno);
	public int update(freeBoardVO vo);
	public int delete(int free_bno);
}
