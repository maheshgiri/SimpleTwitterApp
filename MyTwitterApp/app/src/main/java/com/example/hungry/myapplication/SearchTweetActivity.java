package com.example.hungry.myapplication;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;


import com.example.hungry.myapplication.data.SearchUserData;
import com.example.hungry.myapplication.data.Status;
import com.example.hungry.myapplication.data.TwitterUser;
import com.example.hungry.myapplication.utility.Initialize;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hungry on 2/2/2016.
 */

public class SearchTweetActivity extends AppCompatActivity {
    String query;
    ProgressDialog progressDialog;
    ListView listView;
    List<Status> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_tweet_activity);
        listView=(ListView)findViewById(R.id.tweetsList);
        list=new ArrayList<>();
        handleIntent(getIntent());
    }
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
             query = intent.getStringExtra(SearchManager.QUERY);
            new SearchTweetFromService().execute();
        }
    }

    private class SearchTweetFromService extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            HttpURLConnection urlConnection = null;
            StringBuffer sb = null;
            try {
                String URL="https://api.twitter.com/1.1/search/tweets.json"+"?q="+URLEncoder.encode("#"+query,"UTF-8")+"&result_type=recent";
                url = new URL(URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                Log.d("BEARER", " " + Initialize.BEARER_TOKEN);
                urlConnection.setRequestProperty("Authorization", "Bearer " + Initialize.BEARER_TOKEN);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                InputStream in = urlConnection.getInputStream();
                int ch;
                sb = new StringBuffer();
                while ((ch = in.read()) != -1) {
                    sb.append((char) ch);
                }
                in.close();

            } catch (FileNotFoundException e) {
                e.getLocalizedMessage();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            } finally {

                urlConnection.disconnect();

            }
            if (sb.length() > 0)
                return sb.toString();
            else return String.valueOf(0);

        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(SearchTweetActivity.this);
            progressDialog.setMessage("Please Wait....");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(true);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Log.d("P", "" + result);
                try {
                    Gson gson = new Gson();
                    SearchUserData searchUserData;
                    searchUserData=gson.fromJson(result,SearchUserData.class );
                    list=searchUserData.getStatuses();
                    progressDialog.dismiss();
                } catch (Exception e) {
                    Log.e("EXPE", e.getMessage());
                }
            }

            listView.setAdapter(new SearchTweetAdaptor(SearchTweetActivity.this, list));

        }
    }

}
