# Topup wallet API Service 
# Instruction to run the project
- Install JDK 11 or higher on your machine
- Install Maven on your machine
- Install Mongo DB 
- Install Compass to view data 
- Install any IDE Tool ( IntelliJ)
# Steps to run the service 
- start Mongo Compass on your machine 
- apply command (maven clean install)
- Note that Mongo DB is running on the default localhost ( localhost:27017 ) which is included by default in the spring mongo DB configuration 
- Start the server from the IDE , Server will be up and running on port 8080  , you can view the swagger through this URL   
  http://localhost:8080/swagger-ui/index.html#/  
- you also can topup the user by using this curl request  
  curl -X POST "http://localhost:8080/api/topup" -H "accept: */*" -H "Content-Type: application/json" -d "{\"topupAmount\":10.2,\"topupCurrency\":\"AED\",\"chargeId\":\"1\",\"customerDto\":{\"id\":\"1\",\"walletId\":\"123\"},\"feesDto\":{\"amount\":1.2,\"currency\":\"AED\"}}"
  
# What is Purpose of This service
 - This Service has only one API that can be used for topup customer, the Post request contain the wallet Id for customer and customer Id,if the customer is not exist in the DB then we will create a new customer and topup his balance.
 - Customer can have multiple wallet at his account 
 - Fees is optional in the request which me we can apply or not apply fees to topup request
 - for each request there will be randome generated UUID
 - we will provide the total balance for user which will be accumulated if we made multiple topup for him
# Technology used

- Spring Boot version 2.5.5
- Java 11 
- Swageer Documentation
- Spring Data Mongo
- Lombok
- Spring boot test
- MapStruct
- jacoco for test coverage 
