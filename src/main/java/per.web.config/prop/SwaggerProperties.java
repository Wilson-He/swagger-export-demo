package per.web.config.prop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * @author: hewx
 * @date: 2018/9/26
 * @since:
 */
@Component
@ConfigurationProperties("swagger")
@PropertySource(value = "classpath:/swagger.properties",encoding = "utf-8")
@Setter
@ToString
@Getter
public class SwaggerProperties {

  private String title;
  private String serviceUrl;
  private String description;
  private String license;
  private String version;
  private String contactName;
  private String contactUrl;
  private String contactEmail;
  private String groupName;
  private String host;

  public ApiInfo toApiInfo() {
    return new ApiInfoBuilder()
        .title(title)
        .termsOfServiceUrl(serviceUrl)
        .description(description)
        .license(license)
        .version(version)
        .contact(new Contact(contactName, contactUrl, contactEmail))
        .build();
  }
}
