package com.otherTools;/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-25
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
class dealLogFile {

    static main(args) {
        def file =new File("C:\\Users\\xuping\\Desktop\\Noname1.txt")
        file.eachLine{
            if(it.startsWith("\"")){
                def filepath= it.tokenize("\"")[0].replace("E:\\web20130926\\sedisk\\web\\projects\\qic","\\gec")
                filepath=filepath.replace("\\","/")
                println "mv ${filepath} /home/qic"
            }

        }
        println "================"
        file.eachLine{
            if(it.startsWith("\"")){
                def filepath= it.tokenize("\"")[0].replace("E:\\web20130926\\sedisk\\web\\projects\\qic","\\gec")
                filepath=filepath.replace("\\","/")

//                println ("${filepath.lastIndexOf("/")} ${filepath.length()}")
                def fileName=filepath[filepath.lastIndexOf("/")+1..filepath.length()-1]

                println("cp /home/qic/${fileName} ${filepath.replace("${fileName}","")}")

            }
        }
    }


}