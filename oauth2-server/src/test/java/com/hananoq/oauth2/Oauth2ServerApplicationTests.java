package com.hananoq.oauth2;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.hananoq.common.entity.HUser;
import com.hananoq.oauth2.mapper.HUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;

@SpringBootTest
class Oauth2ServerApplicationTests {

    @Resource
    private HUserMapper userMapper;

    @Test
    void contextLoads() {

        HUser user = userMapper.selectOne(new QueryWrapper<HUser>()
                .eq("username", "hananoq")).setPassword(null);

        user.setPassword("***");

        Gson gson = new Gson();

        String s = gson.toJson(user);

        System.out.println("s = " + s);

        HUser fromJson = gson.fromJson(s, HUser.class);

        System.out.println("fromJson = " + fromJson);

    }

}
