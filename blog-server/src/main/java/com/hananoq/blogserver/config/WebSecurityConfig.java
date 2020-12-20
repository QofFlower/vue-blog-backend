package com.hananoq.blogserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author :花のQ
 * @since 2020/11/23 20:19
 **/
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
// .antMatchers("/r/r1").hasAuthority("p2")
// .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/blog/list").permitAll()
                .antMatchers("/blog/oss").permitAll()
                .antMatchers("/**").authenticated()//所有/**的请求必须认证通过
        ;
    }
}
