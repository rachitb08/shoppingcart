# Bazinga Shopping Cart
## About
This is a demo project to demonstrate Shopping cart for Bazinga with the feature for adding, updating and removing products from the cart through api.
It is made using **Java 1.8, Spring Boot, Spring Data JPA. Database is MySql.**

## Assumptions
1. User login and registration functionality is not implemented. In Every api request user id will be passed and cart functionality for that user will work. Database design is however done in such a way that user functionality can be extended.
2. This application will support only reading products from the database.

## Configurations
### Configuration Files
Folder **src/resources/** contains config files for Shopping Cart Spring Boot application.
- **src/resources/application.properties** - main configuration file. 
- **src/main/resources/sql/shopping_cart.sql** - This file contains mysql queries that are to be executed before running the application.

