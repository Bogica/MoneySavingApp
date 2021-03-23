package com.example.moneySavingApp.savings.rule;


import com.example.moneySavingApp.savings.event.SavingsEvent;
import com.example.moneySavingApp.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savings/rule")
public class SavingsRulesController {

    private final SavingsRulesService savingsRulesService;


    @Autowired
    public SavingsRulesController(SavingsRulesService savingsRulesService) {
        this.savingsRulesService = savingsRulesService;
    }

    @GetMapping("/active/{userId}")
    public List<SavingsRule> activeRulesForUser(@PathVariable Long userId) {
        return savingsRulesService.activeRulesForUser(userId);
    }

    @GetMapping("/savings")
    public List<SavingsEvent> executeRule(@RequestBody SavingsRule savingsRule){
        return savingsRulesService.executeRule(savingsRule);
    }

    @GetMapping("/transactions")
    public List<Transaction> allTransactionsByUser(@RequestBody SavingsRule savingsRule){
        return savingsRulesService.allTransactions(savingsRule);
    }





}
