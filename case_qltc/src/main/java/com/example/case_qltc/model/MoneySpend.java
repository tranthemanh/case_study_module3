package com.example.case_qltc.model;

import java.time.LocalDate;

public class MoneySpend {
    private int id;
    private LocalDate date;
    private int amount;
    private String note;
    private int categoryId;
    private int walletId;

    public MoneySpend() {
    }

    public MoneySpend(LocalDate date, int amount, String note, int categoryId, int walletId) {
        this.date = date;
        this.amount = amount;
        this.note = note;
        this.categoryId = categoryId;
        this.walletId = walletId;
    }

    public MoneySpend(int id, LocalDate date, int amount, String note, int categoryId, int walletId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.note = note;
        this.categoryId = categoryId;
        this.walletId = walletId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }
}
