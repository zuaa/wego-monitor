wego-monitor
维构的监控工程，监控核心。用于检测网站内容以及网站可用性。


监控
==============================
调用当方法
http://*******:4287/monitor/grab?url=http://www.baidu.com&eval=x.select('form').size()%20==1

eval后面是一个jsoup的语法，x就是url之后的对象。相当于：Document x =Jsoup.parse(html);


jsoup的介绍，官方网站-->http://jsoup.org/


--------------
没有注册中心的可以注释掉
Application下的代码
//@EnableFeignClients
//@EnableDiscoveryClient
 