package com.jwt.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jwt.exception.BlogNotFoundException;
import com.jwt.models.Blog;
import com.jwt.models.BlogUpdateRequest;
import com.jwt.models.MessageResponse;
import com.jwt.repository.BlogRespository;
import com.jwt.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogRespository blogRepository;

	public List<Blog> findAll() {
		return blogRepository.findAll();
	}

	public Blog save(Blog blog) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		blog.setCreatedBy(userDetails.getUsername());
		blog.setCreatedDate(LocalDate.now());
		return blogRepository.save(blog);
	}

	public Blog updateVersion(BlogUpdateRequest req) throws BlogNotFoundException {
		Blog blog = blogRepository.findById(req.getId())
				.orElseThrow(() -> new BlogNotFoundException("Blog Not Found with id: " + req.getId()));

		blog.setVersion(req.getVersion());
		return blogRepository.save(blog);
	}

	public List<Blog> findByCreatedBy(String username) {
		return blogRepository.findByCreatedBy(username);
	}

	@Override
	public MessageResponse delete(String id) throws BlogNotFoundException {
		blogRepository.findById(id)
				.orElseThrow(() -> new BlogNotFoundException("Blog Not Found with id: " + id));

		blogRepository.deleteById(id);
		return new MessageResponse("Blog deleted successfully");
	}

}
