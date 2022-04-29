package com.teamproject.myweb.comment;

import java.util.ArrayList;

import com.teamproject.myweb.command.CommentVO;

public interface CommentService {
	
	public ArrayList<CommentVO> commentList(int free_bno);

}
