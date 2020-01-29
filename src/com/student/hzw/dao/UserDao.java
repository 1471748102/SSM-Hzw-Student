package com.student.hzw.dao;

import org.springframework.stereotype.Repository;

import com.student.hzw.entity.User;

@Repository
public interface UserDao {
	public User findByUserName(String username);
}
