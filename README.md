# moj-coding-exercise
Simple Account Service handling RESTful API

## Get all Accounts
HTTP Method : GET
Use URL : http://localhost:8080//rest/accounts/json/

## Add an Account
HTTP Method : POST
Use URL : http://localhost:8080//rest/accounts/json/

With a JSON payload with request format:

{ "firstName":"John", 
  "lastName":"Doe", 
  "accountNumber":"1234" 
}


## Delete an Account
HTTP Method : DELETE
Use URL : http://localhost:8080//rest/accounts/json/{id}
