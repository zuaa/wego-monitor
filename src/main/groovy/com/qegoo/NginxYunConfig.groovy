 

//根据云生成nginx配置文件

 
 
 
 
 
 
 
int portstat=10001;
String allUpstream=""
( [1,2,3] as String[]).each{
	allUpstream=allUpstream+upstream(portstat);
	println service(portstat++,"http://heiji${it}.aliapp.com/","http://yungoogle/"); 
}
println """  upstream yunali {"""
println allUpstream;
println """}"""

println service(30009,"http://yunali/","http://yungoogle/");



allUpstream=""
( [0,1,2,3,4,5,"6-",7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23] as String[]).each{
	allUpstream=allUpstream+upstream(portstat);
	println service(portstat++,"http://heiji${it}zuaa.appspot.com/");
}

( [99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123] as String[]).each{
	allUpstream=allUpstream+upstream(portstat);
	println service(portstat++,"http://qegoocn-${it}.appspot.com/");
} 
 
( [99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123] as String[]).each{
	allUpstream=allUpstream+upstream(portstat);
	println service(portstat++,"http://qegoocn1-${it}.appspot.com/");
}

( [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19.20] as String[]).each{
	allUpstream=allUpstream+upstream(portstat);
	println service(portstat++,"http://qegoo-${it}.appspot.com/");
}

println """  upstream yungoogle{"""
println allUpstream;
println """}"""
allUpstream=""
println service(30008,"http://yungoogle/","http://yunali/");


( [6,7,8,9,10,11,12,13,14,15,16] as String[]).each{
	allUpstream=allUpstream+upstream(portstat);
	println service(portstat++,"http://heiji${it}.duapp.com/grabpoint/");
}

println """  upstream yunbaidu{"""
println allUpstream;
println """}"""
allUpstream=""
println service(30007,"http://yunbaidu/","http://yungoogle/");



 
println """  upstream all{ 
	${upstream(30009)}
	${upstream(30008)}
	${upstream(30007)}
}"""
 
println service(80,"http://all/","http://yungoogle/");


 
















def upstream(port){
	def re="""	server 127.0.0.1:${port}  weight=50;
"""
}
def service(port,yunurl){
	 return service(port,yunurl,"http://yunali/");
}

def service(port,yunurl,error){
	def re="""
    server {
        listen       ${port};
        location / { 
            proxy_pass ${yunurl};
        }
        error_page  400 401 403 404  500 502 503 504  /50x.html;
        location = /50x.html {
			#出现错误将请求转发给他的服务组
             proxy_pass ${error};
        }

    }
"""
return re;
}






