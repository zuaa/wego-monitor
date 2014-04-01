package com.dandd.tools;/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-24
 * Time: 上午11:27
 * To change this template use File | Settings | File Templates.
 */
class SearchFiles {

    static main(args) {

        searchDir("E:\\web20130926\\sedisk\\web\\projects\\qic\\apps\\gecweb")

    }


    static searchDir(String dir){
        new File(dir).eachFile{ file->
            if(file.isFile()){
                searchFile("${file}")
            }else if(file.isDirectory()){
                searchDir("${file}")
            }else{
                println "Uh, I'm not sure what it is..."
            }
        }
    }
    static  searchFile(String file){
        println("${file}")
    }
}