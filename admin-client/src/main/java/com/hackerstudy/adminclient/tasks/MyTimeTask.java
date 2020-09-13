package com.hackerstudy.adminclient.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @class: MyTimeTask
 * @description: java中用TimeTask实现定时器
 * @author: Administrator
 * @date: 2019-08-21 22:21
 */
@Component
public class MyTimeTask {
    public static final Logger logger = LoggerFactory.getLogger(MyTimeTask.class);

//    @PostConstruct
    public void init(){
        logger.info("TimeTask启动:倒计时");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                logger.info("task  run:{}",new Date());
            }
        };
        Timer timer = new Timer();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。这里是每3秒执行一次
        timer.schedule(timerTask,10,3000);
    }
}
