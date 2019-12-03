package com.cleancodesoft.connectus.HomePage.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cleancodesoft.connectus.FirstScreen;
import com.cleancodesoft.connectus.MainActivity;
import com.cleancodesoft.connectus.R;
import com.cleancodesoft.connectus.entity.PostEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WritePostFragment extends Fragment {
  Spinner spinner;
    View view;
    TextView posttext,btnPublish;
    ImageView postimage ;
    FragmentManager fm;
    public WritePostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       view=inflater.inflate(R.layout.fragment_write_post, container, false);
        posttext=view.findViewById(R.id.edit_txt_puplish);
        postimage=view.findViewById(R.id.write_post_image);
        btnPublish=view.findViewById(R.id.btn_publish_post);
        fm=getFragmentManager();
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostEntity postEntity = new PostEntity(FirstScreen.getMyMacAddress(),
                        posttext.getText().toString(), "IMAGE PATH", "POSTPRIVACY");
                try {
                    MainActivity.mEntityManager.save(postEntity);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
               fm.popBackStack();
            }
        });
        setSpinner();
        return view;
    }
  private  void setSpinner(){
      spinner=view.findViewById(R.id.spinner);
//     // spinner.setOnItemSelectedListener();
//      List<Object> spinnerlist = new ArrayList<Object>();
//      spinnerlist.add(R.drawable.puplish_for_public_icon);
//      spinnerlist.add(R.drawable.puplich_for_friend_icon);
      ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.privacyicon);
//      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(dataAdapter);

  }
private  void setDatabase(){

}
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        // On selecting a spinner item
//        String item = parent.getItemAtPosition(position).toString();
//
//        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
//    }
//    public void onNothingSelected(AdapterView<?> arg0) {
//        // TODO Auto-generated method stub
//    }
}
