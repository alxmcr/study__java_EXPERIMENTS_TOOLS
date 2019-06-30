package com.example.dao;

public interface Dao<T> {
       
    T save(T t) throws Exception;

    T save(T t, boolean isRollback) throws Exception;
}