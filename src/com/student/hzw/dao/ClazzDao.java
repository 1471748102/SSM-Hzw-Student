package com.student.hzw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.student.hzw.entity.Clazz;
/***
 * °à¼¶dao
 * @author Hzw
 *
 */
@Repository
public interface ClazzDao {
	public int add(Clazz clazz);
	public int edit(Clazz clazz);
	public int delete(String ids);
	public List<Clazz> findList(Map<String,Object> queryMap);
	public List<Clazz> findAll();
	public int getTotal(Map<String,Object> queryMap);
}