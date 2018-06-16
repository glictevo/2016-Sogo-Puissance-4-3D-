package application;

import java.io.Serializable;


/**
  * Cette classe permet de gérer l'affichage des informations dans le terminal
  * Il décrit les fonctions permettant d'afficher le plateau,
  * et d'afficher un message
  */
public class Terminal implements Affichage,Serializable{

  /**
    * Affiche le plateau avec des coordonnées alphanumériques (pour faciliter le choix pour le joueur)
    * @param plateau le plateau qu'on affiche
    */
  public void afficherPlateau(Plateau plateau){

    //On affiche les coordonnees alpha
    char caractere = 'A';
    for(int i = 0; i < plateau.getPlateau().length; i++){
      System.out.print(caractere + " ");
      caractere++;
    }
    System.out.println();

    for(int y = 0; y < plateau.getPlateau().length; y++){
      for(int z = plateau.getPlateau().length - 1; z >= 0; z--){
        for(int x = 0; x < plateau.getPlateau().length; x++){
          //Si il n'y a pas de pion à cet endroit du plateau
          if(plateau.getPion(x, y, z) == null){
            System.out.print("| ");
          } else { //Si il y a un pion, on regarde à quel joueur il appartient et on affiche X ou O
            int id = plateau.getPion(x, y, z).getJoueur().getId();
            if(id == 0){
              System.out.print("X ");
            } else {
              System.out.print("O ");
            }
          }
        }
        System.out.println();
      }
      for(int i = 0; i < plateau.getPlateau().length; i++){
        System.out.print("- ");
      }
      //On affiche les coordonnees numeriques
      System.out.println("  " + (y + 1));
      System.out.println("\n");
    }

  }
  /**
    * Affiche un message à l'éconfirmation
    * @param le message à afficher
    */
  public void afficherMessage(String str){
    System.out.println(str);
  }

}
