package per.web.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hewx
 * @date: 2018/9/28
 * @since:
 */
@SpringBootApplication
@RestController
@RequestMapping("/")
public class Application {

  @GetMapping("/index")
  @ApiOperation("首页")
  public String index() {
    return "index";
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
