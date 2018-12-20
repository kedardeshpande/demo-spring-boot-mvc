package com.example.demo.rest;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.example.demo.dao.PostRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.model.Laptop;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class HomeRestController {

	@Autowired
	Laptop l;
	
	@Autowired
	UserRepo uRepo;
	
	@Autowired
	PostRepo postRepo;
	
	HomeRestController()
	{
		System.out.println("Home REST controller created");
	}
	
	@RequestMapping(value="/getLaptop")
	public Laptop getLaptop()
	{
		l.setLaptopName("HP");
		return l;
	}
	
	@PostMapping(path="/addUser",consumes="application/json")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user)
	{
		uRepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUser_id()).toUri();
		return ResponseEntity.created(location).build();
	}
	@RequestMapping("/getUsers")
	public  MappingJacksonValue getUsers()
	{
		System.out.println("in getUsers rest");
		List<User> user = uRepo.findAll();
		SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("username","mobile_number","user_id");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserBeanFilter1", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		
		mapping.setFilters(filters);
		return mapping;
	}
	
	@DeleteMapping("/deleteUser")
	public void deleteUser(@PathVariable int uid)
	{
		Optional<User> data = uRepo.findById(uid);
		User user = data.get();
		uRepo.delete(user);
		//Object links = ControllerLinkBuilder.linkTo())
		//Resource resource = new Resource<User>(user);
		
	}
	
	@PutMapping("/updateUser/{uid}/{mobileNumber}")
	public void updateUser(@PathVariable int uid, @PathVariable long mobileNumber)
	{
		Optional<User> data = uRepo.findById(uid);
		User user = data.get();
		user.setMobile_number(mobileNumber);
		uRepo.save(user);
	}
	
	@PutMapping("/updateUserFromRequest")
	public void updateUserFromRequest(@RequestParam(name="uid") int uid, @RequestParam(name="mobileNumber") long mobileNumber)
	{
		Optional<User> data = uRepo.findById(uid);
		User user = data.get();
		user.setMobile_number(mobileNumber);
		uRepo.save(user);
	}
	
	@RequestMapping("/getUserById/{id}")
	public  User getUserById(@PathVariable int id)
	{
		System.out.println("in getUsers rest");
		User user =(User) uRepo.findById(id).orElse(new User());
		if (user == null)
		{
			throw new UserNotFoundException("User with id "+id+" not found");
		}
		return user;
	}
	
	@GetMapping(value="/users/{uid}/posts")
	public List<Post> getPostByUser(@PathVariable int uid)
	{
		System.out.println("in getUsers rest");
		  Optional<User> user = uRepo.findById(uid);
		if (!user.isPresent())
		{
			throw new UserNotFoundException("User with id "+uid+" not found");
		}
		return user.get().getPosts();
		
	}
	
	@PostMapping(value="/users/{id}/posts")
	public void createPost(@PathVariable int id, @RequestBody Post post)
	{
		 Optional<User> users = uRepo.findById(id);
			if (!users.isPresent())
			{
				throw new UserNotFoundException("User with id "+id+" not found");
			}
			User user = users.get();	
			post.setUser(user);
			postRepo.save(post);
	}
}
