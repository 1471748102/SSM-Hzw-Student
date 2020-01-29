package com.student.hzw.service;

import org.springframework.stereotype.Service;

import com.student.hzw.entity.User;

@Service
public interface UserService {
	public User findByUserName(String username);
}
