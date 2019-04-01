
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class VipEndJob : Job {

    private val LOGGER = LoggerFactory.getLogger(VipEndJob::class.java)

    @Autowired
    private lateinit var jobService: JobService

    @Transactional
    override fun execute(context: JobExecutionContext) {
        LOGGER.info("执行用户vip过期任务" + System.currentTimeMillis())
        jobService.endUserVip()
    }
}