Create JPA entities that implement the UML diagram for Movie application. All primary keys should be named ID and be AUTO_INCREMENT where applicable. All inheritance relations should use the JOINED inheritance strategy, i.e., each class in the inheritance relation should have its own table with fields matching the class properties that are primitive data types. Tables for base classes declare an additional DTYPE column as described in the class notes and/or slides. Entity classes should be declared in a JPA persistence.xml using a persitence unit named JPAMOVIES

Create JPA DAOs

create DAOs that use the persistence unit JPAMOVIES to implement the basic CRUD operations for each of the entities listed below. All DAOs should implement the following minimum set of operations: create, findById, findAll, update, and delete. Add additional operations as needed to support use cases described below. The names of the methods that implement the operations should be based on the name of the entity they are operating on, e.g., for the Movie entity the methods would be createMovie, findMovieById, etc., for the Actor entity the methods would be createActor, findActorById, etc. Methods should support the use cases described below. Do not use hardcoded IDs anywhere. Instead use the IDs generated when creating records. All operations should be reflected in the database where applicable. Create the DAOs such that they support the following use cases


Create JAX-RS Web Services

create JAX-RS Web Service endpoints that expose the movie database created in the previous steps. Add additional annotations to the datamodel as needed, e.g., you might need to add @XmlRootElement, @XmlAttribute, and other JAXB annotations.

Actor Webservice
Implement ActorService.java that uses the ActorDao to expose the actor data. Use the HTTP endpoints shown below. Grayed rows are optional and will not be graded, but you are strongly encouraged to complete them.
Method
URL
Description
GET
/actor
List of all actors
GET
/actor/{aid}
Actor instance whose primary key is aid
GET
/actor/{aid}/movie
Retrieves array of movies for actor whose primary key is aid
POST
/actor
Create actor
DELETE
/actor/{aid}
Delete actor instance whose primary key is aid
PUT
/actor/{aid}
Update actor instance whose primary key is aid
(15pts.) Movie Webservice
Implement MovieService.java that uses MovieDao to expose the movie data. Use the HTTP endpoints shown below. Grayed rows are optional and will not be graded, but you are strongly encouraged to complete them.
Method
URL
Description
GET
/movie
List of all movies
GET
/movie/{mid}
Movie instance whose primary key is mid
GET
/movie/{mid}/actor
Retrieves array of actors for movie whose primary key is mid
POST
/movie
Create movie
DELETE
/movie/{mid}
Delete movie instance whose primary key is mid
PUT
/movie/{mid}
Update movie instance whose primary key is mid
