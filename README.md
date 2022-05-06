# personality-test-app
This is only the server side of the personality test project.

This project can be run locally and tested. 
To test the project follow the steps below.

1- create a docker image with postgresql database 
```bash
docker run --name db -p 5432:5432 --network=db -v "$PWD:/var/lib/postgresql/data" -e POSTGRES_PASSWORD=password -d postgres:alpine
```
2- run the database
```bash
docker run -it --rm --network=db postgres:alpine psql -h db -U postgres
```
3- create database
```bash
create database personalitytest;
```
4- run the project on your machine 
5- on terminal, run the below command to insert questions. don't forget to change directory.
```bash
cat /your_directory/src/main/resources/data_insert.sql | docker exec -i db psql -U postgres -d personalitytest
```

# Project Features
This project has 2 main components.
1- User component
2- Question component

# User Component
Via this project, you can save usernames and email addresses of the users in the database. 
There is an email control while saving the user. If the email is already in the database, user will be informed.

# Question Component
When user successfully added in the database, the first question will be returned to the client. 
I designed the application such as that test will continue one question at a time. 
Under every question, except the last one, there should be a Next Question button to continue. 
The application will check if there is an answer provided by the client. This control can be done at the client side as well. However, I wanted to be sure for the sake of the application.
Also, client need to provide the server userId and questionId. 
Under the last question, there should be the Submit Test button to finalize the test.





