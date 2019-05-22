package com.bugwolf.proxy.myjdk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

	private File baseFile;

	public MyClassLoader() {
		String path = MyClassLoader.class.getResource("").getPath();
		this.baseFile = new File(path);
	}

	public Class findClass(String name) {
		File classFile = null;
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			String className = MyClassLoader.class.getPackage().getName() + "." + name;
			if (baseFile != null) {
				classFile = new File(baseFile, name.replace("\\", "/") + ".class");
				if (classFile.exists()) {
					in = new FileInputStream(classFile);
					out = new ByteArrayOutputStream();
					byte[] buff = new byte[1024];
					int len;
					while ((len = in.read(buff)) != -1) {
						out.write(buff, 0, len);
					}
				}
				return defineClass(className, out.toByteArray(), 0, out.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (classFile.exists()) {
				classFile.delete();
			}
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

}
