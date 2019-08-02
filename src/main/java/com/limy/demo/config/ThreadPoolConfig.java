package com.limy.demo.config;

/**
 * @author: Limy
 * @create: 2019/07/24 17:35
 * @description: ${description}
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by limengyang on 2018/10/18.
 * 线程池配置
 */
@Configuration
public class ThreadPoolConfig {

    private int corePoolSize = 2;
    private int maxPoolSize = 4;
    private int queueCapacity = 10;
    private String threadNamePrefix1 = "myThread1-";//数据同步线程
    private String threadNamePrefix2 = "myThread2-";//数据同步线程
    private String jobTThreadName = "JobThread-";//数据同步线程
    private int keepAlive = 60;
    private boolean allowCoreThreadTimeOut = true;

    /**
     * 定时任务线程池
     * @return
     */
    @Bean
    public Executor JobExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量-核心线程
        executor.setCorePoolSize(corePoolSize);
        //线程池维护线程的最大数量-最大线程
        executor.setMaxPoolSize(maxPoolSize);
        //缓存队列
        executor.setQueueCapacity(queueCapacity);
        //线程名称
        executor.setThreadNamePrefix(jobTThreadName);
        // 当线程已经达到最大的时候，不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //允许的空闲时间
        executor.setKeepAliveSeconds(keepAlive);
        //是否允许核心线程空闲退出，默认值为false
        executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
        // 初始化线程
        executor.initialize();
        return executor;
    }


    /**
     * 子线程池1
     */
    @Bean
    public Executor myExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量-核心线程
        executor.setCorePoolSize(corePoolSize);
        //线程池维护线程的最大数量-最大线程
        executor.setMaxPoolSize(maxPoolSize);
        //缓存队列
        executor.setQueueCapacity(queueCapacity);
        //线程名称
        executor.setThreadNamePrefix(threadNamePrefix1);
        // 当线程已经达到最大的时候，不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //允许的空闲时间
        executor.setKeepAliveSeconds(keepAlive);
        //是否允许核心线程空闲退出，默认值为false
        executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
        // 初始化线程
        executor.initialize();
        return executor;
    }

    /**
     * 子线程池2
     */
    @Bean
    public Executor myExecutor2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量-核心线程
        executor.setCorePoolSize(corePoolSize);
        //线程池维护线程的最大数量-最大线程
        executor.setMaxPoolSize(maxPoolSize);
        //缓存队列
        executor.setQueueCapacity(queueCapacity);
        //线程名称
        executor.setThreadNamePrefix(threadNamePrefix2);
        // 当线程已经达到最大的时候，不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //允许的空闲时间
        executor.setKeepAliveSeconds(keepAlive);
        //是否允许核心线程空闲退出，默认值为false
        executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
        // 初始化线程
        executor.initialize();
        return executor;
    }
}