package com.appcent.todoist.security.dto;

import lombok.Data;

@Data
public class RefreshRequest {

	Long userId;
	String refreshToken;
}