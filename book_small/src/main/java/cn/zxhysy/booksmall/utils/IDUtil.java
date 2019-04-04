package cn.zxhysy.booksmall.utils;

import java.util.Random;

/**
 * @className: IDUtil
 * @description: 商品id生成工具
 * @author: zxh
 * @date:
 */
public class IDUtil {

    /**
     * 如果采用fastDFS,图片名字是自动生产的
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒 long millis = System.nanoTime()
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        return millis + String.format("%03d", end3);
    }

    /**
     * 详情id生成
     */
    public static String getDetailId() {
        //取当前时间的长整形值包含毫秒  long millis = System.nanoTime()
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        return "DETAIL"+millis + String.format("%02d", end2);
    }

    /**
     * 主表id生成
     */
    public static String getMasterId() {
        //取当前时间的长整形值包含毫秒  long millis = System.nanoTime()
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        return "MASTER"+millis + String.format("%02d", end2);
    }
}
