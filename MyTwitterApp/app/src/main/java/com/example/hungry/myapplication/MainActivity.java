package com.example.hungry.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.example.hungry.myapplication.data.TwitterUser;
import com.example.hungry.myapplication.utility.CheckNetwork;
import com.example.hungry.myapplication.utility.Initialize;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener{
    private TextView te;
    private List<TwitterUser> listTweets ;
    private ListView listTweetsView;
    private Thread t;
    CheckNetwork checkNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listTweets = new ArrayList<>();


        listTweetsView = (ListView) findViewById(R.id.userTweetlistView);
       checkNetwork=new CheckNetwork(MainActivity.this);
        if(checkNetwork.isNetworkAvailable()) {
            new Initialize().execute();

            t = new Thread() {
                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            Thread.sleep(2000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    new LongOperation1().execute();
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                    }
                }
            };
            t.start();
        }
        else {
            try {
             finish();
            }
            catch(Exception e)
            {
                Log.d("Excep", "Show Dialog: "+e.getMessage());
            }
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        t.interrupt();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==1){
            finish();
        }
        finish();
    }

    private class LongOperation1 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            URL url = null;
            HttpURLConnection urlConnection = null;
            StringBuffer sb = null;
            try {
                url = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=hackinfo89&count=10");
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
        protected void onPostExecute(String result) {
            if (result != null) {
                Log.d("P", "" + result);
                try {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<ArrayList<TwitterUser>>() {
                    }.getType();
                    listTweets = gson.fromJson(result, listType);
                    Log.d("P", "" + listTweets.size());
                } catch (Exception e) {
                    Log.e("EXPE", e.getMessage());
                }
            }

            listTweetsView.setAdapter(new ShowUserTwiiterAdapotor(MainActivity.this, listTweets));

        }

        @Override
        protected void onPreExecute() {
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

}
