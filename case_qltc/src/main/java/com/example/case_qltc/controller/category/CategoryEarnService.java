package com.example.case_qltc.controller.category;

import com.example.case_qltc.exception.CommonException;
import com.example.case_qltc.model.Category;
import com.example.case_qltc.service.category.CategoryEarnDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryEarnService", urlPatterns = "/categories_earn")
public class CategoryEarnService extends HttpServlet {
    CategoryEarnDao category_earn_Dao = new CategoryEarnDao();

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
                case"delete":
                    DeleteCategory_earn(req,resp);
                    break;
                default:
                    List<Category> categories_earn = category_earn_Dao.getAllCategory();
                    req.setAttribute("categories_earn", categories_earn);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/showCategory_earn.jsp");
                    requestDispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    private void DeleteCategory_earn(HttpServletRequest req, HttpServletResponse resp) {
        int id_Category_earn= Integer.parseInt(req.getParameter("id"));
        category_earn_Dao.deleteCategory(id_Category_earn);

        List<Category> categories_earn = category_earn_Dao.getAllCategory();
        req.setAttribute("categories_earn",categories_earn);
        try {
            req.getRequestDispatcher("/category/showCategory_earn.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFormUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        CategoryEarnDao category_earn_DAO = new CategoryEarnDao();
        int id = Integer.parseInt(req.getParameter("id"));
        Category category_earn = category_earn_DAO.getCategoryByID(id);
        req.setAttribute("category_earn", category_earn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/edit_Category_earn.jsp");
        requestDispatcher.forward(req, resp);

    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/createCategory_earn.jsp");
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

            Category category_earn = new Category(id, name, note);

            boolean isUpdated = category_earn_Dao.updateCategory(category_earn);

            if (isUpdated) {
                req.setAttribute("message", "Thay doi danh muc thanh cong!");
            } else {
                req.setAttribute("message", "Thay doi thay bai!");
            }

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/edit_Category_earn.jsp");
            requestDispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID format");
        }
    }

    private void createCategory(HttpServletRequest req, HttpServletResponse resp) throws RuntimeException {
        String name = req.getParameter("name");
        String note = req.getParameter("note");
        Category category = new Category(name, note);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/category/createCategory_earn.jsp");
        try {
            if (category_earn_Dao.insertCategory(category)) {
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
