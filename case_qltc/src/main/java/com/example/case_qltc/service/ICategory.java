package com.example.case_qltc.service;

import com.example.case_qltc.exception.CommonException;
import com.example.case_qltc.model.Category;

import java.util.List;

public interface ICategory {
    List<Category> getAllCategory();

    boolean insertCategory(Category category) throws CommonException;

    boolean updateCategory(Category category);

    boolean deleteCategory(int id);
}
