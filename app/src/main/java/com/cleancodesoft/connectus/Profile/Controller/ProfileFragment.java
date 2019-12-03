package com.cleancodesoft.connectus.Profile.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleancodesoft.connectus.FirstScreen;
import com.cleancodesoft.connectus.MainActivity;
import com.cleancodesoft.connectus.Profile.Model.PostModel;
import com.cleancodesoft.connectus.R;
import com.cleancodesoft.connectus.entity.Entity;
import com.cleancodesoft.connectus.entity.PostEntity;
import com.cleancodesoft.connectus.entity.PostjoinEntity;
import com.cleancodesoft.connectus.entity.UserEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public static PostAdapter adapter;
    public RecyclerView recyclerView;
    List<PostModel> postList = new ArrayList<>();
    CircleImageView circleImageView;
    TextView txtName, txtSubdata;
    ImageView btn_edite;
    View view;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        postList.clear();
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView = view.findViewById(R.id.profile_post_list);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        try {
            getDataToInterface();
        } catch (Exception e) {
            e.printStackTrace();
        }
        editInfo();

//        UserEntity userEntity = new UserEntity("90:06:28:82:60:89",
//                "STRING",
//                "STRING",
//                "STRING",
//                "STRING",
//                "STRING"
      //  );
//        try {
//            MainActivity.mEntityManager.save(userEntity);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < 10; i++) {
            PostEntity postEntity = new PostEntity("90:06:28:82:60:89", "OTHERUSERSOTHERUSERSOTHERUSERS" + i,
                    "IMAGEPATHIMAGEPATHIMAGEPATHIMAGEPATH" + i, "POSTPRIVACYPOSTPRIVACYPOSTPRIVACYPOSTPRIVACY" + i);
            try {
               MainActivity.mEntityManager.save(postEntity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        List<Entity> l = null;
        PostjoinEntity postjoinEntity = new PostjoinEntity();
        try {
            l =MainActivity.mEntityManager.getPost(postjoinEntity);
            PostjoinEntity postjoinEntity123 = new PostjoinEntity();
            MainActivity.mEntityManager.remove(postjoinEntity123);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Log.d("DATABASE", "onCreateView: "+l.size());
        for (int i = 0; i < l.size(); i++) {
            PostjoinEntity postjoinEntity1 = new PostjoinEntity(((PostjoinEntity) l.get(i)).getId(),
                    ((PostjoinEntity) l.get(i)).getUserName(), ((PostjoinEntity) l.get(i)).getUserImage(),
                    ((PostjoinEntity) l.get(i)).getText(), ((PostjoinEntity) l.get(i)).getImagePath(),
                    ((PostjoinEntity) l.get(i)).getPostPrivacy()
            );


            try {
                MainActivity.mEntityManager.save(postjoinEntity1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

//            Log.d("XXXXXXXXXXXx", "onCreateView: "+((PostjoinEntity) l.get(i)).getId());
//            Log.d("XXXXXXXXXXXx", "onCreateView: "+((PostjoinEntity) l.get(i)).getUserName());

        }


        PostjoinEntity postjoinEntity1 = new PostjoinEntity();
        List<Entity> l5666 = null;
        try {
            l5666 = MainActivity.mEntityManager.findAll(postjoinEntity1, FirstScreen.getMyMacAddress());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        Log.d("size", "onCreateView: "+l5666.size());
        for (int i = 0; i < l5666.size() ; i++) {
            PostModel postMode4556 = new PostModel(
                    ((PostjoinEntity) l5666.get(i)).getId(),
                    ((PostjoinEntity) l5666.get(i)).getUserName(),
                    ((PostjoinEntity) l5666.get(i)).getUserImage(),
                    ((PostjoinEntity) l5666.get(i)).getText(),
                    ((PostjoinEntity) l5666.get(i)).getImagePath());
            postList.add(postMode4556);
        }
        for (int i = 0; i < postList.size(); i++) {
            Log.d("size", "onCreateView: " + postList.get(i).getId());
            Log.d("size", "onCreateView: " + postList.get(i).getPostText());
        }

        adapter = new PostAdapter(postList);
        recyclerView.setAdapter(adapter);
        adapter.notifyItemInserted(postList.size() - 1);
        return view;
    }

    private void editInfo() {
        btn_edite = view.findViewById(R.id.ic_edit_profile);
        btn_edite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.profile_container, new EditeFragment(), "findprofilefragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    private void getDataToInterface() throws Exception {
        circleImageView = view.findViewById(R.id.profile_profile_image);
        txtName = view.findViewById(R.id.profile_txt_userName);
        txtSubdata = view.findViewById(R.id.profile_txt_subdata);
        //circleImageView.setImageURI();

        UserEntity userEntity = new UserEntity();
        userEntity = (UserEntity) MainActivity.mEntityManager.find(userEntity, FirstScreen.getMyMacAddress());

        txtName.setText(userEntity.getUserName());
        txtSubdata.setText(userEntity.getUserSubdata());
        circleImageView.setImageURI(userEntity.getUserImage());
    }
}
