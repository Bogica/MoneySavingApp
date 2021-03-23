package com.example.moneySavingApp.transaction;

import com.example.moneySavingApp.savings.event.SavingsEvent;

import java.util.List;

public interface TransactionsService {

    List<Transaction> latestTransactionsForUser(Long userId);


}
