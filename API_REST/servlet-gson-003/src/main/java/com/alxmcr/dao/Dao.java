package com.alxmcr.dao;

import java.util.List;

public interface Dao<T> {
    
    T findById(Long id);

    T findByName(String name);
     
    List<T> getAll();
     
    T save(T t) throws Exception;

    T save(T t, boolean isRollback) throws Exception;
     
    T update(T t, String[] params);
     
    T delete(T t);
}

