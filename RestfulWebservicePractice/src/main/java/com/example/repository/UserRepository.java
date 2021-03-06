package com.example.repository;

import java.util.List;

import com.example.models.User;

public interface UserRepository {

	public List<User> findAll();
	
	public User findOne(int id);
	
	public User save(User user);
	public User delete(int id);
	//public User update(int id);
	
}
