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
import edu.neu.cs5200.orm.jpa.entities.MovieLibrary;

/**
 * The Class MovieLibraryDao.
 */
@XmlRootElement
public class MovieLibraryDao {
	
	/** The Constant UNIT. */
	private static final String UNIT = "JPAWeb";
	
	/** The factory. */
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	
	/**
	 * Creates the movie library.
	 *
	 * @param library the library
	 * @return the int
	 */
	public int createMovieLibrary(MovieLibrary library) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(library);
		em.flush();

		em.getTransaction().commit();
		em.close();
		
		return library.getId();

	}
	
	
	/**
	 * Adds the movie to library.
	 *
	 * @param mId the m id
	 * @param lId the l id
	 */
	public void addMovieToLibrary(int mId, int lId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, mId);
		MovieLibrary library = em.find(MovieLibrary.class, lId);
		library.getMovies().add(movie);
		movie.setLibrary(library);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Delete all movie libraries.
	 */
	public void deleteAllMovieLibraries() {
		EntityManager em =
	factory.createEntityManager();
		em.getTransaction().begin();
		int query = em.createQuery("Delete from MovieLibrary").executeUpdate();
		em.getTransaction().commit();
	}
	
	
	/**
	 * Find movie library by id.
	 *
	 * @param id the id
	 * @return the movie library
	 */
	public MovieLibrary findMovieLibraryById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		MovieLibrary movieLibrary = em.find(MovieLibrary.class, id);
		
		em.getTransaction().commit();
		em.close();
		
		return movieLibrary;
	}
	
	/**
	 * Delete movie library.
	 *
	 * @param id the id
	 */
	public void deleteMovieLibrary(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		MovieLibrary movieLibrary = em.find(MovieLibrary.class, id);
		em.remove(movieLibrary);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Find all movie library.
	 *
	 * @return the list
	 */
	public List<MovieLibrary> findAllMovieLibrary() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from MovieLibrary m");
		List<MovieLibrary> movieLibrary = (List<MovieLibrary>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return movieLibrary;
	}
	
	
	/**
	 * Find library by title.
	 *
	 * @param name the name
	 * @return the movie library
	 */
	public MovieLibrary findLibraryByTitle(String name) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from MovieLibrary m where m.name=:name");
		query.setParameter("name", name);
		MovieLibrary library = (MovieLibrary)query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return library;
	}
	
	/**
	 * Test.
	 */
	public void test(){
		MovieDao dao = new MovieDao();
		
		Movie movie1 = new Movie();
		movie1.setTitle("Star Wars A New Hope");
		int mid = dao.createMovie(movie1);
		
		ActorsDao adao = new ActorsDao();
		Actor actor = new Actor("Mark","Hamill");
		int aid = adao.createActor(actor);
		
		dao.addActorToMovie(aid, mid);
		
		
		Actor actor1 = new Actor("Carrie","Fisher");
		int aid1 = adao.createActor(actor1);

		dao.addActorToMovie(aid1, mid);
		
		DirectorDao ddao = new DirectorDao();
		Director director = new Director("George","Lucas");
		int did = ddao.createDirector(director);
		
		dao.addDirectorToMovie(did, mid);
		
		
		Movie movie2 = new Movie();
		movie2.setTitle("The Revanant");
		int mid1 = dao.createMovie(movie2);
		
		
		Actor actor2 = new Actor("Leonardo","DiCaprio");
		int aid2 = adao.createActor(actor2);

		dao.addActorToMovie(aid2, mid1);
		
		
		
		Actor a = new Actor("Tom","Hardy");
		int a1 = adao.createActor(a);

		dao.addActorToMovie(a1, mid1);
		
		Director director1 = new Director("Alejandro","Inarritu");
		int did1 = ddao.createDirector(director1);
		
		dao.addDirectorToMovie(did1, mid1);
		
		MovieLibrary lib = new MovieLibrary();
		lib.setName("Favorite Movies");
		MovieLibraryDao ldao = new MovieLibraryDao();
		
		ldao.deleteAllMovieLibraries();
		
		int libId = ldao.createMovieLibrary(lib);
		
		ldao.addMovieToLibrary(mid, libId);
		ldao.addMovieToLibrary(mid1, libId);
		
		MovieLibrary libprint = ldao.findLibraryByTitle("Favorite Movies");
		System.out.println(libprint.getName());
		
		
		List<Movie> movies = libprint.getMovies();
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
		
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
//		MovieLibraryDao dao = new MovieLibraryDao();
//
//		dao.test();
//
//	}

}
}
