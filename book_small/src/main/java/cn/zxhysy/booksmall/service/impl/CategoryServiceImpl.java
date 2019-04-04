package cn.zxhysy.booksmall.service.impl;

import cn.zxhysy.booksmall.mapper.CategoryMapper;
import cn.zxhysy.booksmall.pojo.Category;
import cn.zxhysy.booksmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: book_small
 * @className: CategoryServiceImpl
 * @description: 类别接口服务
 * @author: zxh
 * @date: 2019-04-01 00:14
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 查询类别信息
     *
     * @return List<Category>
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.selectAll();
    }
}
