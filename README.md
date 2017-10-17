# Babyfoot backend

This project is a part of a bigger one called Fun At Work (see below for more detail).
This part contains the backend rewrite in Kotlin (in place of php). 

## Overview

Project is based on [http4k library](https://www.http4k.org/) to handle web service endpoints.
Data is persisted with [ORMLite library](http://ormlite.com/) which allow to use Kotlin `data class` and doesn't generate any obscure code. 

### Architectures

Project is composed of 3 types of actor. 

 * Action: handle the request and format the response (generally, on action by routes group)
 * Resource: manage database operation (generally, one resource by table)
 * Filter: apply pre/post processing on request/response (currently used to check user authentication on specifics API)
 
Data is split in 3 layers:
 
 * Entry: `data class` which represent the input request
 * Model: `data class` which defined data to exchange them between request to database and vis et versa
 * Entity: `data class` which defined ORM entities for ORMLite

### Data structure

```
+------------------+
|                  |
|   Organization   |
|                  |
+--------+---------+
       1 |
         |
         | *
+--------+---------+        +------------------+
|                  |        |                  |
|       User       +--------+      Player      |
|                  |        |                  |
+------------------+        +------------------+
                                   4 ||
                                     ||
                                     || 0..*
                            +------------------+
                            |                  |
                            |   BabyfootGame   |
                            |                  |
                            +------------------+
                                   1 ||
                                     ||
                                     || 0..*
                            +------------------+
                            |                  |
                            |   BabyfootGoal   |
                            |                  |
                            +------------------+
```

## APIs

FunAtWork backend provides API to handle player by organization. Goal is to provide interface for each organization to manage its players.

### General rules

All request are defined with method (POST, GET,...), input data (JSON format or getter params), output data (JSON) and access restriction (API available for only authenticated user, admin, all).
If request successful, HTTP response code will be 2XX with output data (by default 200 for access, 201 for creation).
If request failed, some reasons can happens, main reasons:
 * Internal error: HTTP code 500 with detail in HTTP message
 * Access denied: HTTP code 401
 * Missing mandatory parameters: HTTP Code 400 with missing parameters in HTTP message
 
By default, the HTTP code are used to indicate the status with a associated message. Data are not set in an envelop and prefer used header to indicate extra information (like paging)

### Organization APIs

#### GET `/api/v1/organizations`

Endpoint to get or list organizations

 * Access limitation: No limit
 * Input data: organization id (optional) to select only one organization (example: `/api/v1/organizations/1`)
 * Output data:
```json
{
  "id": 1, // unique id
  "name": "Organization name",
  "logo": "Full URL path to logo"
}
```
Note: If no organization id set from request, the data will be set in an array

#### POST `/api/v1/organizations`

Endpoint to create new organization

 * Access limitation: Root administrator
 * Input data: 
```json
{
  "name": "Organization name", // mandatory
  "logo": "Full URL path to logo" // mandatory
}
```
 * Output data: created organization with unique ID
```json
{
  "id": 1, // unique id
  "name": "Organization name",
  "logo": "Full URL path to logo"
}
```

### Player APIs

WIP

## Build/Installation

TBD

## Contribution