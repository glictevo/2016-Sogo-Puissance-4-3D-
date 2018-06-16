import java.io.Serializable;

/**
  * Cette classe permet de gérer des coordonnées de type (int, int)
  * Les fonctions permettent de récupérer les deux coordonnées indépendamment
  */

public class Coordonnees implements Serializable{

  /**
    * Les attributs de cette classe sont les deux coordonnées numériques
    * correspondant à x et y dans (x,y) où x et y sont des int
    */
  private int x;
  private int y;

  public Coordonnees(int x, int y){
  	this.x = x;
  	this.y = y;
  }

  /**
    * Renvoie la coordonnée en X
    * @return la valeur de la coordonnée en X
    */
  public int getX(){
    return this.x;
  }

  /**
    * Renvoie la coordonnée en Y
    * @return la valeur de la coordonnée en Y
    */
  public int getY(){
    return this.y;
  }

  /**
    * Renvoie la description des coordonnées sous la forme (x,y)
    * @return la description des coordonnées sous la forme (x,y)
    */
  public String toString(){
    char alpha = 'A';
    alpha += x;
    return "(" + alpha + "," + (y+1) + ")";
  }

}
