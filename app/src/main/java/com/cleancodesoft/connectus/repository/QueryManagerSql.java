package com.cleancodesoft.connectus.repository;

import android.util.Log;

import com.cleancodesoft.connectus.entity.Entity;
import com.example.myapplication.exception.ConnectionFailureException;
import com.example.myapplication.exception.SqlQueryCreationException;

import java.sql.*;

import java.lang.reflect.Field;

public class QueryManagerSql {

    public static final String TABLE_PRIMARY_KEY_NAME = "id";
    Connection connection=null;
    DatabaseMetaData databaseMetaData=null;
    String PACKAGE_NAME;

    public QueryManagerSql(Connection con, String PACKAGE_NAME1) throws SQLException {
        this.connection = con;
        this.PACKAGE_NAME = PACKAGE_NAME1;
        this.databaseMetaData= connection.getMetaData();
    }


    public String findSql(Entity entity, String STRING) {
        String sql = "SELECT * FROM " + getTableName(entity) + " WHERE " + TABLE_PRIMARY_KEY_NAME  + " = " + "'"+STRING+"'";
        return sql;
    }

    public String findAllSql(Entity entity) {
        String sql = "SELECT * FROM " + getTableName(entity);
        return sql;
    }

    public String saveSql(Entity entity) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, SQLException {
        String[] nameOfAttributes = new String[10];
        int numberOfAttributes = 0;
        ResultSet columns = databaseMetaData.getColumns(null, null, getTableName(entity), null );
        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            nameOfAttributes[numberOfAttributes]=columnName;
            numberOfAttributes++;
        }
        String[] values = getValues(entity, nameOfAttributes, numberOfAttributes);

        String sql = "INSERT INTO "+getTableName(entity)+" VALUES ( '"+entity.getId()+"'";

        for (int i = 1; i < numberOfAttributes ; i++) {
            sql = sql+", '"+values[i]+"'";
        }
        sql = sql+")";

        return sql;
    }

    public String updateSql(Entity entity) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, SQLException {
        String[] nameOfAttributes = new String[10];
        int numberOfAttributes = 0;
        ResultSet columns = databaseMetaData.getColumns(null, null, getTableName(entity), null );
        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            nameOfAttributes[numberOfAttributes]=columnName;
            numberOfAttributes++;
        }
        String[] values = getValues(entity, nameOfAttributes, numberOfAttributes);
        String sql = "UPDATE "+getTableName(entity)+" SET ";

        for (int i = 1; i < numberOfAttributes ; i++) {
            sql = sql+nameOfAttributes[i]+" = '"+values[i]+"'";
            if(i == numberOfAttributes-1){
                continue;
            }
            sql = sql+", ";
        }
        sql = sql+" WHERE id = '"+entity.getId()+"'";
        return sql;
    }

    public String updateSql(Entity entity, String[] params) throws SQLException {
        String[] nameOfAttributes = new String[10];
        int numberOfAttributes = 0;
        ResultSet columns = databaseMetaData.getColumns(null, null, getTableName(entity), null );
        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            nameOfAttributes[numberOfAttributes]=columnName;
            numberOfAttributes++;
        }
        String[] values = params;

        String sql = "UPDATE "+getTableName(entity)+" SET ";

        for (int i = 1; i < numberOfAttributes ; i++) {
            sql = sql+nameOfAttributes[i]+" = '"+values[i]+"'";
            if(i == numberOfAttributes-1){
                continue;
            }
            sql = sql+", ";
        }
        sql = sql+" WHERE id = '"+entity.getId()+"'";
        return sql;
    }

    public String removeSql(Entity entity) {
        String sql = "DELETE FROM "+ getTableName(entity);
        return sql;
    }

    public String removeSql(Entity entity, long id) {
        String sql = "DELETE FROM "+ getTableName(entity) + " WHERE id = "+id;
        return sql;
    }

    public String getTableName(Entity entity) {

        String cName = entity.getClass().getSimpleName(); // CLASS NAME
        String tName = cName.substring(0, cName.length() - 6); // TABLE NAME FROM CLASS NAME
        String cName2 = tName.substring(0, 1).toLowerCase() + tName.substring(1); // Make the first char in column name small
        return cName2;
    }

    // Get the value of each attribute
    public String[] getValues(Entity entity, String[] nameOfAttributes, int numberOfAttributes) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        String[] values = new String[numberOfAttributes];

        for (int i = 1; i < numberOfAttributes; i++) {
            String strClassName = "entity."+entity.getClass().getSimpleName();
            Field privateField = Class.forName( PACKAGE_NAME + "." + strClassName  ).getDeclaredField(nameOfAttributes[i]);
            privateField.setAccessible(true);
            values[i] = ( String )privateField.get(entity);
        }
        return values;
    }
    public ResultSet sqlToResultSet(String sql) throws SqlQueryCreationException, ConnectionFailureException, SQLException {

        Statement mStatement = null;
        ResultSet mResultSet = null;

        try {
            mStatement = connection.createStatement();
            mResultSet = mStatement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mResultSet;
    }
}
