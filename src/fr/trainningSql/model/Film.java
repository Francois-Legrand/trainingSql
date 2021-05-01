package fr.trainningSql.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Film {
	private int film_id;
	private String title;
	private String description;
	private Timestamp release_year;
	private String film_category;
	private String language;
	private List<Acteur> listeActeur = new ArrayList<Acteur>();
	
	public Film() {
		super();
	}
	public int getFilm_id() {
		return film_id;
	}
	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getRelease_year() {
		return release_year;
	}
	public void setRelease_year(Timestamp release_year) {
		this.release_year = release_year;
	}
	public String getFilm_category() {
		return film_category;
	}
	public void setFilm_category(String film_category) {
		this.film_category = film_category;
	}
	
	public List<Acteur> getListeActeur() {
		return listeActeur;
	}
	public void setListeActeur(List<Acteur> listeActeur) {
		this.listeActeur = listeActeur;
	}
	public void ajouterActeur(Acteur acteur) {
		this.listeActeur.add(acteur);
	}
	@Override
	public String toString() {
		return "Film [film_id=" + film_id + ", title=" + title + ", description=" + description + ", release_year="
				+ release_year + ", film_category=" + film_category + "]";
	}
	
	
}
