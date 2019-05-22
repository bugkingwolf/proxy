package com.bugwolf.proxy.cglib;

public class MainTest {

	public static void main(String[] args) {

		Lawyer lawyer = new Lawyer();
		Tom instance = (Tom) lawyer.getInstance(Tom.class);
		instance.court();

	}
	
}
