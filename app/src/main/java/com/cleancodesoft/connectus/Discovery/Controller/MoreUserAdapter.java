package com.cleancodesoft.connectus.Discovery.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleancodesoft.connectus.Discovery.Model.User;
import com.cleancodesoft.connectus.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MoreUserAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> user = new ArrayList<>();
    private Context context;
    public MoreUserAdapter() {
    }

    public MoreUserAdapter(List<User> user) {
        this.user = user;
    }

    public MoreUserAdapter(List<User> user, Context context) {
        this.user = user;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.more_user_item, viewGroup, false);


        return new MenuItemViewHolder(menu1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MenuItemViewHolder menuItemViewHolder = (MenuItemViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return null != user ? user.size() : 0;
    }



    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView txt_name;
        TextView txt_faclty;
        ImageView btn_addFriend;

        public MenuItemViewHolder(View menu1) {
            super(menu1);
            imageView = menu1.findViewById(R.id.MoreUser_profile_image);
            txt_name=menu1.findViewById(R.id.MoreUser_txt_name);
            txt_faclty=menu1.findViewById(R.id.MoreUser_txt_faculty);
            btn_addFriend=menu1.findViewById(R.id.btn_MoreUser_addFriend);
        }
    }
}
