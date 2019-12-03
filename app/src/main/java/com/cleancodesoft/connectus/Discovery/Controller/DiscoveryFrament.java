package com.cleancodesoft.connectus.Discovery.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cleancodesoft.connectus.Discovery.Model.User;
import com.cleancodesoft.connectus.FirstScreen;
import com.cleancodesoft.connectus.MainActivity;
import com.cleancodesoft.connectus.R;
import com.cleancodesoft.connectus.entity.UserEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFrament extends Fragment implements DiscoveryInterface {

    private MyServer server;
    public RecyclerView recyclerView1, recyclerView2, recyclerView3;
    public static DiscoverRecAdapter discoverRecAdapter;
    private GridLayoutManager gridLayoutManager1, gridLayoutManager2, gridLayoutManager3;
    public static FriendRrequestAdapter friendRrequestAdapter;
    public static MoreUserAdapter moreUserAdapter;
    public static List<User> users_list_design = new ArrayList<>();
    NsdHelper mNsdHelper;

    public DiscoveryFrament() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_discovery_frament, container, false);
        setupRecyclerView();

        // adapter.notifyItemInserted(users_list_design.size() - 1);



        mNsdHelper = new NsdHelper(getActivity(), this);
        //mNsdHelper.initializeNsd();

        mNsdHelper.registerService(5000);
        Log.d("nsdhelper", "onCreateView: " + users_list_design.size());
        mNsdHelper.discoverServices();
        return view;
    }



    private void setupRecyclerView() {
        recyclerView1 = view.findViewById(R.id.id_rec_friend);
        recyclerView1.setHasFixedSize(true);
        gridLayoutManager1 = new GridLayoutManager(getActivity(), 1);
        recyclerView1.setLayoutManager(gridLayoutManager1);
        discoverRecAdapter = new DiscoverRecAdapter(users_list_design);
        recyclerView1.setAdapter(discoverRecAdapter);

        /////////////////////////

        recyclerView2 = view.findViewById(R.id.id_rec_friendRequest);
        recyclerView2.setHasFixedSize(true);
        gridLayoutManager2 = new GridLayoutManager(getActivity(), 1);
        recyclerView1.setLayoutManager(gridLayoutManager2);
        friendRrequestAdapter = new FriendRrequestAdapter();
        recyclerView2.setAdapter(friendRrequestAdapter);

        ///////////////////////////////////

        recyclerView3 = view.findViewById(R.id.id_rec_morefriend);
        recyclerView3.setHasFixedSize(true);
        gridLayoutManager3 = new GridLayoutManager(getActivity(), 1);
        recyclerView3.setLayoutManager(gridLayoutManager3);
        moreUserAdapter = new MoreUserAdapter();
        recyclerView3.setAdapter(moreUserAdapter);

    }

    public void GetDataVolley(final String url) {
        JSONObject objectkk = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, objectkk, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("eeeeeeeeeeeeee", "try");
                    JSONArray jsonArray = response.getJSONArray("User");
//                    users_list_design=new ArrayList<>();

                    boolean userExists = false;
                    for (int i = 0; i <= jsonArray.length() - 1; i++) {
                        JSONObject object = null;
                        object = jsonArray.getJSONObject(i);
                        //  Log.d("eeeeeeeeeeeeee", "try"+object.getString("userName"));

                        for (int j = 0; j < users_list_design.size(); j++) {
                            User currentUser = users_list_design.get(j);

                            if (currentUser.getUserName().equals(url)) {
                                userExists = true;
                                break;
                            }
                        }
                        //  users_list_design.clear();
                        if (!userExists) {
                            users_list_design.add(new User(object.getString("id")
                                    , url
                                    , object.getString("userSubdata")
                                    , object.getString("userImage")
                                    , object.getString("friendshipStatus")));
                            //    Log.d("eeeeeeeeeeeeee", "try"+object.getString("userName"));
                            discoverRecAdapter.notifyItemInserted(users_list_design.size() - 1);
                        }
                        Log.d("DisFragment", "Listsize" + users_list_design.size());
                    }
                } catch (JSONException e) {
                    Log.d("eeeeeeeeeeeeee", "errrorororor");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(getActivity(), "Check Internet Connection!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Response error check your network" + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Volley.newRequestQueue(getActivity()).add(jsonObjectRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            server = new MyServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (server != null) {
            server.stop();
        }
    }

    @Override
    public void onDiscover(List<InetAddress> users_IPs) {

        Log.d("onDiscoverrrrrr", "list: " + users_IPs.size());
        Log.d("onDiscoverrrrrr", "list: " + users_IPs.toString());
//        users_list_design=new ArrayList<>();

        for (int i = 0; i <= users_IPs.size() - 1; i++) {
            String url = "http:/" + users_IPs.get(i) + ":" + "8080/";
            Log.d("eeeeeeeeeeeeee", url);
            GetDataVolley(url);

            //   adapter.notifyItemInserted(users_list_design.size() - 1);
        }
    }
}
