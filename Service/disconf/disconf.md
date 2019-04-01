1.添加maven依赖

<dependency>
   <groupId>com.baidu.disconf</groupId>
   <artifactId>disconf-client</artifactId>
   <version>2.6.36</version>
</dependency>
2.在resource目录中添加disconf.xml,修改扫描配置类的包名

<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">

    <aop:aspectj-autoproxy proxy-target-class="true"/>

    <!-- 使用disconf必须添加以下配置 -->
    <bean id="disconfMgrBean" class="com.baidu.disconf.client.DisconfMgrBean"
          destroy-method="destroy">
        <property name="scanPackage" value="bid.airss.auth,bid.airss..archetype.server"/><!--修改对应的包名  -->
    </bean>
    <bean id="disconfMgrBean2" class="com.baidu.disconf.client.DisconfMgrBeanSecond"
          init-method="init" destroy-method="destroy">
    </bean>

</beans>
3.在disconf后台配置创建app版本和配置文件
http://disconf-test.airss.bid:8081/login.html
用户名：admin 密码：admin

4.在resource目录中添加disconf.properties

# 是否使用远程配置文件
# true(默认)会从远程获取配置 false则直接获取本地配置
disconf.enable.remote.conf=true
#
# 配置服务器的 HOST,用逗号分隔  127.0.0.1:8000,127.0.0.1:8000
#
disconf.conf_server_host=disconf-test.airss.bid:8801

# 版本
disconf.version=

# APP 请采用 产品线_服务名 格式
disconf.app=

# 环境
disconf.env=@disconf.env@

# debug
disconf.debug=true

# 忽略哪些分布式配置，用逗号分隔
disconf.ignore=

# 获取远程配置 重试次数，默认是3次
disconf.conf_server_url_retry_times=1
# 获取远程配置 重试时休眠时间，默认是5秒
disconf.conf_server_url_retry_sleep_seconds=1
# 本地配置文件保存位置
disconf.user_define_download_dir=./disconf/download
5.在resource目录中添加disconf_sys.properties

# \u4ED3\u5E93 URL
disconf.conf_server_store_action=/api/config

# zoo URL
disconf.conf_server_zoo_action=127.0.0.1:8581

# \u83B7\u53D6\u8FDC\u7A0B\u4E3B\u673A\u4E2A\u6570\u7684URL
disconf.conf_server_master_num_action=/api/getmasterinfo

# \u4E0B\u8F7D\u6587\u4EF6\u5939, \u8FDC\u7A0B\u6587\u4EF6\u4E0B\u8F7D\u540E\u4F1A\u653E\u5728\u8FD9\u91CC
disconf.local_download_dir=./disconf/download
6.在SpringBoot的启动类添加注解@ImportResource("classpath:disconf.xml")

7.创建配置文件类

package com.handarui.archetype.server.biz.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
@DisconfFile(filename = "mysql.properties")
public class MysqlConfig {

    private String dataSourceUrl;
    private String dataSourceDriverClassName;
    private String dataSourceUsername;
    private String dataSourcePassword;

    @DisconfFileItem(name="spring.datasource.url")
    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    public void setDataSourceUrl(String dataSourceUrl) {
        this.dataSourceUrl = dataSourceUrl;
    }

    @DisconfFileItem(name="spring.datasource.driver-class-name")
    public String getDataSourceDriverClassName() {
        return dataSourceDriverClassName;
    }

    public void setDataSourceDriverClassName(String dataSourceDriverClassName) {
        this.dataSourceDriverClassName = dataSourceDriverClassName;
    }

    @DisconfFileItem(name="spring.datasource.username")
    public String getDataSourceUsername() {
        return dataSourceUsername;
    }

    public void setDataSourceUsername(String dataSourceUsername) {
        this.dataSourceUsername = dataSourceUsername;
    }

    @DisconfFileItem(name="spring.datasource.password")
    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

    public void setDataSourcePassword(String dataSourcePassword) {
        this.dataSourcePassword = dataSourcePassword;
    }

}
8.使用配置类

使用spring注入对应的Bean或者SpringBeanUtil.getBean(id)

参考：http://disconf.readthedocs.io/zh_CN/latest/