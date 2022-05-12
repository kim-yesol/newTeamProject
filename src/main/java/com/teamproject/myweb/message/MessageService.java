package com.teamproject.myweb.message;

import java.util.ArrayList;

import com.teamproject.myweb.command.MessageVO;
import com.teamproject.myweb.util.Criteria;

public interface MessageService {
	
	public int write(MessageVO vo); //메시지 보내기
	public int reply(MessageVO vo); //답장하기
	public ArrayList<MessageVO> getList(String sender, Criteria cri); //보낸메시지 목록
	public ArrayList<MessageVO> re_getList(String sender, Criteria cri); //받은
	public int getSendTotal(String sender);
	public int getReceiveTotal(String sender);
	public MessageVO getReceiveDetail(int mno);//받은메시지 상세보기
	public MessageVO getSendDetail(int mno);//보낸메시지 상세보기
	public int delete(int mno); //삭제
	
	public int update(int mno);
	
}
