package com.limy.demo.task;

import com.alibaba.fastjson.JSON;
import com.limy.demo.job.TestJob;
import com.limy.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * @author: Limy
 * @create: 2019/07/24 16:58
 * @description: ${description}
 */
@Component
public class Task {

    @Autowired
    private TestJob testJob;


//    @Scheduled(cron = "* 0/10 * * * ?")
//    @Scheduled(initialDelay=1000, fixedRate=20000)
    @Async("JobExecutor")
    public void configureTasks() throws InterruptedException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("定时任务1启动:" + sdf.format(new Date()));
        List<Integer> num = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
        List<String> list = testJob.job1(num);
        System.out.println("定时任务1结果:"+JSON.toJSONString(list));
        System.err.println("定时任务1结束:" + sdf.format(new Date()));
    }

    /**
     * 定时任务Task 调用 Job，Job调用service多线程执行
     * @Scheduled(cron = "0/50 * * * * ?")
     */
    @Async("JobExecutor")
//    @Scheduled(initialDelay=1000, fixedRate=50000)
    public void configureTask2() throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("定时任务2启动:" + sdf.format(new Date()));
        List<Integer> num = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
        List<String> list = testJob.job2(num);
        System.out.println("定时任务2结果:"+JSON.toJSONString(list));
        System.err.println("定时任务2结束:" + sdf.format(new Date()));
    }

    public static void main(String[] args) {
        Long a = 100L;
        Long b = 0L;
        Long orderAmount = (a - b)/100;
        System.out.println(orderAmount);
    }
}