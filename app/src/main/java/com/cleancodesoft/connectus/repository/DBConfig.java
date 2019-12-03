package com.cleancodesoft.connectus.repository;

public interface DBConfig {

    public abstract String getDriver();

    public abstract String getConnectionString();

    public abstract String getUsername();

    public abstract  String getPassword();
}
