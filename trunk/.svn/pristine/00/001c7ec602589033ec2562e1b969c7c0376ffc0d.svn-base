package application;

import javafx.scene.input.MouseEvent;

/**
  * Cette classe gère les actions d'un joueur Humain
  * Les fonctions sont donc adaptées à l'utilisation du jeu par
  * un joueur réel
  */
public class Humain extends Joueur{

  /**
    * Constructeur d'un objet Humain
    * @param nom le nom du joueur
    * @param input le type d'input permettant au joueur de communiquer avec le jeu
    */
  public Humain(String nom, Input input){
    super(nom, input);
  }

  /**
   * Appelle l'input du joueur Humain pour demander son coup
   * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
   * les coups joués par le joueur dans des fonctions appellées dans cette fonction
   * @param event pour voir où le joueur a cliqué
   * @param input pour savoir si l'on est en mode interface ou terminal
   * @return les coordonnées où le coup est joué
     */
  public Coordonnees demanderCoup(MouseEvent event,Input input){
	return input.donneCoordonnees(event,this);
  }

  /**
   * Demande si le joueur souhaite effectuer un quart de tour des piliers
   * du milieu dans une partie de sogo tournant
   * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
   * le fait que le joueur ait effectué un quart de tour à ce moment là du jeu
   * @param event pour voir où le joueur a cliqué
   * @param input pour savoir si l'on est en mode interface ou terminal
   * @return
     */
  public int demanderNbQuartDeTour(MouseEvent event,Input input){
    return input.demandeQuartDeTour(event,this);
  }

}
