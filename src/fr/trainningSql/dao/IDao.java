package fr.trainningSql.dao;

import java.util.List;

public interface IDao <T>{
	boolean create ( T o); // M�thode permettant d'ajouter un objet o de type T.
	boolean delete (T o); // M�thode permettant de supprimer un objet o de type T.
	boolean update (T o); // M�thode permettant de modifier un objet o de type T.
	T findById (int id); // M�thode permettant de renvoyer un objet dont id est pass� en param�tre.
	T findByName (String o);
	List <T> findAll (); // M�thode permettant de renvoyer la liste des objets de type T.
}
