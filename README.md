[![CodeFactor](https://www.codefactor.io/repository/github/pavelslabikov/wallet-api/badge)](https://www.codefactor.io/repository/github/pavelslabikov/wallet-api) &emsp; [![CircleCI](https://circleci.com/gh/pavelslabikov/wallet-api.svg?style=shield)](<LINK>)
# Wallet-API
Simple REST-based application which can be used as API for bank transactions management.
# Launch
In order to make application work properly you have to set appropriate PostgreSQL database credentials in `.env` file:
```
POSTGRES_USER=your_username
POSTGRES_DB=accounts
POSTGRES_PASSWORD=your_password
POSTGRES_HOST=db
```
### Note:
- Migration to another SQL database is not supported yet.  
- By default, application uses predefined (`src/main/resources/db/scripts/init.sql`) SQL initialization script.
It's not recommended changing its content.
- All Dockerfiles are located in `docker` directory.
### Starting application: 
```
$ docker-compose up -d --build --no-start .
$ docker-compose start
```
# Resources
All requests must have header `Accept` with value `*/*` or `application/json`. 
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
###`/v1/accounts/{number}`  
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
- **Refactoring** and code reorganization *(in progress)*.
- Adding more **test classes**.
- Logging.
- **OAuth 2.0** support.
