import org.quartz.Scheduler
import org.quartz.spi.JobFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.PropertiesFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import java.util.*


@Configuration
class QuartzJobConfig {

    @Autowired
    private lateinit var jobFactory: JobFactory

    @Bean
    @DependsOn("disconfMgrBean2")
    fun quartzProperties(disconfRedis: DisconfRedis): Properties {
        val propertiesFactoryBean = PropertiesFactoryBean()
        val properties = Properties()
        properties["org.quartz.jobStore.class"] = "net.joelinn.quartz.jobstore.RedisJobStore"
        properties["org.quartz.jobStore.host"] = disconfRedis.host
        properties["org.quartz.jobStore.port"] ="" + disconfRedis.port
        properties["org.quartz.jobStore.password"] = disconfRedis.password
        properties["org.quartz.jobStore.keyPrefix"] = disconfRedis.jobStorageKeyPrefix + ":"
        properties["org.quartz.scheduler.makeSchedulerThreadDaemon"] = true
        propertiesFactoryBean.setProperties(properties)
        propertiesFactoryBean.afterPropertiesSet()
        return propertiesFactoryBean.`object`
    }

    @Bean
    fun schedulerFactoryBean(quartzProperties: Properties): SchedulerFactoryBean {
        val schedulerFactoryBean = SchedulerFactoryBean()
        schedulerFactoryBean.setJobFactory(jobFactory)
        schedulerFactoryBean.setOverwriteExistingJobs(true)
        schedulerFactoryBean.setQuartzProperties(quartzProperties)
        schedulerFactoryBean.isAutoStartup = false
        return schedulerFactoryBean
    }

    @Bean
    fun scheduler(schedulerFactoryBean: SchedulerFactoryBean): Scheduler {
        return schedulerFactoryBean.scheduler
    }

}