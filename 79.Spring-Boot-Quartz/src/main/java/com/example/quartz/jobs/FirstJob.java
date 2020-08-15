package com.example.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: vector
 * @Date: 2020/7/26 20:10
 */
@PersistJobDataAfterExecution
public class FirstJob implements Job {

    /**
     * jobDataMap的数据通过key的setter方法自动注入进值
     * 每次job被调度的时候，都是新创建一个当前job对象，然后将jobDataMap中的值通过setter设置到对应成员变量
     * 所以有状态的job中存值只能通过jobDataMap中进行，每次将上一个job中的值set进对应变量
     */
    private String jobDefData;
    private String triggerDefData;

    private Integer count = 0;

    @Override
    public void execute(JobExecutionContext context) {

        System.out.println("jobName = " + jobDefData + ",triggerName = " + triggerDefData);

        System.out.println(String.format("执行调度程序，当前时间：" +
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                + "，下一次调用时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(context.getNextFireTime())
        ));

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Integer preCount = ((Integer) jobDataMap.get("count"));
        System.out.println("preCount = " + preCount);
        jobDataMap.put("count", ++count);
        System.out.println(count);
    }

    public void setTriggerDefData(String triggerDefData) {
        this.triggerDefData = triggerDefData;
    }

    public void setJobDefData(String jobDefData) {
        this.jobDefData = jobDefData;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
