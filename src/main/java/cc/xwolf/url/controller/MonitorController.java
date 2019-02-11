package cc.xwolf.url.controller;

import cc.xwolf.url.HttpUtils;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RequestMapping(value = "/monitor")
@Log
@RestController
public class MonitorController {
  @Value("${xwolf.owner:zu}")
String owner;
  @RequestMapping(value = "/hello")
  public String messageBusClient( ) {
    log.info(" ====messageBusClient====");
    return owner;
  }

  /**
   * 检测指定url的http请求返回的状态码
   * @param url
   * @param code
   * @return
   */
  @RequestMapping(value = "/grab")
  public boolean grab( String url,int code ) {
    return HttpUtils.state(url)==code;
  }

}
