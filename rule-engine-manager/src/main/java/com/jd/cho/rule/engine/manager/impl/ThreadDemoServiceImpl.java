package com.jd.cho.rule.engine.manager.impl;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.manager.ThreadDemoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: DongThread Demo
 *
 * @author DongBoot
 * @see <a href="https://joyspace.jd.com/pages/QC6YAkxyJI9MDudZksjh">DongThread使用说明</a>
 */
@Service
public class ThreadDemoServiceImpl implements ThreadDemoService {

    private final List<ThreadPoolExecutor> threadPoolExecutor;

    private final List<ScheduledThreadPoolExecutor> scheduledThreadPoolExecutor;

    private final List<ThreadPoolTaskExecutor> threadPoolTaskExecutor;

    private final Random random;

    public ThreadDemoServiceImpl(@Qualifier("tp") ThreadPoolExecutor threadPoolExecutor,
                                 @Qualifier("stp") ScheduledThreadPoolExecutor scheduledThreadPoolExecutor,
                                 @Qualifier("tpt") ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolExecutor = Lists.newArrayList(threadPoolExecutor);
        this.scheduledThreadPoolExecutor = Lists.newArrayList(scheduledThreadPoolExecutor);
        this.threadPoolTaskExecutor = Lists.newArrayList(threadPoolTaskExecutor);
        this.random = new Random();
    }

    //    public ThreadDemoServiceImpl(List<ThreadPoolExecutor> threadPoolExecutor,
    //                                 List<ScheduledThreadPoolExecutor> scheduledThreadPoolExecutor,
    //                                 List<ThreadPoolTaskExecutor> threadPoolTaskExecutor) {
    //        this.threadPoolExecutor = threadPoolExecutor;
    //        this.scheduledThreadPoolExecutor = scheduledThreadPoolExecutor;
    //        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    //        this.random = new Random();
    //    }


    /**
     * 使用普通线程池
     *
     * @param name
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @see <a href="https://joyspace.jd.com/pages/4tzNx0gR7o5NrrlX91v1">动态调整线程池</a>
     */
    @Override
    public String getFromThreadPool(String name) throws ExecutionException, InterruptedException {
        int r = random.nextInt(100) % this.threadPoolExecutor.size();
        ThreadPoolExecutor exe = this.threadPoolExecutor.get(r);
        return exe.submit(() -> "get " + name + " from No." + r + " " + exe.getClass().getSimpleName()).get();
    }


    /**
     * 使用调度线程池
     *
     * @param name
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @see <a href="https://joyspace.jd.com/pages/4tzNx0gR7o5NrrlX91v1">动态调整线程池</a>
     */
    @Override
    public String getFromScheduledThreadPool(String name) throws ExecutionException, InterruptedException {
        int r = random.nextInt(100) % this.scheduledThreadPoolExecutor.size();
        ScheduledThreadPoolExecutor exe = this.scheduledThreadPoolExecutor.get(r);
        return exe.schedule(() -> "get " + name + " from No." + r + " " + exe.getClass().getSimpleName(), 500, TimeUnit.MICROSECONDS).get();
    }

    @Override
    public String getFromThreadPoolTask(String name) throws ExecutionException, InterruptedException {
        int r = random.nextInt(100) % this.threadPoolTaskExecutor.size();
        ThreadPoolTaskExecutor exe = this.threadPoolTaskExecutor.get(r);
        return exe.submit(() -> "get " + name + " from No." + r + " " + exe.getClass().getSimpleName()).get();
    }


}