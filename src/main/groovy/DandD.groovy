/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-22
 * Time: 下午3:31
 * To change this template use File | Settings | File Templates.
 */
class DandD {

    static int redomNumber(int num) {
        Random r = new Random();
        return r.nextInt(num) + 1
    }

    public static void main(args) {
        1000.times {
            print(redomNumber(19) + "==")
            Log.writeLog("========" + redomNumber(19) + "==")
        }
    }


}
