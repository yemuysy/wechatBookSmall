package cn.zxhysy.booksmall.controller;

import cn.zxhysy.booksmall.pojo.User;
import cn.zxhysy.booksmall.utils.ApiJSONResult;
import cn.zxhysy.booksmall.utils.FileUtil;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @className: FastDFSController
 * @description:
 * @author: zxh
 * @date: 2018-12-29 09:58:59
 */
@Api(value = "文件上传接口", tags = "文件操作")
@RestController
@RequestMapping("/fdfs")
public class FastdfsController {

    private BasicController basicController;

    @Autowired
    public FastdfsController(BasicController basicController) {
        this.basicController = basicController;
    }


    /**
     * 上传文件
     * @param file 文件
     * @return 文件路径
     * @throws Exception 异常
     */
    @ApiOperation(value = "文件上传", notes = "上传文件")
    @PostMapping
    public ApiJSONResult uploadFile2(@ApiParam(value = "上传的文件", required = true) MultipartFile file) throws Exception {
        if (file == null) {
            throw new Exception("文件不能为空");
        }
        String path = basicController.fastDFSClient.uploadFile(file);

        return ApiJSONResult.ok(path);
    }
    /**
     * @param file   上传的文件
     * @param userId 用户id
     * @return StorePath
     * @deprecated 文件上传
     * storePath
     * "group": "zxh",
     * "path": "M00/00/01/wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx",
     * fullPath": "group1/M00/00/01/wKj4RFvuZlOALpzMAAJZc0IRPSY16.docx"
     */
    @ApiOperation(value = "文件上传", notes = "根据用户id上传文件")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Integer", paramType = "query")
    @PostMapping(value = "/upload")
    public ApiJSONResult uploadFileAndUserId(@ApiParam(value = "上传的文件", required = true) MultipartFile file, Integer userId) throws Exception {
        /* 设置文件信息 */
        if (StringUtils.isBlank(userId.toString())) {
            throw new Exception("userId不能为空");
        }

        if (file == null) {
            throw new Exception("文件不能为空");
        }

        String path = basicController.fastDFSClient.uploadFile(file);

        return getApiJSONResultByImg(userId, path);
    }
    /**
     * @param base64 base64字符串
     * @param userId 用户id
     * @return 返会图片路径
     * @throws Exception 异常
     */
    @ApiOperation(value = "图片上传", notes = "base64图片上传")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "base64", value = "图片base64字符串", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "Integer"),
    })
    @PostMapping("/uploadFaceBase64")
    public ApiJSONResult uploadFaceBase64(String base64, Integer userId) throws Exception {
        System.out.println(base64);
        System.out.println(userId);
        /* 这里可以根据部署服务器，换成对应的服务器就行了 */
        String userFacePath = "E:\\bookSmallImg\\" + userId + "\\userface64.png";
        /* 判读文件是否存在，不存在就创建 */
        FileUtil.isExistFile(userFacePath);

        FileUtil.base64ToFile(userFacePath, base64);

        /*上传文件到fastdfs */
        MultipartFile faceFile = FileUtil.fileToMultipart(userFacePath);
        System.out.println(faceFile);
        String path = basicController.fastDFSClient.uploadBase64(faceFile);

        return getApiJSONResultByImg(userId, path);
    }

    /**
     * @description: 根据id查询对象
     * @param: userId
     * @param: path
     * @return:
     * @author: zxh
     * @date: 2019/3/29 11:58
     */
    private ApiJSONResult getApiJSONResultByImg(Integer userId, String path) {
        User user = new User();
        user.setAvatarUrl("http://39.108.13.196:88/zxh/" + path);
        user.setId(userId);
        basicController.userService.updateUser(user);
        return ApiJSONResult.ok("http://39.108.13.196:88/zxh/" + path);
    }

}
