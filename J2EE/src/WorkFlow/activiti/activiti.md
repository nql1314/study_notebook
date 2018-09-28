## activiti实战
* BPM 业务流程管理 
* 工作流生命周期：定义，发布，执行，监控，优化
* BPMN：业务流程建模标注，2.0是通用的规范
* Mybatis,7大Service接口


### BMPN2.0规范
* 启动事件(startEvent)：空启动，定时启动，异常启动，消息启动
* 结束事件(EndEvent)：空结束，异常结束，终止结束，取消结束
* 顺序流(sequenceFlow)：标准顺序流，条件顺序流
* 任务
    * 用户任务(userTask)
    * 脚本任务(scriptTask)
    * Java Service 任务(serviceTask)
    * Web Service 任务(serviceTask)
    * 业务规则任务(businessRuleTask)
    * 邮件任务(serviceTask)
    * Camel任务(serviceTask)
    * Mule任务(sendTask)
    * 手动任务(manualTask)
    * 接收任务(receiveTask)
    * Shell任务(serviceTask)
    * 多实例：顺序执行或并行执行多次
* 网关
    * 排他网关：if-eles if-else类似的逻辑
    * 并行网关：fork-join 
    * 包容网关：包含排他网关和并行网关的特性
    * 事件网关：中间捕获事件
* 子流程和调用活动：创建子流程方便复用，调用活动是调用外部的流程，事件子流程通过事件触发执行，事务子流程满足事务特性
* 边界事件：定时器边界事件，异常边界事件，信号边界事件，取消边界事件，补偿边界事件，
* 中间捕获事件：定时器中间捕获事件，信号中间捕获事件，消息中间捕获事件
* 中间抛出事件：空之间抛出事件，信号中间抛出事件
* 监听器：执行监听器，任务监听器

###

