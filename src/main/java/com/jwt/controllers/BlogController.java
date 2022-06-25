package com.jwt.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.exception.BlogNotFoundException;
import com.jwt.models.Blog;
import com.jwt.models.BlogUpdateRequest;
import com.jwt.models.MessageResponse;
import com.jwt.service.BlogService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/jwt/blog")
public class BlogController {
	
	@Autowired
	BlogService service;

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
	public ResponseEntity<List<Blog>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
	public ResponseEntity<Blog> save(@Valid @RequestBody Blog blog) {
		return new ResponseEntity<>(service.save(blog), HttpStatus.CREATED);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<?> update(@Valid @RequestBody BlogUpdateRequest req) {
		try {
			return new ResponseEntity<>(service.updateVersion(req), HttpStatus.CREATED);
		} catch (BlogNotFoundException e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Blog with request Id not found"));
		}
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
		} catch (BlogNotFoundException e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Blog with request Id not found"));
		}
	}
}
