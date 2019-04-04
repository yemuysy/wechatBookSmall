package cn.zxhysy.booksmall.controller;

import cn.zxhysy.booksmall.pojo.Category;
import cn.zxhysy.booksmall.utils.ApiJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: book_small
 * @className: CategoryController
 * @description: 类别操作
 * @author: zxh
 * @date: 2019-04-01 00:20
 */
@Api(value = "类别接口", tags = "类别操作")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final BasicController basicController;

    @Autowired
    public CategoryController(BasicController basicController) {
        this.basicController = basicController;
    }

    @ApiOperation(value = "获取全部类别信息")
    @GetMapping
    public ApiJSONResult showAllCategory(){
        List<Category> allCategory = basicController.categoryService.getAllCategory();
        return ApiJSONResult.ok(allCategory);
    }

}
