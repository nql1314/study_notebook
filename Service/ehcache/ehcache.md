https://www.cnblogs.com/mxmbk/articles/5162813.html

```xml
            <!--cache-->
            <dependency>
                <groupId>net.sf.ehcache</groupId>
                <artifactId>ehcache</artifactId>
                <version>2.8.3</version>
            </dependency>
```


application.properties

#echache
spring.cache.type=ehcache
spring.cache.ehcache.config=classpath:ehcache.xml