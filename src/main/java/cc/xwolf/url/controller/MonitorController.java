package cc.xwolf.url.controller;

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
  @RequestMapping(value = "/grab")
  public String grab( String url,String left,String right) {
    try {
      Document doc = Jsoup.connect(url).get();


    } catch (IOException e) {
      e.printStackTrace();
    }

    return owner;
  }

}
