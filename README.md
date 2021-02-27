
Project
-------
Github API Test

Test Scenarios Covered
----------------------
***Scenario 1:

Search repositories with keyword "cypress" and verify the api resonse and response code
and then QUERY the "java" based cypress repositories and SORT BY most starred and ORDER BY DESC

***Scenario 2:

Search repositories with keyword "cypress" and verify the api resonse and response code
and then QUERY the "java" based cypress repositories and SORT BY most starred and ORDER BY ASC

***Scenario 3:

Search repositories with keyword "pageobject" and verify the api resonse and response code
and then QUERY the "python" based pageobject repositories and SORT BY most starred and ORDER BY DESC

***Scenario 4:

Search repositories with keyword "lambda" and verify the api resonse and response code
and then QUERY the "python"  based lambda repositories and SORT BY most starred and ORDER BY DESC

Author/Contributor
------------------


API Url
---------------
https://api.github.com/

 
Prerequisites
-------------
java version "1.8.0_201"+
Google Chrome Version 87+ // (API Run in google headless in case of UI to API comparision)
Eclipse/IntelliJ with Maven&TestNg Plugins


Tools & Technologies Used 
--------------------------
Tech - selenium with java 
Plugin's - Maven & TestNg
Dependencies - testng v 6.8.5 , selenium-java 3.141.59, poi - 3.10-FINAL, poi-ooxml - 3.10-FINAL , rest-assured - 4.3.2
google guava - 27.0.1-jre , extentreports - 3.0.5


Framework  & Design Pattern
---------------------------
For this project TestNg Framework used in Page Object Model(POM) designed pattern . Page class contains web elements 
and methods to interact with web elements and object of these Page Classes and interact with web elements by calling the methods of these classes

Reporting
---------
Used aventstack Extent reports to genearate test results report (Licenced to TekStrom extent reports
Licence Agreement https://www.extentreports.com/wp-content/uploads/2020/06/EULA.pdf)


Steps to Run the Script 
-----------------------
Step 1 : Make sure system having all Prerequisites
Step 2 : Clone project from Git Repo to local directory
Step 3 : Import project into IDE (Import as Maven Project)
Step 4 : Run As 'githubSanityTest.xml' with TestNg (userdir/GithubAPIProject/TestRunner/githubSanityTest.xml
Step 5 : Wait for test completion
Step 6 : Test Results Reports will be generated under Reports folder in framework (userdir/GithubAPIProject/Reports)