## RMI
https://www.cnblogs.com/javalouvre/p/3726256.html
* 远程方法调用
* 开发步骤
     1. 编写远程服务接口，该接口必须继承 java.rmi.Remote 接口，方法必须抛出 java.rmi.RemoteException 异常；
     2. 编写远程接口实现类，该实现类必须继承 java.rmi.server.UnicastRemoteObject 类；
     3. 运行RMI编译器（rmic），创建客户端 stub 类和服务端 skeleton 类;
     4. 启动一个RMI注册表，以便驻留这些服务;
     5. 在RMI注册表中注册服务；
     6. 客户端查找远程对象，并调用远程方法；
     
 ### zookeeper 实现 
 TODO