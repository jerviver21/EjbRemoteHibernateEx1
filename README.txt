==============================================================================
Example EJB Remote

http://www.manning.com/bauer3/

==============================================================================


GETTING STARTED
------------------------------------------------------------------------------

- Install JDK 7.

- Install Maven 3.x.

- Create a Postgres Database 


RUNNING EXAMPLE APPS
------------------------------------------------------------------------------

- Install Wildfly 8.2.0.Final

- Run the application server with $WILDFLY/bin/standalone.sh in the background

- Run the "Stateless Client/Server" example app:

    mvn -P server-eh1 clean install
    mvn -P server-eh1 clean package wildfly:deploy
    mvn -P client-eh1 clean test
    
    if an error is present for data:  run the script model-eh1/src/main/resources/testdata.sql
    
    mvn -P server-eh1 clean package wildfly:undeploy
    
    you could run the main class of the client from the IDE if you will


