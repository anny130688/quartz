package com.example.demo.component;

import com.example.demo.job.ScheduledJob;
import com.example.demo.service.HelloService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Job1 extends ScheduledJob {
    @Autowired
    private HelloService helloService;

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
        helloService = SpringContextUtils.getBean(HelloService.class);
        helloService.SayHello();
        System.out.println("job1 is running.............."+new Date());
    }


}

