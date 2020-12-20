package com.hananoq.blogserver.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author :花のQ
 * @since 2020/11/20 11:52
 **/
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 配置分页组件
     *
     * @return 分页组件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
