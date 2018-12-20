package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;


@Controller
public class HomeController {
	
	@Autowired
	UserRepo uRepo;
	
	HomeController()
	{
		System.out.println("Home controller created");
	}
	
	@RequestMapping("/")
	public String home()
	{
		return "home";
	}
	
	@RequestMapping("/addUserDrp")
	public String addUser(User user)
	{
		uRepo.save(user);
		return "home";
	}
	
	@RequestMapping("/getUsers1")
	public ModelAndView getUsers1(@RequestParam int id)
	{
		
		System.out.println("in getUsers1 controller");
		ModelAndView mv = new ModelAndView("showUser");
		User users = uRepo.findById(id).orElse(new User());
		mv.addObject(users);
		return mv;
	}

}
