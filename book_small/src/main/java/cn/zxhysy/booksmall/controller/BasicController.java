package cn.zxhysy.booksmall.controller;

import cn.zxhysy.booksmall.service.*;
import cn.zxhysy.booksmall.utils.component.FastDFSClient;
import cn.zxhysy.booksmall.utils.component.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @className: BasicController
 * @description: 模块化服务
 * @author: zxh
 * @date: 2018-12-29 09:58:36
 */
@ApiIgnore
@Component
public class BasicController {
    public final AdminService adminService;
    public final RedisOperator redisService;
    public final FastDFSClient fastDFSClient;
    public final UserService userService;
    public final BookService bookService;
    public final CategoryService categoryService;
    public final OrderService orderService;
    @Autowired
    public BasicController(AdminService adminService, RedisOperator redisService, FastDFSClient fastDFSClient, UserService userService,BookService bookService,CategoryService categoryService, OrderService orderService) {
        this.adminService = adminService;
        this.redisService = redisService;
        this.fastDFSClient = fastDFSClient;
        this.userService = userService;
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    /**
     * 用户登录回话状态钥匙
     */
    public static final String USER_REDIS_SESSION = "user_redis_session";
    /**
     * 文件保存的命名空间
     */
    public static final String FILE_SPACE = "D:/zxh_wenjian";
    /**
     * ffmpeg所在目录
     */
    public static final String FFMPEG_EXE = "C:\\ffmpeg\\bin\\ffmpeg.exe";
    /**
     * 每页分页的记录数
     */
    public static final Integer PAGE_SIZE = 5;

}
