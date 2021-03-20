package com.example.moneySavingApp.savings.rule;

import com.example.moneySavingApp.savings.constants.RuleType;
import com.example.moneySavingApp.savings.event.SavingsEvent;
import com.example.moneySavingApp.transaction.Transaction;
import com.example.moneySavingApp.transaction.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        SavingsRule guiltyPleasureRule = SavingsRule.createGuiltyPleasureRule(1l, userId, "Starbucks", 5.34d);
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

        List<SavingsEvent> savingsEventList = new ArrayList<>();

        long savingsEventId = 1L;

        for(Transaction t : userTransaction){
            List<SavingsEvent> savingsEvents = new ArrayList<>();

                Double amountTransaction = t.getAmount();

                if (amountTransaction >= 0) {
                    continue;
                }

                if (savingsRule.getRuleType() == RuleType.ROUNDUP) {
                    double wholeNumber = Math.ceil(amountTransaction / 2);
                    double result = wholeNumber * 2;
                    double saving = result - amountTransaction;
                    savingsEvents = getSavingEvents(t.getId(), saving, savingsRule);
                }

                if (savingsRule.getRuleType() == RuleType.GUILTYPLEASURE) {
                    if (savingsRule.getPlaceDescription().equals(t.getDescription())) {
                        savingsEvents = getSavingEvents(t.getId(), savingsRule.getAmount(), savingsRule);
                    }
                }


                for(SavingsEvent se : savingsEvents){
                    se.setId(savingsEventId ++);
                    savingsEventList.add(se);
                }

        }

        return savingsEventList;

    }

    public List<SavingsEvent> getSavingEvents(Long transactionId, double amount, SavingsRule savingsRule) {
        List<Long> savingGoalIds = savingsRule.getSavingsGoalIds();
        List<SavingsEvent> savingsEventsList = new ArrayList<>();
        for (Long id : savingGoalIds) {
            SavingsEvent savingsEvent = new SavingsEvent();
            savingsEvent.setAmount(amount / savingGoalIds.toArray().length);
            savingsEvent.setSavingsGoalId(id);

            savingsEvent.setTransactionId(transactionId);
            savingsEvent.setUserId(savingsRule.getUserId());
            savingsEvent.setSavingsRuleId(savingsRule.getId());
            savingsEvent.setDate(LocalDate.now());
            savingsEvent.setRuleType(savingsRule.getRuleType());

            savingsEventsList.add(savingsEvent);
        }
        return savingsEventsList;
    }

    @Override
    public List<Transaction> allTransactions(SavingsRule savingsRule) {
        List<Transaction> userTransaction = transactionsService.latestTransactionsForUser(savingsRule.getUserId());
        return userTransaction;
    }

}