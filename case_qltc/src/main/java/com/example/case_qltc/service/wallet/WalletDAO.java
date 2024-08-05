package com.example.case_qltc.service.wallet;

import com.example.case_qltc.model.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletDAO implements IWalletService{

    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/financial_management";
        String user = "root";
        String password = "012345";
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
             PreparedStatement preparedStatement = connection.prepareStatement("select * from wallet");
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String walletName = resultSet.getString("walletName");
                int amount = resultSet.getInt("amount");
                Wallet wallet = new Wallet(id, walletName,amount);
                wallets.add(wallet);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wallets;
    }

    @Override
    public void create(Wallet wallet) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into wallet (walletName, amount) values (?,?)");
        ){
            preparedStatement.setString(1, wallet.getName());
            preparedStatement.setInt(2, wallet.getAmount());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Wallet findById(int id) {
        Wallet wallet = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from wallet where id = ?");
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String walletName = resultSet.getString("walletName");
                int amount = resultSet.getInt("amount");
                wallet = new Wallet(id,walletName,amount);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wallet;
    }

    @Override
    public boolean update(Wallet wallet) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update wallet set walletName = ?, amount = ? where id = ?");
        ){
            preparedStatement.setString(1, wallet.getName());
            preparedStatement.setInt(2, wallet.getAmount());
            preparedStatement.setInt(3, wallet.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from wallet where id = ?");
        ){
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }
}
