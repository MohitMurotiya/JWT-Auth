package com.jwt.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jwt.models.Blog;

public interface BlogRespository extends MongoRepository<Blog, String> {

	List<Blog> findByCreatedBy(String username);
}