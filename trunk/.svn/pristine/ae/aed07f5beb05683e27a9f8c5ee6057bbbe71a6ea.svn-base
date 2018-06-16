/**
  * Cette classe gère l'exception générée lorsqu'un pilier du jeu est rempli
  * C'est-à-dire lorsque qu'on ne peut donc plus mettre un nouveau pion dessus
  */

public class ExceptionPilierRempli extends Exception{

  /**
    * Cette classe a un attribut : les @link Coordonnees du pilier rempli
    */
  private Coordonnees coordonnees;

  /**
    * Constructeur de l'exception
    * Elle prend en argument les @link Coordonnees du pilier rempli
    * @param coordonnees les coordonnées du pilier rempli
    */
  public ExceptionPilierRempli(Coordonnees coordonnees){
    this.coordonnees = coordonnees;
  }

  /**
    * Description de l'exception contenant les coordonnées du pilier rempli
    * @return la description de l'exception qui contient les coordonnées du pilier rempli
    */
  public String toString(){
    return "Le pilier en position : " + coordonnees.toString() + " est rempli";
  }

}
