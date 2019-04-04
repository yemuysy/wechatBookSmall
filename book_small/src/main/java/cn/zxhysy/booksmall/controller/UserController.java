package cn.zxhysy.booksmall.controller;

import cn.zxhysy.booksmall.pojo.User;
import cn.zxhysy.booksmall.pojo.vo.UserVO;
import cn.zxhysy.booksmall.utils.ApiJSONResult;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @className: UserController
 * @description:
 * @author: zxh
 * @date: 2012-12-29 09:59:22
 */
@Api(value = "用户接口", tags = "用户操作")
@RestController
@RequestMapping("/user")
public class UserController {


    private final BasicController basicController;

    @Autowired
    public UserController(BasicController basicController) {
        this.basicController = basicController;
    }

    /**
     * 用户登录
     * paramType:
     * header：请求参数放置于Request Header，使用@RequestHeader获取
     * query：请求参数放置于请求地址，使用@RequestParam获取
     * path：（用于restful接口）-->请求参数的获取：@PathVariable
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，
     *                      使用code 换取 session_key api，将 code 换成 openid 和 session_key
     * @return 用户信息
     */
    @ApiOperation(value = "微信登录", notes = "微信用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "encryptedData", value = "明文", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "iv", value = "加密算法的初始向量", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "code", value = "临时登录凭证", required = true, dataType = "String")
    })
    @PostMapping(value = "/decodeUser")
    public ApiJSONResult weChatLogin(String encryptedData, String iv, String code) throws Exception {
        if (StringUtils.isBlank(encryptedData + iv + code)) {
            throw new Exception("参数不能为空");
        }
        User user = basicController.userService.weChatLogin(encryptedData, iv, code);
        String uniqueToken = UUID.randomUUID().toString();
        basicController.redisService.set(BasicController.USER_REDIS_SESSION + ":" + user.getId(), uniqueToken, 1000 * 60 * 30);
        UserVO userVo = new UserVO();
        BeanUtils.copyProperties(user, userVo);
        userVo.setUserToken(uniqueToken);
        return ApiJSONResult.ok(userVo);
    }

    /**
     * 用户更新
     *
     * @param user 用户对象
     * @return 成功与否
     */
    @ApiOperation(value = "用户更新", notes = "用户更新")
    @PutMapping
    public ApiJSONResult updateUser(@ApiParam(value = "用户对象", required = true) @RequestBody User user) {
        boolean b = basicController.userService.updateUser(user);
        if (b) {
            return ApiJSONResult.ok("更新成功");
        } else {
            return ApiJSONResult.errorMsg("更新失败");
        }

    }
}
