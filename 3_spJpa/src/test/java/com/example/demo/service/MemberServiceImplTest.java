package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.impl.MemberServiceImpl;

@SpringBootTest
public class MemberServiceImplTest 
{
	@Autowired
	MemberServiceImpl msi;
	
	@Test
	public void updateMemberTest()
	{
		msi.updateMember(15,"myuyanlin","台北");
		System.out.println("update success");
	}
}