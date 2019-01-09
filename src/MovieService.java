package edu.neu.cs5200.web.services.jaxrs;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;



import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.daos.MovieDao;
import edu.neu.cs5200.orm.jpa.entities.Movie;

/**
 * The Class MovieService.
 */
@Path("movie")
public class MovieService {
	
	/**
	 * Gets the movies.
	 *
	 * @return the movies
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<Movie> getMovies() 
	{ 
		MovieDao md = new MovieDao();
		List<Movie> m = md.findAllMovies();
		return m;
	}
	
	/**
	 * Find movie for id.
	 *
	 * @param mid the mid
	 * @return the movie
	 */
	@Path("{mid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Movie findMovieForId(@PathParam("mid") int mid) 
	{
	MovieDao md = new MovieDao();
	Movie m = md.findMovieById(mid);
	if(m.getId() == mid) 
	{ 
		return m; 
		}
	return null;
	}
	
	/**
	 * Find actors for movie id.
	 *
	 * @param mid the mid
	 * @return the list
	 */
	@Path("{mid}/actor")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> findActorsForMovieId(@PathParam("mid") int mid) 
	{
	MovieDao md = new MovieDao();
	Movie m = md.findMovieById(mid);	
	if(m.getId() == mid) 
	{ 
		return m.getActors(); 
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
