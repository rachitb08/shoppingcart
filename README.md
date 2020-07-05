# Bazinga Shopping Cart
## About
This is a demo project to demonstrate Shopping cart for Bazinga with the feature for adding, updating and removing products from the cart through api.
It is made using **Java 1.8, Spring Boot, Spring Data JPA, Lombok. Database is MySql.**

## Assumptions
1. User login and registration functionality is not implemented. In Every api request user id will be passed and cart functionality for that user will work. Database design is however done in such a way that user functionality can be extended.
2. This application will support only reading products from the database.

## Configurations
### Configuration Files
Folder **src/resources/** contains config files for Shopping Cart Spring Boot application.
- **src/resources/application.properties** - main configuration file. 
- **src/main/resources/sql/shopping_cart.sql** - This file contains mysql queries that are to be executed before running the application.

## Build Project
1. Clone the project and navigate to the root path
2. Create database - **shopping_cart**
3. Set Username and Password in the **application.properties** file.
4. Execute queries present in **shopping_cart.sql**.
5) Invoke `mvn clean install -DskipTests`

## Run Project
Invoke following command `java -jar target/shoppingcart-0.0.1-SNAPSHOT.jar`

## API Description
1. **Get All Products API**
  - URI - http://localhost:8080/api/products/
  - Method - GET
2. **Add a product into cart or update product's quantity.**
  - URI - http://localhost:8080/api/cart/add
  - Method - POST
  - Request Body - {"productId":6,"userId":101,"quantity":3}
  - Request Headers
    - Content-Type:application/json
3. **Remove any particular product from api.**
  - URI - http://localhost:8080/api/cart/remove
  - Method - POST
  - Request Body - {"productToRemove":6,"userId":101}
  - Request Headers
    - Content-Type:application/json
4. **Fetch the latest cart of a user.**
  - URI - http://localhost:8080/api/cart/getUserCart
  - Method - GET
  - Request Headers
    - userId:102
  
