package cn.zxhysy.booksmall.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @className: FileUtil
 * @description: 文件处理，文件转换
 * @author: zxh
 * @date:
 */
@Service
public class FileUtil {

    /**
     * 带文件名的Path：如 D:\\xml2\\2018\\04\\abc.text
     * 判断文件是否存在
     *
     * @param filePath 文件
     */
    public static void isExistFile(String filePath) throws Exception {
        String[] paths = filePath.split("\\\\");
        String dir = paths[0];
        //注意此处循环的长度
        for (int i = 0; i < paths.length - 2; i++) {
            File dirFile;
            try {
                dir = dir + "/" + paths[i + 1];
                dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                }
            } catch (Exception err) {
                System.err.println("文件夹创建发生异常");
            }
        }
        File file = new File(filePath);

        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new Exception("文件创建失败");
            }
        }
    }

    /**
     * 根据url拿取file
     *
     * @param url    路径
     * @param suffix 文件后缀名
     */
    public static File createFileByUrl(String url, String suffix) {
        byte[] byteFile = getImageFromNetByUrl(url);
        if (byteFile != null) {
            return getFileFromBytes(byteFile, suffix);
        } else {
            return null;
        }
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 路径
     *               网络连接地址
     * @return 数据字节流
     */
    private static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            // 通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            // 得到图片的二进制数据
            return readInputStream(inStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return 字节流数据 byte[]
     * @throws Exception 异常
     */
    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 创建临时文件
     *
     * @param b      字节流 byte[]
     * @param suffix 后缀
     * @return 文件
     */
    private static File getFileFromBytes(byte[] b, String suffix) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = File.createTempFile("pattern", "." + suffix);
            System.out.println("临时文件位置：" + file.getCanonicalPath());
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static MultipartFile createImg(String url) {
        try {
            // File转换成MutipartFile
            File file = FileUtil.createFileByUrl(url, "jpg");
            return new MockMultipartFile(file.getName(), new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MultipartFile fileToMultipart(String filePath) {
        try {
            // File转换成MutipartFile
            File file = new File(filePath);
            FileInputStream inputStream = new FileInputStream(file);
            return new MockMultipartFile(file.getName(), "png", "image/png", inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean base64ToFile(String filePath, String base64Data) throws Exception {
        /*

        String dataPrix = "";

        * */

        String data;

        if (base64Data == null || "".equals(base64Data)) {
            return false;
        } else {
            String[] d = base64Data.split("base64,");
            int i = 2;
            if (i == d.length) {
//                dataPrix = d[0];
                data = d[1];
            } else {
                return false;
            }
        }

        // 因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
        byte[] bs = Base64Utils.decodeFromString(data);
        // 使用apache提供的工具类操作流
        org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(filePath), bs);

        return true;
    }
}


