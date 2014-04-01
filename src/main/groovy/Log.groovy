/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-22
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */
class Log {


static File log= new File("/log.maid");


    static void main(agrs){

    }
    static writeLog(String logstr){
        log<<logstr<<"\n"
    }
}
