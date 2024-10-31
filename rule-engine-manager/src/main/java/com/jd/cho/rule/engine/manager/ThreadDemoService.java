package com.jd.cho.rule.engine.manager;

import java.util.concurrent.ExecutionException;;

/**
 * Description: DongThread Demo
 *
 * @author DongBoot
 * @see <a href="https://joyspace.jd.com/pages/QC6YAkxyJI9MDudZksjh">DongThread使用说明</a>
 */
public interface ThreadDemoService {

    /**
     * 使用普通线程池
     *
     * @param name
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @see <a href="https://joyspace.jd.com/pages/4tzNx0gR7o5NrrlX91v1">动态调整线程池</a>
     */
    String getFromThreadPool(String name) throws ExecutionException, InterruptedException;


    /**
     * 使用调度线程池
     *
     * @param name
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @see <a href="https://joyspace.jd.com/pages/4tzNx0gR7o5NrrlX91v1">动态调整线程池</a>
     */
    String getFromScheduledThreadPool(String name) throws ExecutionException, InterruptedException;

    /**
    * 使用任务线程池
    *
    * @param name
    * @return
    * @throws ExecutionException
    * @throws InterruptedException
    * @see <a href="https://joyspace.jd.com/pages/4tzNx0gR7o5NrrlX91v1">动态调整线程池</a>
    */
    String getFromThreadPoolTask(String name) throws ExecutionException, InterruptedException;


}