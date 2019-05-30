# proxy
代理模式

静态代理和动态代理的区别:  
1.静态代理只能手动完成代理操作，如果被代理类新增方法，代理类需要同步新增，违背了开闭原则  
2.动态代理采用了运行时动态生成代码，重新生成一个代理类来实现，不需要原先的代理类同步扩展，遵循开闭原则  
  
Jdk代理和cglib代理区别:  
1.Jdk代理是实现了被代理类对象的接口，cglib是继承了被代理类对象  
2.jdk代理和cglib代理都是重新生成了class字节码，jdk代理是直接写class字节码，cglib是使用了ASM框架写字节码，cglib更复杂，效率低一些  
3.jdk调用代理类是采用反射机制，cglib是使用fastclass机制直接调用，cglib调用效率高一些  
 
