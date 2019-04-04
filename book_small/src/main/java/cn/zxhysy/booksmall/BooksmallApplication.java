package cn.zxhysy.booksmall;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/** 这里需要用的是 tk.的mapperScan */
@MapperScan("cn.zxhysy.booksmall.mapper")
public class BooksmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksmallApplication.class, args);
    }

}
