CREATE TABLE MOVIE2ACTOR (moviesActed_ID INTEGER NOT NULL, actors_ID INTEGER NOT NULL, PRIMARY KEY (moviesActed_ID, actors_ID));
ALTER TABLE MOVIE2ACTOR ADD CONSTRAINT FK_MOVIE2ACTOR_moviesActed_ID FOREIGN KEY (moviesActed_ID) REFERENCES MOVIE (ID);
ALTER TABLE MOVIE2ACTOR ADD CONSTRAINT FK_MOVIE2ACTOR_actors_ID FOREIGN KEY (actors_ID) REFERENCES PERSON (ID);
