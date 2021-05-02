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

public class FilmService implements IDao<Film>{
	List<Film> listeFilm = new ArrayList<Film>();
	@Override
	public boolean create(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Film findById(int id) {
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();
			
			Film filmId = new Film();
			
			String filmInfos = "SELECT film.title, category.name, language.name FROM film JOIN film_category ON film.film_id = film_category.film_id JOIN category ON film_category.category_id = category.category_id JOIN language ON film.language_id = language.language_id "
					+ "WHERE film.film_id ="+id+";";
			String tableauActeur = "SELECT actor.actor_id, actor.first_name, actor.last_name FROM `film` JOIN film_category ON film.film_id = film_category.film_id JOIN category ON film_category.category_id = category.category_id JOIN film_actor ON film.film_id = film_actor.film_id JOIN actor ON film_actor.actor_id = actor.actor_id JOIN language ON film.language_id = language.language_id "
			+ "WHERE film.film_id ="+id+";";
			
			ResultSet rs = statement.executeQuery(filmInfos);
			
			while(rs.next()) {
				
				String title = rs.getString("film.title");
				filmId.setTitle(title);
				
				String category = rs.getString("category.name");
				filmId.setFilm_category(category);
				
				String language = rs.getString("language.name");
				filmId.setLanguage(language);
			}
			rs.close();
			
			ResultSet rs2 = statement.executeQuery(tableauActeur);
			
			while(rs2.next()) {
				Acteur acteur = new Acteur();
				
				acteur.setActor_id(Integer.parseInt(rs2.getString("actor.actor_id")));
				
				acteur.setFirst_name(rs2.getString("actor.first_name"));
				
				acteur.setLast_name(rs2.getString("actor.last_name"));
				
				filmId.ajouterActeur(acteur);
			}
			rs2.close();
			return filmId;
			
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
	public List<Film> findAll() {
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();
			String sql = "Select film_id, title, description, release_year from film Limit 10";
			
			Film film = null;
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				film = new Film();
				int film_id = rs.getInt("film_id");
				film.setFilm_id(film_id);
				
				String title = rs.getString("title");
				film.setTitle(title);
				
				String description = rs.getString("description");
				film.setDescription(description);
				
				Timestamp release_year = rs.getTimestamp("release_year");
				film.setRelease_year(release_year);
				
				listeFilm.add(film);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeFilm;
	}

}
