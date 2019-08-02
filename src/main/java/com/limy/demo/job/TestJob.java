package com.limy.demo.job;

import com.alibaba.fastjson.JSON;
import com.limy.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Limy
 * @create: 2019/07/25 15:46
 * @description: ${description}
 */
@Service
public class TestJob {

    @Autowired
    ApplicationContext context;

    @Autowired
    private TaskService service;

    private static List<String> result = new ArrayList<>();

    /**
     * 知道子线程个数
     */
    public List<String> job1(List<Integer> num) {
        List<String> resultlList = new ArrayList<>();
        try {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            for (int i = 0; i < num.size(); i++) {
                if (i < 3) {
                    list1.add(num.get(i));
                }else {
                    list2.add(num.get(i));
                }
            }
            Future<List<String>> listFuture1 = service.task1(list1);
            Future<List<String>> listFuture2 = service.task1(list2);

            while (true) {
                if (listFuture1.isDone() && listFuture2.isDone()) {
                    resultlList.addAll(listFuture1.get());
                    resultlList.addAll(listFuture2.get());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultlList;
    }

    /**
     * 无法知道子线程会启动多少个
     * 调用@Async注解的异步无返回任务执行，等待全部线程执行完毕后再返回汇总数据。
     * 汇总数据由主线程方法同级变量，通过线程安全方法注入。
     */
    public List<String> job2(List<Integer> num) {
        try {
            List<Integer> list2 = new ArrayList<>();
            for (Integer integer : num) {
                if (list2.size() > 2) {
                    service.task2(list2);
                    list2 = new ArrayList<>();
                }
                list2.add(integer);
            }
            if (list2.size() > 0) {
                service.task2(list2);
                list2 = new ArrayList<>();
            }
            ThreadPoolTaskExecutor myExecutor = (ThreadPoolTaskExecutor) context.getBean("myExecutor2");
//            myExecutor.shutdown();//停止接入线程，正在执行中的线程不会中断--子线程中不能sleep
            //等待线程全部结束
            if(myExecutor.getThreadPoolExecutor().awaitTermination(10, TimeUnit.SECONDS)){
                while (myExecutor.getActiveCount() == 0) {
                   break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 线程安全注入汇总数据
     */
    public static synchronized void combList(List<String> list) {
        for (String s : list) {
            if (s == null) {
                continue;
            }
            result.add(s);
        }
    }

}
