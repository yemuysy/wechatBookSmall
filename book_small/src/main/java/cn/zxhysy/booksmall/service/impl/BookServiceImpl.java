package cn.zxhysy.booksmall.service.impl;

import cn.zxhysy.booksmall.mapper.BookMapper;
import cn.zxhysy.booksmall.pojo.Book;
import cn.zxhysy.booksmall.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * @program: book_small
 * @className: BookServiceImpl
 * @description: 图书服务
 * @author: zxh
 * @date: 2019-03-29 17:02
 */
@Service
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    /**
     * 根据书本id查询
     *
     * @param id 书本id
     * @return 图书信息
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Book getBookById(String id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页类别查询
     *
     * @param categoryId 类别id
     * @return 分页信息
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public PageInfo<Book> getBookByCategoryId(Integer page, Integer pageSize, Integer categoryId){
        if(categoryId == 0){
          categoryId = null;
        }
        PageHelper.startPage(page, pageSize);
        Example example = new Example(Book.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",categoryId);
        List<Book> bookList =  bookMapper.selectByExample(example);
        return new PageInfo<>(bookList);
    }

    /**
     * 根据书本名称查询书本信息
     *
     * @param pageNum 当前页
     * @param pageSize 个数
     * @param name 图书名字
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public PageInfo<Book> getBookByName(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(Book.class);
        Criteria criteria = example.createCriteria();
        criteria.andLike("name", "%"+name+"%");

        List<Book> bookList =  bookMapper.selectByExample(example);
        return new PageInfo<>(bookList);
    }

}
