## ActiveMQ
支持多种语言编写客户端,对spring的支持，很容易和spring整合,支持多种传输协议：TCP,SSL,NIO,UDP等,支持AJAX

点对点，一对多

* 确认机制
    1. DELIVERED_ACK_TYPE = 0    消息"已接收"，但尚未处理结束
    2. STANDARD_ACK_TYPE = 2    "标准"类型,通常表示为消息"处理成功"，broker端可以删除消息了
    3. POSION_ACK_TYPE = 1    消息"错误",通常表示"抛弃"此消息，比如消息重发多次后，都无法正确处理时，消息将会被删除或者DLQ(死信队列)
    4. REDELIVERED_ACK_TYPE = 3    消息需"重发"，比如consumer处理消息时抛出了异常，broker稍后会重新发送此消息
    5. INDIVIDUAL_ACK_TYPE = 4    表示只确认"单条消息",无论在任何ACK_MODE下    
    6. UNMATCHED_ACK_TYPE = 5    BROKER间转发消息时,接收端"拒绝"消息
* 消息类型(https://www.cnblogs.com/dennisit/p/4551795.html)
    包括TextMessage、MapMessage、ObjectMessage、BytesMessage、和StreamMessage
* 模式： Queue（点对点）；Topic（发布/订阅）