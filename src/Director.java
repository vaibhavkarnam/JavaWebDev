package edu.neu.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Director.
 */
@Entity

public class Director extends Person implements Serializable {


	
	/** The oscar wins. */
	private int oscarWins;
	
	/** The movies directed. */
	@ManyToMany(mappedBy="directors", cascade=CascadeType.ALL)
	private List<Movie> moviesDirected = new ArrayList();

	/**
	 * Instantiates a new director.
	 *
	 * @param oscarWins the oscar wins
	 * @param moviesDirected the movies directed
	 */
	public Director(int oscarWins, List<Movie> moviesDirected) {
		super();
		this.oscarWins = oscarWins;
		this.moviesDirected = moviesDirected;
	}
	
	/**
	 * Instantiates a new director.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Director(String firstName, String lastName) {
		super(firstName, lastName);
	}
	

	/**
	 * Gets the oscar wins.
	 *
	 * @return the oscar wins
	 */
	public int getOscarWins() {
		return oscarWins;
	}

	/**
	 * Sets the oscar wins.
	 *
	 * @param oscarWins the new oscar wins
	 */
	public void setOscarWins(int oscarWins) {
		this.oscarWins = oscarWins;
	}

	/**
	 * Gets the movies directed.
	 *
	 * @return the movies directed
	 */
	public List<Movie> getMoviesDirected() {
		return moviesDirected;
	}

	/**
	 * Sets the movies directed.
	 *
	 * @param moviesDirected the new movies directed
	 */
	public void setMoviesDirected(List<Movie> moviesDirected) {
		this.moviesDirected = moviesDirected;
		for(Movie movie: moviesDirected) {
			movie.getDirectors().add(this);
		}

	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new director.
	 */
	public Director() {
		super();
	}


}
