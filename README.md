wego-monitor
维构的监控工程，监控核心。用于检测网站内容以及网站可用性。


监控
==============================
调用当方法
http://ip/productName/Grab?url=http://www.baidu.com&eval=x.select('form').size()%20==1

eval后面是一个jsoup的语法，x就是url之后的对象。相当于：Document x =Jsoup.parse(html);


jsoup的介绍，官方网站-->http://jsoup.org/


===============================
Java + Groovy + Maven + War template with GitHub repository. For developement environment setup-test use case, and servlet programming quickstart.

Maven Build:

    mvn clean groovy:compile groovy:testCompile package


Tomcat6: `$ mvn tomcat6:run`  and open `http://localhost:8090/tomcat6-demo` .

Tomcat7: `$ mvn tomcat7:run` and open `http://localhost:8090/tomcat7-demo` .

Powered By [Apache Tomcat Maven Plugin 2.1](http://tomcat.apache.org/maven-plugin-2.1/index.html)

