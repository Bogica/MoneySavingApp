package com.example.moneySavingApp.savings.event;

import com.example.moneySavingApp.savings.constants.EventName;
import com.example.moneySavingApp.savings.constants.RuleType;
import com.example.moneySavingApp.savings.rule.SavingsRule;
import com.example.moneySavingApp.transaction.Transaction;
import org.apache.tomcat.util.digester.Rule;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * A Savings Event represents an event in the history of a Savings Goal.
 * Events can be either monetary (triggered by the application of Savings Rules,
 * manual transfers, interest payments or incentive payouts), or other events
 * of significance in the history of the goal, such as pausing or unpausing
 * Savings Rules or other users joining or leaving a shared goal.
 */
public class SavingsEvent {

    private Long id;
    private Long userId;
    private Long transactionId;
    private Long savingsGoalId;
    private Long savingsRuleId;
    private EventName eventName;
    private String date;
    private Double amount;
    private Long triggerId;
    private RuleType ruleType;
    private Long savingsTransferId;
    private Boolean cancelled;
    private Instant created;


    public SavingsEvent() {}

    public SavingsEvent(Long id, Long userId, Long transactionId, Long savingsGoalId, Long savingsRuleId, EventName eventName, String date, Double amount, Long triggerId, RuleType ruleType, Long savingsTransferId, Boolean cancelled, Instant created) {
        this.id = id;
        this.userId = userId;
        this.transactionId = transactionId;
        this.savingsGoalId = savingsGoalId;
        this.savingsRuleId = savingsRuleId;
        this.eventName = eventName;
        this.date = date;
        this.amount = amount;
        this.triggerId = triggerId;
        this.ruleType = ruleType;
        this.savingsTransferId = savingsTransferId;
        this.cancelled = cancelled;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getSavingsGoalId() {
        return savingsGoalId;
    }

    public void setSavingsGoalId(Long savingsGoalId) {
        this.savingsGoalId = savingsGoalId;
    }

    public Long getSavingsRuleId() {
        return savingsRuleId;
    }

    public void setSavingsRuleId(Long savingsRuleId) {
        this.savingsRuleId = savingsRuleId;
    }

    public EventName getEventName() {
        return eventName;
    }

    public void setEventName(EventName eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        if (amount == null) {
            return 0d;
        } else {
            return amount;
        }
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(Long triggerTransactionId) {
        this.triggerId = triggerTransactionId;
    }

    public Long getSavingsTransferId() {
        return savingsTransferId;
    }

    public void setSavingsTransferId(Long savingsTransferId) {
        this.savingsTransferId = savingsTransferId;
    }

    public boolean isCancelled() {
        if (cancelled == null) {
            return false;
        } else {
            return cancelled;
        }
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public RuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

}