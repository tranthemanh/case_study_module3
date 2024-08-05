package com.example.case_qltc.service.category;

import com.example.case_qltc.model.Category;
import com.example.case_qltc.service.IGenerateService;

import java.util.List;
import java.util.concurrent.CompletionException;

public interface ICategory {
    public List<Category> getAllCategory();

    public boolean insertCategory(Category category) throws Exception;

    public boolean updateCategory(Category category);

    public boolean deleteCategory(int id);
}
