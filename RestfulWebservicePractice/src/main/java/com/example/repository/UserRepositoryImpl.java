package com.example.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.models.User;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

@Component
public class UserRepositoryImpl implements UserRepository {

	private static List<User> users=new ArrayList<User>();
	
	private int Idcount=3;
	static
	{
		users.add(new User(1,"Nilesh Sherla",new Date()));
		users.add(new User(2,"Vaishali Sherla",new Date()));
		users.add(new User(3,"Ganavee Sherla",new Date()));
	}
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public User findOne(int id) {
		// TODO Auto-generated method stub
		for(User user:users)
		{
			if(user.getId()==id)
			{
				return user;
			}
		}
		return null;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		
		if(user.getId()==null)
		{
			user.setId(++Idcount);
			
		}
		users.add(user);
		return user;
	}

	@Override
	public User delete(int id) {
		// TODO Auto-generated method stub
		
		Iterator<User> itr=users.iterator();
		while(itr.hasNext())
		{
			User user=itr.next();
			if(user.getId()==id)
			{
				itr.remove();
				return user;
				
			}
			
		}
		return null;
	}

	/*
	 * @Override //update the existing user
	 * 
	 * public User update(int id) { // TODO Auto-generated method stub
	 * 
	 * for(User user:users) { if(user.getId()==id) { //user.setName("Ajay"); return
	 * user; } } return null; }
	 */
}
