package cn.zxhysy.booksmall.config;

import cn.zxhysy.booksmall.handler.MyHandlerInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: WebConfig
 * @description: web 配置文件
 * @author: zxh
 * @date: 2018-3-25 09:57:45
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * @description: 配置消息转换器--这里我用的是alibaba 开源的 fastjson
     * @param: converters
     * @return:
     * @author: zxh
     * @date: 2019/3/29
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //5.将convert添加到converters当中.
        //指定 fastJson 执行顺序，不然可能会用 jsonfast SpringBoot 2.0.1版本中加载WebMvcConfigurer的顺序发生了变动
        // 故需使用converters.add(0, converter);指定FastJsonHttpMessageConverter在converters内的顺序，否则在SpringBoot 2.0.1及之后的版本中将优先使用Jackson处理
        converters.add(0, fastJsonHttpMessageConverter);
    }

    /**
     * @description: 配置跨域
     * @param: CorsRegistry
     * @return:
     * @author: zxh
     * @date: 2019/3/29
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

    /**
     * 这里作用是为了让 MyHandlerInterceptor 里的 redis 能够自动注入
     * @return
     */
    @Bean
    public MyHandlerInterceptor myHandlerInterceptor(){
        return new MyHandlerInterceptor();
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHandlerInterceptor())
                .addPathPatterns("/order/**")
                .addPathPatterns("/user")
                .addPathPatterns("/fdfs/**");
    }

    /**
     * 添加类型转换器和格式化器
     * @param registry
     */
/*    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(LocalDate.class, new USLocalDateFormatter());
    }*/

    /**
     * 添加静态资源--过滤swagger-api (开源的在线API文档)
     * @param registry
     */
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //过滤swagger
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger*");

        registry.addResourceHandler("/v2/api-docs/**")
                .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");

    }*/

}