package com.hananoq.blogserver;

import com.hananoq.blogserver.mapper.HBlogMapper;
import com.hananoq.blogserver.service.IHBlogService;
import com.hananoq.common.entity.HBlog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class BlogServerApplicationTests {

    @Resource
    private IHBlogService blogService;

    @Resource
    private HBlogMapper blogMapper;

    @Test
    void contextLoads() {
        HBlog blog = HBlog.builder()
                .userId(3L)
                .created(LocalDateTime.now())
                .status(0)
                .content("阿富汗撒旦发哈点五十分")
                .description("撒大好风光赛夺冠哈")
                .title("方式考拉海购飞洒地方即可")
                .build();

        System.out.println("blog = " + blog);

        blogService.save(blog);
    }

    @Test
    void name() {
        List<HBlog> list = blogService.list();
        list.forEach(blog -> System.out.println(blog.getId()));
    }
}
