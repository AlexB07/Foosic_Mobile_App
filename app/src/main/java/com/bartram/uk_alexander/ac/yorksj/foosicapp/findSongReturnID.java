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

public class findSongReturnID extends AsyncTask<String, Void, String> {

    public TasteScreen parent;

    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection conn = null;
        String result = "";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("sweet", strings[0]);
        params.put("sour", strings[1]);
        params.put("salty", strings[2]);
        params.put("bitter", strings[3]);
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
            url = new URL("https://cs2s.yorkdc.net/~george.causer/findSongReturnID.php");
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
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder res = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                res.append(line);
            }

            Log.d("FindSongID", "result from server: " + res.toString());
            return res.toString();
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
