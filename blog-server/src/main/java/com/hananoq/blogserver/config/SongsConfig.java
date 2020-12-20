package com.hananoq.blogserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author :花のQ
 * @since 2020/12/6 19:06
 * 用于加载配置文件中的文化歌曲信息列表
 **/
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "mysongs")
public class SongsConfig {

    private List<Map<String, String>> songs;

    public List<Map<String, String>> getSongs() {
        return songs;
    }

    public void setSongs(List<Map<String, String>> songs) {
        this.songs = songs;
    }
}
