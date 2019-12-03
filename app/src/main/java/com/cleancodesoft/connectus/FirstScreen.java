package com.cleancodesoft.connectus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cleancodesoft.connectus.entity.Entity;
import com.cleancodesoft.connectus.entity.UserEntity;
import com.cleancodesoft.connectus.repository.DBManager;
import com.cleancodesoft.connectus.repository.EntityManager;
import com.cleancodesoft.connectus.repository.EntityManagerDBImpl;
import com.cleancodesoft.connectus.repository.MySQLDroidConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirstScreen extends AppCompatActivity {

    private Connection connection;

    public static EntityManager mEntityManager;
    public static String PACKAGE_NAME;

    private CircleImageView profileImage;
    private static final int PICK_IMAGE =1;
    Uri imageUrl;
    Button btnSave;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        profileImage=findViewById(R.id.enteruser_image);
        name=findViewById(R.id.name);
        //faculty=findViewById(R.id.faculty);
        btnSave=findViewById(R.id.btn_save);

        PACKAGE_NAME = getApplicationContext().getPackageName();
        CONNECT_TO_SQLDROID();
        try {
            CREATE_SCHEMA();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d("mac outside anything", "onCreate: "+getMyMacAddress());

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallrey=new Intent();
                gallrey.setType("image/+");
                gallrey.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallrey,"select picture"),PICK_IMAGE);
            }
        });

        saveUserData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", false);

        if (!firstStart) {

            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean("firstStart", Boolean.TRUE);
            edit.commit();
            saveUserData();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void  saveUserData(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserEntity userEntity = new UserEntity(getMyMacAddress(),name.getText().toString() , "", "", "", "");
                try {
                    Log.d("before save", "onClick: "+userEntity.getId());
                    mEntityManager.save(userEntity);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                List<Entity> xx = null;
                UserEntity userEntity1 = new UserEntity();
                try {
                    xx=  mEntityManager.findAll(userEntity1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("MAC_ADRESS", "onClick: "+xx.size());
                for (int i = 0; i < xx.size(); i++) {
                    Log.d("MAC_ADRESS", "onClick: "+((UserEntity)xx.get(i)).getId()+((UserEntity)xx.get(i)).getUserName());
                }

                Intent intent=new Intent(FirstScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE && requestCode==RESULT_OK){
            imageUrl=data.getData();
          //  new File(data.getData()).getAbsolutePath();
            profileImage.setImageURI(imageUrl);
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUrl);
                profileImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private void CONNECT_TO_SQLDROID() {
        MySQLDroidConfig mySQLDroidConfig = new MySQLDroidConfig(PACKAGE_NAME);
        DBManager dbManager = new DBManager(mySQLDroidConfig);
        dbManager.connect();
        connection = dbManager.getConnection();
        try {
            mEntityManager = new EntityManagerDBImpl(connection, PACKAGE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void CREATE_SCHEMA() throws SQLException {

        Statement statementCREATE_SCHEMA = null;
        statementCREATE_SCHEMA = connection.createStatement();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getApplicationContext().getAssets().open("schema.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                statementCREATE_SCHEMA.executeUpdate(mLine);
            }
        } catch (IOException e) {
            //log the exception
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }
//private  void setDBUser(){
//    UserEntity UENTITY = new UserEntity();
//    //UENTITY.setId(getMyMacAddress());
//    UENTITY.setUserName("Enas");
//    UENTITY.setUserSubdata("FCI");
//    UENTITY.setFriendshipStatus("");
//    UENTITY.setUserImage("ddddddddddddddd");
//
//
//    try {
//        mEntityManager.save(UENTITY);
//    } catch (ClassNotFoundException e) {
//        e.printStackTrace();
//    } catch (NoSuchFieldException e) {
//        e.printStackTrace();
//    } catch (IllegalAccessException e) {
//        e.printStackTrace();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//
//
//}

    public static String getMyMacAddress() {

        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nintf : all) {
                if (!nintf.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nintf.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder sb1 = new StringBuilder();
                for (byte b : macBytes) {
                    String hex = Integer.toHexString(b & 0xFF);
                    if (hex.length() == 1) {
                        hex = "0" + hex;
                    }
                    sb1.append(hex + ":");
                }

                if (sb1.length() > 0) {
                    sb1.deleteCharAt(sb1.length() - 1);
                }
                return sb1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

}
