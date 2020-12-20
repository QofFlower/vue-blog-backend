package com.hananoq.blogserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hananoq.blogserver.mapper.HBlogMapper;
import com.hananoq.blogserver.service.IHBlogService;
import com.hananoq.common.entity.HBlog;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hananoq
 * @since 2020-11-20
 */
@Service
public class HBlogServiceImpl extends ServiceImpl<HBlogMapper, HBlog> implements IHBlogService {

}
