# API Automation Framework

A scalable API Automation Framework built using **Rest Assured**, **Java**, **Cucumber BDD**, **TestNG**, and **Maven**.

This framework automates end-to-end API workflows for an demo E-Commerce application including Authentication, Product Management, and Order Management APIs.

---

## Tech Stack

- Java 
- Rest Assured
- Cucumber BDD
- TestNG
- Maven
- Jackson Databind
- Jenkins
- Git & GitHub

---

## Framework Features

- BDD implementation using Cucumber Feature Files
- Reusable API Client Layer
- Centralized API Resource Management using Enum
- Request Specification Builder for reusable configurations
- Dynamic Authentication using Cucumber Hooks
- JSON Data-Driven Testing
- POJO Serialization & Deserialization
- Reusable Response Utility Methods
- Detailed Request & Response Logging
- Maven Build Integration
- Jenkins Pipeline Support

---

## Project Structure

```text
API_Framework
│
├── src/main/java
│   ├── api
│   │   └── EcomAPIClient.java
│   │
│   ├── pojo
│   │   ├── LoginRequest.java
│   │   ├── LoginResponse.java
│   │   ├── OrderDetails.java
│   │   └── Orders.java
│   │
│   └── utils
│       ├── APIResources.java
│       ├── JsonDataReader.java
│       ├── SpecBuilder.java
│       └── TestData.java
│
├── src/test/java
│   ├── Cucumber.options
│   │   └── TestRunner.java
│   │
│   ├── stepDefinitions
│   │   ├── APISteps.java
│   │   └── Hooks.java
│   │
│   └── utils
│       └── ResponseUtils.java
│
├── src/test/resources
│   ├── features
│   │   ├── Login.feature
│   │   ├── AddProduct.feature
│   │   ├── CreateOrder.feature
│   │   ├── DeleteProduct.feature
│   │   ├── OrderDetails.feature
│   │   ├── DeleteOrder.feature
│   │   └── GetAllProducts.feature
│   │
│   ├── testdata
│   │   ├── products.json
│   │   └── image.jpg
│   │
│   └── global.properties
│
├── Jenkinsfile
├── pom.xml
└── README.md
```

---

#### APIs Automated

##### Authentication APIs

- Login API

##### Product APIs

- Add Product
- View All Products
- Delete Product

##### Order APIs

- Create Order
- Get Order Details
- Delete Order

---

#### Test Coverage

##### Positive Scenarios

- Valid User Login
- Add Product Successfully
- Create Order Successfully
- Get Order Details Successfully
- Delete Product Successfully
- Delete Order Successfully
- View All Products Successfully

##### Negative Scenarios

- Login with Invalid Credentials
- Add Product without Authentication
- Create Order without Authentication
- Delete Product with Invalid Product ID
- Delete Order with Invalid Order ID
- Get Order Details with Invalid Order ID

---

#### Configuration

Update the following values in:

```properties
src/test/resources/global.properties
```

```properties
baseURL=https://rahulshettyacademy.com
email=your-email
password=your-password
```

---

#### Running Tests

##### Execute Complete Suite

```bash
mvn clean test
```

##### Execute Specific Cucumber Tag

Update the tag inside:

```java
TestRunner.java
```

Example:

```java
tags = "@Regression"
```

Then run:

```bash
mvn test
```

---

#### JSON Test Data

Product test data is maintained in:

```text
src/test/resources/testdata/products.json
```

Sample:

```json
[
  {
    "name": "Shirt",
    "productName": "Shirt",
    "productCategory": "Dress",
    "productSubCategory": "Shirt",
    "productPrice": "500",
    "productDescription": "White Shirt",
    "productFor": "Women"
  }
]
```

---

#### Logging

Request and Response logs are automatically generated under:

```text
logs/
```

Example:

```text
logs/log_yyyymmdd_HHmmss.txt
```

---

#### Jenkins Integration

This framework includes a Jenkins Pipeline (`Jenkinsfile`) for CI execution.

Pipeline stages:

1. Checkout Source Code
2. Build Project
3. Execute Tests
4. Publish Cucumber Report

Run using:

```bash
mvn clean test
```

inside Jenkins Pipeline.

---

#### Design Patterns & Framework Concepts Used

- Page Object Inspired API Client Layer
- Builder Pattern
- POJO Serialization / Deserialization
- Enum Based API Resource Management
- Hook Based Authentication Management
- Data Driven Testing
- Reusable Utility Classes

---

#### Author

**Deepasree S**

QA Automation Engineer

Skills:

- Selenium Java
- Playwright
- Rest Assured
- API Testing
- TestNG
- Cucumber BDD
- Jenkins
- Git & GitHub
