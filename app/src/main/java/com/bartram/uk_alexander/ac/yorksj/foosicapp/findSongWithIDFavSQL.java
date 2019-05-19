package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class findSongWithIDFavSQL extends AsyncTask<String, Void, byte[]> {

    public favouritesLoggedIn parent;

    @Override
    protected byte[] doInBackground(String... strings) {


        byte[] result = new byte[50];
        URL url;
        HttpURLConnection conn = null;


        HashMap<String, String> params = new HashMap<String, String>();
        params.put("songID", strings[0]);
        StringBuilder sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0) {
                    sbParams.append("&");
                }
                sbParams.append(key).append("=").append(URLEncoder.encode(params.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }

        try {
            url = new URL("https://cs2s.yorkdc.net/~alexander.bartram/findSongWithID.php");
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            StringBuilder res = new StringBuilder();
            byte[] data = new byte[50];
            int current = 0;

            while ((current = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, current);
            }

            result = buffer.toByteArray();

            Log.d("findSong", "result from server: " + buffer.toString());
            //return test;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(byte[] s) {
        super.onPostExecute(s);
        parent.result(s);


    }
}