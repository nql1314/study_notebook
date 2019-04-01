package elasticsearch

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * 初始化 es 启动类
 */
@Component
class InitialElasticsearchRunner : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(InitialElasticsearchRunner::class.java)

    @Autowired
    private lateinit var service: Service

    override fun run(vararg args: String?) {
        try {
            service.checkAndInitIndex()
        } catch (e: Exception) {
            logger.error("初始化 es 失败", e)
        }
    }

}