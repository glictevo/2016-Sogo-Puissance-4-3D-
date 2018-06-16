package application;
import java.util.ArrayList;

import javafx.scene.input.MouseEvent;

import java.io.Serializable;

/**
  * Classe abstraite permettant de spécifier les actions et les fonctions
  * liées à un joueur pour le jeu.
  * Il décrit les fonctions nécessaire : demander un coup au joueur, demander
  * s'il veut effectuer un quart de tour. Mais aussi des fonctions pour récupérer
  * certains de ses attributs
  */
public abstract class Joueur implements Serializable{

  /**
    * Attributs décrivant un objet de type Joueur
    * Ces attribuent sont : le nom du joueur, le type d'input qu'il utilise,
    * son id
    * l'attribut de classe countId permet de savoir combien de joueurs ont été créés
    * et permet d'attribuer un id à un joueur lors de sa création
    */
  protected Input input;
  private String nom;
  private int id;
  private static int countId = 0;

  /**
    * Constructeur d'un objet Joueur
    * @param nom le nom voulu pour le joueur
    * @param input le type d'input que le joueur utilisera
    */
  public Joueur(String nom, Input input){
    this.nom = nom;
    this.id = countId;
    countId++;
    this.input = input;
  }

  /**
    * Renvoie l'id du joueur
    * @return l'id du joueur
    */
  public int getId(){
    return this.id;
  }

  /**
    * Renvoie le nom du joueur
    * @return le nom du joueur
    */
  public String getNom(){
    return this.nom;
  }

  /**
    * Appelle l'input du joueur pour demander son coup
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * les coups joués par le joueur dans des fonctions appellées dans cette fonction
    * @param event pour voir où le joueur a cliqué
    * @param input pour savoir si l'on est en mode interface ou terminal
    * @return les coordonnees où le joueur veut placer son pion
    */
  public abstract Coordonnees demanderCoup(MouseEvent event,Input input);

  /**
    * Renvoie la description du joueur (son nom)
    * @return la description du joueur (son nom)
    */
  public String toString(){
    return "Joueur " + nom;
  }

  /**
    * Demande au joueursi il veut effectuer un quart de tour
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * le fait qu'un joueur ait effectué un quart de tour à ce moment là du jeu
    * @param event pour voir où le joueur a cliqué
    * @param input pour savoir si l'on est en mode interface ou terminal
    */
  public abstract int demanderNbQuartDeTour(MouseEvent event,Input input);

}
