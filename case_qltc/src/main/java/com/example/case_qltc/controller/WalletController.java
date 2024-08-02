package com.example.case_qltc.controller;

import com.example.case_qltc.model.Wallet;
import com.example.case_qltc.service.wallet.WalletDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "walletController", value = "/wallets")
public class WalletController extends HttpServlet {
    private static WalletDAO walletDAO = new WalletDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "create":
                formCreate(request,response);
                break;
            default:
                List<Wallet> listWallet = walletDAO.showAll();
                request.setAttribute("listWallet", listWallet);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wallet/list.jsp");
                dispatcher.forward(request, response);
        }
        
    }

    private void formCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/wallet/create.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "create":
                createWallet(request, response);
                break;
        }
    }

    private void createWallet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        Wallet wallet = new Wallet(name, amount);
        System.out.println(wallet);
        walletDAO.create(wallet);
        response.sendRedirect(request.getContextPath()+"/wallets");
    }
}
