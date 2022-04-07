# Solution

The application was developed using Java 11 with Spring Boot.

## LoadDatabase.java

The class LoadDatabase is responsible for loading the database once the application is up and running. It imports the json files provided by you, transform them into Java
Object and persist them in the database.

## Domain classes

The domains classes are Merchant.java, Order.java and Shopper.java. They are a java representation of the json files.

I decided to alter Order.java adding a new column. The new column is the disbursement value.

e.g:

```
ID | MERCHANT ID | SHOPPER ID | AMOUNT | CREATED AT           | COMPLETED AT        | DISBURSEMENT
1  | 25          | 3351       | 61.74  | 01/01/2017 00:00:00  | 01/07/2017 14:24:01 | 61.15
```

## Job

The job class is DisbursementJob.java. The job will run every monday at 00:00 according to the cron expression above the run() method (@Scheduled(cron = "0 0 0 * * MON"))

## Web

DisbursementController.java exposes two endpoints

1. localhost:8080/api/v1/disbursement/merchant?id=2&page=3 which list all disbursements by merchandId
2. localhost:8080/api/v1/disbursement?page=0 which list all disbursement

All endpoints have pagination and are sorted by order id.

## TDD

The tests are located at src\test\java\com\sequra\disbursements. It covers the funcionality of both endpoints and the disbursemnt calculation method.

## Running the application

Unfortunately I was not able to make it available. I had some issues while packaging the application due to some Maven dependencies.

The plan was to provide a docker image so you could run it on your machine, but I couldn't spend more time trying to figure it out.

If I go to the next step I would be glad to show it up and running on my local machine.

Thanks.
