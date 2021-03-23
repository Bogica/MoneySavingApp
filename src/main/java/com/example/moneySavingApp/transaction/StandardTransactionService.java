package com.example.moneySavingApp.transaction;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StandardTransactionService implements TransactionsService {

    @Override
    public List<Transaction> latestTransactionsForUser(Long userId) {
        return createDummyTransactions(userId);
    }

    private static List<Transaction> createDummyTransactions(Long userId) {

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(1l,userId, -5.34d, "Starbucks", LocalDate.of(2015,7,1)));
        transactions.add(new Transaction(2l,userId, -2.16d, "Starbucks", LocalDate.of(2015,7,2)));
        transactions.add(new Transaction(3l,userId, -3.09d, "McDonald's", LocalDate.of(2015,7,2)));
        transactions.add(new Transaction(4l,userId, -1.03d, "Starbucks", LocalDate.of(2015,7,3)));
        transactions.add(new Transaction(5l,userId, -2.99d, "Apple Itunes", LocalDate.of(2015,7,7)));
        transactions.add(new Transaction(6l,userId, 1945.00d, "Salary", LocalDate.of(2015,7,25)));
        transactions.add(new Transaction(7l,userId, -9.76d, "Amazon", LocalDate.of(2015,7,8)));
        transactions.add(new Transaction(8l,userId, -59.45d, "Walmart", LocalDate.of(2015,7,8)));
        transactions.add(new Transaction(9l,userId, -13.14d, "Papa Joe's", LocalDate.of(2015,7,13)));
        transactions.add(new Transaction(10l,userId, -2.16d, "Starbucks", LocalDate.of(2015,7,29)));
        transactions.add(new Transaction(11l, userId, -1.99d, "Apple Itunes", LocalDate.of(2015, 8, 3)));

        return transactions;
    }


}
