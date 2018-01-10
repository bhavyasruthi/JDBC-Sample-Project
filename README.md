# JDBC-Sample-Project
Sample Project to connect to the database and perform Signup, login operations using JDBC.

### Project Workflow :
web.xml -> Welcome.html -> Signup.html/Login.html -> StudentRegistration.java/LoginValidation.java -> LoginSuccesful.jsp/LoginFailed.jsp

### web.xml :
It is deployment descriptor of the project. LoginValidation Servlet and StudentRegistration Servlet are mapped in this file.

### Welcome.html :
This is welcome page of our project with 2 options - Signup / Login.
#### Signup.html :
This is student registration form with fields such as name, rollno, branch, marks, pwd, confirm password. It has javascript validation to make sure user enter valid details in this form. Once submitted, action="/StudentInfo/StudentRegistration will redirect control to StudentRegistration Servlet.
#### Login.html :
This is student login form with fields rollno and pwd. It has javascript validation to make sure user enter valid details in this form. Once submitted, action="/StudentInfo/LoginValidation will redirect control to LoginValidation Servlet.

### StudentRegistration.java :
This is a java class that extends HttpServlet to get request and reponse objects. From the request object we will get all details submitted by the user in Signup form into this servlet. Using JDBC, we will connect to the Oracle database in 5 steps-
- Register the driver class : The forName() method of Class class is used to register the driver class. This method is used to dynamically load the driver class.
- Create the connection object : The getConnection() method of DriverManager class is used to establish connection with the database.
- Create the Statement object : The createStatement() method of Connection interface is used to create statement. The object of statement is responsible to execute queries with the database.
- Execute the query : The executeQuery() method of Statement interface is used to execute queries to the database. This method returns the object of ResultSet that can be used to get all the records of a table.
- Close the connection object - By closing connection object statement and ResultSet will be closed automatically. The close() method of Connection interface is used to close the connection.
To register student details in this project we will use PreparedStatement object by calling method prepareStatement(insert into student values(?,?,?,?,?)). If update is successful, we will redirect user to LoginSuccesful.jsp else to Signup.html.

### LoginValidation.java :
This is a java class that extends HttpServlet to get request and reponse objects. From the request object we will get rollno, pwd submitted by the user in Login form into this servlet. According to rollno, fetch password from database using prepareStatement(). If password matches, we will redirect user to LoginSuccesful.jsp else to LoginFailed.jsp.
