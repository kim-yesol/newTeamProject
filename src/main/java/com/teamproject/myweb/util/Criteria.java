package com.teamproject.myweb.util;

import lombok.Data;

@Data
public class Criteria {

	private int page;
	private int amount;
	private int leftpage;
	
	//검색키워드

	private String searchKey;
	private String searchoption;
	
	public Criteria() {
		this(1, 10);
	}
	
		
	public Criteria(int page, int amount) {
		this.page = page;
		this.amount = amount;
		
		
	}


}