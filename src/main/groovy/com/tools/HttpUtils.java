package com.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpUtils {


	public static final String ACCEPT_ENCODING_VALUE = "gzip,deflate";

	private static final String ACCEPT_ENCODING = "Accept-Encoding";
	private static final String GZIP = "gzip";
	private static final String CONTENT_ENCODING = "Content-Encoding";


	
	public static String get(String urlString) throws IOException {		
		 
		return get(urlString,null);
	}
	
	
	public static String get(String urlString,Map<String,String> headerMap) throws IOException {

		GetMethod method = new GetMethod(urlString);

		try {
			boolean isCompressed = true;
			HttpClient httpClient = new HttpClient();

			if (isCompressed) {
				method.addRequestHeader(ACCEPT_ENCODING,
						ACCEPT_ENCODING_VALUE);
			}
			method.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			method.addRequestHeader("Accept-Encoding", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			method.addRequestHeader("Connection", "keep-alive");
			method.addRequestHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			method.addRequestHeader("Host", "www.rocelec.com");
//			method.addRequestHeader("Referer", "http://www.chip1stop.com/web/CHN/zh/");
			method.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			
			if(headerMap!=null){
			Set set=headerMap.keySet();
			Iterator ite=set.iterator();
			while(ite.hasNext()){
				String key=(String)ite.next();
				method.addRequestHeader(key,
						headerMap.get(key));
			}
			}

			int statusCode ;
			try{
				statusCode = httpClient.executeMethod(method);
			}
			catch(UnknownHostException e){
				
			}
			Header header = method.getResponseHeader(CONTENT_ENCODING);

			String acceptEncoding = "";

			if (header != null) {
				acceptEncoding = header.getValue();
			}

			InputStream is = method.getResponseBodyAsStream();
			if (acceptEncoding.toLowerCase().indexOf(GZIP) > -1) {
				is = new GZIPInputStream(is);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			StringBuilder sb = new StringBuilder();
			String s = null;
			while ((s = br.readLine()) != null) {
				sb.append(s);
				sb.append("\n\t");
			}
			return sb.toString();

		} catch (IOException t) {
			// release connection
			method.releaseConnection();
			throw t;
		}

	}
	
	
	public static String post(String urlString,Map<String,String> param) throws IOException {
		
		PostMethod method = new PostMethod(urlString);
		
		NameValuePair[] paramArray = _getNameValParams(param);

		if (null != paramArray) {
			method.setRequestBody(paramArray);
		}

		try {
			boolean isCompressed = true;
			HttpClient httpClient = new HttpClient();

			if (isCompressed) {
				method.addRequestHeader(ACCEPT_ENCODING,
						ACCEPT_ENCODING_VALUE);
			}

			int statusCode = httpClient.executeMethod(method);
			Header header = method.getResponseHeader(CONTENT_ENCODING);

			String acceptEncoding = "";

			if (header != null) {
				acceptEncoding = header.getValue();
			}

			InputStream is = method.getResponseBodyAsStream();
			if (acceptEncoding.toLowerCase().indexOf(GZIP) > -1) {
				is = new GZIPInputStream(is);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			StringBuilder sb = new StringBuilder();
			String s = null;
			while ((s = br.readLine()) != null) {
				sb.append(s);
				sb.append("\n\t");
			}
			return sb.toString();

		} catch (IOException t) {
			// release connection
			method.releaseConnection();
			throw t;
		}

		
	}
	
	private static NameValuePair[] _getNameValParams(Map<String, String> paramMap) {
		if (paramMap == null) {
			return null;
		}
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		for (String s : paramMap.keySet()) {
			NameValuePair nameValuePair = new NameValuePair();
			nameValuePair.setName(s);
			nameValuePair.setValue(paramMap.get(s));
			valuePairs.add(nameValuePair);
		}

		return (NameValuePair[]) valuePairs.toArray(new NameValuePair[0]);
	}

	public static void main(String[] args) throws Exception {
		// String s = HttpUtils
		// .get("http://www.mouser.cn/ProductDetail/Molex/63819-0900/?qs=sGAEpiMZZMuqHt7hmXVVHtD%2fSIV3U8ei5uWBlBDLLs0%3d");
		String url="http://www.mouser.cn/ProductDetail/Molex/63819-0900/?qs=sGAEpiMZZMuHCD5%2fnvq3PvrQKLvTekR37KWKQ020uN0%3d";
		
		String s = HttpUtils.get(url);
		System.out.println(s);
	}

}
