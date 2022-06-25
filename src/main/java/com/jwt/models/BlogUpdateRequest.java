package com.jwt.models;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BlogUpdateRequest {

	@NotBlank
	private String id;

	@NotBlank
	private String version;
}
