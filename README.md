atm-restful-service
===============================

'atm-restful-service' is back-end system for ATM machines. It is a restful API with below features.

Application uses Apache Derby embedded DB, to make configuration easy. createschema.sql and insertdata.sql is used to create schema and setup accounts. 

1. Check balance
2. Deposit money
3. Withdraw money

Running the Application
=========================
Please execute main method in Application.java to star the embedded tomcat server.

Without Authentication
========================

'atm-restful-service' provides a token based authentication. How ever authentication is disable in developement. 
by setting property ignoreAuth to true in env.properties. Default value is true.

Below are the URLs when authentication is disabled.

1. Check balance: https://localhost:9011/teller/checkbalance/{token}/{accountNumber}, ex: https://localhost:9011/teller/checkbalance/token/10000
2. Deposit Money: https://localhost:9011/teller/deposit/{token}/{accountNumber}/{amount},ex: https://localhost:9011/teller/deposit/token/10000/100
3. Withdraw Money: https://localhost:9011/teller/withdraw/{token}/{accountNumber}/{amount}, https://localhost:9011/teller/withdraw/token/10000/100


With Authentication
=====================

Use below REST url to get token

https://localhost:9011/teller/gettoken

Use below json object

{
"accountNumber":"10000", 
"accountName":"John",
"pin":"5555"
}

Use chrome extention chrome-extension://hgmloofddffdnphfgcellkdfbfbjeloo/RestClient.html to execute post url, a screen shot is added to the project.


This rest call will return a token, for example '68bff9de-1259-4360-ba2b-687df2f058e8'

One token is valid only for one use. ie, token need to be regenerated after each service call.

URLs with token
=======================

1. Check balance: https://localhost:9011/teller/checkbalance/68bff9de-1259-4360-ba2b-687df2f058e8/10000
2. Deposit Money: https://localhost:9011/teller/deposit/68bff9de-1259-4360-ba2b-687df2f058e8/10000/100
3. Withdraw Money: https://localhost:9011/teller/withdraw/68bff9de-1259-4360-ba2b-687df2f058e8/10000/100



