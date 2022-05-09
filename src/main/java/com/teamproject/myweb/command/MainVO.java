package com.teamproject.myweb.command;

import lombok.Data;

@Data
public class MainVO {
	
	private String review_title;
	private String review_lat;
	private String review_lng;
	private String review_category;
	private String review_realaddress;
	private Integer review_no;
	private String review_filename;
	private String review_filepath;
	private String review_uuid;
}
