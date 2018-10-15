package per.web.config.model;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * @author: hewx
 * @date: 2018/10/8
 * @since:
 */
public class UserBase {
  @NotBlank
  @ApiModelProperty("姓名")
  private String name;
  @NotNull(message = "密码不能为空")
  private String password;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserBase{" +
        "name='" + name + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
