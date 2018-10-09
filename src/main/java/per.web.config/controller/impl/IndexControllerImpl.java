package per.web.config.controller.impl;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import per.web.config.controller.IndexController;
import per.web.config.model.UserBase;

/**
 * @author: hewx
 * @date: 2018/10/8
 * @since:
 */
@RestController
@Validated
public class IndexControllerImpl implements IndexController {

  @Override

  public Object add(@Validated UserBase userBase) {
    return userBase.toString();
  }

  @Override
  public Object get(String name, String password) {
    return "name:" + name + "\tpassword:" + password;
  }
}
