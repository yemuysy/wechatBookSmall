package cn.zxhysy.booksmall.service.impl;

import cn.zxhysy.booksmall.mapper.UserMapper;
import cn.zxhysy.booksmall.pojo.User;
import cn.zxhysy.booksmall.service.UserService;
import cn.zxhysy.booksmall.utils.AesCbcUtil;
import cn.zxhysy.booksmall.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: UserServiceImpl
 * @description:
 * @author: zxh
 * @date: 2019-1-29 10:04:23
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;
    @Value("${wx.grant_type}")
    private String grantType;
    @Value("${wx.url}")
    private String url;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean saveUser(User user) {
        /* 插入的时候回调id列 */
        int i = userMapper.insertUseGeneratedKeys(user);
        return i == 1;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public User queryUserByOpenid(String openId) {
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("openid", openId);
        return userMapper.selectOneByExample(userExample);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public User weChatLogin(String encryptedData, String iv, String code) throws Exception {
        Map<String, String> param = new HashMap<>(4);
        param.put("appid", appid);
        param.put("secret", secret);
        param.put("js_code", code);
        param.put("grant_type", grantType);

        //获取session_id
        String sr = HttpClientUtil.doGet(url, param);
        // 判断获取的是否有值
        if (StringUtils.isBlank(sr)) {
            throw new Exception("获取微信数据失败");
        }
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        String openid = json.getString("openid");
        User user = queryUserByOpenid(openid);
        if (user != null) {
            return user;
        }
        //获取会话密钥（session_key）
        String sessionKey = json.get("session_key").toString();
        //2、对encryptedData加密数据进行AES解密
        String result = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");

        if (StringUtils.isBlank(result)) {
            throw new Exception("解析微信数据失败");
        }

        JSONObject userInfoJSON = JSONObject.parseObject(result);
        user = new User();
        user.setOpenid(userInfoJSON.getString("openId"));
        user.setNickName(userInfoJSON.getString("nickName"));
        user.setAvatarUrl(userInfoJSON.getString("avatarUrl"));

        /* user 使用的是回调 id */
        boolean isSave = saveUser(user);
        System.out.println(user.getId());
        if (!isSave) {
            throw new Exception("服务器忙碌，请等下再试");
        }
//        user = queryUserByOpenid(openid);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean updateUser(User user) {
        Example userExample = new Example(User.class);
        Example.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andEqualTo("id", user.getId());

        int i = userMapper.updateByExampleSelective(user, userExample);
        return i == 1;
    }
}
