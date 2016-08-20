package imaxct.signer.misc;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Utility
 * Created by maxct on 2016/8/11.
 */
public class Lib {

    private static final Gson gson = new Gson();

    private static Logger logger = Logger.getLogger(Lib.class);

    private static final String BASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZacbdefghijklmnopqrstuvwxyz";

    public static String gsonToString(Object object){
        return gson.toJson(object);
    }

    public static boolean haveToBeEncoded(String s){
        return s.matches(".*[+/?%#&= ].*");
    }

    public static Date today(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        return today.getTime();
    }

    public static String generatePassword(String pass){
        StringBuilder res = new StringBuilder(md5(pass));
        res.append(Reference.SALT);
        res = new StringBuilder(res.toString().substring(0, 30)).reverse();
        return res.toString();
    }

    public static String rand(int len){
        StringBuilder s = new StringBuilder();
        for (int i=0; i<len; ++i)
            s.append(BASE.charAt((int)(Math.random() * 23456789) % 62));
        return s.toString();
    }

    public static String urlEncode(String str){
        String res;
        try {
            res = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("urlEncode error, " + e.getMessage());
            return null;
        }
        return res;
    }

    public static String md5(String str){
        char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try{
            byte[] bt = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bt);
            byte[] md = messageDigest.digest();
            int len = md.length;
            char s[] = new char[len * 2];
            int k = 0;
            for (byte aMd : md) {
                s[k++] = hex[aMd >>> 4 & 0xf];
                s[k++] = hex[aMd & 0xf];
            }
            return new String(s);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String streamToString(InputStream stream, String encoding){
        if (stream == null)
            return null;
        StringBuilder res = new StringBuilder();
        String buff;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(stream, encoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        try{
            while ((buff=in.readLine()) != null)
                res.append(buff);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return res.toString().trim();
    }

    public static InputStream getStream(String url, String cookie, String userAgent,
                                        int timeout, Map<String, String>prop){
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection;
        try {
            URL requestUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            if (userAgent != null)
                httpURLConnection.setRequestProperty("User-Agent", userAgent);
            if (cookie != null)
                httpURLConnection.setRequestProperty("Cookie", cookie);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (prop != null)
                for (String key : prop.keySet())
                    httpURLConnection.setRequestProperty(key, prop.get(key));
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(timeout);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200)
                inputStream = httpURLConnection.getInputStream();
            else
                logger.error("responseCode:" + responseCode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return inputStream;
    }

    public static InputStream postStream(String url, String params, String cookie,
                                         String userAgent, int timeout, Map<String, String>prop){
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection;
        try{
            URL requestUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
            if (userAgent != null)
                httpURLConnection.setRequestProperty("User-Agent", userAgent);
            if (cookie != null)
                httpURLConnection.setRequestProperty("Cookie", cookie);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (prop != null)
                for (String key : prop.keySet())
                    httpURLConnection.setRequestProperty(key, prop.get(key));
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(timeout);
            httpURLConnection.setDoOutput(true);
            OutputStreamWriter os = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
            os.write(params);
            os.flush();
            os.close();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200)
                inputStream = httpURLConnection.getInputStream();
            else
                logger.error("responseCode:" + responseCode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return inputStream;
    }

    public static String getCookieString(Map<String, String> map){
        StringBuilder sb = new StringBuilder();
        if (map==null || map.isEmpty())
            return null;
        Set<String>set = map.keySet();
        for (String key : set){
            sb.append(key).append("=").append(map.get(key)).append("; ");
        }
        return sb.toString().trim();
    }
}
