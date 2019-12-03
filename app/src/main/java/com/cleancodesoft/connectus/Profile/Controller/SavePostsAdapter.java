package com.cleancodesoft.connectus.Profile.Controller;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleancodesoft.connectus.Profile.Model.PostModel;
import com.cleancodesoft.connectus.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SavePostsAdapter extends    RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<PostModel> post= new ArrayList<>();
    private Context context;

    public SavePostsAdapter() {
    }

    public SavePostsAdapter(List<PostModel> post) {
        this.post = post;
    }

    public SavePostsAdapter(List<PostModel> post, Context context) {
        this.post = post;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profile_rec_post_item, viewGroup, false);
        return new MenuItemViewHolder(menu1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MenuItemViewHolder menuItemViewHolder = (MenuItemViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return null != post ? post.size() : 0;
    }

    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        ImageView postimage;
        TextView txt_name;
        TextView post_text;

        public MenuItemViewHolder(View menu1) {
            super(menu1);
            imageView = menu1.findViewById(R.id.pro_profile_imagepost);
            postimage=menu1.findViewById(R.id.pro_post_image);
            txt_name=menu1.findViewById(R.id.pro_post_username);
            post_text=menu1.findViewById(R.id.pro_post_txt_readmore);

        }
    }
}
