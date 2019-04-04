package cn.zxhysy.booksmall.service;

import cn.zxhysy.booksmall.pojo.User;

/**
 * @className: UserService
 * @description:
 * @author: zxh
 * @date: 2019-1-29 9:20:23
 */
public interface UserService {

    /**
     * 保存用户信息
     *
     * @param user 用户对象
     * @return 返回是否成功
     */
    boolean saveUser(User user);

    /**
     * 微信登录
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          code值五分钟限制
     * @return 返回用户信息
     * @throws Exception 登录失败
     */
    User weChatLogin(String encryptedData, String iv, String code) throws Exception;

    /**
     * 用户更新
     *
     * @param user 用户对象
     * @return 更新成功或失败
     */
    boolean updateUser(User user);

    /**
     * 根据 openid 查询对象信息
     *
     * @param openId 微信openid
     * @return 用户对象
     */
    User queryUserByOpenid(String openId);
}
