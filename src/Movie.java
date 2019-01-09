package edu.neu.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity implementation class for Entity: Movie.
 */
@Entity
@XmlRootElement
public class Movie implements Serializable {

	   
	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/** The title. */
	private String title;
	
	/** The library. */
	@ManyToOne
	private MovieLibrary library;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** The actors. */
	@ManyToMany
	@JoinTable(name="MOVIE2ACTOR")
	private List<Actor> actors = null;
	
	/** The directors. */
	@ManyToMany
	@JoinTable(name="MOVIE2DIRECTOR")
	private List<Director> directors = null;
	
	
	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * Instantiates a new movie.
	 *
	 * @param title the title
	 * @param library the library
	 * @param actors the actors
	 * @param directors the directors
	 */
	public Movie(String title, MovieLibrary library, List<Actor> actors, List<Director> directors) {
		super();
		this.title = title;
		this.library = library;
		this.actors = actors;
		this.directors = directors;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the library.
	 *
	 * @return the library
	 */
	public MovieLibrary getLibrary() {
		return library;
	}
	
	/**
	 * Sets the library.
	 *
	 * @param library the new library
	 */
	@XmlTransient
	public void setLibrary(MovieLibrary library) {
		this.library = library;
		
	}
	
	/**
	 * Gets the actors.
	 *
	 * @return the actors
	 */
	public List<Actor> getActors() {
		return actors;
	}
	
	/**
	 * Sets the actors.
	 *
	 * @param actors the new actors
	 */
	@XmlTransient
	public void setActors(List<Actor> actors) {
		this.actors = actors;
		for(Actor actor: actors) {
			actor.getMoviesActed().add(this);
		}

	}
	
	/**
	 * Gets the directors.
	 *
	 * @return the directors
	 */
	public List<Director> getDirectors() {
		return directors;
	}
	
	/**
	 * Sets the directors.
	 *
	 * @param directors the new directors
	 */
	@XmlTransient
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
		for(Director director: directors) {
			director.getMoviesDirected().add(this);
		}
	}
	
	/**
	 * Instantiates a new movie.
	 */
	public Movie() {
		super();
	}   
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}   
	
   
}
