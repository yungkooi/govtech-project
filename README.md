################### INSTRUCTIONS TO RUN ###################

- Start as SpringBootApplication
- Import postman collection included in base directory
- For POST request, refer to csv file attached in sample-csv/

################### OTHER NOTES ###################

Database:
- Application is developed with in-memory database (no additional setup required to start)
- Upon start-up, seed data is supplied via data.sql with 4 valid users
- Database can be viewed on the browser via the URL: http://localhost:8080/govtech/h2-console

HTTP Request:
- GET request is controlled by 5 params (max, sort, min, offset, limit) set in request headers
- All request params are optional, so toggle them accordingly

Sample CSV Files:
- users.csv -> Includes new users, existing user, user with 0 salary, user with negative salary 
- users2.csv -> Includes 2 valid users, user with invalid salary
- users3.csv -> Includes a new user, user with 0 salary, user with negative salary