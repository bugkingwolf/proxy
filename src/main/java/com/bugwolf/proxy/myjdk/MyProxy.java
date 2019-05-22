package com.bugwolf.proxy.myjdk;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MyProxy {

	private static final String ln = "\r\n";

	public static Object newProxyInstance(MyClassLoader loader, Class<?>[] interfaces, MyInvocationHandler h) {
		File file = null;
		try {
			// 1.动态生成源代码.java文件
			String src = generateSrc(interfaces);
			
			// 2.java文件输出到磁盘
			String path = MyProxy.class.getResource("").getPath();
			file = new File(path + "$Proxy00.java");
			FileWriter fw = new FileWriter(file);
			fw.write(src);
			fw.flush();
			fw.close();
			
			// 3.java文件编译成class文件
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(null, null, null);
			Iterable<? extends JavaFileObject> iterable = standardFileManager.getJavaFileObjects(file);
			CompilationTask task = compiler.getTask(null, standardFileManager, null, null, null, iterable);
			task.call();
			standardFileManager.close();
			
			// 4.class文件加载到JVM
			Class proxyClass = loader.findClass("$Proxy00");
			Constructor constructor = proxyClass.getConstructor(MyInvocationHandler.class);
			
			// 5.返回字节码重组后新的代理对象
			return constructor.newInstance(h);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(file.exists()){
				file.delete();
			}
		}
		return h;
	}

	// 生成源代码.java文件(待优化,目前写死)
	private static String generateSrc(Class<?>[] interfaces) {
		StringBuffer sb = new StringBuffer();
		sb.append("package com.bugwolf.proxy.myjdk;" + ln);
		sb.append("import com.bugwolf.proxy.myjdk.Person;" + ln);
		sb.append("import java.lang.reflect.Method;" + ln);
		sb.append("public class $Proxy00 implements " + interfaces[0].getName() + "{ " + ln);

		sb.append("MyInvocationHandler h;" + ln);

		sb.append("public $Proxy00 (MyInvocationHandler h){" + ln);
		sb.append("this.h = h;" + ln);
		sb.append("}" + ln);

		for (Method m : interfaces[0].getMethods()) {
			sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(){" + ln);
			sb.append("try{" + ln);
			sb.append("Method m = Class.forName(\"com.bugwolf.proxy.myjdk.Tom\").getMethod(\"court\",new Class[0]);"
					+ ln);
			sb.append("this.h.invoke(this,m,null);" + ln);
			sb.append("}catch(Throwable e){" + ln);
			sb.append("e.printStackTrace();" + ln);
			sb.append("}");
			sb.append("}" + ln);
		}
		sb.append("}" + ln);
		return sb.toString();
	}
}
