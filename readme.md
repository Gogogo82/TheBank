**"The bank" app**

User name GogoMP
Password 123

Features:
-   Spring security with custom login form
-   connection to db, described in resources\database.properties
-   CRUD list of clients; clients has acounts
-   CRUD list of accounts; accounts has transactions
-   CRUD list of transactions; transaction affects 2 accounts
-   annotation validation for phone, Hibernate validation for account and amount
-   aspect exception handling - redirect to custom error page