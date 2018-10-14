package com.cnblogs.yjmyzz;

import com.cnblogs.yjmyzz.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        TestService testService = context.getBean(TestService.class);
        String test60 = testService.guavaCache10seconds("test");
        System.out.println(test60);

        Thread.sleep(500);

        test60 = testService.guavaCache10seconds("test");
        System.out.println(test60);

    }
}
