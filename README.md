The creation of the application was done with Java SE 8 and it’s purpose is to exemplify network communication between server and client through the use of RMI, while using JPA (EclipseLink implementation) to store the information into a MySQL database.

In the current example, a user needs to register a username and a password, that get stored into the database and that can then be used to access the main window of the application. Here the user can add products into an inventory and the products also get stored into the database.

For this purpose I have used swing to create a basic interface that would allow clients to register a new username and password and an interface that allows users to log in. Logging in takes the user to a basic main frame that represents the inventory into which they can add products. The products are succinctly defined by use of a name and a price.

In order to run the application I have used the following tools:

- Java IDE: NetBeans;

- Web server package: XAMPP;

- Database design tool: MySQL Workbench.

The architecture of the projects is designed following the MVC design pattern as follows:

The server application:
  
- dao

  > the Data Access Object package holds the class that contains the direct operations with the database; 
  
  > the class receives an EntityManager given as a dependency through the constructor;
  
- entities

  > this package holds the class that describes the objects that are to be mapped in the database;

- server

  > implements a Registry Pattern that can contain multiple implementations for remote interfaces;

- service

  > this package contains the classes that inherit UnicastRemoteObject and implement the WebService interface;
  
  > the service class instantiates an EntityManagerFactory in the constructor and is responsible for implementing the use-cases by overriding the methods of the Web Service methods using working instances of the Dto and Dao objects;

The library application:

- dto

  > the Data Transfer Object package contains serializable versions of the objects that that allows them to be 
    transferred through a flux;
    
  > the classes placed in the ‘entities’ package of the server cannot reach the client because of the annotations that mark 
    the classes in that package, which serve to implement the JPA standard; 
    
  > the client does not use JPA, as he only consumes the services offered by the server; as such, separate classes are needed (Dto classes)
    that represent the object and describe precisely what it transfers;
   
 - ws
 
   > the Web Service package contains the interfaces that extend the Remote interface and have the role of exposing the 
     services offered by the server for the clients to consume;
 
 The client application:
 
 - controller
 
   > this package holds Singleton classes that serves to decouple the service call to the server from the graphical interface;
 
 - gui
 
   > the  package contains the Graphical User Interfaces that clients initialise in order to register a new username and password, perform the login and finally define products and add them to 
     the common inventory;
