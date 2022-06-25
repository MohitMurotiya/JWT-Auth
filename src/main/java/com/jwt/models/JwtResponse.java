package com.jwt.models;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
	
	private String id;
	private String username;
	private String email;
	private List<String> roles;
	private String type = "Bearer";
	private String token;
	
	public JwtResponse(String accessToken, String id, String username, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
