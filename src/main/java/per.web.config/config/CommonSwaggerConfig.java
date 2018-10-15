package per.web.config.config;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import per.web.config.prop.SwaggerProperties;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: hewx
 * @date: 2018/9/26
 * @since:
 */
@Configuration
@EnableSwagger2
@Import(SwaggerProperties.class)
@ConditionalOnProperty(havingValue = "true",name = "swagger.enabled")
public class CommonSwaggerConfig {

  private final static Logger LOGGER = LoggerFactory.getLogger(CommonSwaggerConfig.class);

  @Resource
  private SwaggerProperties swaggerProperties;

  @Bean
  public Docket docket() {
    System.err.println("init swagger:" + swaggerProperties.toString());
    LOGGER.info("init swagger:" + swaggerProperties.toString());
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName(swaggerProperties.getGroupName())
        .host(swaggerProperties.getHost())
        .pathMapping("/")
        .apiInfo(swaggerProperties.apiInfo())
        .protocols(Sets.newHashSet("http", "https"))
        .globalResponseMessage(RequestMethod.GET, responseMessageList())
        .globalResponseMessage(RequestMethod.POST, responseMessageList())
        .globalResponseMessage(RequestMethod.DELETE, responseMessageList())
        .globalResponseMessage(RequestMethod.PUT, responseMessageList())
        .select()
        .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
        .paths(PathSelectors.any())
        .build();
  }

  private List<ResponseMessage> responseMessageList() {
    List<ResponseMessage> list = new ArrayList<>(3);
    list.add(
        new ResponseMessageBuilder().code(0).responseModel(new ModelRef("JsonResult")).build());
    list.add(new ResponseMessageBuilder().code(-1).responseModel(new ModelRef("JsonResult"))
        .message("用户未登录").build());
    list.add(new ResponseMessageBuilder().code(404).responseModel(new ModelRef("JsonResult"))
        .message("页面不存在").build());
    return list;
  }
}