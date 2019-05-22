package com.bugwolf.proxy.myjdk;

import java.lang.reflect.Method;

/**
 * 代理类
 */
public class Lawyer implements MyInvocationHandler {

	private Person target;
	
	public Person getInstance(Person target){
		this.target = target;
		Class clazz = target.getClass();
		return (Person) MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("我是律师: 收到tom的需求");
		Object invoke = method.invoke(this.target, args);
		System.out.println("我是律师: 打官司结束");
		
		return invoke;
	}
}
