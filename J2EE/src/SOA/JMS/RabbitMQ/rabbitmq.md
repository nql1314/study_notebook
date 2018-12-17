## RabbitMq
https://blog.csdn.net/dreamchasering/article/details/77653512
* AMQP，ERLANG实现
![](https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=8732641cf703738dca470470d272db34/902397dda144ad3467b19c3bd1a20cf431ad857b.jpg)
* 生产者(P)-交换器(EXCHANGE)-绑定(BINDING)-队列(QUEUE)-消费者(C)
* Exchange Type、Bingding key、routing key
    1. binding key 用来绑定exchange和queue
    2. routing key 决定消息的路由规则
* Exchange Type有三种：fanout、direct、topic。
    1. fanout:把所有发送到该Exchange的消息投递到所有与它绑定的队列中。
    2. direct:把消息投递到那些binding key与routing key完全匹配的队列中。
    3. topic:将消息路由到binding key与routing key模式匹配的队列中。
### 确认机制
* 消费者处理完任务后需要发送ack确认，如果消费者挂掉，mq就会发送给下一个消费者处理
* 默认打开，设置autoAck = false 关闭


### 实现延迟任务
https://www.cnblogs.com/haoxinyue/p/6613706.html

