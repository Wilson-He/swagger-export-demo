package per.web.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.web.config.model.UserBase;

/**
 * @author: hewx
 * @date: 2018/9/28
 * @since:
 */
@SpringBootApplication
@RestController
@RequestMapping("/")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
