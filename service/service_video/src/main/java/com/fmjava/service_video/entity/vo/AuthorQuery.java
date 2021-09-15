package com.fmjava.service_video.entity.vo;

import lombok.Data;

/**
 * vo当中的实体 与前端交互封装的实体类
 */
@Data
public class AuthorQuery {
    private String name;
    private Integer level;
    private String begin;
    private String end;
}
