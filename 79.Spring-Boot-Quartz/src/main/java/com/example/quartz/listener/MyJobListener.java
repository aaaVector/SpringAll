package com.example.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 *
 * job监听器
 *
 * @Author: vector
 * @Date: 2020/8/1 20:10
 */
public class MyJobListener implements JobListener {

    /**
     * 用于获取该Listener的名字
     */
    @Override
    public String getName() {
        String name = this.getClass().getName();
        System.out.println("监听器的名称：" + name);
        return name;
    }

    /**
     * 即将执行时调用
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("Job即将执行时调用");
    }

    /**
     * 在JobDetail即将被执行，但被TriggerListener否决时执行
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("Job即将被执行，但被TriggerListener否决时执行");
    }

    /**
     * JobDetail执行之后调用
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("JobDetail执行之后调用");
    }
}
