package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.HashMap;

public class loginSQL extends AsyncTask<String, Void, String> {

        public LoginScreen Parent;
    @Override
    protected String doInBackground(String... strings) {


        boolean isConnected = false;
        URL url;
        HttpURLConnection conn = null;
        String result = "";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email",strings[0]);
        params.put("password", strings[1]);
        StringBuilder sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()){
            try {
                if (i != 0) {
                    sbParams.append("&");
                }
                sbParams.append(key).append("=").append(URLEncoder.encode(params.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            i++;
        }

        try {
            url = new URL("https://cs2s.yorkdc.net/~alexander.bartram/Login.php");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "UTF-8");

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.connect();

            String paramsString = sbParams.toString();

            DataOutputStream ds = new DataOutputStream(conn.getOutputStream());
            ds.writeBytes(paramsString);
            ds.flush();
            ds.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder res = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                res.append(line);
            }
            Log.d("Login", "result from server: " + res.toString());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (conn != null){
                conn.disconnect();
            }
        }


        //    return  isConnected
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String test = s;

        Parent.loginSuccessful(s);


    }

}