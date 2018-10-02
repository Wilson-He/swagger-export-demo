package per.web.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 *
 * @author Wilson
 * @date 18-7-10
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8080/")
                .pathProvider(new SwaggerPathProvider("/pms"))
                .pathMapping("/")
                .apiInfo(apiInfo())
                .protocols(Sets.newHashSet("http", "https"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("per"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("user-consumer")
//                .termsOfServiceUrl("用户消费者模块")
                .version("latest")
//                .termsOfServiceUrl("http://localhost:51001/")
//            .contact(new Contact("Wilson", "http://blog.csdn.net/z28126308", "845023508@qq.com"))
                .contact(new Contact("Wilson","http://blog.csdn.net/z28126308","http://blog.csdn.net/z28126308"))
                .license("Wilson_license")
                .licenseUrl("http://blog.csdn.net/z28126308")
                .build();
    }

}
