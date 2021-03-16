package com.servlet.interfaces;

import java.util.List;

public interface CrudDao<T> {
    T find(Long id);
    void save(T model);
    void update(T model);
    void delete(Long id);
    List<T> findAll();
}
