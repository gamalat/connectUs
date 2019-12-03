package com.cleancodesoft.connectus.HomePage.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cleancodesoft.connectus.Discovery.Controller.DiscoverRecAdapter;
import com.cleancodesoft.connectus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ImageView writePost;
    public RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
     private  PostsAdapter postsAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view=inflater.inflate(R.layout.fragment_home, container, false);

        writePost=view.findViewById(R.id.btn_writepost);
        gotoWritepost();
        setPostRecAdapter();
        return view;
    }

    private void gotoWritepost(){
        writePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Frame_Container, new WritePostFragment(), "findWritePostefragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
    public void setPostRecAdapter(){
        recyclerView = view.findViewById(R.id.rec_home_posts);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        postsAdapter = new PostsAdapter();
        recyclerView.setAdapter(postsAdapter);
    }

}
