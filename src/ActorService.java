package edu.neu.cs5200.web.services.jaxrs;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.daos.ActorsDao;
import edu.neu.cs5200.orm.jpa.entities.Movie;

/**
 * The Class ActorService.
 */
@Path("actor")
public class ActorService {
	
	/**
	 * Gets the actors.
	 *
	 * @return the actors
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<Actor> getActors() 
	{ 
		ActorsDao ad = new ActorsDao();
		List<Actor> a = ad.findAllActors();
		return a;
		
	}
	
	/**
	 * Find actor for id.
	 *
	 * @param aid the aid
	 * @return the actor
	 */
	@Path("{aid}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Actor findActorForId(@PathParam("aid") int aid) 
{
ActorsDao ad = new ActorsDao();
Actor a = ad.findActorById(aid);
if(a.getId() == aid) 

{
	
	return a; 
	}
return null;
}

/**
 * Find movies for actor id.
 *
 * @param aid the aid
 * @return the list
 */
@Path("{aid}/movie")
@GET
@Produces(MediaType.APPLICATION_JSON)
public List<Movie> findMoviesForActorId(@PathParam("aid") int aid)
	{
	ActorsDao ad = new ActorsDao();
	Actor a = ad.findActorById(aid);
	if(a.getId() == aid) 
	{ 
		return a.getMoviesActed(); 
		}
	return null;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		
	}
}
