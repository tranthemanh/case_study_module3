package com.example.case_qltc.service.category;

import com.example.case_qltc.model.Category;

import java.util.List;

public interface ICategory{
    public List<Category> getAllCategory();

    public boolean insertCategory(Category category) throws Exception;

    public boolean updateCategory(Category category);

    public boolean deleteCategory(int id);
}
