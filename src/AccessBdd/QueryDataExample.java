package AccessBdd;

import java.sql.SQLException;
import java.util.Scanner;

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
			System.out.println("Si vous voulez voir les acteurs tapez 1, pour voir les film tapez 2");
			String choix = scanner.nextLine();

			switch (Integer.parseInt(choix)) {
			case 1:
				returnStart = true;

				for (Acteur acteur : acteurService.findAll()) {

					System.out.println(acteur.getActor_id() + "- " + acteur.getFirst_name() + " " + acteur.getLast_name()
							+ " " + acteur.getLast_update());
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

					System.out.println("- "+film.getTitle()+"\n- Catégorie du film: " + film.getFilm_category() + "\n- Langage: "
							+ film.getLanguage() + "\n- Liste d'acteur:\n");

					for (Acteur acteur : film.getListeActeur()) {
						System.out.println(
								acteur.getActor_id() + "- " + acteur.getFirst_name() + " " + acteur.getLast_name());
					}
					System.out.println("Saisissez l'identifiant de l'acteur pour avoir plus d'information");
					choix = scanner.nextLine();

					Acteur acteur = acteurService.findById(Integer.parseInt(choix));
					boolean ok = false;
					System.out.println(acteur.getFirst_name() + " " + acteur.getLast_name() + "\nListe des films de "
							+ acteur.getFirst_name() + " " + acteur.getLast_name() + ":");

					int j = -1;
					int count = 0;
					while (count != acteur.getListeDefilm().size()) {
						for (Film filmItem : acteur.getListeDefilm()) {
							j++;

							if (j == 5) {
								System.out.println("suivant y/n");
								choix = scanner.nextLine();
								if (choix.equals("y"))
									j = 0;
							}
							count++;

							System.out.println(count + "/" + filmItem.getTitle() + "\n - Categorie: "
									+ filmItem.getFilm_category() + "\n - Synopsis:" + filmItem.getDescription()
									+ "\n - Année de sortie: " + filmItem.getRelease_year());

						}
					}
					if (count == acteur.getListeDefilm().size()) {
						System.out.println("Fin");
						returnStart = false;
					}

				}

			default:
				break;
			}

		}

	}

}
