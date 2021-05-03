package AccessBdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ListIterator;
import java.util.Scanner;

import connexionBdd.ConnectionUtils;
import fr.trainningSql.dao.ActeurService;
import fr.trainningSql.dao.FilmService;
import fr.trainningSql.model.Acteur;
import fr.trainningSql.model.Film;

public class QueryDataExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		boolean returnStart = false;
		Scanner scanner = new Scanner(System.in);
		ActeurService acteurService = new ActeurService();
		FilmService filmService = new FilmService();
		while (!returnStart) {
			System.out.println(
					"Si vous voulez voir les acteurs tapez 1, pour voir les film tapez 2, pour checher un film 3, pour chercher un acteur 4");
			String choix = scanner.nextLine();

			switch (Integer.parseInt(choix)) {
			case 1:
				returnStart = true;

				for (Acteur acteur : acteurService.findAll()) {

					System.out.println(acteur.getActor_id() + "- " + acteur.getFirst_name() + " "
							+ acteur.getLast_name() + " " + acteur.getLast_update());
				}
				System.out.println("Tapez 0 si vous voulez retourner au menu principal");
				choix = scanner.nextLine();
				if (choix.equals("0")) {
					returnStart = false;
				}
				break;
			case 2:
				for (Film film : filmService.findAll()) {
					System.out.println(film.getFilm_id() + " - " + film.getTitle());

				}
				System.out.println("Tapez sur l'identifiant du film pour visualiser plus d'informations");

				choix = scanner.nextLine();

				if (Integer.parseInt(choix) == 0) {
					returnStart = false;
				} else {
					returnStart = true;
					Film film = filmService.findById(Integer.parseInt(choix));

					System.out.println("- " + film.getTitle() + "\n- Catégorie du film: " + film.getFilm_category()
							+ "\n- Langage: " + film.getLanguage() + "\n- Liste d'acteur:\n");

					for (Acteur acteur : film.getListeActeur()) {
						System.out.println(
								acteur.getActor_id() + "- " + acteur.getFirst_name() + " " + acteur.getLast_name());
					}
					System.out.println("Saisissez l'identifiant de l'acteur pour avoir plus d'information");
					choix = scanner.nextLine();
					
					pageable(choix);
					returnStart = false;
					
				}
				break;
			case 3:
				System.out.println("Donnez le nom du film");
				String choixFilm = scanner.nextLine();

				Film film = filmService.findByName(choixFilm);
				System.out.println("---------------------------------");
				System.out.println("Titre du film: " + film.getTitle() + "\nCategorie: " + film.getFilm_category()
						+ "\nLangue originale: " + film.getLanguage() + "\nDescription: " + film.getDescription());
				for (Acteur acteur : film.getListeActeur()) {
					System.out.println(
							acteur.getActor_id() + "- " + acteur.getFirst_name() + " " + acteur.getLast_name());
				}
				break;
			case 4:
				System.out.println("Donnez le nom d'un acteur");
				String choixActeur = scanner.nextLine();
				Acteur acteur = acteurService.findByName(choixActeur);
				System.out.println("---------------------------------");
				pageable(Integer.toString(acteur.getActor_id()));
			default:
				break;
			}

		}
		scanner.close();
	}
	public static void pageable(String choix) {
		
		try {
			Scanner scanner = new Scanner(System.in);
			Film film = new Film();
			Acteur acteurId = new Acteur();

			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();

			String sql = "SELECT actor.actor_id, actor.first_name, actor.last_name,actor.last_update FROM actor "
					+ "WHERE actor.actor_id =" + Integer.parseInt(choix) + ";";

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				int acteur_id = rs.getInt("actor.actor_id");
				System.out.print(acteur_id + "/");
				acteurId.setActor_id(acteur_id);

				String first_name = rs.getString("actor.first_name");
				acteurId.setFirst_name(first_name);
				System.out.print(first_name + " ");

				String last_name = rs.getString("actor.last_name");
				acteurId.setLast_name(last_name);
				System.out.println(last_name);

				Timestamp last_update = rs.getTimestamp("actor.last_update");
				acteurId.setLast_update(last_update);
				System.out.println("Mise à jour: " + last_update);
				acteurId.ajouterFilm(film);
			}
			rs.close();
			
			int page = 0;
			boolean sortir = false;
			while (!sortir) {
				String sql2 = "SELECT * FROM category JOIN film_category ON category.category_id = film_category.category_id "
						+ "JOIN film ON film_category.film_id = film.film_id JOIN film_actor ON film.film_id = film_actor.film_id "
						+ "JOIN actor ON film_actor.actor_id = actor.actor_id JOIN language ON film.language_id = language.language_id "
						+ "WHERE film_actor.actor_id ="
						+ Integer.parseInt(choix) + " limit " + page + ", 5";

				ResultSet rs2 = statement.executeQuery(sql2);
				System.out.println("Liste des films:");

				while (rs2.next()) {
					System.out.println("----------------------------\n");
					int film_id = rs2.getInt("film.film_id");
					film.setFilm_id(film_id);
					System.out.print(film_id + "/");

					String film_title = rs2.getString("film.title");
					film.setTitle(film_title);
					System.out.println(film_title);

					String film_langage = rs2.getString("language.name");
					film.setLanguage(film_langage);
					System.out.println(film_langage);

					String film_description = rs2.getString("film.description");
					film.setDescription(film_description);
					System.out.println("Description: " + film_description);

					String category = rs2.getString("category.name");
					film.setFilm_category(category);
					System.out.println("Catégories: " + category);

					Timestamp release_year = rs2.getTimestamp("film.release_year");
					film.setRelease_year(release_year);
					System.out.println("Sortie: " + release_year);

				}
				System.out.println("prev next ou sortir");
				String choix2 = scanner.nextLine();
				if (choix2.equals("next")) {
					page += 5;
				} else if (choix2.equals("prev")) {
					page -= 5;
				} else if (choix2.equals("sortir")) {
					sortir = true;
				}
				rs2.close();
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
