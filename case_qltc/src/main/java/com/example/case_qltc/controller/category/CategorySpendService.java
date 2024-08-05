package com.example.case_qltc.controller.category;

import com.example.case_qltc.exception.CommonException;
import com.example.case_qltc.model.Category;
import com.example.case_qltc.service.category.CategoryEarnDao;
import com.example.case_qltc.service.category.CategorySpendDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategorySpendService", urlPatterns = "/categories_spend")
public class CategorySpendService extends HttpServlet {
    CategorySpendDAO category_spendDao = new CategorySpendDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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
                case"delete":
                    DeleteCategory_spend(req,resp);
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

    private void DeleteCategory_spend(HttpServletRequest req, HttpServletResponse resp) {
        int id= Integer.parseInt(req.getParameter("id"));
        category_spendDao.deleteCategory(id);

        List<Category>categories_spend = category_spendDao.getAllCategory();
        req.setAttribute("categories_spend",categories_spend);
        try {
            req.getRequestDispatcher("/category/showCategory_spend.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFormUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        CategoryEarnDao category_earn_DAO = new CategoryEarnDao();
        int id = Integer.parseInt(req.getParameter("id"));
        Category category_spend = category_earn_DAO.getCategoryByID(id);
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
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String note = req.getParameter("note");

            Category category_spend = new Category(id, name, note);

            boolean isUpdated = category_spendDao.updateCategory(category_spend);

            if (isUpdated) {
                req.setAttribute("message", "Thay doi danh muc thanh cong!");
            } else {
                req.setAttribute("message", "Thay doi thay bai!");
            }

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/edit_Category_spend.jsp");
            requestDispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID format");
        }
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
