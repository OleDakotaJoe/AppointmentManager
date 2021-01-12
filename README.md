# Appointment Manager #
#### Purpose
 This application was built as a submission for Task 1 in WGU's C195: Software II course. 
 It has a connection to a MySQL database, as well a GUI front end, written in JavaFX which is used 
 for tracking appointments in a simulated business situation, where contacts within a company and 
 customers of that company meet and discuss business. 
 
 ### Copyright Information.
 Author: Steven Sheaves 
 Email: ssheave@wgu.edu 
 Application Version: 1.0.01 
 Date: 1/12/2021

### IDE and Software Information
IntelliJ IDEA Community Edition Version 2020.2.4
Amazon Corretto JDK 11.0.7_10
JavaFX-SDK-11.0.2 

### To run the program
**Clone** this repo from [GitHub](https://github.com/OleDakotaJoe/AppointmentManager/)
Ensure that a valid JDK version is included as an external library as well as well as a 
valid version of JavaFX is included as an external library. 
**Navigate** to the ```Main``` class.
**Right Click** on ```Main``` and click ```Run Main.main()```

### To interact with the program
When the program launches, you land on and unassuming login page. 
Click login, and you may login with the username: test, and password: test. 
You will then see a popup which will display an appointment if you have one within the next 15 minutes, OR a dialog
that indicates that you have no upcoming appointments, whichever is applicable.
Next, you will be taken to the *dashboard*. 
There are 5 tabs across the top of the dashboard: 
**Dashboard, Appointment, Contact, Customer, Reports and Help**
The tab corresponding to the page that you are currently on, will be 'disabled' and will be grey.
On the dashboard, you will see 6 tables. These tables represent all appointments, Today's appointments, this week's 
appointments, and this month's appointments, IN ADDITION to all customers, and all contacts.
From this page you may choose to add, update or view any of the entries in the tables. To do this
click on the item that you would like to modify, and click the desired option under the table, in which it resides, OR if you 
would like to add an item, simply click add under the appropriate table.  
You will then be redirected to another page in the app with the appropriate form loaded. 

If you prefer, you may click any of the tabs at the top, and view items in the page dedicated to it. 
In the Appointments tab, you may filter the results in the ```TableView``` by clicking 
any of the corresponding radio buttons above it. 
If at any point, you get stuck, simply click onto the "Help" button, and a knight in shining armor will come to your rescue.

## Additional Report as indicated by part A.3.f of the requirements
The additional report provided is a schedule for all customers in the organization. 
This is included so that if any user of the program tries to delete a customer, and they currently have items in their schedule, 
they will be able to look up a list of appointments, so that they may take appropriate measures in 
rectifying that issue before removing that customer from the database. 
## Other Requirements to note
#### Lambda Expressions: 
In the ```AppointmentDataState``` class, lambda expressions are used in the ```setAllAppointments()```method.
In the ```AppointementController``` class, lambda expressions are used in the ```handleAppointmentListChange()``` method.

 