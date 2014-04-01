package com.maid.autoCode.bestpricecn.net;/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-30
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
class SaveUrl {

    static main(args) {
        saveImg("http://b.hiphotos.baidu.com/image/q%3D100%3Ba0%3D+%2C1%2C1/sign=5d2962e6f21f3a295cc8d1cea91edd01/738b4710b912c8fc12b335f5fe039245d6882142.jpg")

    }
    static saveImg(url){
        def fileName=System.currentTimeMillis()+url[url.lastIndexOf("/")+1..url.length()-1]
        def file = new File("c:/temp/${fileName}").newOutputStream()
        file << new URL(url).openStream()
        file.close()
        return "C:\\\\temp\\\\${fileName}"
    }
}