# Introduction

The code skeleton consists of a functional Spring Boot application that contains logic for applying Savings Rules for a user, given some hardcoded bank transaction data and settings. 

# **Basic Domain Concepts**
**Transaction**

A Transaction represents a bank transaction on a bank account. It has basic properties such as amount, description and date. Debits such as card expenses have negative amounts, Credits such as income have positive amounts.

**SavingsRule**

A SavingsRule is an automated savings configuration that saves money for a user based on certain settings and the transactions read for that user.

**SavingsGoal**

A Savings Goal is not explicitely modeled as a class in this skeleton, but represents something (eg. a trip or item) that a user is saving towards.

**SavingsEvent**

A SavingsEvent is an event in the history of a Savings Goal. In this task it is the result of an execution of a SavingsRule on a particular Transaction, for a particular SavingsGoal.

**Task Specification**

Implemented method `executeRule()` in StandardSavingsRulesService loads the latest transactions for the user using the TransactionsService and applies the given SavingsRule to those transactions. The result is a list of SavingsEvents. If a rule is configured for more than one SavingsGoal, the saved amount should be split equally amongst the goals. Note that there are two different RuleTypes: The Roundup Rule and the Guilty Pleasure Rule. The implementation should handle both types. Both of these rules should only be applied to expense transactions.


1.1. The Roundup Rule: When the Roundup Rule is applied to a transaction, it rounds the amount on the transaction to the nearest multiple of the configured roundup amount and generates a SavingsEvent with the difference as the saved amount. I.e., for a transaction of $3.55 with a Roundup Rule configured to round up to the nearest $2.00, the saved amount is $0.45. For a transaction of $2.55, with an identically configured amount, the roundup would be $1.45.

1.2. The Guilty Pleasure Rule: When the Guilty Pleasure Rule is applied to a transaction, it should check that the configured description matches the transaction's description. If so, the configured amount is saved.

Endpoint is created in SavingsRulesController that takes a SavingsRule object as a JSON body, calls the executeRule() method implemented above and returns the list of SavingsEvents as a JSON body. Date in the JSON objects is expressed as strings in the ISO 8601 format.

