# Hapdoop 
Hadoop提供了一个稳定的共享存储和分析系统。存储由HDFS实现，分析由MapReduce实现。
## 概要
* HFDS 
主从结构 
  NameNode: 单个，命名空间，管理者，
  DataNode: 存储数据，冗余
* HBase
主服务器：主备，选举产生
域服务器：根域 元域 用户域（根表 元表）
* Hive
    * 元数据存储 存储在关系型数据库中 例如MYSQL
    * 数据存储 存储在HDFS 4种数据模型
        * Table对应一个目录，包含多个Partition，对应子目录
        * Buckets对指定列的值计算hash，分散存储
        * External Table 指向已存在的数据
    * 数据交换
        * 用户接口：包括客户端 web界面 数据库接口
        * 元数据存储：存储在关系型数据库
        * 解释器 编译器 优化器 执行器
        * Hadoop: HDFS MapReduce
## MapReduce
* 一种可用于数据处理的编程模型
* Map提取数据，Shuffle混洗数据，Reduce得出结果
* 气象案例：https://blog.csdn.net/qq_22112963/article/details/84032044

## HDFS
* 分布式文件系统
* 数据块:128MB 最小化寻址开销 适合分布式存储
* 采用辅助namenode 提升高可用
* 堆外块缓存提升读性能
* 联邦HDFS 多namenode分别维护命名空间
* namenode存在单点失效问题，失效冷启动时间长，通过活动-备份namenode故障切换
* HDFS的一些命令操作
* java操作HDFS的一些接口api 
* HDFS的写入过程(hdfs_write.jpg):
    * 客户端调用DistributedFileSystem.create()
    * DistributedFileSystem rpc请求namenode执行相应检查，通过后穿甲记录，DistributedFileSystem返回FSDataOutputStream对象
    * 客户端通过FSDataOutputStream开始写入数据到DataNode，并通过队列和管线复制到其他dataNode
    * 当所有数据队列确认后数据包删除
    * 写入故障交给namenode重新分配处理
* 一致模型：hflush()保证datanode 数据已写入管道，对所有reader可见；hsync()确保数据写入
   到磁盘

## YARN
* https://www.cnblogs.com/wcwen1990/p/6737985.html
* 提供请求和使用集群资源的底层API
* 资源管理器和节点管理器
![](http://images2015.cnblogs.com/blog/669905/201704/669905-20170420115226665-1020933684.png)
* YARN主要由ResourceManager、NodeManager、ApplicationMaster和Container组成
* ResourceManager 一个全局的资源管理器，集群只有一个，负责整个系统的资源管理和分配，包括处理客户端请求、启动/监控APP master、监控nodemanager、资源的分配与调度
    * 调度器：三种FIFO调度器，容量调度器，公平调度器
    * 应用程序管理器： 应用程序管理器负责管理整个系统中所有应用程序，包括应用程序提交、与调度器协商资源以启动ApplicationMaster、监控ApplicationMaster运行状态并在失败时重新启动它等。
* ApplicationMaster：管理YARN内运行的应用程序的每个实例。负责协调来自resourcemanager的资源，并通过nodemanager监视容易的执行和资源使用情况。
    * 数据切分
* NodeManager 整个集群有多个，负责每个节点上的资源和使用。
    * 单个节点上的资源管理和任务。
    * 处理来自于resourcemanager的命令。
    * 处理来自于app master的命令。
* Container 执行特定程序的进程 资源抽象 动态生成

## I/O
* CRC-32C校验保证数据完整性
* 校验 压缩 序列化  
    * 为应用程序申请资源并进一步分配给内部任务。
    * 任务监控与容错
