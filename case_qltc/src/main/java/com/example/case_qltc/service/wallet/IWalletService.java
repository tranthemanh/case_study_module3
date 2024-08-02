package com.example.case_qltc.service.wallet;

import com.example.case_qltc.model.Wallet;
import com.example.case_qltc.service.IGenerateService;

import java.util.List;

public interface IWalletService extends IGenerateService<Wallet> {
    @Override
    List<Wallet> showAll();

    @Override
    void create(Wallet wallet);

    @Override
    Wallet findById(int id);

    @Override
    boolean update(Wallet wallet);

    @Override
    boolean delete(int id);
}
