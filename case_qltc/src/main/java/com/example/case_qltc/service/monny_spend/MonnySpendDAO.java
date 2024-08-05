package com.example.case_qltc.service.monny_spend;

import com.example.case_qltc.model.MonnySpend;
import com.example.case_qltc.model.Wallet;

import java.sql.*;

public class MonnySpendDAO {
    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/financial_management";
        String user = "root";
        String password = "123456Abc";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Ket noi thanh cong");
        }catch (SQLException e) {
            System.out.println("Loi ket noi");
        }
        return connection;
    }

    public void createMonnySpend(MonnySpend monnySpend) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into monny_spend(date, amount, note, categorySpend_id, wallet_id) value (?,?,?,?,?)");
        ){
            preparedStatement.setDate(1, Date.valueOf(monnySpend.getDate()));
            preparedStatement.setInt(2, monnySpend.getAmount());
            preparedStatement.setString(3, monnySpend.getNote());
            preparedStatement.setInt(4, monnySpend.getCategoryId());
            preparedStatement.setInt(5, monnySpend.getWalletId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
