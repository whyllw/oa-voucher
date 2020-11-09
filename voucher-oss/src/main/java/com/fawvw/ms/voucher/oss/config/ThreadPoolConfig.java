package com.fawvw.ms.voucher.oss.config;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    private static final Integer MULTIPLE = 4;//倍数
    private static final int CPU_NUM = Runtime.getRuntime().availableProcessors();//cpu核数
    private static final int QUEUE_MULTIPLE = 10;//队列与cpu的倍数
    private static final int KEEP_ALIVE_SECONDS = 60;
    private static final int AWAIT_TERMINATION_SECONDS = 60;

    /**
     * 用于 发送异步消息
     */
    @Bean(name = "messageExecutor")
    public ThreadPoolTaskExecutor messageExecutor() {
        return createThreadPoolTaskExecutor();
    }


    private ThreadPoolTaskExecutor createThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(CPU_NUM * 2 + 1);
        pool.setMaxPoolSize(CPU_NUM * MULTIPLE + 1);
        //队列大小设置为负数,使用SynchronousQueue,所有任务不等待
        Integer queueCapacity = -1;
        pool.setQueueCapacity(queueCapacity);
        //如果超出最大线程数，由调用线程自己执行任务
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }

    /**
     * 用于异步处理
     */
    @Bean(name = "asyncExecutor")
    public ThreadPoolTaskExecutor asyncExecutor() {
        return createAsyncThreadPoolTaskExecutor();
    }


    private ThreadPoolTaskExecutor createAsyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(CPU_NUM * 2 + 1);
        pool.setMaxPoolSize(CPU_NUM * MULTIPLE + 1);
        pool.setQueueCapacity(CPU_NUM * QUEUE_MULTIPLE);
        //如果超出最大线程数，由调用线程自己执行任务
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        pool.setWaitForTasksToCompleteOnShutdown(true);
        pool.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        pool.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS);
        return pool;
    }

}
