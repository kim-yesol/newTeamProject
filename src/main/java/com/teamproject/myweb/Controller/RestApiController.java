package com.teamproject.myweb.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamproject.myweb.Service.boardService;
import com.teamproject.myweb.command.MainVO;

@RestController
public class RestApiController {
	
	@Value("${project.upload.path}")
	private String uploadpath;
	
	@Autowired
	boardService boardservice;
	
	@GetMapping("/getLocation")
	public ArrayList<MainVO> getLocation() {
			
		ArrayList<MainVO> list= boardservice.getLocation();
		System.out.println(list.toString());
		return list;
	}
	
	@GetMapping("/display")
	public byte[] display(@RequestParam("review_filename") String filename,
						  @RequestParam("review_uuid") String uuid,
						  @RequestParam("review_filepath") String filepath) {
		
		//System.out.println(filepath + "\\" + uuid + "_" + filename);
		
		File file = new File(uploadpath + "\\" + filepath + "\\" + uuid + "_" + filename);
		
		byte[] result = null;
		try {
			result = FileCopyUtils.copyToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
