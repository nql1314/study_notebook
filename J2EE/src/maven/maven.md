### http://www.runoob.com/maven/maven-tutorial.html
## 生命周期
验证 validate->编译 compile->测试 Test->包装 package->检查 verify->安装 install	->部署 deploy
maven 常用命令： https://www.cnblogs.com/wkrbky/p/6352188.html

maven 私服:https://blog.csdn.net/u010539352/article/details/51722199

依赖范围(scope)  <scope>test</test> 

跳过单元测试
 <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <configuration>
            <skip>true</skip>
        </configuration>
    </plugin>