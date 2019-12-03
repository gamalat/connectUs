package com.cleancodesoft.connectus.repository;


import android.util.Log;

import com.cleancodesoft.connectus.entity.Entity;
import com.example.myapplication.exception.ConnectionFailureException;
import com.example.myapplication.exception.SqlQueryCreationException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EntityManagerDBImpl implements EntityManager {

    Statement statement;
    Connection connection;
    QueryManagerSql queryManagerSql;
    String PACKAGE_NAME;

    public EntityManagerDBImpl(Connection conn, String PACKAGE_NAME1) throws SQLException {
        super();
        this.PACKAGE_NAME = PACKAGE_NAME1;
        this.connection = conn;
        statement = connection.createStatement();
        this.queryManagerSql = new QueryManagerSql(connection, PACKAGE_NAME1);

    }

    @Override
    public Entity find(Entity entity, String STRING) throws Exception {
        ResultSet mResultSet = null;
        try {
            String sql = queryManagerSql.findSql(entity, STRING);
            mResultSet = sqlToResultSet(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mResultSet.next();
        return fillEntity(entity, mResultSet);
    }


    @Override
    public List<Entity> findAll(Entity entity) throws SQLException {
        ResultSet mResultSet = null;
        try {
            String sql = queryManagerSql.findAllSql(entity);
            mResultSet = sqlToResultSet(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Entity> list = new ArrayList<>();
        while( mResultSet.next() ) {
            Entity mEntity = entity.getNewEntity();
            list.add(fillEntity(mEntity, mResultSet));
        }
        return list;
    }


    @Override
    public int save(Entity entity) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, SQLException {
        String sql = queryManagerSql.saveSql(entity);
        return statement.executeUpdate(sql);
    }


    @Override
    public void update(Entity entity) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException {
        String sql = queryManagerSql.updateSql(entity);
        statement.executeUpdate(sql);

    }


    @Override
    public void update(Entity entity, String[] params) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException {

        String sql = queryManagerSql.updateSql(entity, params);
        System.out.println(statement.executeUpdate(sql));

    }


    @Override
    public void remove(Entity entity) throws SQLException {

        String sql = queryManagerSql.removeSql(entity);
        statement.executeUpdate(sql);
    }


    @Override
    public void remove(Entity entity, long id) throws SQLException {
        String sql  =  queryManagerSql.removeSql(entity, id);
        statement.executeUpdate(sql);
    }

    @Override
    public List<Entity> getPost(Entity entity) throws SQLException {
        ResultSet mResultSet = null;
        String sql = "SELECT user.id, userName, userImage, text, imagePath, postPrivacy FROM post join user USING (id)";
        try {
            mResultSet = sqlToResultSet(sql);
        } catch (SqlQueryCreationException e) {
            e.printStackTrace();
        } catch (ConnectionFailureException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Entity> list = new ArrayList<>();
        while( mResultSet.next() ) {
            Entity mEntity = entity.getNewEntity();
            list.add(fillEntity(mEntity, mResultSet));
        }

        return list;
    }

    @Override
    public List<Entity> findAll(Entity entity, String STRING) throws SQLException {
        ResultSet mResultSet = null;
        String sql = "SELECT * FROM postjoin WHERE id ='"+STRING+"'";
        try {
            mResultSet = sqlToResultSet(sql);
        } catch (SqlQueryCreationException e) {
            e.printStackTrace();
        } catch (ConnectionFailureException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Entity> list = new ArrayList<>();
        while( mResultSet.next() ) {
            Entity mEntity = entity.getNewEntity();
            list.add(fillEntity(mEntity, mResultSet));
        }

        return list;
    }

    public ResultSet sqlToResultSet(String sql) throws SqlQueryCreationException, ConnectionFailureException, SQLException {

        ResultSet mResultSet = null;

        try {
            statement = connection.createStatement();
            mResultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mResultSet;
    }

    public Entity fillEntity(Entity entity, ResultSet mResultSet) throws SQLException {
        ResultSetMetaData rsMetaData;
        rsMetaData = mResultSet.getMetaData();
        int numberOfColumns = rsMetaData.getColumnCount(); //Returns the number of columns in the result set.

        try {
            String strClassName = "entity."+entity.getClass().getSimpleName();
            for (int i = 1; i <= numberOfColumns; i++) {
                String cName1 = rsMetaData.getColumnName(i); // Return column name from database
                Field privateField = Class.forName( PACKAGE_NAME + "." + strClassName ).getDeclaredField( cName1 ); // Prepare the name of the method that I want to call in run time
                privateField.setAccessible(true);
                privateField.set(entity,mResultSet.getString(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return entity;
    }

}