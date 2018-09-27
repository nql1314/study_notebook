## workflow
开源工作流引擎：jbpm，activiti，flowable

### jbmp5
* 持久层采用Hibernate3，JPA规范
* Bitronix，基于JTA事务管理
* 基于Apache Mina异步通讯
* 不适合非常复杂业务流程
* api封装好，与业务操作松耦合
* 无官方流程设计器
* BPMN2.0

### activiti5
* 持久层使用的Mybatis
* 原生支持spring，方便管理事务
* Service间通过API调用
* BPMN2.0规范，有专门的流程设计器
* 运行和历史数据分离，数据存取效率高
* 开源社区活跃，技术资源多
* 基于restful风格的activiti explorer来管理仓库等
* 对表单支持弱，流程表单设计需要额外开发
* 学习成本较高，结合业务二次开发需要花时间
* 数据库表庞大，23张

### flowable
* activiti主创从activiti分离出的工作流引擎，基于activiti扩展
* 核心是超快速、稳定的BPMN2流程引擎，易于与 Spring集成使用
* 相比activiti开发更快速，修复了一些bug，表更多
* 新特性
    * 异步处理历史数据
    * 回退功能
    * 增加和拓展对事件子流程的支持
    * 提高对事件监听器事务生命周期的支持
    * 新增全局Counter功能
* 开源社区更新频率挺高，但出的时间不长，教程较少
