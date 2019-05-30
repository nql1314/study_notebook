# Netty实战

用于创建高性能网络应用程序的高级框架

## 简介
* 统一的API,易于使用,性能,健壮性,安全性,社区驱动
* 异步非阻塞，选择器使得使用较少的线程监听事件
* 核心组件
    * Channel：nio的基本构造，数据载体
    * 回调：使用回调处理事件
    * Future：异步操作的结果的占位符，netty提供了自己的实现ChannelFuture，注册ChannelFutureListene异步通知
    * 事件和ChannelHandler：处理方法实现，业务逻辑
    * EventLoop: 为每个channel分配一个EventLoop，用以注册事件,派发给ChannelHandler,一个给定Channel 的I/O 操作都是由相同的Thread 执行的，实际上消除了对于同步的需要
### 服务器构建
* 所有Netty服务器都需要ChannelHandler和引导
* ChannelHandler需要响应传入的消息，所以需要实现ChannelInboundHandler接口
* 引导服务器：创建ChannelHandler,EventLoopGroup;创建ServerBootstrap,并绑定group,channel,localAddress,childHandler;在childHandler中将ChannelHandler的示例加入到该channel的ChannelPipeLine中;
            绑定服务器，sync()阻塞至完成，closeFuture()阻塞至完成；关闭EventLoopGroup，释放所有资源
### 客户端构建
* channelActive(),channelRead0(),exceptionCaught()
* 引导客户端：创建EventLoopGroup;创建BootStrap并绑定group,remoteAddress,handler;handler会创建一个新的ChannelHandler实例并添加进pipeline;阻塞等待远程连接完成;阻塞直到Channel关闭;关闭线程池并释放所有资源

## Netty组件及设计
* Channel接口提供了基本的I/O操作
* EventLoop接口定义了Netty的核心抽象,处理连接生命周期内的事件,一个channel只会绑定一个EventLoop，在专有的线程处理事件，所以不需要同步
* ChannelFuture接口的addListener()方法注册了一个listener(),完成的时候得到通知，可以看作将要执行操作的结果的占位符
* ChannelHandler接口中的方法由网络事件触发
* ChannelPipeline接口提供ChannelHandler链的容器，入站从头部取，出站从尾部取；ChannelHandlerContext 代表了handler和pipeline之间的绑定
* 编码器解码器：字节和字符转换
* 引导：Bootstrap用于客户端，ServerBootstrap用于服务器

## 传输
* 每个channel都是独一无二的，都被分配ChannelPipeline和ChannelConfig
* channel实现是线程安全的
* 内置的传输：NIO,Epoll,OIO,Local,Embedded

## ByteBuf
* 相比JDK的ByteBuffer更卓越和灵活，读写使用不同的索引，零拷贝等等
* 使用模式：堆缓冲区，直接缓冲区，复合缓冲区
* 随机访问索引
* discardReadBytes()丢弃可丢弃字节，该部分空间变成可写，但可能会导致内存复制
* 读写等相关api

## ChannelHandler 家族
* Channel生命周期：ChannelUnregistered,ChannelRegistered,ChannelActive,ChannelInActive
* ChannelHandler生命周期：handlerAdded，handlerRemoved，exceptionCaught
* ChannelInboundHandler接口和ChannelOutboundHandler接口
* ChannelHandler适配器

## ChannelPipeline，ChannelHandlerContext 接口
* ChannelHandler 可以通过添加、删除或者替换其他的ChannelHandler 来实时地修改ChannelPipeline 的布局
* ChannelHandlerContext代表了ChannelHandler 和ChannelPipeline 之间的关联

## EventLoop和线程模型
* 每个事件都是一个Runnable的实例，一个EventLoop有一个永远不会改变的Thread驱动，任务可以直接提交给EventLoop实现
* 使用EventLoop调度任务
* 永远不要将一个长时间运行的任务放入到执行队列中，因为它将阻塞需要在同一线程上执行的任何其他任务。

## 引导
* Channel和EventLoopGroup 前缀要一致，oio or nio
* BootStrap 和 ServerBootStrap 绑定过程
