package com.fmjava.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fmjava.service_video.entity.Author;
import com.fmjava.service_video.entity.vo.AuthorQuery;
import com.fmjava.service_video.mapper.AuthorMapper;
import com.fmjava.service_video.service.AuthorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 创作者 服务实现类
 * </p>
 *
 * @author fmjava
 * @since 2021-09-04
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

    @Override
    public void pageQuery(Page<Author> pageInfo, AuthorQuery authorQuery) {
        QueryWrapper<Author> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if (authorQuery == null){
            //条件查询
            baseMapper.selectPage(pageInfo,queryWrapper);
            return;
        }
        /*有条件 判断条件是否为空*/
        if (!StringUtils.isEmpty(authorQuery.getName())){
            /*模糊查询name*/
            queryWrapper.like("name",authorQuery.getName());
        }
        if (!StringUtils.isEmpty(authorQuery.getLevel())){
            queryWrapper.eq("level",authorQuery.getLevel());
        }
        if (!StringUtils.isEmpty(authorQuery.getBegin())){  /*查询创建的时间大于等于开始时间*/
            queryWrapper.ge("gmt_create",authorQuery.getBegin());
        }
        if (!StringUtils.isEmpty(authorQuery.getEnd())){ /*查询创建的时间在小于等于结束时间*/
            queryWrapper.le("gmt_create",authorQuery.getEnd());
        }
        baseMapper.selectPage(pageInfo,queryWrapper);

    }
}
