package com.student.hzw.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.student.hzw.entity.User;

@Service
public interface UserService {
	public User findByUserName(String username);
	public int add(User user);
	public List<User> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
}
