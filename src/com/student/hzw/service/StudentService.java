package com.student.hzw.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.student.hzw.entity.Student;

/***
 * Service²ã
 * @author Hzw
 *
 */
@Service
public interface StudentService {
	public Student findByUserName(String username);
	public int add(Student student);
	public int edit(Student student);
	public int delete(String ids);
	public List<Student> findList(Map<String,Object> queryMap);
	public List<Student> findAll();
	public int getTotal(Map<String,Object> queryMap);
}
