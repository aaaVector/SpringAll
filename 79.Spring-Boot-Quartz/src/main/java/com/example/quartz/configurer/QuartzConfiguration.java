package com.example.quartz.configurer;

import com.example.quartz.jobs.FirstJob;
import com.example.quartz.listener.MyJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: vector
 * @Date: 2020/7/19 21:48
 */
@Configuration
public class QuartzConfiguration {

    public QuartzConfiguration() throws SchedulerException {
        Scheduler scheduler = scheduler();
        ListenerManager listenerManager = scheduler.getListenerManager();
        //关联job和trigger
        scheduler.scheduleJob(jobDetail(FirstJob.class), trigger());
        //注册一个全局的JobListener
        listenerManager.addJobListener(new MyJobListener(), EverythingMatcher.allJobs());
        //注册一个局部的JobListener
        listenerManager.addJobListener(new MyJobListener(), KeyMatcher.keyEquals(new JobKey("jobKey", "jobGroup")));
        //开启
        scheduler.start();
    }

    public Scheduler scheduler() throws SchedulerException {
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        return stdSchedulerFactory.getScheduler();
    }

    public JobDetail jobDetail(Class<? extends Job> aClass) {
        return JobBuilder.newJob()
                .ofType(aClass)
                .withIdentity("jobKey", "jobGroup")
                .usingJobData("jobDefData", "job定义时的数据hello")
                .build();
    }

    public Trigger trigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity("triggerKey", "triggerGroup")
                .usingJobData("triggerDefData", "trigger触发器数据")
                .withSchedule(
                        //withRepeatCount 指的是额外再执行3次
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(5)
                        )   //四种触发器都是以scheduleBuilder结尾的类来构造
                //.withSchedule(CronScheduleBuilder.cronSchedule("cron expression").inTimeZone(TimeZone.getDefault()))
                .build();
    }



}
