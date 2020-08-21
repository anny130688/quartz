package com.example.demo.job;

import com.example.demo.component.TaskManager;
import org.quartz.*;
import javax.annotation.PostConstruct;

public  abstract class ScheduledJob implements Job {


    @PostConstruct
    public void init(){
        TaskManager.JOB_LIST.add(this);
    }

    /**
     * cron表达式在线生成 http://cron.qqe2.com/
     *
     * @return
     */
    public abstract String getCron();

    /**
     *
     * @param scheduler
     * 0/5 * * * * ?
     * @throws SchedulerException
     */
    public void startJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(this.getClass())
                .withIdentity(this.getClass().getName() + "-job", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(getCron());
        CronTrigger cronTrigger = getCronTrigger(scheduleBuilder);
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    /**
     * 若要自定义触发器,请在子类重写此方法
     * @return
     */
    protected CronTrigger getCronTrigger(CronScheduleBuilder scheduleBuilder){
        return TriggerBuilder.newTrigger().withIdentity(this.getClass().getName() + "-trigger", "group1")
                .withSchedule(scheduleBuilder).build();

    }


}

