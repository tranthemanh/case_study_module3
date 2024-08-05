package com.example.case_qltc.controller.wallet;

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
                formCreate(request, response);
                break;
            case "update":
                formUpdate(request, response);
                break;
            case "delete":
                formDelete(request,response);
                break;
            default:
                List<Wallet> listWallet = walletDAO.showAll();
                request.setAttribute("listWallet", listWallet);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/wallet/list.jsp");
                dispatcher.forward(request, response);
        }

    }

    private void formDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Wallet walletDelete = walletDAO.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/wallet/delete.jsp");
        request.setAttribute("walletDelete", walletDelete);
        dispatcher.forward(request,response);
    }

    private void formUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Wallet updateWallet = walletDAO.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/wallet/update.jsp");
        request.setAttribute("wallet", updateWallet);
        dispatcher.forward(request, response);
    }

    private void formCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/wallet/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "create":
                createWallet(request, response);
                break;
            case "update":
                updateWallet(request, response);
                break;
            case "delete":
                deleteWallet(request, response);
                break;
        }
    }

    private void deleteWallet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        walletDAO.delete(id);
        response.sendRedirect(request.getContextPath()+"/wallets");
    }

    private void updateWallet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        Wallet wallet = new Wallet(id, name, amount);
        walletDAO.update(wallet);
        response.sendRedirect(request.getContextPath() + "/wallets");
    }

    private void createWallet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        Wallet wallet = new Wallet(name, amount);
        System.out.println(wallet);
        walletDAO.create(wallet);
        response.sendRedirect(request.getContextPath() + "/wallets");
    }
}
