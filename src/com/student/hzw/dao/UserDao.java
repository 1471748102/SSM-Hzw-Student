package com.student.hzw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.student.hzw.entity.User;

@Repository
public interface UserDao {
	public User findByUserName(String username);
	
}
