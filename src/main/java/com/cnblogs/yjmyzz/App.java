package com.cnblogs.yjmyzz;

import com.cnblogs.yjmyzz.cache.config.CacheConfiguration;
import com.cnblogs.yjmyzz.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jimmy
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(CacheConfiguration.class);
        ctx.refresh();
        TestService service = ctx.getBean(TestService.class);

        String data1 = service.guavaCache10seconds("菩提树下的杨过");
        //首次，不走缓存
        System.out.println("1:" + data1 + ", 不走缓存");

        Thread.sleep(6000);

        //10秒缓存命中
        data1 = service.guavaCache10seconds("菩提树下的杨过");
        System.out.println("2:" + data1 + ", 走缓存");

        Thread.sleep(6000);

        //10秒过期，不走缓存
        data1 = service.guavaCache10seconds("菩提树下的杨过");
        System.out.println("3:" + data1 + ", 不走缓存");

        Thread.sleep(2000);

        //首次30秒缓存
        data1 = service.guavaCache30seconds("菩提树下的杨过");
        System.out.println("4:" + data1 + ", 不走缓存");

        Thread.sleep(29000);

        //30秒缓存命中
        data1 = service.guavaCache30seconds("菩提树下的杨过");
        System.out.println("5:" + data1 + ", 走缓存");

        Thread.sleep(3000);

        //30秒过期
        data1 = service.guavaCache30seconds("菩提树下的杨过");
        System.out.println("6:" + data1 + ", 不走缓存");
    }
}
