
import org.quartz.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PreDestroy

@Component
class QuartzStartRunner : CommandLineRunner {

    private val LOGGER = LoggerFactory.getLogger(QuartzStartRunner::class.java)

    @Autowired
    private lateinit var scheduler: Scheduler

    @Autowired
    private lateinit var disconfJob: DisconfJob

    override fun run(vararg p0: String?) {
        LOGGER.info("初始化Quartz")
        endUserVipJob()
        scheduler.start()
        LOGGER.info("任务已启动")
    }

    /**
     *  每日用户vip过期任务
     */
    fun endUserVipJob() {
        val job = JobBuilder.newJob(VipEndJob::class.java)
                .withIdentity("endUserVip",VipEndJob::class.java.name)
                .build()
        val triggerKey = TriggerKey("endUserVip",VipEndJob::class.java.name)
        val trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(disconfJob.endUserVipJob).inTimeZone(TimeZone.getTimeZone("Asia/Jakarta")))
                .build()
        try {
            scheduler.scheduleJob(job, trigger)
        } catch (e: ObjectAlreadyExistsException) {
            scheduler.rescheduleJob(triggerKey, trigger)
        }
        LOGGER.info("每日用户vip过期任务"+System.currentTimeMillis())
    }

    @PreDestroy
    fun stopScheduler() {
        LOGGER.info("Stopping Quartz")
        scheduler.shutdown(false)
    }

}