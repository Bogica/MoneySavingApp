package com.example.moneySavingApp.transaction;

import java.util.List;

public interface TransactionsService {

    List<Transaction> latestTransactionsForUser(Long userId);

}
