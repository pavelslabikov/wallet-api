[![CodeFactor](https://www.codefactor.io/repository/github/pavelslabikov/wallet-api/badge)](https://www.codefactor.io/repository/github/pavelslabikov/wallet-api) &emsp; [![CircleCI](https://circleci.com/gh/pavelslabikov/wallet-api.svg?style=shield)](https://circleci.com/gh/pavelslabikov/wallet-api)
# Wallet-API
Simple REST-based application which can be used as an API for bank transactions management.
# Launch
In order to make application work properly you have to set appropriate PostgreSQL database credentials in `.env` file:
```
POSTGRES_USER=your_username
POSTGRES_DB=db_name
POSTGRES_PASSWORD=your_password
POSTGRES_HOST=db
```
### Note:
- `POSTGRES_HOST` variable should reference database service name from `docker-compose.yml`.
- Migration to another SQL database service is not supported yet.  
- By default, application uses predefined SQL initialization script (`src/main/resources/db/scripts/init.sql`).
It's not recommended to change its content.
- All Dockerfiles are located in `docker` directory.
### Starting application: 
```
$ docker-compose up -d
```
# Resources
The web server is running on the `localhost:8080` host and port. All requests must have header `Accept` with value `*/*` or `application/json`. 
## Account
***JSON Representation:***  
```
{
    "number": 1,
    "balance": 0.0
}
```
- **number** - unique account identifier *(required, non-negative)*
- **balance** - current account balance *(required, can be negative)*  
### `/v1/accounts/{number}`  
***Supported HTTP methods:*** `GET`, `DELETE`  
### `/v1/accounts/`
***Supported HTTP methods:*** `GET`, `POST`  
## Transaction  
***JSON Representation:*** 
```
{
    "type": "income",
    "amount": 1500.0,
    "date": "2016-03-28T19:00:00.000+01:00",
    "description": "Some description"
}
```
- **type** - enum with possible values `INCOME` / `OUTCOME` *(required)*
- **amount** - transaction amount *(required)*
- **date** - transaction date in ISO format *(required)*
- **description** - transaction description *(optional)*
### `/v1/accounts/{number}/transactions`  
***Supported HTTP methods:*** `GET`, `POST` 
# Frameworks & Technologies
- ***Spring Boot + MVC***
- ***Maven Framework***
- ***PostgreSQL***
- ***Docker***
# TODOs
- ***Refactoring and code reorganization (in progress)***
- ***Adding more test classes***
- ***Logging***
- ***OAuth 2.0 support***
