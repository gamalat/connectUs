package com.cleancodesoft.connectus;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.cleancodesoft.connectus.Discovery.Controller.DiscoveryFrament;
import com.cleancodesoft.connectus.Discovery.Model.User;
import com.cleancodesoft.connectus.HomePage.Controller.HomeFragment;
import com.cleancodesoft.connectus.Profile.Controller.ProfileFragment;
import com.cleancodesoft.connectus.entity.UserEntity;
import com.cleancodesoft.connectus.repository.DBManager;
import com.cleancodesoft.connectus.repository.EntityManager;
import com.cleancodesoft.connectus.repository.EntityManagerDBImpl;
import com.cleancodesoft.connectus.repository.MySQLDroidConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Connection connection;
    public static  EntityManager mEntityManager;
    public static String PACKAGE_NAME;
    public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        CONNECT_TO_SQLDROID();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_24_px);
        tabLayout.getTabAt(1).setIcon(R.drawable.discover_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_person_24_px);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {
            startActivity(new Intent(MainActivity.this, FirstScreen.class));
        }

        getSharedPreferences("prefs", MODE_PRIVATE).edit()
                .putBoolean("firstStart", false).commit();
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new DiscoveryFrament());
        adapter.addFragment(new ProfileFragment());
        viewPager.setAdapter(adapter);
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

}