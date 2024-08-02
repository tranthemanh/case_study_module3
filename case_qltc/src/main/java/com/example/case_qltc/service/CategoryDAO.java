package com.example.case_qltc.service;

import com.example.case_qltc.exception.CommonException;
import com.example.case_qltc.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategory {
    private String jdbcURL = "jdbc:mysql://localhost:3306/financial_management";
    private String jdbcName = "root";
    private String jdbcPassword = "012345";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcName, jdbcPassword);
            System.out.println("Ket noi thanh cong");
        } catch (SQLException e) {
            System.out.println("Ket 0 thanh cong");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        String selectAll = "SELECT * FROM category_earn ORDER BY id DESC";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectAll)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                categories.add(new Category(name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public boolean insertCategory(Category category) throws CommonException {
        if (category.getName().isEmpty()) {
            throw new CommonException("Vui long nhap ten");
        }
        if (category.getName().length() > 200) {
            throw new CommonException("Ten qua dai");
        }
        int rowAffected = 0;
        String insertCategory = "INSERT INTO category_earn(name, note) value (?, ?);";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertCategory)) {
            preparedStatement.setString(1, category.name);
            preparedStatement.setString(2, category.note);
            rowAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CommonException(e.getMessage());
        }
        return rowAffected > 0;
    }

    @Override
    public boolean updateCategory(Category category) {
        return false;
    }

    @Override
    public boolean deleteCategory(int id) {
        return false;
    }
}
