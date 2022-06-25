package com.jwt.service;

import com.jwt.models.JwtResponse;
import com.jwt.models.LoginRequest;
import com.jwt.models.MessageResponse;
import com.jwt.models.SignUpRequest;

public interface AuthService {

	JwtResponse authenticateUser(LoginRequest loginRequest);
	MessageResponse registerUser(SignUpRequest signUpRequest);
}
