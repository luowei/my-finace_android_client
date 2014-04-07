package com.rootls.util;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 14-4-7
 * Time: 下午10:34
 * To change this template use File | Settings | File Templates.
 */
public class URLHelper {

    private static final String TAG = "URLHelper";

    public static void main(String[] args) {
        try {
            System.out.println(send_url("http://www.anyuok.com/index.asp?id=testId&pwd=testPwd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *     发送URL请求的方法
     * @param urlString    请求的URL地址
     * @return    被请求的URL返回的页面代码
     * @throws Exception
     */
    public static String send_url(String urlString){
        try {
            StringBuffer html = new StringBuffer();
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            URLConnection c = url.openConnection();
            c.connect();
            String contentType = c.getContentType();
            String characterEncoding = null;
            int index = contentType.indexOf("charset=");
            if(index == -1){
                characterEncoding = "UTF-8";
            }else{
                characterEncoding = contentType.substring(index + 8, contentType.length());
            }
            InputStreamReader isr = new InputStreamReader(conn.getInputStream(), characterEncoding);
            BufferedReader br = new BufferedReader(isr);
            String temp;
            while ((temp = br.readLine()) != null) {
                html.append(temp).append("\n");
            }
            br.close();
            isr.close();
            return html.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
