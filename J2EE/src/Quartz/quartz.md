## Quartz
https://www.cnblogs.com/drift-ice/p/3817269.html
###Quartz是一个任务调度框架
* Scheduler：调度器。所有的调度都是由它控制。
* Trigger： 定义触发的条件。例子中，它的类型是SimpleTrigger，每隔1秒中执行一次（什么是SimpleTrigger下面会有详述）。
* JobDetail & Job： JobDetail 定义的是任务数据，而真正的执行逻辑是在Job中，例子中是HelloQuartz。 为什么设计成JobDetail + Job，不直接使用Job？这是因为任务是有可能并发执行，如果Scheduler直接使用Job，就会存在对同一个Job实例并发访问的问题。而JobDetail & Job 方式，sheduler每次执行，都会根据JobDetail创建一个新的Job实例，这样就可以规避并发访问的问题。
* 关于name和group
    * JobDetail和Trigger都有name和group。
    * name是它们在这个sheduler里面的唯一标识。如果我们要更新一个JobDetail定义，只需要设置一个name相同的JobDetail实例即可。
    * group是一个组织单元，sheduler会提供一些对整组操作的API，比如 scheduler.resumeJobs()。
    
* Trigger
    * StartTime & EndTime
        * startTime和endTime指定的Trigger会被触发的时间区间。在这个区间之外，Trigger是不会被触发的。
    * 优先级（Priority） 优先级高的先执行。

* Misfire(错失触发）策略 
    * MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
      这个不是忽略已经错失的触发的意思，而是说忽略MisFire策略。它会在资源合适的时候，重新触发所有的MisFire任务，并且不会影响现有的调度时间。
    * MISFIRE_INSTRUCTION_FIRE_NOW
      忽略已经MisFire的任务，并且立即执行调度。这通常只适用于只执行一次的任务。
    * MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT
      将startTime设置当前时间，立即重新调度任务，包括的MisFire的
    * MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT
      类似MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT，区别在于会忽略已经MisFire的任务
    * MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT
      在下一次调度时间点，重新开始调度任务，包括的MisFire的
    * MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT
      类似于MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT，区别在于会忽略已经MisFire的任务。
    * MISFIRE_INSTRUCTION_SMART_POLICY
      所有的Trigger的MisFire默认值都是这个，大致意思是“把处理逻辑交给聪明的Quartz去决定”。基本策略是，
    * 如果是只执行一次的调度，使用MISFIRE_INSTRUCTION_FIRE_NOW
      如果是无限次的调度(repeatCount是无限的)，使用MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT
      否则，使用MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT
      
* Calendar
    补充Trigger的时间,排除或加入某一些特定的时间点
    * HolidayCalendar。指定特定的日期，比如20140613。精度到天。
    * DailyCalendar。指定每天的时间段（rangeStartingTime, rangeEndingTime)，格式是HH:MM[:SS[:mmm]]。也就是最大精度可以到毫秒。
    * WeeklyCalendar。指定每星期的星期几，可选值比如为java.util.Calendar.SUNDAY。精度是天。
    * MonthlyCalendar。指定每月的几号。可选值为1-31。精度是天
    * AnnualCalendar。 指定每年的哪一天。使用方式如上例。精度是天。
    * CronCalendar。指定Cron表达式。精度取决于Cron表达式，也就是最大精度可以到秒。

* Trigger实现类
    * SimpleTrigger
        * 指定从某一个时间开始，以一定的时间间隔（单位是毫秒）执行的任务。
        * repeatInterval 重复间隔
        * repeatCount 重复次数。实际执行次数是 repeatCount+1。因为在startTime的时候一定会执行一次。** 下面有关repeatCount 属性的都是同理。　**
    * CalendarIntervalTrigger
        * 类似于SimpleTrigger，指定从某一个时间开始，以一定的时间间隔执行的任务。 但是不同的是SimpleTrigger指定的时间间隔为毫秒，没办法指定每隔一个月执行一次（每月的时间间隔不是固定值），而CalendarIntervalTrigger支持的间隔单位有秒，分钟，小时，天，月，年，星期。
    * DailyTimeIntervalTrigger
        * 指定每天的某个时间段内，以一定的时间间隔执行任务。并且它可以支持指定星期。
    * CronTrigger
        * 在线生成工具 http://cron.qqe2.com/
        * 适合于更复杂的任务，它支持类型于Linux Cron的语法（并且更强大）。基本上它覆盖了以上三个Trigger的绝大部分能力（但不是全部）

* JobDetail & Job
    * JobDetail是任务的定义，而Job是任务的执行逻辑。在JobDetail里会引用一个Job Class定义。
* JobDataMap
    * 对于同一个JobDetail实例，执行的多个Job实例，是共享同样的JobDataMap，也就是说，如果你在任务里修改了里面的值，会对其他Job实例（并发的或者后续的）造成影响。
        除了JobDetail，Trigger同样有一个JobDataMap，共享范围是所有使用这个Trigger的Job实例。
* Job并发
    * 一个@DisallowConcurrentExecution解决这个问题。
* JobExecutionException
    * Job.execute()方法是不允许抛出除JobExecutionException之外的所有异常的（包括RuntimeException)，所以编码的时候，最好是try-catch住所有的Throwable，小心处理。

