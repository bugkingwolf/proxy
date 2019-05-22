package com.bugwolf.proxy.cglib;

import com.bugwolf.proxy.myjdk.Person;

public class Tom implements Person {

	@Override
	public void court() {
		System.out.println("tom要打官司");
	}
	
}
