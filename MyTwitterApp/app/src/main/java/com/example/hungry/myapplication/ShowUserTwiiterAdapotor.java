package com.example.hungry.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.hungry.myapplication.data.TwitterUser;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hungry on 2/2/2016.
 */
public class ShowUserTwiiterAdapotor extends BaseAdapter {
   private Context context;
   private List<TwitterUser> list;
    public ShowUserTwiiterAdapotor(MainActivity mainActivity, List<TwitterUser> listTweets) {
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
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.usertweets_listview,null);
        userImageView=(ImageView)view.findViewById(R.id.userImage);
        userNameText=(TextView)view.findViewById(R.id.userNameText);
        userScreenNameText=(TextView)view.findViewById(R.id.userScreenName);
        descriptionText=(TextView)view.findViewById(R.id.description);
        Picasso.with(context).load(list.get(position).getUser().getProfile_image_url()).into(userImageView);
        userNameText.setText("" + list.get(position).getUser().getName());
        userScreenNameText.setText("" + list.get(position).getUser().getScreen_name());
        descriptionText.setText(""+list.get(position).getText());
        return view;
    }
}
