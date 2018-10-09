package per.web.config.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * @author: hewx
 * @date: 2018/10/8
 * @since:
 */
@Validated
public class UserBase {
  @NotBlank
  private String name;
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
