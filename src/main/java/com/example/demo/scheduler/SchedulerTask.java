package com.example.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

    private int count=0;

    @Scheduled(cron="*/1 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }

}