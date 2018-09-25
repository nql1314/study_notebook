## Netty实战

用于创建高性能网络应用程序的高级框架

### 简介
* 统一的API,易于使用,性能,健壮性,安全性,社区驱动
* 异步非阻塞，选择器使得使用较少的线程监听事件
* 核心组件
    * Channel：nio的基本构造，数据载体
    * 回调：使用回调处理事件
    * Future：netty提供了自己的实现ChannelFuture
    * 事件和ChannelHandler
###
* EventLoop: 为每个channel分配一个EventLoop，用以处理所有事件,一个给定Channel 的I/O 操作都是由相同的Thread 执行的，实际上消除了对于同步的需要
