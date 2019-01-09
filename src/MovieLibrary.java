package edu.neu.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MovieLibrary.
 */
@Entity

public class MovieLibrary implements Serializable {

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**
	 * Instantiates a new movie library.
	 *
	 * @param name the name
	 * @param movies the movies
	 */
	public MovieLibrary(String name, List<Movie> movies) {
		super();
		this.name = name;
		this.movies = movies;
	}

	/**
	 * Instantiates a new movie library.
	 *
	 * @param id the id
	 * @param name the name
	 * @param movies the movies
	 */
	public MovieLibrary(int id, String name, List<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
		this.movies = movies;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the movies.
	 *
	 * @return the movies
	 */
	public List<Movie> getMovies() {
		return movies;
	}

	/**
	 * Sets the movies.
	 *
	 * @param movies the new movies
	 */
	public void setMovies(List<Movie> movies) {
		this.movies = movies;	
	}

	/** The name. */
	private String name;
	
	/** The movies. */
	@OneToMany(mappedBy="library", cascade=CascadeType.ALL)
	private List<Movie> movies = new ArrayList();
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new movie library.
	 */
	public MovieLibrary() {
		super();
	}
   
}
