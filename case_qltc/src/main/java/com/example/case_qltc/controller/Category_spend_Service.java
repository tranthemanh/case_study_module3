package com.example.case_qltc.controller;

import com.example.case_qltc.exception.CommonException;
import com.example.case_qltc.model.Category;
import com.example.case_qltc.service.Category.Category_spend_DAO;
import com.example.case_qltc.service.Category.Category_spendDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Category_spend_Service", urlPatterns = "/categories_spend")
public class Category_spend_Service extends HttpServlet {
    Category_spendDAO category_spendDao = new Category_spendDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        try {
            switch (action) {
                case "create":
                    req.setAttribute("message", "");
                    showFormCreate(req, resp);
                    break;
                case "edit":
                    showFormUpdate(req, resp);
                    break;
                default:
                    List<Category> categories_spend = category_spendDao.getAllCategory();
                    req.setAttribute("categories_spend", categories_spend);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/showCategory_spend.jsp");
                    requestDispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    private void showFormUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Category_spend_DAO category_spend_DAO = new Category_spend_DAO();
        int id = Integer.parseInt(req.getParameter("id"));
        Category category_spend = category_spend_DAO.getCategoryByID(id);
        req.setAttribute("category_spend", category_spend);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/edit_Category_spend.jsp");
        requestDispatcher.forward(req, resp);

    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/createCategory_spend.jsp");
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
        try {
            switch (action) {
                case "create":
                    createCategory(req, resp);
                    break;
                case "edit":
                    updateCategory(req, resp);
                    break;
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String name = req.getParameter("name");
        String note = req.getParameter("note");
        Category category= new Category(name,note);
        category_spendDao.updateCategory(category);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/edit_Category_spend.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void createCategory(HttpServletRequest req, HttpServletResponse resp) throws RuntimeException {
        String name = req.getParameter("name");
        String note = req.getParameter("note");
        Category category_spend = new Category(name, note);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/createCategory_spend.jsp");
        try {
            if (category_spendDao.insertCategory(category_spend)) {
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
