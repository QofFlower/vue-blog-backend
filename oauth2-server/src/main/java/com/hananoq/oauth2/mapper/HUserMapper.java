package com.hananoq.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hananoq.common.entity.HUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hananoq
 * @since 2020-11-20
 */
public interface HUserMapper extends BaseMapper<HUser> {

    @Select("select * from h_user where username = #{username}")
    HUser findByUsername(@Param("username") String username);
}
