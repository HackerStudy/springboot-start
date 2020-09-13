package com.hackerstudy.adminclient.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring task的定时任务调度
 */
@Component
public class TimingTask {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static final Logger logger = LoggerFactory.getLogger(TimingTask.class);

    public static Integer synNumber = 0;

    public static Integer number = 0;

//    @Async
	// 定义每过10秒执行任务
    @Scheduled(fixedRate = 10000)
//	@Scheduled(cron = "4-40 * * * * ?")
    public void reportCurrentTime() {
        logger.info("现在时间:{}",dateFormat.format(new Date()));
    }

//    @Async
    @Scheduled(fixedRate = 3000)
    public void print() {
        logger.info("HelloWorld");
    }

//    @Async
    @Scheduled(fixedRate = 20000)
    public void synSum() {
        logger.info("synNumber={}",synNumber);
        synchronized (synNumber){
            synNumber++;
        }
    }

    @Async
    @Scheduled(fixedRate = 20000)
    public void sum() {
        logger.info("number={}",number);
        number++;
    }

    //@Async
    //@Scheduled(fixedRate = 20000)
    //public void Memoryoverflow() {
    //    while(true){
    //        User user = new User();
    //        user.setId(0);
    //        user.setName("");
    //        user.setPassword("");
    //        user.setAge(0);
    //        user.setBirthday(new Date());
    //        user.setDesc("");
    //    }
    //}





}
