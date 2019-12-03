package com.cleancodesoft.connectus.Profile.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cleancodesoft.connectus.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditeFragment extends Fragment {
  CircleImageView imageView;
  EditText editUserName,editSubData;
  TextView  btn_save;
    public EditeFragment() {
        // Required empty public constructor
    }
View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view=inflater.inflate(R.layout.fragment_edite, container, false);
         imageView=view.findViewById(R.id.pro_edit_image);
         editUserName=view.findViewById(R.id.edit_username);
         editSubData=view.findViewById(R.id.edit_faculty);
         btn_save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {



             }
         });
        return view;
    }

}
