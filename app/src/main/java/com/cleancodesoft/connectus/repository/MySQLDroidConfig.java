package com.cleancodesoft.connectus.repository;

import android.util.Log;

public class MySQLDroidConfig implements DBConfig {

    static final String DATABASE_NAME = "my-database.db";
    static final String DRIVER_CLASS = "org.sqldroid.SQLDroidDriver";
    String PACKAGE_NAME="";

    public MySQLDroidConfig(String PACKAGE_NAME1){
        this.PACKAGE_NAME = PACKAGE_NAME1;
    }

    @Override
    public String getDriver() {
        return DRIVER_CLASS;
    }

    @Override
    public String getConnectionString() {
//        Log.w("TAG", "jdbc:sqldroid:" + "/data/data/" + PACKAGE_NAME + "/"+DATABASE_NAME);
        return "jdbc:sqldroid:" + "/data/data/" + PACKAGE_NAME + "/"+DATABASE_NAME;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
