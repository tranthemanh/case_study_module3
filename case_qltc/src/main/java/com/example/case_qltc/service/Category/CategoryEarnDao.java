package com.example.case_qltc.service.category;

import com.example.case_qltc.exception.CommonException;
import com.example.case_qltc.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryEarnDao implements ICategory {
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
        List<Category> category_earn = new ArrayList<>();
        String selectAll = "SELECT * FROM category_earn ORDER BY id DESC";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectAll)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id= rs.getInt("id");
                String name = rs.getString("name");
                category_earn.add(new Category(id,name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category_earn;
    }

    @Override
    public boolean insertCategory(Category category) throws CommonException {
        String insertCategory = "INSERT INTO category_earn(name, note) value (?, ?);";
        if (category.getName().isEmpty()) {
            throw new CommonException("Vui long nhap ten");
        }
        if (category.getName().length() > 200) {
            throw new CommonException("Ten qua dai");
        }
        int rowAffected = 0;
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
        boolean rowUpdated;
        String update = "UPDATE category_earn SET name =?, note=? where id=?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, category.name);
            preparedStatement.setString(2, category.note);
            preparedStatement.setInt(3, category.id);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

    public Category getCategoryByID(int id) {
        Category category_earn = null;
        String selectID = "select *from category_earn where  id=?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectID)) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String note = rs.getString("note");
                category_earn = new Category(name, note);
                return category_earn;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteCategory(int id) {
            boolean rowDelete;
            String deleteCategory_earn="delete from category_earn where id=?;";
            try(Connection connection= getConnection(); PreparedStatement preparedStatement= connection.prepareStatement(deleteCategory_earn)) {
                preparedStatement.setInt(1,id);
                rowDelete=preparedStatement.executeUpdate()>0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return rowDelete;
    }
}
