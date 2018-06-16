package application;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
  * Cette classe gère le jeu du Sogo Tournant
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
   super.setSogoTournant(affichage, input, new PlateauTournant(4, 4, 4));
  }

  /**
    * Appelle placerPion dans la classe mère
    * Permet à un joueur de placer un pion sur le plateau
    * Puis lui permet de tourner ou non les piliers du centre du plateau
    *@param event intervient pour deux actions, une pour le placement d'un pion, l'autre pour le quart de tour
    */
  public void placerPion(MouseEvent event){
    //Le joueur choisit s'il veut tourner le centre du plateau ou non
    boolean verif = false;
    int nbQuartDeTour = 0;
    int count = 0;
    int nbQuartDeTourJoueurActeul = 0;
    if(partieTerminal.getTypeDePartie().equals("Terminal")){
      super.placerPion(event);
      do{
        nbQuartDeTour = joueurActuel.demanderNbQuartDeTour(event,partieTerminal);
        affichage.afficherMessage("nbQuartDeTour correspond à : " + nbQuartDeTour);
        count++;
        if(count > 3){
          System.exit(0);
        }
        if(joueurActuel == joueur1){
          nbQuartDeTourJoueurActeul  = ((PlateauTournant)super.plateau).getNbQuartDeTourJoueur(0);
          if(nbQuartDeTour <= nbQuartDeTourJoueurActeul){
            verif = true;
            ((PlateauTournant)super.plateau).setNbQuartDeTourJoueur(1, nbQuartDeTourJoueurActeul  - nbQuartDeTour);
          }
        } else {
          nbQuartDeTourJoueurActeul = ((PlateauTournant)super.plateau).getNbQuartDeTourJoueur(1);
          if(nbQuartDeTour <= nbQuartDeTourJoueurActeul){
            verif = true;
            ((PlateauTournant)super.plateau).setNbQuartDeTourJoueur(2, nbQuartDeTourJoueurActeul-nbQuartDeTour);
          }
        }
      } while(!verif);
    }else{
      Node  source = (Node)  event.getSource();
      if(this.joueurActuel instanceof IA){
        super.placerPion(event);
        partieInterface.setNbQuartDeTour(1);
        return;
      }
      //Controle le nombre des tours
      if(this.joueurActuel == this.joueur1 && !verifJoueurNBTours(event,0)){
        return;
      }else if(this.joueurActuel == this.joueur2 && !verifJoueurNBTours(event,1)){
        return;
      }
      String id = ((Node) event.getSource()).getId();
    verifPartieLecture();
    if(!(id.equals("nbQuartDeOui"))&&!(id.equals("nbQuartDeNon"))&&!partieInterface.getModeJeux().equals("lecture")){
      super.placerPion(event);
      partieInterface.demanderNbQuartDeTour(event,this.joueurActuel);
        (new Capteurs()).desactiveCapteurs();
        return;
    }
    if(!partieInterface.getModeJeux().equals("lecture")){
      partieInterface.demanderNbQuartDeTour(event,this.joueurActuel);
      nbQuartDeTour = partieInterface.getNbQuartDeTour();
      if(joueurActuel == joueur1){
        nbQuartDeTourJoueurActeul = joueurActuelPlaceNBTours(nbQuartDeTour,0);
      }else if(joueurActuel == joueur2){
        nbQuartDeTourJoueurActeul = joueurActuelPlaceNBTours(nbQuartDeTour,1);
      }
    }else if(partieInterface.getModeJeux().equals("lecture")){
      nbQuartDeTour = partieInterface.getNbQuartDeTour();
    }
      
    }
    //Si le joueur a choisi de déplacer le centre, on le fait
    if(nbQuartDeTour > 0){
      for(int i = 0; i < nbQuartDeTour; i++){
        ((PlateauTournant)super.plateau).tournerCentre();
      }
    }
    communicationJoueur(nbQuartDeTourJoueurActeul,nbQuartDeTour);
    return;
  }

  /**
    * Réinitialise le plateau de jeu et le nombre de pions de Sogo pour permettre à une nouvelle partie d'être lancée
    */
  public void reinitialiserPartie(){
    joueurGagnant = false;
    this.nombreDePions = 64;
    ((PlateauTournant)plateau).setNbQuartDeTourJoueur(1, 4);
    ((PlateauTournant)plateau).setNbQuartDeTourJoueur(2, 4);
    this.plateau = new PlateauTournant(4, 4, 4);
  }
  public boolean verifJoueurNBTours(MouseEvent event,int numJoueur){
    Node  source = (Node)  event.getSource();
    if(0 == ((PlateauTournant)super.plateau).getNbQuartDeTourJoueur(numJoueur)){
      super.placerPion(event);
      partieInterface.setNbQuartDeTour(1);
      source = (partieInterface.getStage()).getScene().lookup("#nbQuartDeOui");
      ((Button)source).setDisable(true);
      source = (partieInterface.getStage()).getScene().lookup("#nbQuartDeNon");
    ((Button)source).setDisable(true);
    return false;
    }
    return true;
  }
  /**
   * methode gérant le placement des quarts de tour d'un joueur
   * @param nbQuartDeTour correspond au quart de tour
   * @param numJoueur correspond au numero du joueur actuel
   */
  public int joueurActuelPlaceNBTours(int nbQuartDeTour,int numJoueur){
   int nbQuartDeTourJoueurActeul = ((PlateauTournant)super.plateau).getNbQuartDeTourJoueur(numJoueur);
    ((PlateauTournant)super.plateau).setNbQuartDeTourJoueur(numJoueur, (nbQuartDeTourJoueurActeul - nbQuartDeTour));
    return nbQuartDeTourJoueurActeul;
  }
  /**
   * methode gérant la communication après le placement du quart de tour
   * @param nbQuartDeTourJoueurActeul
   * @param nbQuartDeTour correspond au choix effectuer par le joueur oui ->1 ou non ->0
   */
  public void communicationJoueur(int nbQuartDeTourJoueurActeul,int nbQuartDeTour){
    if(partieTerminal.getTypeDePartie().equals("Terminal")){
      System.out.println("nbQuartDeTourJoueur avant : " +nbQuartDeTourJoueurActeul);
      System.out.println("nbQuartDeTour effectué : " + nbQuartDeTour);
      System.out.println("nbQuartDeTour après : " + (nbQuartDeTourJoueurActeul  - nbQuartDeTour));
    }else if(!partieInterface.getModeJeux().equals("lecture")){
      Node n = (partieInterface.getStage()).getScene().lookup("#communication");
      ((TextArea )n).appendText("nbQuartDeTourJoueur avant : " + nbQuartDeTourJoueurActeul +
      "\n"+"nbQuartDeTour effectué : " + nbQuartDeTour + 
      "\n" + "nbQuartDeTour après : " + (nbQuartDeTourJoueurActeul  - nbQuartDeTour)+"\n");
    }else if(partieInterface.getModeJeux().equals("lecture")){
      Node n = (partieInterface.getStage()).getScene().lookup("#communication");
      ((TextArea )n).appendText("nbQuartDeTour : " + nbQuartDeTour + "\n");
    }
  }
  /**
   * méthode servant à verifier qu'il s'agit bien d'une partie en mode lecture
   */
  public void verifPartieLecture(){
    if(partieInterface.getModeJeux().equals("lecture")){
      super.placerPion(null);
      partieInterface.demanderNbQuartDeTour(null,this.joueurActuel);
      (new Capteurs()).desactiveCapteurs();
    }
    return;
  }

}
