package per.web.config.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import per.web.config.model.UserBase;

/**
 * @author: hewx
 * @date: 2018/10/8
 * @since:
 */
@Api(tags = "索引控制器")
@RequestMapping("/index")
public interface IndexController {

  @PostMapping("/")
  @ApiOperation("添加method")
  Object add(@Validated @RequestBody UserBase userBase);

  @GetMapping("/")
  @ApiOperation("名字method")
  Object get(@ApiParam("名字") @RequestParam String name,
      @ApiParam("密码") @NotBlank @RequestParam String password);
}
