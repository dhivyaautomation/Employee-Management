Project - Employee Management App
DB - H2 In-memory DB

Welcome! 
Employee Management App is a Springboot Rest API project developed in JAVA. This project uses the H2 database and contains tables :
>> Employee - Attributes(Id, name, department_id)
>> Department - Attributes (Id,name)
>> Employee_Skills - Attributes (employee_id,skill)

>> To access the in-memory H2 DB: http://localhost:8080/h2-console

>> NOTE : Postman Collection to the various end-points built are attached.

>> Functionality:
>> Create API endpoints for 
   i.   GET  - Using this I should be able to get candidate details (List all Employees, Find Employee by ID, Find employee by department)
     ii.  PUT – Update any details about candidate (update skillset, tag employee to a department)
     iii. DELETE – Delete a candidate
     iv.  POST – Create a candidate

