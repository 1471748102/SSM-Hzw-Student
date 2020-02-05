package com.student.hzw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.student.hzw.entity.User;

@Repository
public interface UserDao {
	public User findByUserName(String username);
	public int add(User user);
	public int edit(User user);
	public List<User> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
}
