package com.hananoq.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hananoq.common.entity.HBlog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hananoq
 * @since 2020-11-20
 */
public interface HBlogMapper extends BaseMapper<HBlog> {

    boolean saveBlog(HBlog blog);

}
