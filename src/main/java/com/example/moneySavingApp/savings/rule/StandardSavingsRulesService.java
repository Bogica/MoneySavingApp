package com.example.moneySavingApp.savings.rule;

import com.example.moneySavingApp.savings.event.SavingsEvent;
import com.example.moneySavingApp.transaction.Transaction;
import com.example.moneySavingApp.transaction.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandardSavingsRulesService implements SavingsRulesService {

    private final TransactionsService transactionsService;

    @Autowired
    public StandardSavingsRulesService(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @Override
    public List<SavingsRule> activeRulesForUser(Long userId) {

        SavingsRule guiltyPleasureRule = SavingsRule.createGuiltyPleasureRule(1l, userId, "Starbucks", 3.00d);
        guiltyPleasureRule.addSavingsGoal(1l);
        guiltyPleasureRule.addSavingsGoal(2l);
        SavingsRule roundupRule = SavingsRule.createRoundupRule(2l, userId, 2.00d);
        roundupRule.addSavingsGoal(1l);

        List<SavingsRule> savingsRuleLIst = new ArrayList<>();
        savingsRuleLIst.add(guiltyPleasureRule);
        savingsRuleLIst.add(roundupRule);

        return savingsRuleLIst;
    }

    @Override
    public List<SavingsEvent> executeRule(SavingsRule savingsRule) {
        List<Transaction> userTransaction = transactionsService.latestTransactionsForUser(savingsRule.getUserId());
        List<SavingsRule> savingsRuleList = activeRulesForUser(savingsRule.getUserId());

        List<SavingsEvent> savingsEventList = new ArrayList<>();

        for(SavingsEvent se : savingsEventList){
            se.setUserId(savingsRule.getUserId());
            se.setSavingsGoalId(se.getSavingsGoalId());
            savingsEventList.add(se);
        }


       return savingsEventList;
    }

    @Override
    public List<Transaction> allTransactions(SavingsRule savingsRule) {
        List<Transaction> userTransaction = transactionsService.latestTransactionsForUser(savingsRule.getUserId());
        return userTransaction;
    }
}