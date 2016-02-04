package com.example.hungry.myapplication.utility;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Hungry on 2/2/2016.
 */
public class Initialize extends AsyncTask<String,Void,String> {
private String TWITTER_BEARER_TOKEN_URL="https://api.twitter.com/oauth2/token";
private String CONSUMER_KEY="2KqPOfpo6HXvVA2oTNHXKucZ7";
private String CONSUMER_SECRET_KEY="IjneNjfWWYrzKGHMj2K3blWEZpTF9pggK6ERvHRvCSH2X82LzY";
public static String BEARER_TOKEN=null;

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        StringBuffer sb = null;
        try {
            url = new URL(TWITTER_BEARER_TOKEN_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(true);
            String urlApiKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8");
            String urlApiSecret = URLEncoder.encode(CONSUMER_SECRET_KEY, "UTF-8");
            String combined = urlApiKey + ":" + urlApiSecret;
            String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);
            urlConnection.setRequestProperty("Authorization","Basic "+base64Encoded);
            OutputStream outputStream=urlConnection.getOutputStream();
            String str =  "grant_type=client_credentials";
            byte[] outputInBytes = str.getBytes("UTF-8");
            outputStream.write(outputInBytes);
            outputStream.close();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            int ch;
            sb = new StringBuffer();
            while ((ch = in.read()) != -1) {
                sb.append((char) ch);
            }
            in.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        finally {

            urlConnection.disconnect();

        }
        return sb.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        if(s!=null){

            try{
                JSONObject obj= new JSONObject(s);
                BEARER_TOKEN =obj.getString("access_token");
            }catch (JSONException e){

            }
            Log.d("RESPONSE", "" + s);
        }

    }
}
