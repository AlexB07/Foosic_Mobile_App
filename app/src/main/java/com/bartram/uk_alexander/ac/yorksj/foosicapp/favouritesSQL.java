package com.bartram.uk_alexander.ac.yorksj.foosicapp;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class favouritesSQL extends AsyncTask<String, Void, String> {
    public favouritesLoggedIn parent;
    @Override
    protected String doInBackground(String... strings) {

        boolean isConnected = false;
        URL url;
        HttpURLConnection conn = null;
        String result = "";

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id",strings[0]);
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (String key : hashMap.keySet()){
            try {
                if (i != 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(key).append("=").append(URLEncoder.encode(hashMap.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            i++;
        }

        try {
            url = new URL("https://cs2s.yorkdc.net/~alexander.bartram/fav.php");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.connect();

            String s = stringBuilder.toString();

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(s);
            dos.flush();
            dos.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder res = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                res.append(line);
            }
            return res.toString();

        } catch(IOException e){
            e.printStackTrace();
        } finally {
            if (conn != null){
                conn.disconnect();
            }
        }

        return result;

    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String test = s;
        Log.d("test",s);
        Log.d("test",s);


        parent.faves(s);

    }
}

