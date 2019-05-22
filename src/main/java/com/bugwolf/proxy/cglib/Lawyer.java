package com.bugwolf.proxy.cglib;

import com.bugwolf.proxy.myjdk.MyClassLoader;
import com.bugwolf.proxy.myjdk.MyInvocationHandler;
import com.bugwolf.proxy.myjdk.MyProxy;
import com.bugwolf.proxy.myjdk.Person;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理类
 */
public class Lawyer implements MethodInterceptor {
	
	public Object getInstance(Class clazz){

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}


	@Override
	public Object intercept(Object object, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
		System.out.println("我是律师: 收到tom的需求");
		Object o = proxy.invokeSuper(object, objects);
		System.out.println("我是律师: 打官司结束");
		return o;
	}
}
