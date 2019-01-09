package edu.neu.cs5200.orm.jpa.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlRootElement;

import edu.neu.cs5200.orm.jpa.entities.Actor;

/**
 * The Class ActorsDao.
 */
@XmlRootElement
public class ActorsDao {
	
	/** The Constant UNIT. */
	private static final String UNIT = "JPAWeb";
	
	/** The factory. */
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	/**
	 * Creates the actor.
	 *
	 * @param actor the actor
	 * @return the int
	 */
	public int createActor(Actor actor) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(actor);
		em.flush();
		em.getTransaction().commit();
		
		em.close();
		
		return actor.getId();
	}
	
	/**
	 * Find all actors.
	 *
	 * @return the list
	 */
	public List<Actor> findAllActors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select a from Actor a");
		List<Actor> actors = (List<Actor>)query.getResultList();
		em.getTransaction().commit();
		em.close();		
		return actors;
		
	}
	
	
	/**
	 * Find actor by id.
	 *
	 * @param id the id
	 * @return the actor
	 */
	public Actor findActorById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Actor actor = em.find(Actor.class, id);
		em.getTransaction().commit();
		em.close();
		return actor;
	}
	
	
	/**
	 * Find first actor by id.
	 *
	 * @return the list
	 */
	public List<Actor> findFirstActorById() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from Actor a");
		 query.setMaxResults(1);
		 List<Actor> actors = (List<Actor>)query.setMaxResults(1).getResultList();
		em.getTransaction().commit();
		em.close();
		
		return actors;
	}

	/**
	 * Find actor by name.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @return the actor
	 */
	public Actor findActorByName(String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery
	("select a from Actor a where a.firstName=:firstName and a.lastName=:lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		Actor actor = (Actor) query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return actor;
	}
	
	
	/**
	 * Modify actor by name.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param newFirstName the new first name
	 * @param newlastName the newlast name
	 */
	public void ModifyActorByName(String firstName, String lastName, String newFirstName, String newlastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery
				("select a from Actor a where a.firstName=:firstName and a.lastName=:lastName");
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					Actor actor = (Actor) query.getSingleResult();
		actor.setFirstName(newFirstName);
		actor.setLastName(newlastName);
		em.getTransaction().commit();
	}

	/**
	 * Delete all actors.
	 */
	public void deleteAllActors() {
		EntityManager em =
	factory.createEntityManager();
		em.getTransaction().begin();
		int query = em.createQuery("Delete from Actor").executeUpdate();
		em.getTransaction().commit();
	}
	
	
	
	/**
	 * Delete actor.
	 *
	 * @param id the id
	 */
	public void deleteActor(int id) {
		EntityManager em =
	factory.createEntityManager();
		em.getTransaction().begin();
		Actor actor = em.find(Actor.class, id);
		em.remove(actor);
		em.getTransaction().commit();
	}
	
	/**
	 * Delete actor by name.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public void deleteActorByName(String firstName, String lastName) {
		EntityManager em =
	factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery
				("select a from Actor a where a.firstName=:firstName and a.lastName=:lastName");
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					Actor actor = (Actor) query.getSingleResult();
		em.remove(actor);
		em.getTransaction().commit();
	}
	
	/**
	 * Test.
	 */
	public void test(){
		ActorsDao dao = new ActorsDao();
		
		dao.deleteAllActors();
		
		Actor actor = new Actor("Sigorney", "Weaver");
		dao.createActor(actor);
		Actor actor1 = new Actor("Dan", "Creg");
		dao.createActor(actor1);
		Actor actor2 = new Actor("Jim", "Carrey");
		dao.createActor(actor2);
		
		List<Actor> firstactor = new ArrayList<>();
		firstactor = dao.findFirstActorById();
		for(Actor acto : firstactor){
			System.out.println(acto.getFirstName()+" "+acto.getLastName());			
		}
	
		
		List<Actor> list = new ArrayList<>();
		list = dao.findAllActors();
		for(Actor a : list){
			
			System.out.println(a.getFirstName()+" "+a.getLastName());
		}
		
		dao.findActorByName("Dan", "Creg");
		
	dao.ModifyActorByName("Dan", "Creg", "Daniel", "Craig");	
		
		dao.deleteActorByName("Jim", "Carrey");
		
		List<Actor> list1 = new ArrayList<>();
		list1 = dao.findAllActors();
		for(Actor a : list1){
			
			System.out.println(a.getFirstName()+" "+a.getLastName());
		}
	}


	
/**
 * The main method.
 *
 * @param args the arguments
 */
public static void main(String[] args) {
//	ActorsDao dao = new ActorsDao();
//	dao.test();
//	
		
	}

	

}
