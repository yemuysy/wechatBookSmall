package cn.zxhysy.booksmall.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;


/**
 * @className: FastdfsConfig
 * @description: jmx 重复注册bean的问题 导入依赖组件
 * @author: zxh
 * @date: 2018-12-29 09:56:31
 */
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastDFSConfig {
}
