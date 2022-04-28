package com.teamproject.myweb.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.teamproject.myweb.command.MainVO;
import com.teamproject.myweb.command.Review_uploadVO;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.command.reviewVO;
import com.teamproject.myweb.util.review_Criteria;



public interface boardService {
	
	public int reviewRegist(reviewVO vo, List<MultipartFile> list);
	
	public ArrayList<reviewVO> reviewList(review_Criteria cri);
	
	public int reviewTotal(review_Criteria cri);
	
	public reviewVO getDetail(int review_no);
	
	public int updateReview(reviewVO vo);
	
	public int deleteReview(int review_no);
	
	public ArrayList<MainVO> getLocation();
	
	public UserVO userCheckes(UserCheckVO vo);
	
	public ArrayList<Review_uploadVO> getImg(int review_no);
}
