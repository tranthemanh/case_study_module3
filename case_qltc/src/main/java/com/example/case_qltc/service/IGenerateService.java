package com.example.case_qltc.service;

import java.util.List;

public interface IGenerateService<T> {
    List<T> showAll();

    void create(T t);

    T findById(int id);

    boolean update(T t);

    boolean delete(int id);
}
