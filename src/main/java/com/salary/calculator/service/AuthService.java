package com.salary.calculator.service;

import com.salary.calculator.dto.AuthResponse;
import com.salary.calculator.dto.LoginRequest;
import com.salary.calculator.dto.RegisterRequest;
import com.salary.calculator.entity.User;
import com.salary.calculator.mapper.UserMapper;
import com.salary.calculator.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    public AuthResponse register(RegisterRequest request) {
        if (userMapper.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        if (userMapper.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        
        userMapper.insert(user);
        
        String token = jwtUtil.generateToken(user.getUsername(), user.getId());
        return new AuthResponse(token, user.getUsername(), user.getEmail(), user.getRole());
    }
    
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        User user = userMapper.findByUsername(request.getUsername());
        String token = jwtUtil.generateToken(user.getUsername(), user.getId());
        
        return new AuthResponse(token, user.getUsername(), user.getEmail(), user.getRole());
    }
}
