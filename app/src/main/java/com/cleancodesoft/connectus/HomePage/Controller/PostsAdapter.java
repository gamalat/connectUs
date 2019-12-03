package com.cleancodesoft.connectus.HomePage.Controller;

import android.net.wifi.ScanResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleancodesoft.connectus.R;
public class PostsAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public PostsAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_rec_post_item, viewGroup, false);

        return new PostsAdapter.MenuItemViewHolder(menu1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PostsAdapter.MenuItemViewHolder menuItemViewHolder = (PostsAdapter.MenuItemViewHolder) viewHolder;
       // menuItemViewHolder.post_txt.setText("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmmmmmmmmmMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {
       ImageView profile_image_userpost ,postImage,postmenu,postPrivacy;
       TextView userName ,postTime,post_txt;

        public MenuItemViewHolder(View menu1) {
            super(menu1);
            profile_image_userpost=menu1.findViewById(R.id.home_profile_imagepost);
            postImage=menu1.findViewById(R.id.post_image);
            postmenu=menu1.findViewById(R.id.post_menu);
            userName=menu1.findViewById(R.id.post_username);
            postTime=menu1.findViewById(R.id.post_time);
            postPrivacy=menu1.findViewById(R.id.post_privacy);
            post_txt=menu1.findViewById(R.id.post_txt_readmore);

        }
    }
}
