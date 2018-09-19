## KAFKA
https://www.cnblogs.com/yangxiaoyi/p/7359236.html
https://www.cnblogs.com/hei12138/p/7805475.html

高吞吐量的分布式发布订阅消息系统

* 名词解释
    * producer 生产者
    * consumer 消费者
    * topic 消息以topic为类别记录,Kafka将消息种子(Feed)分门别类,每一类的消息称之为一个主题(Topic)
    * 以集群的方式运行,可以由一个或多个服务组成，每个服务叫做一个broker;消费者可以订阅一个或多个主题(topic),并从Broker拉数据,从而消费这些已发布的消息
    * 每个消息（也叫作record记录,也被称为消息）是由一个key，一个value和时间戳构成。
* 核心API
    * 应用程序使用producer API发布消息到1个或多个topic中。
    * 应用程序使用consumer API来订阅一个或多个topic,并处理产生的消息。
    * 应用程序使用streams API充当一个流处理器,从1个或多个topic消费输入流,并产生一个输出流到1个或多个topic,有效地将输入流转换到输出流。
    * connector API允许构建或运行可重复使用的生产者或消费者,将topic链接到现有的应用程序或数据系统。 
* 基本原理
    * 队列或发布订阅型
    * 消费者组
    * 生产者发布到topic的分区，且顺序保证。
    * 可配置复制因子N,允许N-1服务器宕机而不丢失任何已经提交（committed）的消息
* 应用场景
    *  构建实时的流数据管道，可靠地获取系统和应用程序之间的数据。
    * 构建实时流的应用程序，对数据流进行转换或反应。
* 主题和日志
    * 分区顺序，不可变，可持续添加。消息带唯一的偏移量
* 分布式
    * 主从，备份冗余
    * Geo-Replication(异地数据同步技术)
