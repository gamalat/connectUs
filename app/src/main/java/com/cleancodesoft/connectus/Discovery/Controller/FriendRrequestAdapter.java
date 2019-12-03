package com.cleancodesoft.connectus.Discovery.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleancodesoft.connectus.Discovery.Model.User;
import com.cleancodesoft.connectus.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendRrequestAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> user = new ArrayList<>();
    private Context context;

    public FriendRrequestAdapter() {
    }

    public FriendRrequestAdapter(List<User> user) {
        this.user = user;

    }

    public FriendRrequestAdapter(List<User> user, Context context) {
        this.user = user;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dicover_rec_request_item, viewGroup, false);


        return new MenuItemViewHolder(menu1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MenuItemViewHolder menuItemViewHolder = (MenuItemViewHolder) viewHolder;

    }

    @Override
    public int getItemCount() {
        //return 0;
        return null != user ? user.size() : 0;
    }


    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView txt_name,txt_faclty;
       Button btn_accept;
       ImageView btn_reject;
        public MenuItemViewHolder(View menu1) {
            super(menu1);
            imageView = menu1.findViewById(R.id.dis_profile_image_Reqest);
            txt_name=menu1.findViewById(R.id.dis_txt_name_Reqest);
            txt_faclty=menu1.findViewById(R.id.dis_txt_faculty_Reqest);
            btn_accept=menu1.findViewById(R.id.btn_accept_friend);
            btn_reject=menu1.findViewById(R.id.btn_reject_request);

        }
    }
}
