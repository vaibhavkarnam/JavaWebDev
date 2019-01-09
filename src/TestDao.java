package edu.neu.cs5200.orm.jpa.daos;

/**
 * The Class TestDao.
 */
public class TestDao {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		
		ActorsDao adao = new ActorsDao();
		adao.test();
		DirectorDao ddao = new DirectorDao();
		ddao.test();
		MovieDao mdao = new MovieDao();
		mdao.test();
		MovieLibraryDao libdao = new MovieLibraryDao();
		libdao.test();
		
	}

}
