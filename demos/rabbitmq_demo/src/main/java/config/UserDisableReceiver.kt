package config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserDisableReceiver {

    private val logger = LoggerFactory.getLogger(UserDisableReceiver::class.java)

    @Autowired
    private lateinit var authUserService: AuthUserService

    /**
     * 处理消息
     */
    fun receiveMessage(message: String) {
        try {
            logger.info("收到禁用用户消息，userId {}", message)
            val userId = message.toLongOrNull() ?: return
            authUserService.removeUserCache(listOf(userId))
        } catch (e: Exception) {
            logger.error("处理消息失败", e)
        }
    }

}