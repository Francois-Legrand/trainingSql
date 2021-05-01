package fr.trainningSql.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import connexionBdd.ConnectionUtils;
import fr.trainningSql.model.Acteur;
import fr.trainningSql.model.Film;



public class ActeurService implements IDao<Acteur>{
	
List<Acteur> listeActeur = new ArrayList<Acteur>();

	@Override
	public boolean create(Acteur acteur) {
		return false;
		
	}

	@Override
	public boolean delete(Acteur acteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Acteur acteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Acteur findById(int id) {
		
		try {
			Acteur acteurId = new Acteur();
			
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();
			
			String sql = "SELECT actor.actor_id, actor.first_name, actor.last_name,actor.last_update FROM actor "
					+ "WHERE actor.actor_id ="+id+";";
			
			String sql2 = "SELECT * FROM category \r\n"
					+ "JOIN film_category ON category.category_id = film_category.category_id \r\n"
					+ "JOIN film ON film_category.film_id = film.film_id \r\n"
					+ "JOIN film_actor ON film.film_id = film_actor.film_id\r\n"
					+ "JOIN actor ON film_actor.actor_id = actor.actor_id\r\n"
					+ "WHERE film_actor.actor_id ="+id+";";
			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				
				int acteur_id = rs.getInt("actor.actor_id");
				acteurId.setActor_id(acteur_id);
				
				String first_name = rs.getString("actor.first_name");
				acteurId.setFirst_name(first_name);
				
				String last_name = rs.getString("actor.last_name");
				acteurId.setLast_name(last_name);
				
				Timestamp last_update = rs.getTimestamp("actor.last_update");
				acteurId.setLast_update(last_update);
			}
			rs.close();
			
			ResultSet rs2 = statement.executeQuery(sql2);
			
			while(rs2.next()) {
				Film film = new Film();
				
				String film_title = rs2.getString("film.title");
				film.setTitle(film_title); 
				
				String film_description = rs2.getString("film.description");
				film.setDescription(film_description);
				
				String category = rs2.getString("category.name");
				film.setFilm_category(category);
				
				Timestamp release_year = rs2.getTimestamp("film.release_year");
				film.setRelease_year(release_year);
				
				acteurId.ajouterFilm(film);
			}
			rs2.close();
			return acteurId;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Acteur> findAll() {
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();
			
			String sql = "Select actor_id, first_name, last_name, last_update from actor";
			
			Acteur acteur = null;
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				acteur = new Acteur();
				int acteur_id = rs.getInt("actor_id");
				acteur.setActor_id(acteur_id);
				
				String first_name = rs.getString("first_name");
				acteur.setFirst_name(first_name);
				
				String last_name = rs.getString("last_name");
				acteur.setLast_name(last_name);
				
				Timestamp last_update = rs.getTimestamp("last_update");
				acteur.setLast_update(last_update);
				
				listeActeur.add(acteur);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return listeActeur;
	}

}
