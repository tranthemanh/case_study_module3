package com.example.case_qltc.controller;

import com.example.case_qltc.model.Category;
import com.example.case_qltc.service.CategoryDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="CategoryController", urlPatterns = "/categorys")
public class CategoryController extends HttpServlet {
    CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category>categories= categoryDao.getAllCategory();
        req.setAttribute("categorys",categories);
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("/category/list.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
