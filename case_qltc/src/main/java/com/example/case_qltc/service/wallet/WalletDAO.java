package com.example.case_qltc.service.wallet;

import com.example.case_qltc.model.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WalletDAO implements IWalletService{

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

    @Override
    public List<Wallet> showAll() {
        List<Wallet> wallets = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select walletName, amount from wallet");
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String walletName = resultSet.getString("walletName");
                int amount = resultSet.getInt("amount");
                Wallet wallet = new Wallet(walletName,amount);
                wallets.add(wallet);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wallets;
    }

    @Override
    public void create(Wallet wallet) {

    }

    @Override
    public Wallet findById(int id) {
        return null;
    }

    @Override
    public boolean update(Wallet wallet) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
