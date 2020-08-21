package com.example.demo.component;

import com.example.demo.job.ScheduledJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Job2 extends ScheduledJob {


    /**
     * 从0秒开始，每1分钟跑一次
     *
     * @return
     */
    @Override
    public String getCron() {
        return "0 */1 * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job2 is running.............."+new Date());
    }


}

