package cn.zxhysy.booksmall.controller;

import cn.zxhysy.booksmall.pojo.Book;
import cn.zxhysy.booksmall.utils.ApiJSONResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: book_small
 * @className: BookServiceController
 * @description: 图书操作类
 * @author: zxh
 * @date: 2019-03-29 17:11
 */
@Api(value = "图书管理接口", tags = "图书操作")
@RestController
@RequestMapping("/book")
public class BookController {

    private final BasicController basicController;

    @Autowired
    public BookController(BasicController basicController) {
        this.basicController = basicController;
    }

    @ApiOperation(value = "根据图书id获取图书信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "图书", required = true, dataType = "String")
    @GetMapping("{id}")
    public ApiJSONResult getBookById(@PathVariable("id") String id) {
        Book book = basicController.bookService.getBookById(id);
        return ApiJSONResult.ok(book);
    }

    @ApiOperation(value = "根据类别id获取图书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "显示记录数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "categoryId", value = "图书类别id", required = true, dataType = "Integer")
    })
    @GetMapping("/category")
    public ApiJSONResult getPageBookByCategoryId(Integer pageNum, Integer pageSize, Integer categoryId){
        PageInfo<Book> list = basicController.bookService.getBookByCategoryId(pageNum,pageSize,categoryId);
        return ApiJSONResult.ok(list);
    }
    @ApiOperation(value = "根据类别id获取图书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "显示记录数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "图书名字", required = true, dataType = "String")
    })
    @GetMapping("/search")
    public ApiJSONResult getAllBookByName(Integer pageNum, Integer pageSize, String name){
        PageInfo<Book> bookList = basicController.bookService.getBookByName(pageNum, pageSize, name);
        return ApiJSONResult.ok(bookList);
    }
}
