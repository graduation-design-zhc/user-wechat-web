package com.zhc.wechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghuachang
 * @date 2019-04-29
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public ThreadPoolExecutor asyncWorker() {
        return new ThreadPoolExecutor(10, 100, 50, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000));
    }

}
