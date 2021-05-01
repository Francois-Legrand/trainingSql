package AccessBdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		while(!returnStart) {
			System.out.println("Bonjour! Si vous voulez voir les acteurs tapez 1, pour voir les film tapez 2");
			String choix = scanner.nextLine();
			
			switch (Integer.parseInt(choix)) {
			case 1:
				returnStart = true;
				
				for (Acteur acteur : acteurService.findAll()) {

					System.out.println(acteur.getActor_id() + " " + acteur.getFirst_name() + " " + acteur.getLast_name()
							+ " " + acteur.getLast_update());
				}
				System.out.println("Tapez 0 si vous voulez retourner au menu principal");
				choix = scanner.nextLine();
				if(choix.equals("0")) {
					returnStart = false;
				}
				break;
			case 2:
				for (Film film : filmService.findAll()) {
					System.out.println(film.getFilm_id() + " " + film.getTitle());

				}
				System.out.println("Tapez sur l'identifiant du film pour visualiser plus d'informations");
				
				choix = scanner.nextLine();
				
				if(Integer.parseInt(choix) == 0) {
					returnStart = false;
				}else {
					
					Film film = filmService.findById(Integer.parseInt(choix));
					
					System.out.println("Catégorie du film: "+film.getFilm_category()+ "\nLangage: "+film.getLanguage()+"\nListe d'acteur:\n");
				
					for (Acteur acteur : film.getListeActeur()) {
						System.out.println(acteur.getActor_id()+ "-"+acteur.getFirst_name()+" "+acteur.getLast_name());
					}
					System.out.println("Saisissez l'identifiant de l'acteur pour avoir plus d'information");
					choix = scanner.nextLine();
					
					Acteur acteur = acteurService.findById(Integer.parseInt(choix));
					
					System.out.println(acteur.getFirst_name()+" "+acteur.getLast_name()+"\nListe des films de "+acteur.getFirst_name()+" "+acteur.getLast_name()+":");
					for (Film filmItem : acteur.getListeDefilm()) {
						System.out.println(filmItem.getTitle()+"\n Synopsis: "+filmItem.getDescription()+"\n Categorie: "+filmItem.getFilm_category()+"\n Année de sortie: "+filmItem.getRelease_year());
					}
					
				}

			default:
				break;
			}

		}
		
	}

}
