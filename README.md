# OOAD_Case_Study_Banking
# OOAD-IsA-Relationship-Banking
## Class Diagram
![UML diagram](https://github.com/Abhijit-Thankachan/OOAD_Case_Study_Banking/assets/74815617/129c9a41-e4fd-49ee-a2e4-6afa6a16d4e4)

### Domain: Banking

Use case : When a customer attempts to deposit money, the list of all accounts which the customers possess should be listed so that the customer can select to which account he would like to deposit the money.[Hint: Customer has Accounts] . Customer can have the following accounts

SavingsMaxAccount

CurrentAccount

LoanAccount

- Customer(customerCode,customerName,List<Account>)

- Account(accountNo,accountType,balance,Product).

- Product(productCode,productName,List<Service>)

- SavingsMaxAccount is a Product(minimumBalance of Rs1000 should be maintained in the account)

- CurrentAccount is a Product

- LoanAccount is a Product.(chequeDeposit should be chargeable ie).3%).

- Service(serviceCode,serviceName,rate)

He should be given default services like

SavingsMaxAccount(CashDeposit. ATMWithdrawl,OnlineBanking)

CurrentAccount(CashDeposit,OnlineBanking,ATMWithdrawl,MobileBanking)

LoanAccount(CashDeposit,ChequeDeposit)
