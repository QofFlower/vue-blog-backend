package com.hananoq.oauth2.controller;

import com.hananoq.common.Enum.RequestStatus;
import com.hananoq.common.entity.HUser;
import com.hananoq.common.response.Result;
import com.hananoq.oauth2.mapper.HUserMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author :花のQ
 * @since 2020/11/20 17:33
 **/
@RestController
@RequestMapping("oauth")
public class Oauth2Controller {

    @Resource
    private HUserMapper userMapper;

    @GetMapping("test")
    public Result test() {
        return Result.error(406, "三天之内");
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return message
     */
    @PostMapping("register")
    public Result register(@Validated @RequestBody HUser user) {
        user
                .setLastLogin(LocalDateTime.now())
                .setCreated(LocalDateTime.now())
                .setStatus(0);
        System.out.println("user = " + user);
        userMapper.insert(user);
        return Result.success(RequestStatus.REQUEST_SUCCESS.getCode()
                , "注册成功，你现在可以进行登录");
    }
}
