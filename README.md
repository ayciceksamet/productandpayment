***Product Management and Payment System***

*ProductController*

/product/add - Add new product to system.                 - POST

/product/update/{id} - Update existing product on system. - PUT

/product/remove/{id} - Remove existing product on system. - DELETE

/product/get/{id}- Fetch to product on system.            - GET

/product/get - Fetch all products on system.              - GET


# Product and Payment Management System RESTAPIDoc

These restapi were designed to perform simulate product and payment management system using [Spring Framework](https://spring.io/)

Where full URLs are provided in responses they will be rendered as if service
is running on 'http://localhost:8080/'.

### Product related

Each endpoint manipulates or displays information related to the User whose
Token is provided with the request:

* [Add Product](src/main/java/com/iyzico/challenge/controller/ProductController.java) : `POST /product/add/`
* [Add Product](src/main/java/com/iyzico/challenge/controller/ProductController.java) : `POST /product/add/`

* [Add Product](src/main/java/com/iyzico/challenge/controller/ProductController.java) : `POST /product/add/`

* [Add Product](src/main/java/com/iyzico/challenge/controller/ProductController.java) : `POST /product/add/`

* [Add Product](src/main/java/com/iyzico/challenge/controller/ProductController.java) : `POST /product/add/`

* [Update info](user/put.md) : `PUT /api/user/`

### Account related

Endpoints for viewing and manipulating the Accounts that the Authenticated User
has permissions to access.

* [Show Accessible Accounts](accounts/get.md) : `GET /api/accounts/`
* [Create Account](accounts/post.md) : `POST /api/accounts/`
* [Show An Account](accounts/pk/get.md) : `GET /api/accounts/:pk/`
* [Update An Account](accounts/pk/put.md) : `PUT /api/accounts/:pk/`
* [Delete An Account](accounts/pk/delete.md) : `DELETE /api/accounts/:pk/`
