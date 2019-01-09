package edu.neu.cs5200.orm.jpa.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlRootElement;

import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.entities.Director;
import edu.neu.cs5200.orm.jpa.entities.Movie;

/**
 * The Class MovieDao.
 */
@XmlRootElement
public class MovieDao {

	/** The Constant UNIT. */
	private static final String UNIT = "JPAWeb";
	
	/** The factory. */
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);
	
	/**
	 * Adds the actor to movie.
	 *
	 * @param aId the a id
	 * @param mId the m id
	 */
	public void addActorToMovie(int aId, int mId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Actor actor = em.find(Actor.class, aId);
		Movie movie = em.find(Movie.class, mId);
		movie.getActors().add(actor);
		actor.getMoviesActed().add(movie);
		em.persist(movie);
		em.persist(actor);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Adds the director to movie.
	 *
	 * @param aId the a id
	 * @param mId the m id
	 */
	public void addDirectorToMovie(int aId, int mId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Director director = em.find(Director.class, aId);
		Movie movie = em.find(Movie.class, mId);
		movie.getDirectors().add(director);
		director.getMoviesDirected().add(movie);
		em.persist(movie);
		em.persist(director);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Delete all movies.
	 */
	public void deleteAllMovies() {
		EntityManager em =
	factory.createEntityManager();
		em.getTransaction().begin();
		int query = em.createQuery("Delete from Movie").executeUpdate();
		em.getTransaction().commit();
	}
	
	/**
	 * Creates the movie.
	 *
	 * @param movie the movie
	 * @return the int
	 */
	public int createMovie(Movie movie) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(movie);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		
		return movie.getId();
	}
	
	/**
	 * Find movie by id.
	 *
	 * @param id the id
	 * @return the movie
	 */
	public Movie findMovieById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, id);
		
		em.getTransaction().commit();
		em.close();
		
		return movie;
	}
	
	/**
	 * Delete movie.
	 *
	 * @param id the id
	 */
	public void deleteMovie(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, id);
		em.remove(movie);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Find all movies.
	 *
	 * @return the list
	 */
	public List<Movie> findAllMovies() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from Movie m");
		List<Movie> movies = (List<Movie>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return movies;
	}

	
	/**
	 * Find movie by title.
	 *
	 * @param title the title
	 * @return the movie
	 */
	public Movie findMovieByTitle(String title) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from Movie m where m.title=:title");
		query.setParameter("title", title);
		Movie movie = (Movie)query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return movie;
	}
	
	/**
	 * Rename movie.
	 *
	 * @param id the id
	 * @param newTitle the new title
	 */
	public void renameMovie(int id, String newTitle) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Movie movie = em.find(Movie.class, id);
		movie.setTitle(newTitle);
		em.getTransaction().commit();
	}

	


	/**
	 * Test.
	 */
	public void test(){
		MovieDao dao = new MovieDao();
		
		dao.deleteAllMovies();

		
		Movie movie1 = new Movie();
		movie1.setTitle("Blade Runner");
		int mid = dao.createMovie(movie1);
		
		ActorsDao adao = new ActorsDao();
		Actor actor = new Actor("Harrison","Ford");
		int aid = adao.createActor(actor);
		
		dao.addActorToMovie(aid, mid);
		
		
		Actor actor1 = new Actor("Rutger","Hauer");
		int aid1 = adao.createActor(actor1);

		dao.addActorToMovie(aid1, mid);
		
		DirectorDao ddao = new DirectorDao();
		Director director = new Director("Ridley","Scott");
		int did = ddao.createDirector(director);
		
		dao.addDirectorToMovie(did, mid);
		
		
		Movie movie2 = new Movie();
		movie2.setTitle("Raiders of The Lost Ark");
		int mid1 = dao.createMovie(movie2);

		
		dao.addActorToMovie(aid, mid1);
		
		
		Actor actor2 = new Actor("Karen","Allen");
		int aid2 = adao.createActor(actor2);

		dao.addActorToMovie(aid2, mid1);
		
		
		Director dir = ddao.findDirectorByName("Steven", "Spielberg");
		int did1 = dir.getId();
		
		dao.addDirectorToMovie(did1, mid1);
		
		
		Movie movie3 = new Movie();
		movie3.setTitle("Close Encounters of the Third Kind");
		int mid3 = dao.createMovie(movie3);
		
		Actor actor3 = new Actor("Richard","Dreyfus");
		int aid4 = adao.createActor(actor3);
		
		dao.addActorToMovie(aid4, mid3);
		
		
		Actor actor4 = new Actor("Melinda","Dillon");
		int aid5 = adao.createActor(actor4);

		dao.addActorToMovie(aid5, mid3);
				
		dao.addDirectorToMovie(did1, mid3);
		

		
		List<Movie> movies = dao.findAllMovies();
		List <Actor> actorList = new ArrayList<>();
		List <Director> dList = new ArrayList<>();
		for(Movie movie: movies) {
			System.out.println(movie.getTitle());
			actorList = movie.getActors();
			dList = movie.getDirectors();
			for(Actor actorele : actorList){
				System.out.println(actorele.getFirstName()+" "+actorele.getLastName());
			}
			for(Director dele : dList){
				System.out.println(dele.getFirstName()+" "+dele.getLastName());
			}
			actorList.clear();
			dList.clear();
		}
		
		Actor act = adao.findActorByName("Harrison", "Ford");
		System.out.println(act.getFirstName()+" "+act.getLastName());
		List<Movie> l = new ArrayList<>();
		l = act.getMoviesActed();
		for(Movie m : l){
			System.out.println(m.getTitle());

			
		}
		
		Director d = ddao.findDirectorByName("Steven", "Spielberg");
		System.out.println(d.getFirstName()+" "+d.getLastName());
		List<Movie> o = new ArrayList<>();
		o = d.getMoviesDirected();
		for(Movie m : o){
			System.out.println(m.getTitle());

			
		}
				
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
//		MovieDao dao = new MovieDao();
//
//		dao.test();

	}

	
}
