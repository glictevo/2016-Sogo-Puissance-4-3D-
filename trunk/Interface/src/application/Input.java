package application;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
  * Interface décrivant les actions d'input possibles pour le jeu
  * Il décrit les fonctions permettant de récupérer différent types
  * d'informations dont on a besoin pour le fonctionnement du jeu
  * Ces fonctions permettent de communiquer directement avec le joueur
  * (ou avec l'IA)
  */
public interface Input{

  /**
    * Renvoie les coordonnees du pilier sur lequel le joueur souhaite ajouter son pion
    */
  public void recupererCoordonnees(MouseEvent event,Joueur j);

  public Coordonnees donneCoordonnees(MouseEvent event,Joueur j);

  public String recupererNom(Affichage affichage, int joueur,Write file, String mode, String nomfichier);

  public void demanderNouvellePartie(ActionEvent event);

  public void clear(String file);

  public void demanderNbQuartDeTour(MouseEvent event,Joueur j);

  public void demanderVersion(ActionEvent event);

  public void demanderTypePartie(ActionEvent event);

  public void demanderSogo(ActionEvent event);

  public void archiverPartie(ActionEvent event);

  public void confirmation(ActionEvent event);

  public void lsFichiers(ActionEvent event);

  public void demanderJoueur(ActionEvent event);

  public int localise(String chaine,String[]str);

  public void suitePartie(ActionEvent event);

  public Sogo getSogo();

  public Write getFile();

  public String getModeJeux();

  public String getFileName();

  public int demandeQuartDeTour(MouseEvent event,Joueur j);

}
