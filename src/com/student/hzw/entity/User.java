package com.student.hzw.entity;

import org.springframework.stereotype.Component;

/*
 * 用户实体类
 */
@Component
public class User {
	private Long id;//用户id，主键、自增
	private String username;//用户名
	private String password;//密码
}
