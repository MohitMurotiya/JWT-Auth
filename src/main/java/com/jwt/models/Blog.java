package com.jwt.models;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder.Default;
import lombok.Data;

@Data
@Document(collection = "blogs")
public class Blog {

	@Id
	private String id;

	@NotBlank
	private String title;

	private String description;

	@NotBlank
	private String content;

	private String version;
	private String createdBy;
	private LocalDate createdDate;
}
