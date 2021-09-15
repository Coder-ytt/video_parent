package com.fmjava.service_video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fmjava.service_video.entity.Author;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fmjava.service_video.entity.vo.AuthorQuery;

/**
 * <p>
 * 创作者 服务类
 * </p>
 *
 * @author fmjava
 * @since 2021-09-04
 */
public interface AuthorService extends IService<Author> {
    void pageQuery(Page<Author> pageInfo, AuthorQuery authorQuery);
}
