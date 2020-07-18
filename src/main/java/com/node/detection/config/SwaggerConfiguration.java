package com.node.detection.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 集成 Swagger 文档
 * 访问 http://localhost:8081/doc.html
 * @author xinyu
 * @Configuration 配置类
 * @EnableSwagger2 开启 swagger 文档
 * @EnableKnife4j 开启增强功能
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

}
