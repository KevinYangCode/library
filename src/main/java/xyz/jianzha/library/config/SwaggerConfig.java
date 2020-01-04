package xyz.jianzha.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * http://localhost:8080/swagger-ui.html
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:31
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 配置了Swagger 的Docket 的bean实例
     *
     * @return
     */
    @Bean
    public Docket docket(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 右上的组名
                .groupName("library")
                /* select()与apis()与build()一套使用 */
                .select()
                .build();
    }

    /**
     * 配置Swagger 信息= apiInfo
     *
     * @return
     */
    private ApiInfo apiInfo() {
        // 信息
        Contact contact = new Contact("Kevin", "https://github.com/KevinYangCode", "851512175@qq.com");
        return new ApiInfo(
                "图书管理系统的Api文档",
                "KevinYangCode",
                "1.0",
                "https://github.com/KevinYangCode",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
