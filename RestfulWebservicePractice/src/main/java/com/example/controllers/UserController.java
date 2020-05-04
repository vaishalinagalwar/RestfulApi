package com.example.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.exceptionhandling.UserNotFoundException;
import com.example.models.User;
import com.example.repository.UserRepositoryImpl;

@RestController
public class UserController {

	@Autowired
	
   private UserRepositoryImpl service;
	
	//Used to Retrive all Users
	@GetMapping("/users")
	
	public List<User> retrieveAllUsers()
	{
		return service.findAll();
	}
	
	//Used to Retrieve One User
		@GetMapping("/users/{id}")
		
		public Resource<User> retrieveOneUser(@PathVariable int id)
		{
			
			User user= service.findOne(id);
			if(user==null)
			{
				throw new UserNotFoundException("Id "+ id+ " is not found");
			}
			
			Resource<User> resource=new Resource(user);
			ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveAllUsers());
			resource.add(linkTo.withRel("all-users"));
			
			return resource;
		}
		
	//Used to Save USer
		@PostMapping("/users")
				
		public ResponseEntity<Object> savedUser(@Valid @RequestBody User user)
		{
			User savedUser=service.save(user);
			
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(savedUser.getId()).toUri();
			
			return ResponseEntity.created(location).build();
					
		
		}
	
	//Used to Delete One User
		@DeleteMapping("/users/{id}")
				
		public void deleteById(@PathVariable int id)
		{
			User user=service.delete(id);		
		
			if(user==null)
			{
				throw new UserNotFoundException("Id "+id+ " is not found");
			}
	}
	/*
	 * //Used to update One User
	 * 
	 * @PutMapping("/users/{id}")
	 * 
	 * public void updateById(@PathVariable int id) { User user=service.update(id);
	 * 
	 * if(user==null) { throw new UserNotFoundException("Id "+id+ " is not found");
	 * } }
	 */
}
