package fr.trainningSql.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Acteur {
	
	private int actor_id;
	private String first_name;
	private String last_name;
	private Timestamp last_update;
	private String ville;
	private String pays;
	private List<Film> listeDefilm = new ArrayList<Film>();
	public Acteur() {
		super();
	}
	public int getActor_id() {
		return actor_id;
	}
	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Timestamp getLast_update() {
		return last_update;
	}
	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	
	public List<Film> getListeDefilm() {
		return listeDefilm;
	}
	public void setListeDefilm(List<Film> listeDefilm) {
		this.listeDefilm = listeDefilm;
	}
	
	@Override
	public String toString() {
		return "Acteur [actor_id=" + actor_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", last_update=" + last_update + "]";
	}
	public void ajouterFilm(Film film) {
		this.listeDefilm.add(film);
		
	}
	
	
}
