package com.cleancodesoft.connectus.Discovery.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleancodesoft.connectus.Discovery.Model.User;
import com.cleancodesoft.connectus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DiscoverRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<User> user = new ArrayList<>();
    private Context context;

    public DiscoverRecAdapter() {
    }

    public DiscoverRecAdapter(List<User> user) {
        this.user = user;
    }

    public DiscoverRecAdapter(List<User> user, Context context) {
        this.user = user;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.discover_row_item, viewGroup, false);


        return new MenuItemViewHolder(menu1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MenuItemViewHolder menuItemViewHolder = (MenuItemViewHolder) viewHolder;
      //  Picasso.with(context).load("http://image.tmdb.org/t/p/w185"+user.get(i).getUserImage()).into(menuItemViewHolder.imageView);
        menuItemViewHolder.txt_name.setText(user.get(i).getUserName());
        menuItemViewHolder.txt_faclty.setText(user.get(i).getUserSubdata());
    }

    @Override
    public int getItemCount() {

      return null != user ? user.size() : 0;
      //  return 10;
    }
    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        CircleImageView imageView;
        ImageView imageButton;
        TextView txt_name;
        TextView txt_faclty;

        public MenuItemViewHolder(View menu1) {
            super(menu1);
            cardView = menu1.findViewById(R.id.card);
            imageView = menu1.findViewById(R.id.dis_profile_image);
//            imageButton=menu1.findViewById(R.id.btn_addFriend);
            txt_name=menu1.findViewById(R.id.dis_txt_name);
            txt_faclty=menu1.findViewById(R.id.dis_txt_faculty);

        }
    }
}
