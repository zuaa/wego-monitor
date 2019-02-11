package cc.xwolf.url;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
    public static void main(String[] args) {
        System.out.println(HttpUtils.state("http://www.baidu.com"));
    }
    public static   int state(String url){
        if (url == null || url.length() <= 0) {
            return 0;
        }
        try {
            URL urlObj =  new URL(url);
            HttpURLConnection con= (HttpURLConnection) urlObj.openConnection();
           return  con.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -2;
        }
    }
}
