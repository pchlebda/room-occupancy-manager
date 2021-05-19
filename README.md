# Room Occupancy Manager
The application is java spring-boot based assignment. The goal is to expose REST API for hotels that allows to calculate room usage and total value.

## Requirements
* JDK 11
* maven 3.6.0

## How to build application
`mvn clean package -U`

## How to run application
`mvn spring-boot:run`
The application starts up on port `8080`.

## How to run tests
`mvn test`

## Rest API

* POST `{host}/api/v1/room`
* Request body 
 ```
 {
  "freePremiumRooms":7,
  "freeEconomyRooms" :1
 }
  ```
* Response http `200`
```
{
    "premiumRoomUsage": 7,
    "economyRoomUsage": 1,
    "premiumRoomTotalValue": 1153.99,
    "economyRoomTotalValue": 45,
    "currency": "EUR"
}
```

* CURL usage example 

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "freePremiumRooms":7, "freeEconomyRooms" :1 }' \
  http://localhost:8080/api/v1/room
```