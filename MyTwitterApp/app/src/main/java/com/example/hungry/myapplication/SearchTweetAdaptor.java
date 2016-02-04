package com.example.hungry.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hungry.myapplication.data.Status;
import com.example.hungry.myapplication.data.TwitterUser;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hungry on 2/3/2016.
 */
public class SearchTweetAdaptor extends BaseAdapter {
    private Context context;
    private List<Status> list;
    public SearchTweetAdaptor(SearchTweetActivity mainActivity, List<Status> listTweets) {
        list=listTweets;
        context=mainActivity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        ImageView userImageView;
        TextView userNameText;
        TextView userScreenNameText;
        TextView descriptionText;
        ImageView mediaImage;
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.search_tweet_list,null);
        userImageView=(ImageView)view.findViewById(R.id.userImage);
        userNameText=(TextView)view.findViewById(R.id.userNameText);
        userScreenNameText=(TextView)view.findViewById(R.id.userScreenName);
        descriptionText=(TextView)view.findViewById(R.id.description);
        mediaImage=(ImageView)view.findViewById(R.id.mediaImage);
        Picasso.with(context).load(list.get(position).getUser().getProfile_image_url()).into(userImageView);
        if(list.get(position).getEntities().getMedia()!=null &&!list.get(position).getEntities().getMedia().isEmpty()) {
            mediaImage.setVisibility(View.VISIBLE);
            Picasso.with(context).load(list.get(position).getEntities().getMedia().get(0).getMedia_url()).into(mediaImage);
        }
        userNameText.setText("" + list.get(position).getUser().getName());
        userScreenNameText.setText("" + list.get(position).getUser().getScreen_name());
        descriptionText.setText(""+list.get(position).getText());
        return view;
    }

}
