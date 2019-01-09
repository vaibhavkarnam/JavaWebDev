package edu.neu.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Actor.
 */
@Entity
@XmlRootElement
public class Actor extends Person implements Serializable {
	
		
	
	/** The movies acted. */
	@ManyToMany(mappedBy="actors", cascade=CascadeType.ALL)
	private List<Movie> moviesActed = new ArrayList();

	
	/**
	 * Instantiates a new actor.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Actor(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	
	/**
	 * Gets the movies acted.
	 *
	 * @return the movies acted
	 */
	public List<Movie> getMoviesActed() {
		return moviesActed;
	}

	/**
	 * Sets the movies acted.
	 *
	 * @param moviesActed the new movies acted
	 */
	public void setMoviesActed(List<Movie> moviesActed) {
		this.moviesActed = moviesActed;
		for(Movie movie: moviesActed) {
			movie.getActors().add(this);
		}
		
	}

	/**
	 * Gets the oscar nominations.
	 *
	 * @return the oscar nominations
	 */
	public int getOscarNominations() {
		return oscarNominations;
	}

	/**
	 * Sets the oscar nominations.
	 *
	 * @param oscarNominations the new oscar nominations
	 */
	public void setOscarNominations(int oscarNominations) {
		this.oscarNominations = oscarNominations;
	}

	/** The oscar nominations. */
	private int oscarNominations;

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new actor.
	 */
	public Actor() {
		super();
	}
   
}
