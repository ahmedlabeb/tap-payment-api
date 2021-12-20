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
- Start the server from the IDE , Server will be up and running on port 8080  , you can view the swagger through this URL   
  http://localhost:8080/swagger-ui/index.html#/  
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