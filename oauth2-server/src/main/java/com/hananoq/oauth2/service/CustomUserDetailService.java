package com.hananoq.oauth2.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.hananoq.common.entity.HUser;
import com.hananoq.oauth2.mapper.HUserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.format.DateTimeFormatter;

/**
 * @author :花のQ
 * @since 2020/11/21 18:43
 **/
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Resource
    private HUserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private Gson gson;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //将来连接数据库根据账号查询用户信息
        HUser user = userMapper
                .selectOne(new QueryWrapper<HUser>().eq("username", username));

        if (user == null) {
            //如果用户查不到，返回null，由provider来抛出异常
            return null;
        }

        String password = user.getPassword();

        return User.withUsername(gson.toJson(user.setPassword("******")))
                .password(passwordEncoder.encode(password))
                .authorities("ROLE_USER")
                .build();

    }
}
