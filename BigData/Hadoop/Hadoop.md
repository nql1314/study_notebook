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
