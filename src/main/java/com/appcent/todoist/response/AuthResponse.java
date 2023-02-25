package com.appcent.todoist.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String message;
	private Long userId;
	private String accessToken;
	private String refreshToken;
	private RestErrorResponse restErrorResponse;

}