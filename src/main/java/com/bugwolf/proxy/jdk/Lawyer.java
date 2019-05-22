package com.bugwolf.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Lawyer implements InvocationHandler {

	private Person target;
	
	public Person getInstance(Person target){
		this.target = target;
		Class clazz = target.getClass();
		return (Person) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("我是律师: 收到tom的需求");
		Object invoke = method.invoke(this.target, args);
		System.out.println("我是律师: 打官司结束");
		System.out.println(invoke);
		return invoke;
	}
}
