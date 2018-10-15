package per.web.config.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api
@RequestMapping("/")
public class IndexControllerImpl implements IndexController {

  @Override
  @ApiOperation("add user")
  @PostMapping("/")
  public Object add(@Validated @RequestBody UserBase userBase) {
    return userBase.toString();
  }

  @Override
  @ApiOperation("get user")
  @GetMapping("/")
  public Object get(String name, String password) {
    return "name:" + name + "\tpassword:" + password;
  }
}
