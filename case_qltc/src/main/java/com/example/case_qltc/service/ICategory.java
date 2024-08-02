package com.example.case_qltc.service;

import com.example.case_qltc.model.Category;

import java.util.List;

public interface ICategory {
    public List<Category> getAllCategory();

    public void insertCategory(Category category);

    public boolean updateCategory(Category category);

    public boolean deleteCategory(int id);
}
