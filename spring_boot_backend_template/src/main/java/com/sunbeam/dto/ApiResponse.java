package com.sunbeam.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private String msg;
	private LocalDateTime createdTime;
	public ApiResponse(String msg) {
		this.msg=msg;
		createdTime=LocalDateTime.now();
	}
	
}
