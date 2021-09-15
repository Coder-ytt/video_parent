package com.fmjava.service_video.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fmjava.service_video.entity.Author;
import com.fmjava.service_video.entity.vo.AuthorQuery;
import com.fmjava.service_video.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fmjava.utils.ResponseResult;
import java.util.List;

/**
 * <p>
 * 创作者 前端控制器
 * </p>
 *
 * @author fmjava
 * @since 2021-09-04
 */
@RestController
@CrossOrigin
@RequestMapping("/service_video/author")
@Api(tags = "作者组")  //分组
public class AuthorController {

    /**
     * 使用代码生成器，生成的service当中已经有了很多的基础服务，直接调用即可
     */
    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "所有作者列表数据")
    @GetMapping("getAuthorList")
    public List<Author> getAuthorList(){
        System.out.println(1/0);
        List<Author> list = authorService.list(null);

        return list;
    }

    /**
     * 根据id删除
     */
    @ApiOperation(value = "逻辑删除作者")
    @PostMapping("/deleteAuthor/{id}")
    public ResponseResult deleteAuthor(@ApiParam(name = "id",value = "作者",required = true) @PathVariable String id){
        authorService.removeById(id);
        return ResponseResult.ok();
    }


    /*分页+条件*/
    @ApiOperation(value = "作者条件分页列表数据")
    @PostMapping("/pageList/{page}/{limit}")  /*必须使用post  get请求没有办法传递json*/
    public ResponseResult pageList(
            @ApiParam(name ="page",value = "当前页",required = true)
            @PathVariable Long page,
            @ApiParam(name ="limit",value = "每页记录数",required = true)
            @PathVariable Long limit,
            @ApiParam(name = "authorQuery", value = "查询对象", required = false)
            @RequestBody(required = false) AuthorQuery authorQuery){
        /*分页查询*/
        Page<Author> pageInfo = new Page<>(page,limit);
        /*调用业务做分页条件查询  查询的结果都封装到了pageInfo*/
        authorService.pageQuery(pageInfo,authorQuery);
        /*获取当前页的数据*/
        List<Author> records = pageInfo.getRecords();
        /*获取总记录*/
        long total = pageInfo.getTotal();
        return ResponseResult.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "根据id查询作者")
    @GetMapping("/getAuthorWithId/{id}")
    public ResponseResult getAuthorWithId(
            @ApiParam(name ="id",value = "作者的id",required = true)
            @PathVariable String id){
        Author author = authorService.getById(id);
        return ResponseResult.ok().data("item",author);
    }

    @ApiOperation(value = "添加作者")
    @PostMapping("/addAuthor")
    public ResponseResult addAuthor(
            @ApiParam(name ="author",value = "作者对象",required = true)
            @RequestBody Author author){
        authorService.save(author);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "更新作者")
    @PostMapping("/updateAuthor")
    public ResponseResult updateAuthor(
            @ApiParam(name ="author",value = "作者对象",required = true)
            @RequestBody Author author){
        boolean b = authorService.updateById(author);
        if(b){
            return ResponseResult.ok();
        }else {
            return ResponseResult.error();
        }

    }


}

