package com.hackerstudy.adminclient.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @class: ScheduledExecutorServiceTask
 * @description:以java中的ScheduledExecutorService类来实现定时任务
 * @author: Administrator
 * @date: 2019-08-21 22:03
 */
@Component
public class ScheduledExecutorServiceTask {

    public static final Logger logger = LoggerFactory.getLogger(ScheduledExecutorServiceTask.class);

//    @PostConstruct
    public void init(){
        logger.info("定时任务启动:倒计时");
        //此处如果间隔很短则需要注意并发性 如果任务执行时间>delay时间则存在并发问题
        //schedule()则不会 它是任务完成后的XX分执行下一次 而scheduleAtFixedRate是固定的间隔执行
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new LisfOffRunable(), 0, 20, TimeUnit.MINUTES);
    }
}
