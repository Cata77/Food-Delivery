# Food-Delivery Java Spring Boot Application
Our customer operates a restaurant and also provides **food delivery service** to his customer. The restaurant offers a number of food with actual price, and
stores the history of past orders in a database

## Functionality

The application drives the user through a well defined user flow.

1. Application data initialization: read user credentials.

2. Authenticate the user.

3. List all the foods.

4. The user selects a food and specifies the amount. If a selected food is already in the cart, then selecting that food now and specifying its amount
overwrites the old amount in the cart. If the new amount is 0, then the food is removed from the cart.

5. The user is prompted to continue:

    - If the user continues to shop, go back to #3.
    
    - If not, create the order.
  
6. Print the order details and thank the user for his/her purchase.

Error handling:

  - For functionality (1), authentication.
  
    - If the user enters incorrect credentials, the application writes a message to the console. There is no need to request credentials again,
the application quits.

  - For functionality (4), ordering.

    - If the user enters an amount that is higher than his/her current balance, the system writes notification message and asks for new amount.
    
    - In the following cases, the system draws the user's attention:
    
      - If the user wants to remove a food that he/she haven't ordered
      
      - If the user misspelled the name of the food
      
      - If the user entered a negative number
      
Observations:

To access the admin dashboard, simply log in with the following credentials:

```java

Email address: admin

Password: admin
```
Here, the admin can see the following informations about the restaurant:

  - The ID of the most expensive order and it's total price
  
  - The most popular food based on the ordered amount
  
  - The name of the customer who ordered the most
  
  - The total income
  
In order for the application to work properly, you will first need to run the SQL script that will populate the database with customers and food.
The script can be found in ```resources/data.sql```
  
## Technology

- Java 17

- Spring Boot

- Hibernate

- Spring Data JPA

- Build Tool: Gradle

- Database: PostgreSQL

- IDE: IntelliJ 





  
  
      
  
  
  
  
  
