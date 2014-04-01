package com.dandd.tools;/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-24
 * Time: 下午2:01
 * To change this template use File | Settings | File Templates.
 */
class ReadFile {

    static main(args) {
        def file = new File("e://2")
        file.eachLine {
            def a = (it.tokenize(" "))
            def filename = a[1][a[1].lastIndexOf("/") + 1..a[1].length() - 1]
            println a[0] + " " + a[2] + "${filename} " + a[1]
        }
    }


}