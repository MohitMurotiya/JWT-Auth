package com.jwt.service;

import java.util.List;

import com.jwt.exception.BlogNotFoundException;
import com.jwt.models.Blog;
import com.jwt.models.BlogUpdateRequest;
import com.jwt.models.MessageResponse;

public interface BlogService{

	List<Blog> findAll();
	Blog save(Blog blog);
	Blog updateVersion(BlogUpdateRequest req) throws BlogNotFoundException;
	List<Blog> findByCreatedBy(String username);
	MessageResponse delete(String id) throws BlogNotFoundException;
}
