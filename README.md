wego-monitor
维构的监控工程，监控核心。用于检测网站内容以及网站可用性。


监控
==============================
    调用当方法
    http://127.0.0.1:4287/monitor/grab?url=http://baidu.com&code=200
        true
    http://127.0.0.1:4287/monitor/grab?url=http://baidu.com&code=202
        false
--------------
没有注册中心的可以注释掉
Application下的代码
//@EnableFeignClients
//@EnableDiscoveryClient
 
 
 #TODO
  
 1. 增加调度任务  
 2. 增加报警通知
 