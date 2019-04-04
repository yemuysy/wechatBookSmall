package cn.zxhysy.booksmall.service;

import cn.zxhysy.booksmall.pojo.Category;

import java.util.List;

/**
 * @className: CategoryService
 * @description: 类别服务
 * @author: zxh
 * @date: 2019年4月1日00:11:42
 */
public interface CategoryService {

    /**
     * 获取全部分类信息
     *
     * @return List<Category>
     */
    List<Category> getAllCategory();

}
