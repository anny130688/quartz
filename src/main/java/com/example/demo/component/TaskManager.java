package com.example.demo.component;

import com.example.demo.job.ScheduledJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务管理者
 */
@Component
public class TaskManager {

    /**
     * 存放所有定时任务
     */
    public static final List<ScheduledJob> JOB_LIST = new ArrayList<>();

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 执行所有定时任务
     */
    public void executeTasks(){

        if(!CollectionUtils.isEmpty(JOB_LIST)){
            for (ScheduledJob job : JOB_LIST) {
                try {
                    //跑起来
                    job.startJob(schedulerFactoryBean.getScheduler());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

}


