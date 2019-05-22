package com.bugwolf.proxy.myjdk;

public class MainTest {

	public static void main(String[] args) {
		Person tom = new Tom();
//		tom.court();
		Lawyer lawyer = new Lawyer();
		Person instance = lawyer.getInstance(tom);
		instance.court();
	}
	
}
