package com.example.case_qltc.service.wallet;

import java.util.Collections;
import java.util.List;

public class Wallet implements IWalletService{


    @Override
    public List<com.example.case_qltc.model.Wallet> showAll() {
        return Collections.emptyList();
    }

    @Override
    public void create(com.example.case_qltc.model.Wallet wallet) {

    }

    @Override
    public com.example.case_qltc.model.Wallet findById(int id) {
        return null;
    }

    @Override
    public boolean update(com.example.case_qltc.model.Wallet wallet) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
