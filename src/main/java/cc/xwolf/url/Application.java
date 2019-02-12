package cc.xwolf.url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; ;

/**
 * 方便测试 springcloud中的服务 所有开启feign 以及discover
 */
//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
