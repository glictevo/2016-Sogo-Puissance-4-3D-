
package application;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.util.*;
import java.io.*;
import java.io.Serializable;

/**
 * classe servant à gérer les entrées du joueur en mode terminal
 */
public class ScannerInput implements Input,Serializable{
  private static int curs = 0;
  private static String mem = "";

  private static Write gameFile;//Écriture d'une partie dans un fichier
  private static String filename;//correspond au nom du fichier
  private static String modejeux;//lecture ou ecriture
  private static String typeDePartie = "Interface";//Terminal Interface

  private static Affichage affichage = new Terminal();
  private static Input input = new ScannerInput();

  private static int choiceInt = 0;//utilisé pour prendre les réponses d'un joueur de type int
  private static String choiceString = "";//utilisé pour prendre les réponses d'un joueur de type String
  private static boolean choiceBoolean = false;// ''

  private static Joueur joueurTmp;//Joueur en cours de création
  private static Coordonnees coordschoice;//variable utilisé pour les choix des coordonnées d'un joueur

  private static int nbQuartDeTour;//utilisé pour le SogoTournant
  private static Sogo sogo = null;//Création d'un Sogo localement

  private static String choiceSogo = "";
  private static String file = "";

 /**
  * getter retournant le nom du ficier
  */
  public String getFileName(){
    return filename;
  }
  /**
   * getter utilisant une variable String local
   */
  public boolean getChoiceString() {
    return(choiceBoolean);
  }
  /**
   * getter retournant le fichier courant du jeu
   */
  public Write getFile(){
    return gameFile;
  }
  /**
   * getter retournant le sogo choisie au départ
   */
  public Sogo getSogo(){
    return sogo;
  }
  /**
   * getter utilisant une variable boolean local
   */
  public boolean getChoiceBoolean(){
    return choiceBoolean;
  }
  /**
   * getter retournant le mode de jeu choisie par le joueur
   */
  public String getModeJeux(){
    return modejeux;
  }
  /**
   * getter retournant le type de partie choisie au départ
   */
  public String getTypeDePartie(){
    return typeDePartie;
  }
  /**
   * setter changeant le fichier courant
   * @param file le nom du nouveau fichier
   */
  public void setFile(String file) {
    this.file = file;
    
  }

  /**
   * methode servant du menu principal en mode terminal
   * @param event voir l'interface graphique
   */
  @Override
  public void demanderVersion(ActionEvent event) {
    Read lire= new Read();
    typeDePartie = "Terminal";
    String rep = null;
    input.demanderTypePartie(null);
    if(choiceString.equals("1")){
      proposePartieEcriture();
    }else if(choiceString.equals("2")){
      proposePartieContinue();
      modejeux = "écriture";
    }else{
      lsFichiers(null);
      if(choiceString.equals("")){
        affichage.afficherMessage("Archives vide, veuillez lancer une nouvelle partie");
        Test.main(new String[1]);
      }else{
        proposePartieLecture();
      }
    }
  }
  /**
   * methode gérant la suite d'une partie
   */
  public void proposePartieContinue(){
    DeserializeObjects obj = new DeserializeObjects();
      input.suitePartie(null);
      if(choiceString.equals("1")){
        Sauvegarde save = obj.suiteGame("Partie1.ser");
        sogo = save.sogoEtatOrigine();
      }else{
        Sauvegarde save = obj.suiteGame("Partie2.ser");
        sogo = save.sogoEtatOrigine();
      }
      gameFile = new Write((sogo instanceof Sogo)?"1":"2");
      sogo.deroulement(null);
  }
  /**
   * methode gérant la partie en mode ecriture
   */
  public void proposePartieEcriture(){
    demanderNouvellePartie(null);
      if(choiceBoolean){
        demanderSogo(null);
        if(choiceSogo.equals("1")){
          clear("1");
          File f = new File("Tmp1.txt");//suprression
          f.delete();//suppression 
          gameFile = new Write("1");
          gameFile.write("Sogo : simple");
          sogo = new Sogo();
          sogo.setSogo(affichage, input);
          sogo.deroulement(null);
          modejeux = "écriture";
          filename = "";
        }else{
          input.clear("2");
          File f = new File("Tmp2.txt");
          f.delete();
          gameFile = new Write("2");
          gameFile.write("Sogo : Tournant");
          sogo = new SogoTournant(affichage, input);
          sogo.deroulement(null);
          modejeux = "écriture";
        }
      }else{
          Test.main(new String[1]);
          System.exit(0);
      }
  }
  /**
   * methode gérant la partie en mode lecture
   */
 public void proposePartieLecture(){
   Read lire= new Read();
   String rep = null;
   lsFichiers(null);
     if(choiceString.equals("")){
       affichage.afficherMessage("Archives vide, veuillez lancer une nouvelle partie");
       Test.main(new String[1]);
     }else{
       filename = "Archives//"+choiceString;
       rep = lire.readLine(filename);
       if(rep.equals("simple")){
         sogo = new Sogo();
         sogo.setSogo(affichage, input);
       }else{
         sogo = new SogoTournant(affichage,input);
       }
       modejeux ="lecture";
       sogo.deroulement(null);
     }
  }
  /**
   * methode du début, donnant le choix d'une nouvelle partie, le choix de continuer etc...
   * @param event voir la partie Interface
   */
  public void demanderTypePartie(ActionEvent event){
    Affichage affichage = new Terminal();
    Scanner sc = new Scanner(System.in);
    String reponse = "";
    int count = 0;
    affichage.afficherMessage("Voulez-vous : ");
    affichage.afficherMessage("1. Lancer une nouvelle partie  \n2. Continuer une partie \n3. Lire une partie ");
    do{
      if(count > 0){
        affichage.afficherMessage("Veuillez rentrer un chiffre correct");
      }

      reponse = sc.nextLine();

      if(reponse.equals("1") || reponse.equals("2") || reponse.equals("3")){
        // Avant return reponse;
        choiceString = reponse;
        return;
      }

      count++;
    } while(true);
  }
  /**
   * Demande à l'utilisateur s'il souhaite faire une nouvelle partie
   * @param affichage l'affichage pour les messages et autres à l'écran
   * @param file le fichier où on écrit la partie (ou on la lit)
   * @return true si le joueur veut faire une nouvelle partie, false sinon
   */
  public void demanderNouvellePartie(ActionEvent event){
    Scanner sc = new Scanner(System.in);
    Affichage affichage = new Terminal();
    int count = 0;
    String reponse = "";
    clear(file);
    affichage.afficherMessage("Voulez-vous commencer une nouvelle partie ? (oui/non)");

    do{
      if(count > 0) {
        affichage.afficherMessage("Veuillez taper oui ou non");
      }
      reponse = sc.nextLine();
      count++;
    } while(!reponse.equals("oui") && !reponse.equals("non"));

    if(reponse.equals("oui")){
      //Avant return true
      choiceBoolean = true;
    }else{
      //Avant return false;
      choiceBoolean = false;
    }
  }

   /**
    * Demande à l'utilisateur à quel Sogo il souhaite jouer
    * @param event
    * @return le sogo auquel il veut joueur
    */
  public void demanderSogo(ActionEvent event){
    Affichage affichage = new Terminal();
    Scanner sc = new Scanner(System.in);
    String reponse = "";
    int count = 0;
    affichage.afficherMessage("Choisissez un Sogo : ");
    affichage.afficherMessage("1. Sogo Simple \n2. Sogo tournant");
    do{
      if(count > 0){
        affichage.afficherMessage("Veuillez rentrer un chiffre correct");
      }

      reponse = sc.nextLine();

      if(reponse.equals("1") || reponse.equals("2")){
        //Avant return reponse;
        choiceSogo =  reponse;
        return;
      }

      count++;
    } while(true);
  }
  /**
    * Demande à l'utilisateur s'il souhaite faire une nouvelle partie
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @return true si le joueur veut faire une nouvelle partie, false sinon
    */
  public void clear(String file){
    File f = new File("Partie"+file+".ser");
    f.delete();
  }
  /**
   *fonction servant d'intermédiaire avec la fonction recupererCoordonnees
  */
  public Coordonnees donneCoordonnees(MouseEvent event,Joueur j){
    recupererCoordonnees(event,j);
    return coordschoice;
  }
  /**
    * Renvoie les coordonnees du pilier sur lequel le joueur souhaite ajouter son pion
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param j le joueur à qui on demande si il veut faire un quart de tour
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return les coordonnées du coup joué par le joueur
    */
  public void recupererCoordonnees(MouseEvent even,Joueur j){
    String str = "";
    String[] str1 = null;
    Scanner sc = new Scanner(System.in);
    Read lire = new Read();
    int count = 0;
    if(modejeux != null && modejeux.equals("lecture")){
      str1 = (lire.readLine(filename)).split(",");
      //Avant return(new Coordonnees(Integer.parseInt(str1[0]),Integer.parseInt(str1[1])));
      coordschoice = new Coordonnees(Integer.parseInt(str1[0]),Integer.parseInt(str1[1]));
      return;
    }
    do{
      if(count > 0){
        affichage.afficherMessage("Coordonnees invalides, recommencez !/quitter");
      } else {
        affichage.afficherMessage("Veuillez rentrer les coordonnees pour placer votre pion(ex : b2)/quitter");
      }

      str = sc.nextLine();
      count++;
    } while(!verifCoord(str));

    if(str.length() == 2){
      gameFile.write(j.getNom()+" : "+conversCoord(str).getX()+"," + conversCoord(str).getY());
      //Avant return conversCoord(str);
      coordschoice   = conversCoord(str);
      return;
    }
    //Avant return(new Coordonnees(-1,-1));
    coordschoice = new Coordonnees(-1,-1);
    return;

  }
  /**
   *fonction servant d'intermédiaire avec la fonction demanderJoueur
  */
  public Joueur buildPlayer(ActionEvent event){
    demanderJoueur(event);
    return joueurTmp;
  }
  /**
    * demande le type de joueur(humain ou IA)
    * @param event voir la partie graphique
    */
  public void demanderJoueur(ActionEvent event){
    int numJoueur = ((choiceInt%2==0)?1:2);
    choiceInt++;
    int typeJoueur = demanderHumainOuIA(event);
    if(typeJoueur == 0){ //Humain
      Joueur j = new Humain(input.recupererNom(affichage, numJoueur,gameFile,modejeux, filename), new ScannerInput());
      mem += j.getNom() + " ";
      curs++;
      afficheTypePartieHumainVS();
      joueurTmp = j;
      return;
    } else { //IA
      int profondeur = demanderProfondeur(affichage,gameFile,modejeux,filename);
      int typeComportement = demanderTypeComportement(affichage,gameFile,modejeux,filename);
      Joueur j = new IA(input.recupererNom(affichage, numJoueur, gameFile,modejeux, filename)
          , profondeur, typeComportement);
      mem += j.getNom()+" ";
      curs++;
      afficheTypePartieIAVS();
      joueurTmp = j;
      return;
    }
  }
  /**
   * methode permettant d'afficher le type de partie en cours au tout début
   * IA VS ...
   */
  public void afficheTypePartieIAVS(){
  
      if(curs == 8){//IA VS IA
        String [] tab = mem.split(" ");
        affichage.afficherMessage("Début de partie " + tab[0]+ " VS " + tab[4]);
        affichage.afficherMessage("Joueur1 nom : " + tab[3] + " Profondeur : " + tab[1] + " TypeComportement : " + tab[2]);
        affichage.afficherMessage("Joueur2 nom : " + tab[7] + " Profondeur : " + tab[5] + " TypeComportement : " + tab[6]);
        
        relentiTime(600);
        
        curs = 0;
        mem = "";
      }else if(curs == 6){//Humain VS IA
          String[] tab = mem.split(" ");
          affichage.afficherMessage("Début de partie " + tab[0]+ " VS " + tab[2]);
          affichage.afficherMessage("Joueur1 nom : " + tab[1]);
          affichage.afficherMessage("Joueur2 nom : " + tab[5] + " Profondeur : " + tab[3] + " TypeComportement : " + tab[4]);
          
          relentiTime(600);
          
          curs = 0;
          mem = "";
     }
  }
  /**
   * methode permettant d'afficher le type de partie en cours au tout début
   * Humain VS ...
   */
  public void afficheTypePartieHumainVS(){
    if(curs == 4){//Partie Humain contre Humain
          String[] tab = mem.split(" ");
          affichage.afficherMessage("Début de partie " + tab[0]+ " VS " + tab[2]);
          affichage.afficherMessage(tab[1] +" contre " + tab[3]);
          relentiTime(600);
          curs = 0;
          mem = "";
   }else if(curs == 6){//IA vs Humain
          String[] tab = mem.split(" ");
          affichage.afficherMessage("Début de partie " + tab[0]+ " VS " + tab[4]);
          affichage.afficherMessage("Joueur1 nom : " + tab[3] + " Profondeur : " + tab[1] + " TypeComportement : " + tab[2]);
          affichage.afficherMessage("Joueur2 nom : " + tab[5]);
          
          relentiTime(600);
          
          curs = 0;
          mem = "";
        }
    
  }
  /**
   * methode permettant de faire une pause dans l'execution du programme
   */
  public void relentiTime(int second){
    try{
          Thread.sleep(second);
        }catch(InterruptedException e){
          System.out.println("Erreur du au relentissement de l'execution");
        }
  }
  /**
    * Demande à l'utilisateur s'il veut faire jouer un humain ou une IA
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param numJoueur le numéro du joueur qui sera de ce type
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return un entier correspondant soit à une IA, soit un à humain
    */
  public int demanderHumainOuIA(ActionEvent event){
  int numJoueur = ((choiceInt%2==0)?1:2);
    Scanner sc = new Scanner(System.in);
    boolean surete = false;
    String reponse = "";
    if(modejeux != null && modejeux.equals("lecture")){
      Read lire = new Read();
      mem += lire.readLine(filename)+ " ";
      String[] tab = mem.split(" ");
      reponse = ((tab[curs].equals("IA"))?"1":"0");
      curs++;
      return (Integer.parseInt(reponse));
    }
    affichage.afficherMessage("Pour le joueur " + numJoueur + ", voulez-vous qu'il soit Humain(1), ou IA(2) ?");
    do{
      reponse = sc.nextLine();
      if(reponse.equals("1") || reponse.equals("2")){
          surete = true;
      } else {
        affichage.afficherMessage("Veuillez répondre 1 ou 2");
        affichage.afficherMessage("Pour le joueur " + numJoueur + ", voulez-vous qu'il soit Humain(1), ou IA(2) ?");
      }

    } while(!surete);
    if(Integer.parseInt(reponse)-1==0){
      gameFile.write("TypeJoueur : Humain");
    }else{
      gameFile.write("TypeJoueur : IA");
    }
    return (Integer.parseInt(reponse) - 1);
  }

  /**
    * Demande la profondeur d'analyse de coup de l'IA à l'utilisateur
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return 0, 1, 2 ou 3 selon la profondeur d'analyse souhaitée
    */
  public int demanderProfondeur(Affichage affichage,Write file,String mode,String nomfichier){
    Scanner sc = new Scanner(System.in);
    boolean surete = false;
    int reponse = -1;
    if(mode != null && mode.equals("lecture")){
      Read lire = new Read();
      mem  += lire.readLine(nomfichier) + " ";
      String[] tab = mem.split(" ");
      reponse = Integer.parseInt(tab[curs]);
      curs++;
      return reponse;
    }

    affichage.afficherMessage("Quelle profondeur d'analyse voulez-vous ? (de 0 à 10)");
    do {
      try{
        reponse = sc.nextInt();
        if(reponse >= 0 && reponse < 11){
          surete = true;
        } else {
          affichage.afficherMessage("Veuillez rentrer un nombre entre 0 et 10");
        }
      } catch(Exception e){
        affichage.afficherMessage("Veuillez rentrer un nombre");
      }
    } while(!surete);
    file.write("Profondeur : " + reponse);
    return reponse;
  }

  /**
    * Demande le type de comportement voulu pour l'IA à l'utilisateur
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return 1, 2 ou 3 selon le comportement voulu pour l'IA
    */
  public int demanderTypeComportement(Affichage affichage,Write file,String mode,String nomfichier){
    Scanner sc = new Scanner(System.in);
    String[] tab = {"egoïste","agressif","intelligent"};
    boolean surete = false;
    int reponse = -1;
    
    if(mode != null && mode.equals("lecture")){
      Read lire = new Read();
      mem += lire.readLine(nomfichier)+ " ";
      String[] tmp = mem.split(" ");
      reponse = localise(tmp[curs],tab);
      curs++;
      return reponse;
    }
    
    affichage.afficherMessage("Quel type de comportement voulez-vous pour l'IA ? (1 pour \"egoïste\", 2 pour \"agressif\", 3 pour \"intelligent\")");
    
    do {
      try{
        reponse = sc.nextInt();
        if(reponse > 0 && reponse < 4){
          surete = true;
        } else {
          affichage.afficherMessage("Veuillez rentrer 1, 2 ou 3");
        }
      } catch(Exception e){
        affichage.afficherMessage("Veuillez rentrer 1, 2 ou 3");
      }
    } while(!surete);
    file.write("TypeComportement : "+tab[reponse-1]);
    return reponse;
  }
  
  /**
   * methode permettant d'envoyer un indice equivaut à la valeur du comportement voulu
   * 
  */
  public int localise(String chaine,String[]str){
    for(int i = 0; i < str.length; i++){
      if(str[i].equals(chaine)){
        return (i+1);
      }
    }
    return -1;
  }

  /**
    * Renvoie le nom choisi par l'utilisateur pour un joueur
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param joueur le joueur pour qui sera le nom renvoyé
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return le nom du joueur
    */
  public String recupererNom(Affichage affichage, int joueur,Write file,String mode,String nomfichier){
    Scanner sc = new Scanner(System.in);
    String nom = "";

    if(mode != null && mode.equals("lecture")){
      Read lire = new Read();
      return(lire.readLine(nomfichier));
    }
    affichage.afficherMessage("Joueur " + joueur + " : quel nom voulez-vous avoir ?");
    nom = sc.nextLine();
    file.write("Joueur" + joueur + "nom : " + nom);
    return nom;
  }
  
  /**
   * methode proposant au joueur s'il veut archivé une partie qu'il a joué
   * @param event voir la partie graphique
   */
  public void archiverPartie(ActionEvent event){
    Scanner sc = new Scanner(System.in);
    int count = 0;
    String reponse = "";

    affichage.afficherMessage("Voulez-vous archiver cette partie ? (oui/non)");

    do{
      if(count > 0) {
        affichage.afficherMessage("Veuillez taper oui ou non");
      }

      reponse = sc.nextLine();
      count++;
    } while(!reponse.equals("oui") && !reponse.equals("non"));

    if(reponse.equals("oui")){
      confirmation(event);
      return;
    }
    else return;
  }

  /**
    * Demande une confirmation à l'utilisateur
    * Il répond 'oui' ou 'non'
    * @param event voir la partie graphique
    */
  public void confirmation(ActionEvent event){
  String nomfichier = giveFileName();
    File f = new File(gameFile.getName());
    f.renameTo(new File("Archives//"+nomfichier+".txt"));
    return;
  }
  /**
   * Creation du repertoire Archive
   * @return le repertoire existant ou le repertoire nouvellement créer 
   */
  public File creatRepertoire(){
    File repertoire = new File("Archives");
    if (!repertoire.exists()) {
        try{
          repertoire.mkdir();
        }catch(SecurityException se){
          System.out.println("Erreur lors de la création du dossier Archives");
        }        
      }
    return repertoire;
  }
  /**
   * permet de gérer un nom de fichier donner par un joueur
   */
  public String giveFileName(){
    boolean surete = false;
    Scanner sc = new Scanner(System.in);
    String nomfichier = "";
    String reponse = "";
    File[] files=creatRepertoire().listFiles();
    boolean existe = false;
    int count = 0;
    
    do{
        affichage.afficherMessage("Veuillez donner un nom à votre fichier");
        nomfichier = sc.nextLine();
        do{
          if(count > 0){
            affichage.afficherMessage("Veuillez taper oui ou non");
          }else{
            for(int i = 1; i < files.length; i++){
              if((nomfichier+".txt").equals(files[i].getName())){
                existe = true;
              }
            }
            if(!existe){
              affichage.afficherMessage("Voulez-vous vous appeler votre fichier "+ nomfichier +" ? (oui/non)");
              reponse = sc.nextLine();
            }else{
              affichage.afficherMessage("Le nom voulu existe déjà, veuillez choisir un autre nom");
              affichage.afficherMessage("Veuillez donner un nom à votre fichier");
              nomfichier = sc.nextLine();
              affichage.afficherMessage("Voulez-vous vous appeler votre fichier "+ nomfichier +" ? (oui/non)");
              reponse = sc.nextLine();
            }
          }
          count++;
     }while(!reponse.equals("oui") && !reponse.equals("non"));
    }while(!surete);
    return(nomfichier);
  }
  
  
  /**
   * methode servant d'intermédiare à la methode demanderNbQuartDeTour
   * @param event voir partie graphique
   * @param j correspond au joueur actuel
   */
  public int demandeQuartDeTour(MouseEvent event,Joueur j){
    demanderNbQuartDeTour(event,j);
    return nbQuartDeTour;
  }
  /**
    * Demander au joueur s'il veut effectuer un quart de tour des piliers du centre du plateau
    * @param affichage l'affichage pour les messages et autres à l'écran
    * @param file le fichier où on écrit la partie (ou on la lit)
    * @param j le joueur à qui on demande si il veut faire un quart de tour
    * @param mode le mode (lecture ou écriture) du fichier
    * @param nomfichier nom du fichier où on écrit la partie (ou qu'on la lit)
    * @return le nombre de quart de tour que le joueur souhaite effectuer (0 ou 1)
    */
  public void demanderNbQuartDeTour(MouseEvent event,Joueur j){
    Scanner sc = new Scanner(System.in);
    String reponse = "";
    Read lire = new Read();
    nbQuartDeTour = -1;
    int count = 0;
    if(modejeux != null && modejeux.equals("lecture")){
      nbQuartDeTour = Integer.parseInt(lire.readLine(filename));
      return;
    }
    affichage.afficherMessage("Voulez-vous tourner le plateau d'un quart de tour ? (sens horaire) (oui/non)");
    do{
      if(count > 0){
        affichage.afficherMessage("Veuillez entrer 'oui' ou 'non'");
      }
      reponse = sc.nextLine();
      count++;
    } while(!reponse.equals("oui") && !reponse.equals("non"));
    if(reponse.equals("oui")){
      nbQuartDeTour = 1;
    } else {
      nbQuartDeTour = 0;
    }
    gameFile.write(j.getNom()+" nbQuartDeTour : "+ nbQuartDeTour);
    return;
  }

  /**Vérifie la cohérence des coordonnées str
    *@param correspond aux coordonnées 
    */
  public boolean verifCoord(String str){
    boolean verifNum = false;
    boolean verifAlph = false;
    if(str.equals("quitter")){
      return true;
    }
    try{
      verifNum = 'a'<= str.charAt(0)&&str.charAt(0) <= 'd';
      verifAlph = 1 <= Integer.parseInt(str.charAt(1)+"") && Integer.parseInt(str.charAt(1)+"") <= 4;
    }catch(Exception e){}

    return(str.length() == 2 && verifAlph && verifNum);
  }

  /**Effectue la conversion des coordonnées str
    *en valeur numérique
    */
  public Coordonnees conversCoord(String str){
    int x = Character.getNumericValue(str.charAt(0))-10;
    int y = Integer.parseInt(str.charAt(1)+"")-1;
    return(new Coordonnees(x,y));
  }
  /**
   * methode affichant la liste des fichiers du répertoire Archives
   * @param files liste des fichiers d'Archives
   */
  public void liste(File[] files, Affichage affichage){
    for(int i = 1; i < files.length; i++){
      affichage.afficherMessage(files[i].getName());
    }
  }
  
  /**methode cherchant si le nom voulu existe dans le répertoire Archives
   * @param files tableau contenant les fichiers contenu dans le répertoire Archives
   * @param reponse correspond au nom voulu par le joueur
   */
  public boolean repCorrect(File[] files,String reponse){
    for(int i = 1; i < files.length; i++){
      if((reponse).equals(files[i].getName())){
        return true;
      }
    }
    return false;
  }
  /**
   * methode listant les fichiers contenu dans le repertoire Archives
   * @param utulisé dans la partie graphique
   */
  public void lsFichiers(ActionEvent event){
    Affichage affichage = new Terminal();
    Scanner sc = new Scanner(System.in);
    String reponse = "";
    File repertoire = new File("Archives");
    File[] files  =repertoire.listFiles();
      
    int count = 0;
    if(files.length == 1){choiceString = reponse;return;}
      affichage.afficherMessage("Veuillez choisir un fichier : /quitter");
      liste(files,affichage);
    do{
        if(count > 0){
          affichage.afficherMessage("Veuillez rentrer un nom correct/quitter");
        }
        reponse = sc.nextLine();
        if(repCorrect(files,reponse)){choiceString =  reponse; return;}
        if(reponse.equals("quitter")){System.exit(0);}
        count++;
    } while(true);
  }
  /**
   * Methode offrant au joueur la possibilité de pouvoir continuer une partie
   * @param event utilisé dans la partie interface graphique
   */
  @Override
  public void suitePartie(ActionEvent event) {
  Affichage affichage = new Terminal();
  Scanner sc = new Scanner(System.in);
  String reponse = "";
  int count = 0;
  affichage.afficherMessage("Voulez-vous : ");
  affichage.afficherMessage("1.Continuer  une partie Sogo simple \n2. Continuer une partie  Sogo tournant");
  do{
      if(count > 0){
    affichage.afficherMessage("Veuillez rentrer un chiffre correct");
    affichage.afficherMessage("1.Continuer  une partie Sogo simple \n2. Continuer une partie  Sogo tournant");
      }

      reponse = sc.nextLine();

      if(reponse.equals("1") || reponse.equals("2")){
    choiceString =  reponse;
    return;
      }

      count++;
  } while(true);
  }
}
