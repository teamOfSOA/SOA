package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import static main.java.Reader.readInputStream;

public class SenderTest {

    @Test
    public void getMethod() throws Exception {
        String urlString = "http://localhost:8080/Assignment5_war_exploded/Score?id=17125067";
        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(false);
        byte[] datas;
        if(httpURLConnection.getResponseCode() != 200){
            datas = readInputStream(httpURLConnection.getErrorStream());
        }else{
            datas = readInputStream(httpURLConnection.getInputStream());
        }
        String result = new String(datas);
        //打印返回结果
        System.out.println(result);
    }

    @Test
    public void postMethod() throws Exception {
        String urlString = "http://localhost:8080/Assignment5_war_exploded/Score";
        String soapXml = "./src/main/resources/post.xml";

        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        File fileToSend = new File(soapXml);
        byte[] buf = new byte[(int) fileToSend.length()];
        new FileInputStream(soapXml).read(buf);

        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(buf.length));
        httpURLConnection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        OutputStream out = httpURLConnection.getOutputStream();
        out.write(buf);
        out.close();

        byte[] datas;
        if(httpURLConnection.getResponseCode() != 200){
            datas = readInputStream(httpURLConnection.getErrorStream());
        }else{
            datas = readInputStream(httpURLConnection.getInputStream());
        }
        String result = new String(datas);
        //打印返回结果
        System.out.println(result);
    }
}


