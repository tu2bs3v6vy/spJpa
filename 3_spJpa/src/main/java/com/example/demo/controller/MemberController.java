package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Member;
import com.example.demo.service.impl.MemberServiceImpl;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("member")
public class MemberController 
{
	@Autowired
	MemberServiceImpl msi;
	
	/*****API(int,String,List)*****/
	@GetMapping("hello")
	public String hello()
	{
		return "Hello";
	}
	/*****後台管理頁面(ModelAndView)*****/
	
	@PostMapping("Login")
	public ModelAndView login(String username,String password,Model model,HttpSession session)
	{
		Member m=msi.LoginMember(username,password);
		if(m!=null)
		{
			//model.addAttribute("M",m);
			session.setAttribute("M",m);
			return new ModelAndView("loginSuccess");
		}
		else
		{
			return new ModelAndView("loginError");
		}
	}

	@PostMapping("add")
	public ModelAndView add(String name,String username,String password,String address,String phone)
	{
		boolean b=msi.selectUsername(username);
		if(b)
		{
			return new ModelAndView("addMemberError");
		}
		else
		{
			Member m=new Member(name,username,password,address,phone);
			msi.addMember(m);
			return new ModelAndView("addMemberSuccess");
		}
	}
	
	@GetMapping("query")
	public ModelAndView query(HttpSession session)
	{
		List<Member> l=msi.selectAllMember();
		session.setAttribute("LM",l);
		return new ModelAndView("query");
	}
	
	@PostMapping("updateMember")
	public ModelAndView updateMember(String id,String name,String address)
	{
		/*
		 * 1.接收-->id轉int
		 * 2.執行updateMember
		 * 3.切換loginSuccess
		 */
		int ID=Integer.parseInt(id);
		msi.updateMember(ID,name,address);
		return new ModelAndView("loginSuccess");
	}
	
	@PostMapping("deleteMember")
	public ModelAndView deleteMember(String id)
	{
		int ID=Integer.parseInt(id);
		msi.deleteMember(ID);
		return new ModelAndView("loginSuccess");	
	}
	
	/**切換頁面**/
	@GetMapping("update")
	public ModelAndView update()
	{	
		return new ModelAndView("update");
	}
	
	@GetMapping("delete")
	public ModelAndView delete()
	{	
		return new ModelAndView("delete");
	}
	
	@GetMapping("loginSuccess")
	public ModelAndView loginSuccess()
	{	
		return new ModelAndView("loginSuccess");
	}
}