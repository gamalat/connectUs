package com.cleancodesoft.connectus.repository;




import com.cleancodesoft.connectus.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface EntityManager {
    public Entity find(Entity entity, String STRING) throws Exception;

    public List<Entity> findAll(Entity entity) throws SQLException;

    public int save(Entity entity) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException;

    public void update(Entity entity) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException;

    public void update(Entity entity, String[] params) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException;

    public void remove(Entity entity) throws SQLException;

    public void remove(Entity entity, long id) throws SQLException;
    public List<Entity> getPost(Entity entity) throws SQLException;

    public List<Entity> findAll(Entity entity, String STRING) throws SQLException;


}
