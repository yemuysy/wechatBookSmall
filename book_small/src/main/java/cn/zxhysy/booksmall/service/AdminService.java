package cn.zxhysy.booksmall.service;


import cn.zxhysy.booksmall.pojo.Admin;

/**
 * @className: AdminService
 * @description:
 * @author: zxh
 * @date: 2019-1-29 9:12:23
 */
public interface AdminService {

    /**
     * 管理员登录
     *
     * @param name     用户名
     * @param password 密码
     * @return 用户对象
     */
    Admin login(String name, String password);

}
