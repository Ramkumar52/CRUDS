package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.service.LoginService;

@Controller
public class HomeController {

	@Autowired
	LoginService ls;
	
	@RequestMapping("/")
	public String initial(){
		return "index";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String getlogin(){
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView loginuser(@RequestParam("name") String name,@RequestParam("pwd") String pwd){
		boolean isvaliduser =false;
		User u=new User();
		u.setName(name);
		u.setPassword(pwd);
		isvaliduser =ls.checkUser(u);
		ModelAndView mv=new ModelAndView("index");
		if(isvaliduser==true)
		{
		mv.addObject("msg", "hello welcome");
		mv.addObject("name", u.getName());
		}
		return mv;
	}
}
