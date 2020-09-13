package com.hackerstudy.adminclient.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

/**
 * @class: ListOffThreeRunable
 * @description: 倒计时线程
 * @author: Administrator
 * @date: 2019-07-02 22:37
 */
public class LisfOffRunable implements Runnable{
    public static final Logger logger = LoggerFactory.getLogger(LisfOffRunable.class);

    //倒计时
    private int countDown = 10;

    public LisfOffRunable(int countDown) {
        this.countDown = countDown;
    }

    public LisfOffRunable() {
    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName()+"开始");
        logger.info("{}开始",Thread.currentThread().getName());
        while(countDown>0){
            logger.info("倒计时{}: {}",Thread.currentThread().getName(),countDown);
//            System.out.println("倒计时"+Thread.currentThread().getName()+"："+countDown);
            //线程休眠进入等待状态释放自己的锁
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDown--;
        }
//        System.out.println(Thread.currentThread().getName()+"结束");
        logger.info("{}结束",Thread.currentThread().getName());
    }
}
