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

/**
 * The Class DirectorDao.
 */
@XmlRootElement
public class DirectorDao {
	
	/** The Constant UNIT. */
	private static final String UNIT = "JPAWeb";
	
	/** The factory. */
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	/**
	 * Creates the director.
	 *
	 * @param director the director
	 * @return the int
	 */
	public int createDirector(Director director) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(director);
		em.flush();
		em.getTransaction().commit();
		
		em.close();
		
		return director.getId();
	}
	
	/**
	 * Find all directors.
	 *
	 * @return the list
	 */
	public List<Director> findAllDirectors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select d from Director d");
		List<Director> directors = (List<Director>)query.getResultList();
		em.getTransaction().commit();
		em.close();		
		return directors;
		
	}
	
	
	/**
	 * Find director by id.
	 *
	 * @param id the id
	 * @return the director
	 */
	public Director findDirectorById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Director director = em.find(Director.class, id);
		em.getTransaction().commit();
		em.close();
		return director;
	}

	/**
	 * Find director by name.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @return the director
	 */
	public Director findDirectorByName(String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery
	("select d from Director d where d.firstName=:firstName and d.lastName=:lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		Director director = (Director) query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return director;
	}
	
	
	/**
	 * Delete director.
	 *
	 * @param id the id
	 */
	public void deleteDirector(int id) {
		EntityManager em =
	factory.createEntityManager();
		em.getTransaction().begin();
		Director director = em.find(Director.class, id);
		em.remove(director);
		em.getTransaction().commit();
	}
	
	/**
	 * Delete alldirectors.
	 */
	public void deleteAlldirectors() {
		EntityManager em =
	factory.createEntityManager();
		em.getTransaction().begin();
		int query = em.createQuery("Delete from Director").executeUpdate();
		em.getTransaction().commit();
	}
	
	/**
	 * Find first director by id.
	 *
	 * @return the list
	 */
	public List<Director> findFirstDirectorById() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from Director a");
		 query.setMaxResults(1);
		 List<Director> director = (List<Director>)query.setMaxResults(1).getResultList();
		em.getTransaction().commit();
		em.close();
		
		return director;
	}

	
	/**
	 * Modify director by name.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param newFirstName the new first name
	 * @param newlastName the newlast name
	 */
	public void ModifyDirectorByName(String firstName, String lastName, String newFirstName, String newlastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery
				("select a from Director a where a.firstName=:firstName and a.lastName=:lastName");
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					Director director = (Director) query.getSingleResult();
					director.setFirstName(newFirstName);
					director.setLastName(newlastName);
		em.getTransaction().commit();
	}
	
	
	/**
	 * Delete director by name.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public void deleteDirectorByName(String firstName, String lastName) {
		EntityManager em =
	factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery
				("select a from Director a where a.firstName=:firstName and a.lastName=:lastName");
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					Director director = (Director) query.getSingleResult();
		em.remove(director);
		em.getTransaction().commit();
	}

	
	
	/**
	 * Test.
	 */
	public void test(){
		DirectorDao dao = new DirectorDao();
		
		dao.deleteAlldirectors();
		
		Director director = new Director("Jimmy", "Camaron");
		dao.createDirector(director);
		Director director1 = new Director("Steven", "Spielberg");
		dao.createDirector(director1);
		Director director2 = new Director("Ron", "Howard");
		dao.createDirector(director2);
		
		List<Director> firstDirector = new ArrayList<>();
		firstDirector = dao.findFirstDirectorById();
		for(Director acto : firstDirector){
			System.out.println(acto.getFirstName()+" "+acto.getLastName());			
		}
		
		
		List<Director> list = new ArrayList<>();
		list = dao.findAllDirectors();
		for(Director a : list){
			
			System.out.println(a.getFirstName()+" "+a.getLastName());
		}
		
		
	dao.ModifyDirectorByName("Jimmy", "Camaron", "James", "Cameron");	
		
		dao.deleteDirectorByName("Ron", "Howard");
		
		List<Director> list1 = new ArrayList<>();
		list = dao.findAllDirectors();
		for(Director a : list){
			
			System.out.println(a.getFirstName()+" "+a.getLastName());
		}
	}


	
/**
 * The main method.
 *
 * @param args the arguments
 */
public static void main(String[] args) {
//	DirectorDao dao = new DirectorDao();
//	dao.test();
//	
//		
//	}

	
	
}
}
