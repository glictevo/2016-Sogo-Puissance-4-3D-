package application;

/**
  * Cette classe gère le plateau tournant du jeu Sogo tournant
  * En plus des fonctions présentes dans la classe @link Plateau
  * cette classe permet de savoir le nombre de quart de tour restant pour
  * chaque joueur ainsi que la gestion de la rotations des piliers
  */
public class PlateauTournant extends Plateau{

  /**
    * Les attributs de cette classe permettent de savoir le nombre de
    * quart de tour que les joueurs peuvent encore effectuer dans la partie
    */
  private int nbQuartDeTourJoueur1;
  private int nbQuartDeTourJoueur2;

  /**
    * Constructeur d'un plateau tournant, fonctionne comme un plateau normal
    * Le nombre de quart de tour est initialisé à 4 pour nos règles du jeu
    */
  public PlateauTournant(int x, int y, int z){
    super(x, y, z);
    this.nbQuartDeTourJoueur1 = 4;
    this.nbQuartDeTourJoueur2 = 4;
  }

  /**
    * Renvoie le nombre de quart de tour qu'il reste au joueur ayant
    * l'id passé en argument
    * @param l'id du joueur dont on souhaite savoir le nombre de quart de tour restant
    * @return le nombre de quart de tour qu'il reste au joueur ayant l'id passé en argument
    */
  public int getNbQuartDeTourJoueur(int n){
    if(n == 0){
      return this.nbQuartDeTourJoueur1;
    } else if (n == 1){
      return this.nbQuartDeTourJoueur2;
    } else {
      return 0;
    }
  }

  /**
    * Attribut le nombre de quart de tour restant au joueur ayant
    * l'id passé en argument
    * @param idJoueur l'id du joueur dont on souhaite fixer le nombre de quart de tour restant
    * @param valeur la valeur qu'on souhaite mettre en nombre de quart de tour restant
    */
  public void setNbQuartDeTourJoueur(int idJoueur, int valeur){
    if(idJoueur == 0){
      this.nbQuartDeTourJoueur1 = valeur;
    } else {
      this.nbQuartDeTourJoueur2 = valeur;
    }
  }

  /**
    * Tourne les quatre piliers du centre dans le sens horaire
    * Si le dernier coup était placé dans le centre, change les coordonnees du dernier coup
    */
  public void tournerCentre(){
    Pion[] tmp = this.plateau[1][1];

    this.plateau[1][1] = this.plateau[1][2];
    this.plateau[1][2] = this.plateau[2][2];
    this.plateau[2][2] = this.plateau[2][1];
    this.plateau[2][1] = tmp;
    if(new ScannerInput().getTypeDePartie().equals("Interface")){
    	super.retabliPlateau();
    }

  }

}
