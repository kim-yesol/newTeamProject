package com.teamproject.myweb.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.text.DateFormatter;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teamproject.myweb.command.MainVO;
import com.teamproject.myweb.command.Review_uploadVO;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.command.reviewVO;
import com.teamproject.myweb.util.review_Criteria;



@Service("boardService")
public class boardServiceImpl implements boardService{
	
	@Value("${project.upload.path}")
	private String uploadpath;
	
	@Autowired
	boardMapper boardmapper;
	
	@Transactional
	@Override
	public int reviewRegist(reviewVO vo, List<MultipartFile> list) {
		
		int result = boardmapper.reviewRegist(vo);
		
		for(MultipartFile f : list) {
			
			String originname = f.getOriginalFilename();
			
			String filename = originname.substring( originname.lastIndexOf("\\")+1 );
			
			String filepath = filemaker(); 
			
			String uuid = UUID.randomUUID().toString();
			
			String savename = uploadpath + "\\" + filepath + "\\" + uuid + "_" + filename;
			
			File file = new File(savename);
			
			try {
				f.transferTo(file);
			}  catch (IOException e) {
				e.printStackTrace();
			}
			
			Review_uploadVO	uploadvo = Review_uploadVO.builder().review_filename(filename)
															 .review_filepath(filepath)
															 .review_uuid(uuid)
															 .review_writer(vo.getReview_writer())
															 .build();
			boardmapper.reviewFileRegist(uploadvo);
			
		}
			return result;			
		
	}
	
	public String filemaker() {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		String date = LocalDateTime.now().format(format);
		
		File file = new File(uploadpath + "\\" + date);
		if(file.exists() == false) {
			file.mkdir();
		}
		return date;
	}

	@Override
	public ArrayList<reviewVO> reviewList(review_Criteria cri) {
		return boardmapper.reviewList(cri);
	}

	@Override
	public int reviewTotal(review_Criteria cri) {
		return boardmapper.reviewTotal(cri);
	}

	@Override
	public reviewVO getDetail(int review_no) {
		return boardmapper.getDetail(review_no);
	}

	@Override
	public int updateReview(reviewVO vo) {
		return boardmapper.updateReview(vo);
	}

	@Override
	public int deleteReview(int review_no) {
		return boardmapper.deleteReview(review_no);
	}

	@Override
	public ArrayList<MainVO> getLocation() {
		return boardmapper.getLocation();
	}

	@Override
	public UserVO userCheckes(UserCheckVO vo) {
		return boardmapper.userCheckes(vo);
	}

	@Override
	public ArrayList<Review_uploadVO> getImg(int review_no) {
		return boardmapper.getImg(review_no);
	}
	
	
	
	
}
