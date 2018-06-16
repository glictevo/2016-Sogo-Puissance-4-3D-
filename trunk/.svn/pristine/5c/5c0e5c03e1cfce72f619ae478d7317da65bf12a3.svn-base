import java.io.Serializable;

/**
  * Cette clases gère le jeu du Sogo
  * Elle contient des fonctions permettant l'exécution du jeu et son
  * déroulement, les intéractions avec les deux joueurs, etc.
  */

public class Sogo implements Serializable{

  /**
    * Les attributs du Sogo sont :
    * - un @link Plateau
    * - deux @link Joueur
    * - un des deux joueurs stocké dans joueurActuel, pour savoir lequel est entrain de jouer son tour
    * - un @link Affichage permettant de savoir quel affichage on utilise
    * - un @link Input permettant de savoir quel type d'input on utilise
    * - le nombre de pions, mis a 64 car on décide d'utiliser dans tous les
    *   cas un plateau de 4x4x4 de dimensions
    * - deux compteurs de parties gagnées, un pour chaque joueur
    */
  protected Plateau plateau;
  protected Joueur joueur1;
  protected Joueur joueur2;
  protected Joueur joueurActuel;
  protected Affichage affichage;
  protected Input input;
  protected int nombreDePions = 64;
  private static int nbPartiesGagneesJoueur1 = 0;
  private static int nbPartiesGagneesJoueur2 = 0;

  /**
    * Constructeur du Sogo
    * @param affichage l'affichage qu'on choisit (terminal ou interface)
    * @param input l'input qu'on choisit (terminal ou interface)
    */
  public Sogo(Affichage affichage, Input input){
    this.joueur1 = null;
    this.joueur2 = null;
    this.joueurActuel = null;
    this.affichage = affichage;
    this.plateau = new Plateau(4, 4, 4);
    this.input = input;
  }

  /**
    * Constructeur du sogo prenant un plateau tournant pour plateau
    * Il est toujours appelé depuis la classe @link SogoTournant
    * @param affichage l'affichage qu'on choisit (terminal ou interface)
    * @param input l'input qu'on choisit (terminal ou interface)
    * @param plateau le plateau tournant utilisé pour le cas du SogoTournant
    */
  public Sogo(Affichage affichage, Input input, PlateauTournant plateau){
    this(affichage, input);
    this.plateau = plateau;
  }

  /**
    * Joue la partie en entière
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * les actions se passant en jeu
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    */
  public void deroulement(Write file, String mode, String nomfichier){
    //Pour se donner une idée du déroulement :
    joueurActuel = ((this.joueurActuel==null)?joueur2:changerJoueurActuel(this.joueurActuel));
    initialiserJoueurs(file,mode,nomfichier);
    jouerNouvellePartie(file,mode,nomfichier);

  }

  /**
    * Initialise les joueurs de la partie
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * les actions se passant en jeu
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    */
  public void initialiserJoueurs(Write file, String mode, String nomfichier){
    //joueur1 = ((this.joueur1 == null)? new Humain(input.recupererNom(affichage, 1,file,mode,nomfichier), new ScannerInput()):this.joueur1);
    //joueur2 = ((this.joueur2 == null)? new Humain(input.recupererNom(affichage, 2,file,mode,nomfichier), new ScannerInput()):this.joueur2);
    joueur1 = ((this.joueur1 == null)? input.demanderJoueur(affichage, input, 1, file, mode, nomfichier) : this.joueur1);
    joueur2 = ((this.joueur2 == null)? input.demanderJoueur(affichage, input, 2, file, mode, nomfichier) : this.joueur2);
  }

  /**
    * Permet de jouer plusieurs partie de Sogo si les joueurs le souhaitent
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * les actions se passant en jeu
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    */
  public void jouerNouvellePartie(Write file,String mode, String nomfichier){
    boolean nouvellePartie = true;

    while(nouvellePartie){

      boolean quitter = false;

      //On lance la boucle du jeu
      jouerPartie(file,mode,nomfichier);
      input.archiverPartie(affichage,file);
      nouvellePartie = input.demanderNouvellePartie(affichage,(this instanceof SogoTournant)?"2":"1");
      if(nouvellePartie) reinitialiserPartie();
    }


  }

  /**
    * Joue une partie de Sogo entièrement
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * les actions se passant en jeu
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    */
  public void jouerPartie(Write file,String mode,String nomfichier){
    boolean joueurGagnant = false;

    do{
      joueurActuel = changerJoueurActuel(joueurActuel);

      affichage.afficherPlateau(plateau);
      if(joueurActuel instanceof IA){
        try{
          Thread.sleep(500);
        }catch(InterruptedException e){
          System.out.println("Test en cours");
        }
      }
      //permet de faire une pause dans l'execution du programme
      if(mode.equals("lecture")){
        try{
          Thread.sleep(4000);
        }catch(InterruptedException e){
          System.out.println("Test en cours");
        }
      }
      affichage.afficherMessage("Joueur " + joueurActuel.getNom() + ", à vous de jouer");

      //Le joueur place un pion
      placerPion(file,mode,nomfichier);

      joueurGagnant = plateau.verifierVictoire();

    } while(!joueurGagnant && nombreDePions > 0);


/*    if(joueurGagnant){
      affichage.afficherMessage("Le joueur " + joueurActuel.getNom() + " a gagné !");

      if(joueurActuel == joueur1) {
        nbPartiesGagneesJoueur1++;
      } else {
        nbPartiesGagneesJoueur2++;
      }
    } else {
      affichage.afficherMessage("Match nul !");
    }
*/
    if(plateau.getVictoire(1) && plateau.getVictoire(2)){
      affichage.afficherMessage("Match nul ! Un point pour les deux joueurs");
      nbPartiesGagneesJoueur1++;
      nbPartiesGagneesJoueur2++;
    } else if(plateau.getVictoire(1)){
      affichage.afficherMessage("Le joueur " + joueur1.getNom() + " a gagné !");
      nbPartiesGagneesJoueur1++;
    } else if(plateau.getVictoire(2)){
      affichage.afficherMessage("Le joueur " + joueur2.getNom() + " a gagné !");
      nbPartiesGagneesJoueur2++;
    } else {
      affichage.afficherMessage("Match nul !");
    }

    affichage.afficherPlateau(plateau);
    if(mode.equals("lecture")){
      System.exit(0);
    }

    affichage.afficherMessage(joueur1.getNom() + " : " + nbPartiesGagneesJoueur1 + " | " + nbPartiesGagneesJoueur2 + " : " + joueur2.getNom());

  }

  /**
    * Effectue le placement d'un pion sur le plateau par un joueur
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * les actions se passant en jeu
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    */
  public void placerPion(Write file,String mode, String nomfichier){
    boolean placement = false;
    Coordonnees coordonnees;

    while(!placement){
      coordonnees = joueurActuel.demanderCoup(plateau, affichage,file,mode,nomfichier);
      if(mode.equals("lecture")){
        affichage.afficherMessage(joueurActuel.getNom()+"("+((joueurActuel instanceof IA)?"IA":"Humain")+")"+" a joué en "+((char)(97+coordonnees.getX()))+(coordonnees.getY()+1));
      }
      if(coordonnees.getX()== -1){
        SerializeObjects obj = new SerializeObjects((this instanceof SogoTournant)?"2":"1");
        obj.saveGame(this);//le sogo actuel
        System.exit(0);
      }

      try{
        plateau.placer(coordonnees, joueurActuel);
        nombreDePions--;
        placement = true;
      } catch (ExceptionPilierRempli e){
        affichage.afficherMessage(e.toString());
      }
    }


  }

  /**
    * Change le joueur actuel
    * @param JoueurActuel le joueur actuel
    * @return le joueur qui devient le joueur actuel
    */
  public Joueur changerJoueurActuel(Joueur joueurActuel){
    if(joueurActuel == joueur1){
      return joueur2;
    } else {
      return joueur1;
    }
  }

  /**
    * Réinitialise le plateau de jeu et le nombre de pions de Sogo pour permettre à une nouvelle partie d'être lancée
    */
  public void reinitialiserPartie(){
    this.nombreDePions = 64;
    this.plateau = new Plateau(4, 4, 4);
  }

}
