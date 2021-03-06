package application;

/**
  * Interface décrivant les actions d'affichage possibles pour le jeu
  * Il décrit les fonctions permettant d'afficher le plateau,
  * et d'afficher un message
  */

public interface Affichage{

  /**
    * Affiche le plateau de jeu
    * @param plateau le plateau du jeu
    */
  public void afficherPlateau(Plateau plateau);

  /**
    * Affiche un message à l'éconfirmation
    * @param str le message à afficher
    */
  public void afficherMessage(String str);

}
