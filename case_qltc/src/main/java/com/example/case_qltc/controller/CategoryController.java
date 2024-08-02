package com.example.case_qltc.controller;

import com.example.case_qltc.exception.CommonException;
import com.example.case_qltc.model.Category;
import com.example.case_qltc.service.CategoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryController", urlPatterns = "/categories")
public class CategoryController extends HttpServlet {
    CategoryDAO categoryDao = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        switch (action) {
            case "create":
                req.setAttribute("message", "");
                showFormCreate(req, resp);
                break;
            default:
                List<Category> categories = categoryDao.getAllCategory();
                req.setAttribute("categories", categories);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/list.jsp");
                requestDispatcher.forward(req, resp);
        }

    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/create.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "create":
                createCategory(req, resp);
                break;

        }
    }

    private void createCategory(HttpServletRequest req, HttpServletResponse resp) throws RuntimeException {
        String name = req.getParameter("name");
        String note = req.getParameter("note");
        Category category = new Category(name, note);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/create.jsp");
        try {
            if (categoryDao.insertCategory(category)) {
                req.setAttribute("message", "Them danh muc thanh cong");
            } else {
                req.setAttribute("message", "Them danh muc khong thanh cong");
            }
            requestDispatcher.forward(req, resp);
        } catch (CommonException e) {
            req.setAttribute("message", "Loi he thong: " + e.getMessage());
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
