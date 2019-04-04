package cn.zxhysy.booksmall.service;

import cn.zxhysy.booksmall.pojo.Book;
import com.github.pagehelper.PageInfo;

/**
 * @className: BookService
 * @description: 图书服务
 * @author: zxh
 * @date: 2019年3月31日23:35:56
 */
public interface BookService {

    /**
     * 根据id获取图书BookServer
     *
     * @param id 图书id
     * @return Book
     */
    Book getBookById(String id);

    /**
     * 根据 类别id 分页查询图书列表
     * @param pageNum 当前页
     * @param pageSize 显示记录数
     * @param categoryId 类别id
     * @return PageInfo
     */
    PageInfo<Book> getBookByCategoryId(Integer pageNum, Integer pageSize, Integer categoryId);

    /**
     * 根据书本名称查询书本信息
     *
     * @param pageNum 当前页
     * @param pageSize 个数
     * @param name 图书名字
     * @return
     */
    PageInfo<Book> getBookByName(Integer pageNum, Integer pageSize, String name);
}
