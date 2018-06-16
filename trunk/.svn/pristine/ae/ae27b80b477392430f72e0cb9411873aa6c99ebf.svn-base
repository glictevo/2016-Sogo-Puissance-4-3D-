/**
  * Cette clases gère le jeu du Sogo Tournant
  * Elle reprend les même fonctions que la classe @link Sogo
  * mais modifie certaines pour l'adapter au style de jeu tournant
  * comme la fonction pour placer un pion et réinitialiser la partie
  */

public class SogoTournant extends Sogo{

  /**
    * Constructeur du Sogo Tournant
    * Appelle le constructeur de Sogo permettant de mettre un @link PlateauTournant
    * pour plateau de jeu, initilisé à 4x4x4 de dimensions
    * @param affichage l'affichage qu'on choisit (terminal ou interface)
    * @param input l'input qu'on choisit (terminal ou interface)
    */
  public SogoTournant(Affichage affichage, Input input){
    super(affichage, input, new PlateauTournant(4, 4, 4));
  }
  /**
    * Appelle placerPion dans la classe mère
    * Permet à un joueur de placer un pion sur le plateau
    * Puis lui permet de tourner ou non les piliers du centre du plateau
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * les actions se passant en jeu
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * les actions se passant en jeu
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    */
  public void placerPion(Write file, String mode, String nomfichier){
    super.placerPion(file,mode,nomfichier);

    //Le joueur choisit s'il veut tourner le centre du plateau ou non
    boolean verif = false;
    int nbQuartDeTour = 0;
    int count = 0;
    do{
      nbQuartDeTour = joueurActuel.demanderNbQuartDeTour(super.plateau, affichage,file,joueurActuel,mode,nomfichier);
      affichage.afficherMessage("nbQuartDeTour correspond à : " + nbQuartDeTour);
      count++;
      if(count > 3){
        System.exit(0);
      }

      if(joueurActuel == joueur1){
        int nbQuartDeTourJoueur1 = ((PlateauTournant)super.plateau).getNbQuartDeTourJoueur(0);
        if(nbQuartDeTour <= nbQuartDeTourJoueur1){
          verif = true;
          ((PlateauTournant)super.plateau).setNbQuartDeTourJoueur(1, nbQuartDeTourJoueur1 - nbQuartDeTour);
          System.out.println("nbQuartDeTourJoueur1 avant : " + nbQuartDeTourJoueur1);
          System.out.println("nbQuartDeTour effectué : " + nbQuartDeTour);
          System.out.println("nbQuartDeTour après : " + (nbQuartDeTourJoueur1 - nbQuartDeTour));
        }
      } else {
        int nbQuartDeTourJoueur2 = ((PlateauTournant)super.plateau).getNbQuartDeTourJoueur(1);
        if(nbQuartDeTour <= nbQuartDeTourJoueur2){
          verif = true;
          ((PlateauTournant)super.plateau).setNbQuartDeTourJoueur(2, nbQuartDeTourJoueur2 - nbQuartDeTour);
          System.out.println("nbQuartDeTour : " + (nbQuartDeTourJoueur2 - nbQuartDeTour));        }
      }
    } while(!verif);

    //Si le joueur a choisi de déplacer le centre, on le fait
    if(nbQuartDeTour > 0){
      for(int i = 0; i < nbQuartDeTour; i++){
        ((PlateauTournant)super.plateau).tournerCentre();
      }
    }
  }

  /**
    * Réinitialise le plateau de jeu et le nombre de pions de Sogo pour permettre à une nouvelle partie d'être lancée
    */
  public void reinitialiserPartie(){
    this.nombreDePions = 64;
    ((PlateauTournant)plateau).setNbQuartDeTourJoueur(1, 4);
    ((PlateauTournant)plateau).setNbQuartDeTourJoueur(2, 4);
    this.plateau = new PlateauTournant(4, 4, 4);
  }

}
