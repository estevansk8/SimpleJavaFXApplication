package com.example.exjavafx;

import java.util.List;

public interface RepositoryDAO<K,T> {
    List<T> read();
    boolean save(T object);
    boolean findOne(K key);
    boolean update(T object,K key);
    boolean delete(T object);
    boolean clear();
}
