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
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * les coups joués par le joueur
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param j le joueur à qui on demande si il veut faire un quart de tour
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return les coordonnées du coup joué par le joueur
    */
  public Coordonnees recupererCoordonnees(Affichage affichage,Write file,Joueur j,String mode, String nomfichier);

  /**
    * Renvoie le nom choisi par l'utilisateur pour un joueur
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * le nom choisi pour un jouer par l'utilisateur
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param joueur le joueur pour qui sera le nom renvoyé
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return le nom du joueur
    */
  public String recupererNom(Affichage affichage, int joueur,Write file, String mode, String nomfichier);

  /**
    * Demande à l'utilisateur s'il souhaite faire une nouvelle partie
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @return true si le joueur veut faire une nouvelle partie, false sinon
    */
  public boolean demanderNouvellePartie(Affichage affichage,String file);

  public void clear(String file);

  /**
    * Demander au joueur s'il veut effectuer un quart de tour des piliers du centre du plateau
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * le fait qu'un joueur ait effectué un quart de tour de plateau à ce moment
    * là de la partie
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param j le joueur à qui on demande si il veut faire un quart de tour
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return le nombre de quart de tour que le joueur souhaite effectuer (0 ou 1)
    */
  public int demanderNbQuartDeTour(Affichage affichage,Write file,Joueur j,String mode, String nomfichier);

  public String demanderVersion(Affichage affichage);

  public String demanderTypePartie(Affichage affichage);

  /**
    * Demande à l'utilisateur à quel Sogo il souhaite jouer
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @return le sogo auquel il veut joueur
    */
  public String demanderSogo(Affichage affichage);

  public void archiverPartie(Affichage affichage,Write file);

  /**
    * Demande une confirmation à l'utilisateur
    * Il répond 'oui' ou 'non'
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    */
  public void confirmation(Affichage affichage,Write file);

  public String lsFichiers(Affichage affichage);

  public Joueur demanderJoueur(Affichage affichage, Input input, int numJoueur, Write file, String mode, String nomfichier);

  /**
    * Demande à l'utilisateur s'il veut faire jouer un humain ou une IA
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * quel joueur joue à ce moment là de la partie
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param numJoueur le numéro du joueur qui sera de ce type
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return un entier correspondant soit à une IA, soit un à humain
    */
  public int demanderHumainOuIA(Affichage affichage, int numJoueur,Write file,String mode,String nomfichier);

  /**
    * Demande la profondeur d'analyse de coup de l'IA à l'utilisateur
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * le fait que l'IA a une certaine profondeur
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return 0, 1, 2 ou 3 selon la profondeur d'analyse souhaitée
    */
  public int demanderProfondeur(Affichage affichage,Write file,String mode,String nomfichier);

  /**
    * Demande le type de comportement voulu pour l'IA à l'utilisateur
    * Les paramètres file, mode et nomfichier permettent d'enregistrer ou de lire
    * le fait que l'IA a un certain type de comportement
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return 1, 2 ou 3 selon le comportement voulu pour l'IA
    */
  public int demanderTypeComportement(Affichage affichage,Write file,String mode,String nomfichier);

  public int localise(String chaine,String[]str);

  public String suitePartie(Affichage affichage);
}
