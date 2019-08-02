package com.limy.demo.service;

import com.limy.demo.job.TestJob;
import com.limy.demo.task.Task;
import com.limy.demo.thread.MyThread;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;

/**
 * @author: Limy
 * @create: 2019/07/25 09:31
 * @description: ${description}
 */
@Service
public class TaskService {

    @Async("myExecutor")
    public Future<List<String>> task1(List<Integer> number) {
        List<String> list = new ArrayList<>();
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Integer integer = number.get(number.size() - 1) < 4 ? 1 : 2;
            System.err.println("task1子线程" + integer + "--启动--时间:" + sdf.format(new Date()));

            if (integer == 1) {
                Thread.sleep(3000);
            } else {
                Thread.sleep(5000);
            }

            for (Integer integer1 : number) {
                list.add(integer1 + "*");
            }
            System.err.println("task1子线程" + integer + "-结束-时间:" + sdf.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(list);
    }

    @Async("myExecutor2")
    public void task2(List<Integer> number) {
        try {
            List<String> list = new ArrayList<String>();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Integer integer = number.get(number.size() - 1) < 4 ? 1 : 2;

            System.err.println("task2子线程" + integer + "--启动--时间:" + sdf.format(new Date()));
            if (integer == 1) {
                Thread.sleep(3000);
            } else {
                Thread.sleep(5000);
            }
            for (Integer integer1 : number) {
                list.add(integer1 + "*");
            }
            TestJob.combList(list);
            System.err.println("task2子线程" + integer + "-结束-时间:" + sdf.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
