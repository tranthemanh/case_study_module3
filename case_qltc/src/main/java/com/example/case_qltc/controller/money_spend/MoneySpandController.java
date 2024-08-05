package com.example.case_qltc.controller.money_spend;

import com.example.case_qltc.model.Category;
import com.example.case_qltc.model.MoneySpend;
import com.example.case_qltc.model.Wallet;
import com.example.case_qltc.service.category.CategorySpendDAO;
import com.example.case_qltc.service.monny_spend.MoneySpendDAO;
import com.example.case_qltc.service.wallet.WalletDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "moneySpandController", value = "/moneyspand")
public class MoneySpandController extends HttpServlet {
    private static MoneySpendDAO  moneySpendDAO = new MoneySpendDAO();
    private static CategorySpendDAO categorySpendDAO = new CategorySpendDAO();
    private static WalletDAO walletDAO = new WalletDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categorySpendDAO.getAllCategory();
        List<Wallet> wallets = walletDAO.showAll();
        request.setAttribute("categories", categories);
        request.setAttribute("wallets", wallets);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/moneyspand/spand.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String note = request.getParameter("note");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int walletId = Integer.parseInt(request.getParameter("walletId"));
        MoneySpend moneySpend = new MoneySpend(date, amount, note, categoryId, walletId);
        moneySpendDAO.createMonnySpend(moneySpend);
        response.sendRedirect(request.getContextPath() + "/moneyspand");
    }
}
