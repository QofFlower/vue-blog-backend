package com.hananoq.blogserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.hananoq.blogserver.config.SongsConfig;
import com.hananoq.blogserver.service.IHBlogService;
import com.hananoq.common.Enum.RequestStatus;
import com.hananoq.common.entity.HBlog;
import com.hananoq.common.entity.HUser;
import com.hananoq.common.response.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hananoq
 * @since 2020-11-20
 */
@RestController
@RequestMapping("blog")
@RefreshScope
public class HBlogController {

    @Resource
    private IHBlogService ihBlogService;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessKeyId;

    @Value("${spring.cloud.alicloud.secret-key}")
    private String accessKeySecret;

    @Resource
    private Gson gson;

    @Resource
    private SongsConfig songsConfig;

    @GetMapping("test")
    public Result test() {
        return Result.success(RequestStatus.REQUEST_SUCCESS.getCode(),
                "三天之内");
    }

    /**
     * 博客列表
     *
     * @param currentPage 分页页数
     * @return 博客分页列表
     */
    @GetMapping("list")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page<HBlog> page = new Page<>(currentPage, 5);
        // 根据创建时间查询出博客集合，并利用分页封装
        Page blogPage = ihBlogService.page(page, new QueryWrapper<HBlog>().orderByDesc("created"));
        return Result.success(blogPage);
    }

    /**
     * 查看博客详情
     *
     * @param id blog id
     * @return blog had been queried
     */
    @GetMapping("detail/{id}")
    public Result detail(@PathVariable Long id) {
        HBlog blog = ihBlogService.getById(id);
        Assert.notNull(blog, "该博客不存在");

        return Result.success(blog);
    }

    /**
     * 编辑或添加博客
     * Edit or save blog
     *
     * @param blog      博客
     * @param principal 当前用户
     * @return message
     */
    @PostMapping("edit")
    public Result list(@Validated @RequestBody HBlog blog, Principal principal) throws IOException {
        HUser currentUser = gson.fromJson(principal.getName(), HUser.class);

        HBlog temp;

        if (blog.getId() != null) {
            // 编辑已有博客
            temp = ihBlogService.getById(blog.getId());

            Assert.isTrue(Objects.equals(temp.getUserId(), currentUser.getId())
                    , "只能编辑自己的文章");
        } else {
            // 创建新博客对象
            temp = HBlog.builder()
                    .userId(currentUser.getId())
                    .created(LocalDateTime.now())
                    .status(0)
                    .build();
        }

        temp.setContent(blog.getContent());
        temp.setTitle(blog.getTitle());
        temp.setDescription(blog.getDescription());

        ihBlogService.saveOrUpdate(temp); // 添加或更新数据

        return Result.success(RequestStatus.REQUEST_SUCCESS.getCode()
                , "编辑成功");
    }


    /**
     * 阿里云信息
     *
     * @return oss message
     */
    @GetMapping("oss")
    public Result ossInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("endpoint", endpoint);
        map.put("accessKeyId", accessKeyId);
        map.put("accessKeySecret", accessKeySecret);
        map.put("bucket", bucket);

        return Result.success(map);
    }

    /**
     * 获取歌曲列表
     *
     * @return songs
     */
    @GetMapping("songs")
    public Result songs() {
        return Result.success(songsConfig.getSongs());
    }
}
