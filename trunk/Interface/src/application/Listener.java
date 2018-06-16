package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.sun.javafx.geom.Rectangle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;


/**
 * Classe qui s'occupe de l'interface graphique jusqu'au démarage de la partie.
 * Hérite de Input, tout comme Scanner pour le terminal.
 */
public class Listener implements Input{
	/**
	 *@link curs servant de repère pour le stockage des données des joueurs
	 *@link mem stock les données des joueurs avant leurs affichage au début d'une partie en mode lecture
	 *@link gameFile écriture d'une partie dans un fichier
	 *@link filename correspond au nom du fichier
	 *@link lecture, ecriture ou continue
	 *@link typeDePartie Terminal ou Interface
	 *@link joueurTmp Joueur en cours de création
	 *@link coordschoice variable utilisé pour les choix des coordonnées d'un joueur
	 *@link playerName1 stock le nom du joueur1
	 *@link playerName2 stock le nom du joueur2
	 *idem pour profondeur1, comportement1 profondeur2 et comportement2
	 */
	private static int curs = 0;
	private static String mem = "";
	


	private static Write gameFile;//Écriture d'une partie dans un fichier
	private static String filename;//correspond au nom du fichier
	private static String modejeux ="ecriture";//lecture, ecriture ou continue
	private static String typeDePartie;//Terminal ou Interface

	
	private static Affichage affichage;
	private static Input input;
	
	private static int choiceInt = 0;
	
	private static Joueur joueurTmp;//Joueur en cours de création
	private static Coordonnees coordschoice;//variable utilisé pour les choix des coordonnées d'un joueur
	
	private static Sogo sogo;
	
	private static String playerName1 = null;
	
	private static String playerName2 = null;
	
	@FXML
	private static String profondeur1 = null;
	@FXML
	private static String comportement1 = null;
	@FXML
	private static String profondeur2 = null;
	@FXML
	private static String comportement2 = null;
	
	@FXML
	private Button Continue2;

	@FXML
	private Label ContinuePartieCommunication;
	
	@FXML
    private TextArea lectureLSFichier;
	@FXML
	private Label lectureCommunication;
	@FXML
	private Button lectureValidation;
	@FXML
    private TextField lectureZoneDeSaisie;
	
	
	//utiliser pour l'update de l'interface
	private static int update = 0;

	private static Stage stage;
	
	private static Parent root;
	
	private static int nbQuartDeTour = -1;

	/**
	 * Getter du type de Sogo (simple ou tournant)
	 * @return une instance de Sogo
	 */
	public Sogo getSogo(){
		return sogo;
	}
	/** Getter du fichier associé
	 *
	 * @return le fichier associé
	 */
	public Write getFile(){
		return gameFile;
	}
	/** Getter du mode de jeux (lecture, écriture ou continue)
	 *
	 * @return Le mode de jeux
	 */
	public String getModeJeux(){
		return modejeux;
	}
	/** Getter du nom du fichier associé
	 *
	 * @return Nom du fichier
	 */
	public String getFileName(){
		return filename;
	}
	/** Setter de la racine (JavaFX)
	 *
	 * @param root
	 */
	public void setRoot(Parent root){
		this.root = root;
	}
	/** Getter du "Stage" ou scène actuelle (JavaFX)
	 *
	 * @return "Stage" ou scène actuelle
	 */
	public Stage getStage(){
		return stage;
	}
	/**
	 * garde en mémoire la scene actuelle
	 * stage correspond à la scene
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	/**
	 * getter utilisé pour le Sogo tournant
	 * @return int le nombre de tour
	 */
	public int getNbQuartDeTour(){
		return nbQuartDeTour;
	}
	/**
	 * setter utilisé pour le Sogo tournant
	 * modifie le nombre de tour
	 */
	public void setNbQuartDeTour(int nbQuartDeTour){
		this.nbQuartDeTour = nbQuartDeTour;
	}
	/**
	 * Permet de demander ce qu'on veut faire : démarrer une nouvelle partie,
	 * continuer une partie en cours, lire une partie.
	 * @param event
	 */
	public void demanderTypePartie(ActionEvent event){
		Node  source = (Node)  event.getSource(); 
		Stage stage = (Stage) source.getScene().getWindow();
		String choice = ((Button) event.getSource()).getText();
		
		Affichage affichage = new InterfaceGraphique();
		switch(choice){
			case "Start" :
				((InterfaceGraphique)affichage).afficherPageInterface("menu3",stage, 700,400);
				break;
			case "Read":
				((InterfaceGraphique)affichage).afficherPageInterface("lecture",stage, 700,400);
				break;
			case "Continue" : 
				((InterfaceGraphique)affichage).afficherPageInterface("continue",stage, 700,400);
				break;
		}
		if(choice.equals("Read")){
			lsFichiers(null);
		}
		
	}
	/**
	 * Permet de demander le type de Sogo, simple ou tournant.
	 * @param event
	 */
	public void demanderSogo(ActionEvent event){
		Node  source = (Node)  event.getSource(); 
		Stage stage = (Stage) source.getScene().getWindow();
		String choice = ((Button) event.getSource()).getText();
		Affichage affichage = new InterfaceGraphique();
		initialiseParametreSogo(choice);
		((InterfaceGraphique)affichage).afficherPageInterface("players",stage, 700,400);
		
	}
	/**
	 * Initialise les données du début
	 * de patie
	 * @param typeSogo fait reférence au Sogo choisit
	 * par le joueur
	 */
	public void initialiseParametreSogo(String typeSogo){
		switch(typeSogo){
			case "SOGO SIMPLE" :
				clear("3");//Suppression de la partie sauvegardée
                File f1 = new File("Tmp3.txt");//Remet l'écriture de la partie à 0
                f1.delete();//Remet l'écriture de la partie à 0 
                gameFile = new Write("3");//Création d'un nouveau fichier pour la sauvegarde
                gameFile.write("Sogo : simple");
                sogo = new Sogo();
                sogo.setSogo(affichage, input);
                break;
		   case "SOGO TOURNANT" :
			   clear("4");
               File f2 = new File("Tmp4.txt");
               f2.delete();
               gameFile = new Write("4");
               gameFile.write("Sogo : Tournant");
               sogo = new SogoTournant(affichage, input);
               break;
		}
	}
	/**
	  * methode servant d'intermediare 
	  * à la methode demanderJoueur
	  */
	public Joueur buildPlayerInter(ActionEvent event){
		demanderJoueur(event);
		return joueurTmp;
	}
	/**
	  * methode servant à la création d'un joueur
	  * @param event faisant reférence aux boutons de validation de la page players.fxml
	  */
	public void demanderJoueur(ActionEvent event){
		if(modejeux != null && modejeux.equals("lecture")){
			construitJoueurModeLecture(event);
			return;
		}
		String choice = ((Button) event.getSource()).getId();
		Node source = null;
		if(choice.equals("validationH1")){
			recupereJoueurDonnees(choice,1);
		}else if(choice.equals("validationH2")){
			recupereJoueurDonnees(choice,2);
			return;
		}else if(choice.equals("validationIA1")){
			recupereJoueurDonnees(choice,1);
			return;
		}else if(choice.equals("validationIA2")){
			recupereJoueurDonnees(choice,2);
			return;
		}
		
	}
	/**
	 * methode récupérant les saisies d'un joueur
	 * @param choice correspond au bouton de validation qui a été cliquer
	 * @param numJoueur correspond au numéro du joueur
	 */
	public void recupereJoueurDonnees(String choice, int numJoueur){
		Node source = null;
		if(choice.equals("validationH1")||choice.equals("validationH2")){
			source = stage.getScene().lookup((numJoueur == 1)?"#zoneDeSaisie1":"#zoneDeSaisie2");
			if(choice.equals("validationH1")){
				playerName1 =((TextField)source).getText();
			}else{
				playerName2 =((TextField)source).getText();
			}
			
			gameFile.write("TypeJoueur : Humain");
			gameFile.write((numJoueur == 1) ?"Joueur1" +"nom : "+((numJoueur == 1)?playerName1:playerName2) :"Joueur2" +"nom : " +((numJoueur == 1)?playerName1:playerName2));
			joueurTmp = new Humain((numJoueur == 1)?playerName1:playerName2,new ScannerInput());
			
			((TextField)source).setText("Votre nom");
			((TextField)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#validationH1":"#validationH2");
			((Button)source).setDisable(true);
			return;
		}else if (choice.equals("validationIA1")||choice.equals("validationIA2")){
			String comportementmp = null;
			String profondeurtmp = null;
			source = stage.getScene().lookup((numJoueur == 1)?"#combobox1":"#combobox2");
			profondeurtmp =((ComboBox<String>)source).getValue();
			source = stage.getScene().lookup((numJoueur == 1)?"#combobox3":"#combobox4");
			comportementmp =((ComboBox<String>)source).getValue();
			
			if(choice.equals("validationIA1")){
				profondeur1 = profondeurtmp;
				comportement1 = comportementmp;
			}else{
				profondeur2 = profondeurtmp;
				comportement2 = comportementmp;
			}
			gameFile.write("TypeJoueur : IA");
			gameFile.write("Profondeur : "+profondeurtmp);
			gameFile.write("TypeComportement : "+comportementmp);
			gameFile.write((numJoueur == 1)?"Joueur1" +"nom : " + "Irobot1":"Joueur1" +"nom : " + "Irobot2");
			
			source = stage.getScene().lookup((numJoueur == 1)?"#combobox1":"#combobox2");
			((ComboBox<String>)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#combobox3":"#combobox4");
			((ComboBox<String>)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#validationIA1":"#validationIA2");
			((Button)source).setDisable(true);
			joueurTmp = new IA((numJoueur == 1)?"Irobot1":"Irobot2",Integer.parseInt((numJoueur == 1)?profondeur1:profondeur2),comportement((numJoueur == 1)?comportement1:comportement2));

		}
	}
	/**
	 * methode servant à la  construction d'un joueur en mode lecture
	 *@event associé au bouton read de la page menu2.fml
	 */
	public void construitJoueurModeLecture(ActionEvent event){
		int numJoueur = ((choiceInt%2==0)?1:2);
	    choiceInt++;
	    int typeJoueur = demanderHumainOuIA(event);
	    if(typeJoueur == 0){ //Humain
	      Joueur j = new Humain(recupererNom(affichage, numJoueur,gameFile,modejeux, filename), new ScannerInput());
	      joueurTmp = j;
	      return;
	    } else { //IA
	      int profondeur = demanderProfondeur(affichage,gameFile,modejeux,filename);
	      int typeComportement = demanderTypeComportement(affichage,gameFile,modejeux,filename);
	      Joueur j = new IA(recupererNom(affichage, numJoueur, gameFile,modejeux, filename)
	    		  , profondeur, typeComportement);
	      joueurTmp = j;
	      return;
	    }
		
	}
	/**
	 * methode servant pour la partie lecture 
	 * @event associé au bouton valider de la page lecture.fxml
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
	    return 0;
	}
	/**
	 * methode servant pour la partie lecture 
	 * @param affichage servant pour la version terminal voir la classe ScannerInput
	 * @param file associé au fichier de l'ériture de la partie
	 * @param mode: lecture ici
	 * @param nomfichier associé à au fichier en cours de lecture
	 */
	public int demanderProfondeur(Affichage affichage,Write file,String mode,String nomfichier){
	    int reponse = -1;
	    if(mode != null && mode.equals("lecture")){
	      Read lire = new Read();
	      mem  += lire.readLine(nomfichier) + " ";
	      String[] tab = mem.split(" ");
	      reponse = Integer.parseInt(tab[curs]);
	      curs++;
	      return reponse;
	    }
	    return 0;
	}
	/**
	 * methode servant pour la partie lecture 
	 * @param affichage servant pour la version terminal voir la classe ScannerInput
	 * @param file associé au fichier de l'ériture de la partie
	 * @param mode: lecture ici
	 * @param nomfichier associé à au fichier en cours de lecture
	 */
	 public int demanderTypeComportement(Affichage affichage,Write file,String mode,String nomfichier){
		    String[] tab = {"egoïste","agressif","intelligent"};
		    int reponse = -1;
		    if(mode != null && mode.equals("lecture")){
		      Read lire = new Read();
		      mem += lire.readLine(nomfichier)+ " ";
		      String[] tmp = mem.split(" ");
		      reponse = localise(tmp[curs],tab);
		      curs++;
		      return reponse;
		    }
		    return 0;
	 }
	/**
	 * methode servant d'allumage des pions
	 *@param choix associé aux coordonnées de localisation du pion
	 *@param joueurnum identifiant du joeurActuel
	 */
	public void turnPion(String choix,int joueurnum){
		Node n = stage.getScene().lookup("#"+choix);//ref Pions
		((Sphere)n).setOpacity(1);
		((Sphere)n).setMaterial(appliqueTexture(joueurnum+1));
		update();
	}
	/**
	 * methode appliquant une texture en fonction du joueur
	 * @param joueurnum correspond à l'identidiant du joueur
	 */
	public PhongMaterial appliqueTexture(int joueurnum){
		PhongMaterial material = new PhongMaterial();
		if(joueurnum == 1){
			material.setDiffuseColor(Color.DARKBLUE);
			material.setSpecularColor(Color.WHITE);
		}else if (joueurnum == 2){
			material.setDiffuseColor(Color.DARKRED);
			material.setSpecularColor(Color.WHITE);
		}else{
			Image diffuseMap = new Image("/application/Pictures/im.png");
			material.setDiffuseMap(diffuseMap);
		}
		return material;
		
	}
	/**
	 * methode permettant d'effacer les choix d'un joueur
	 * lui offrant l'ooportunité de rentrer des nouvelles données
	 */
	public void recommencer(String choice,Button b1,Button b2,TextField zone,ComboBox<String> combobox1,ComboBox<String> combobox2){
		if(choice.equals("recommencer1")){
			playerName1 = null;
			profondeur1 = null;
			comportement1 = null;
			zone.setEditable(false);
			combobox1.setDisable(true);
			combobox2.setDisable(true);
			b1.setDisable(false);
			b2.setDisable(false);
		}else if(choice.equals("recommencer2")){
			playerName2 = null;
			profondeur2 = null;
			comportement2 = null;
			zone.setEditable(false);
			combobox1.setDisable(true);
			combobox2.setDisable(true);
			b1.setDisable(false);
			b2.setDisable(false);
		}
	}
	/**
	 * methode servant de mise à jour de l'interface graphique
	 */
	public void update(){
		if(update%2 == 0){
			stage.setWidth(1201);
			update += 1;
		}else{
			stage.setWidth(1200);
			update = 0;
		}
	}
	/**
	 * methode permmetant d'effacer le fichier
	 * servant à ma serialisation de l'objet courant
	 */
	public void clear(String file){
		File f = new File("Partie"+file+".ser");
	    f.delete();
	}
	/**
	 * methode permettant de quitter la partie
	 */
	public void exit(){
		System.exit(0);
	}
	/**
	 * methode servant à cotinuer une partie
	 * @event associé au boutn contienue
	 */
	public void replayStart(ActionEvent event){
		if(sogo instanceof SogoTournant){
			((SogoTournant)sogo).reinitialiserPartie();
			this.modejeux = "continue";
		}else{
			sogo.reinitialiserPartie();
			this.modejeux = "continue";
		}
		sogo.deroulement(event);
	}
	/**
	 * methode servant pour archiver une partie
	 * @param event assocé au bouton archive
	 */
	public void archiverPartie(ActionEvent event){
		String choice = ((Button) event.getSource()).getText();
		Node n = null;
		if(choice.equals("Oui")){
			n = stage.getScene().lookup("#archiveOUI");
			((Button)n).setDisable(true);
			n = stage.getScene().lookup("#archiveNON");
			((Button)n).setDisable(true);
			n = stage.getScene().lookup("#zonesaisieArchive");
			((TextField)n).setVisible(true);
			n = stage.getScene().lookup("#archiveMessage");
			((Label)n).setText("Nommez votre fichier");
			((Label)n).setVisible(true);
			n = stage.getScene().lookup("#validerArchive");
			((Button)n).setVisible(true);
		}else{
			replay(event);
		}
	}
	/**
	 * methode verifiant l'existence du nom voulu
	 * dans le repetoire archives
	 * event associé au bouton valider 
	 */
	public void confirmation(ActionEvent event){
		Node n = null;
		n = stage.getScene().lookup("#zonesaisieArchive");
		String nomfichier = ((TextField)n).getText();
	    File repertoire = new File("Archives");
	    if (!repertoire.exists()) {
	  	    try{
	  	    	repertoire.mkdir();
	  	    } catch(SecurityException se){
	  	    	System.out.println("Erreur lors de la création du dossier Archives");
	  	    }        
	   	}
	    File[] files=repertoire.listFiles();
	    boolean existe = false;
	    for(int i = 1; i < files.length; i++){
	    	if((nomfichier+".txt").equals(files[i].getName())){
	    		existe = true;
	        }
	    }
	    if(existe){
	    	n = stage.getScene().lookup("#archiveMessage");
	    	((Label)n).setText("Nom existant, choisissez un autre nom");
	    	return;
	    }
	    File f = new File(gameFile.getName());
	    f.renameTo(new File("Archives//"+nomfichier+".txt"));
	    replay(event);
	}
	/**
	 * methode servent soit de quiter soit continuer une partie
	 * @param event associé au bouton valider de la page archive
	 */
	public void replay(ActionEvent event){
		Node  source = (Node)  event.getSource(); 
		stage = (Stage) source.getScene().getWindow();
		stage.setWidth(700);
		stage.setHeight(400);
    	
		Affichage affichage = new InterfaceGraphique();
		((InterfaceGraphique)affichage).afficherPageInterface("replay",stage, 700,400);
	}
	/**
	 * Demande le nombre de quart de tour voulu (sogo tournant)
	 * @param event associé  aux boutons oui ou non du plateau SogoTournant
	 * @param j le joueur
	 */
	public void demanderNbQuartDeTour(MouseEvent event,Joueur j){
		Read lire = new Read();
		if(modejeux.equals("lecture")){
			nbQuartDeTour = Integer.parseInt(lire.readLine(filename));
		    return;
		}
		Node  source = (Node)  event.getSource();
		String choice = ((Node) event.getSource()).getId();
		if(!(choice.equals("nbQuartDeOui"))&&!(choice.equals("nbQuartDeNon"))){
			Node n = stage.getScene().lookup("#communication");
			((TextArea )n).appendText("Voulez-vous tourner le plateau \nd'un quart de tour ? \n(sens horaire) (oui/non)\n");
			n = stage.getScene().lookup("#nbQuartDeOui");
			((Button)n).setDisable(false);
			((Button)n).setVisible(true);
			n = stage.getScene().lookup("#nbQuartDeNon");
			((Button)n).setDisable(false);
			((Button)n).setVisible(true);
			return;
		}
		if(choice.equals("nbQuartDeOui")){
			nbQuartDeTour = 1;
			gameFile.write(j.getNom()+" nbQuartDeTour : "+ nbQuartDeTour);
			source =  stage.getScene().lookup("#nbQuartDeOui");
			((Button)source).setDisable(true);
			source =  stage.getScene().lookup("#nbQuartDeNon");
			((Button)source).setDisable(true);
		} else {
			nbQuartDeTour = 0;
			gameFile.write(j.getNom()+" nbQuartDeTour : "+ nbQuartDeTour);
			source =  stage.getScene().lookup("#nbQuartDeNon");
			((Button)source).setDisable(true);
			source =  stage.getScene().lookup("#nbQuartDeOui");
			((Button)source).setDisable(true);
		}
    }
    /**
     * methode permettant de revenir en arrière entre chaque page
     * @param event associé aux boutons d'arrière d'une page
     */
	public void backButton(ActionEvent event){
		Node  source = (Node)  event.getSource(); 
		Stage stage = (Stage) source.getScene().getWindow();
		String choice = ((Button) event.getSource()).getText();
		
		Affichage affichage = new InterfaceGraphique();
		switch(choice){
			case "<" :
				((InterfaceGraphique)affichage).afficherPageInterface("menu1",stage, 700,400);
				break;
			case "<<":
				((InterfaceGraphique)affichage).afficherPageInterface("menu2",stage, 700,400);
				break;
			case "<.<" :
				((InterfaceGraphique)affichage).afficherPageInterface("menu2",stage, 700,400);
				break;
			case "<<<" :
				((InterfaceGraphique)affichage).afficherPageInterface("menuHelp",stage, 700,400);
				break;
			case "SOGO SIMPLE" : 
				((InterfaceGraphique)affichage).afficherPageInterface("players",stage, 700,400);
				break;
		}
		
		
	}
	/**
	 * Demande la version du jeu : Terminal ou interface
	 * @param event
	 */
	public void demanderVersion(ActionEvent event){
		Node  source = (Node)  event.getSource(); 
		Stage stage = (Stage) source.getScene().getWindow();
		String choice = ((Button) event.getSource()).getText();
		
		Affichage affichage = new InterfaceGraphique();
		switch(choice){
			case "Interface" :
				((InterfaceGraphique)affichage).afficherPageInterface("menu2",stage, 700,400);
				break;
			case "Terminal":
				Runnable tache =  new MonRunnable();
				Thread thread = new Thread(tache);
				thread.start();
				((Node)(event.getSource())).getScene().getWindow().hide();
				break;
			
		}
	}
	/** active les champs adaptés dans le menu des joeurs
	 *
	 * @param event
	 */
	public void active(MouseEvent event){
		Node  source = (Node) event.getSource(); 
		stage = (Stage) source.getScene().getWindow();
		String choice = ((Region) event.getSource()).getId();
		if(choice.equals("playerHumain1") && playerName1 == null){
			activeZonePLAYERS(choice,1);
		}else if(choice.equals("playerHumain2") && playerName2 == null){
			activeZonePLAYERS(choice,2);
		}else if(choice.equals("playerIA1")&& profondeur1 == null && comportement1 == null){
			activeZonePLAYERS(choice,1);
		}else if(choice.equals("playerIA2")&& profondeur2 == null && comportement2 == null){
			activeZonePLAYERS(choice,2);
		}
	}
	
	public void activeZonePLAYERS(String choice,int numJoueur){
		Node source = null;
		if(choice.equals("playerHumain1")||choice.equals("playerHumain2")){
			//active partie Humain
			source = stage.getScene().lookup((numJoueur == 1)?"#playerHumain1":"#playerHumain2");
			((Button)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#zoneDeSaisie1":"#zoneDeSaisie2");
			((TextField)source).setDisable(false);
			((TextField)source).setEditable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#validationH1":"#validationH2");
			((Button)source).setDisable(false);
			
			//desactive partie IA
			source = stage.getScene().lookup((numJoueur == 1)?"#playerIA1":"#playerIA2");
			((Button)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#combobox1":"#combobox2");
			((ComboBox<String>)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#combobox3":"#combobox4");
			((ComboBox<String>)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#validationIA1":"#validationIA2");
			((Button)source).setDisable(true);
			return;
		}else if(choice.equals("playerIA1")||choice.equals("playerIA2")){
			//active partie IA
			source = stage.getScene().lookup((numJoueur == 1)?"#playerIA1":"#playerIA2");
			((Button)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#combobox1":"#combobox2");
			((ComboBox<String>)source).setDisable(false);
			source = stage.getScene().lookup((numJoueur == 1)?"#combobox3":"#combobox4");
			((ComboBox<String>)source).setDisable(false);
			source = stage.getScene().lookup((numJoueur == 1)?"#validationIA1":"#validationIA2");
			((Button)source).setDisable(false);
			
			//desactive partie Humain
			source = stage.getScene().lookup((numJoueur == 1)?"#playerHumain1":"#playerHumain2");
			((Button)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#zoneDeSaisie1":"#zoneDeSaisie2");
			((TextField)source).setDisable(true);
			source = stage.getScene().lookup((numJoueur == 1)?"#validationH1":"#validationH2");
			((Button)source).setDisable(true);
		}
	}
	/**
	 * Permet de continuer une partie
	 * @param event
	 */
	@FXML
	public void suitePartie(ActionEvent event){
		String choice = ((Button) event.getSource()).getText();
		DeserializeObjects obj = new DeserializeObjects();
		Sauvegarde save = null;
		if(choice.equals("SOGO SIMPLE")){
			save = obj.suiteGame("Partie3.ser");
			gameFile = new Write("3");
	    	
		}else if(choice.equals("SOGO TOURNANT")){
			save = obj.suiteGame("Partie4.ser");
			gameFile = new Write("4");
		}
		if(save != null){
			sogo = save.sogoEtatOrigine();
			modejeux = "continue1";
			sogo.deroulement(event);
		}
	}
	/**
	 * retourne le chiffre associé aux différents comportements
	 * @param comportement
	 * @return
	 */
	public int comportement(String comportement){
		if(comportement.equals("egoïste")){
			return 1;
		}else if(comportement.equals("agressif")){
			return 2;
		}else{
			return 3;
		}
	}
	/**
	 * Renvoie les coordonnées selon l'endroit cliqué
	 * @param event
	 * @param j jouer
	 * @return les coordonnées où on veut jouer
	 */
	public Coordonnees donneCoordonnees(MouseEvent event, Joueur j) {
		if(modejeux.equals("lecture")){
			 recupererCoordonnees(event,j);
			 return coordschoice;
		}
		Node  source = (Node)  event.getSource(); 
		stage = (Stage) source.getScene().getWindow();
		String choice = ((Region) event.getSource()).getId();
		gameFile.write(j.getNom()+" : "+conversCoord(choice).getX()+"," + conversCoord(choice).getY());
	    coordschoice   = conversCoord(choice);
	    return (conversCoord(choice));
	}
	/**
	 * Converti un String en coordonnées
	 * @param str
	 * @return
	 */
	public Coordonnees conversCoord(String str){
	    int x = Character.getNumericValue(str.charAt(0))-10;
	    int y = Integer.parseInt(str.charAt(1)+"")-1;
	    return(new Coordonnees(x,y));
	}
	
	public void recupererCoordonnees(MouseEvent event,Joueur j){
		Read lire = new Read();
	    String[] str1 = (lire.readLine(filename)).split(",");
		coordschoice = new Coordonnees(Integer.parseInt(str1[0]),Integer.parseInt(str1[1]));
		return;
	}

	public String recupererNom(Affichage affichage, int joueur, Write file, String mode,String nomfichier){
		if(mode != null && mode.equals("lecture")){
		      Read lire = new Read();
		      return(lire.readLine(nomfichier));
		}
		return "";
	}
	/**
	 * permet d'afficher la liste des fichiers contenu dans le repertoire archives
	 * @param event associé read de la page men2.fxml
	 */
	@Override
	public void lsFichiers(ActionEvent event) {
		Node n = null;
		File repertoire = new File("Archives");
		File[] files  =repertoire.listFiles();
		int count = 0;
		if(files.length == 1){
			n = stage.getScene().lookup("#lectureCommunication");
			((Label)n).setText("Archives vide");
			n = stage.getScene().lookup("#lectureValidation");
			((Button)n).setDisable(true);
			n = stage.getScene().lookup("#lectureZoneDeSaisie");
			((TextField)n).setDisable(true);
			return;
		}
		liste(files,affichage);
		if(event == null){
			n = stage.getScene().lookup("#lectureCommunication");
			((Label)n).setText("Choisissez un fichier parmis la liste");
			n = stage.getScene().lookup("#lectureValidation");
			((Button)n).setDisable(false);
			n = stage.getScene().lookup("#lectureZoneDeSaisie");
			((TextField)n).setDisable(false);
			return;
		}
		n = stage.getScene().lookup("#lectureZoneDeSaisie");
		String reponse = ((TextField)n).getText();
		if(repCorrect(files,reponse)){
			filename = "Archives//"+reponse;
			Read lire= new Read();
			String rep = lire.readLine(filename);
			if(rep.equals("simple")){
				sogo = new Sogo();
		        sogo.setSogo(affichage, input);
			}else{
		        sogo = new SogoTournant(affichage,input);
		    }
			modejeux ="lecture";
	        sogo.deroulement(event);
		}else{
			n = stage.getScene().lookup("#lectureCommunication");
			((Label)n).setText("Veuillez rentrer un nom correct");
		}
		
	}
	/**
	 * methode utilisé par la méthode lsfichier permettant d'afficher la liste des fichiers
	 * @param files correspond à un tableau des fichiers contenu dans le repertoire Archives
	 * @param affichage sert pour afficher la liste en mode Terminal
	 */
	public void liste(File[] files, Affichage affichage){
		Node n = stage.getScene().lookup("#lectureLSFichier");
		String fileListe = "";
	    for(int i = 1; i < files.length; i++){
	    	fileListe += files[i].getName()+"\n";
	    }
	    ((TextArea)n).setText(fileListe);
	}
	/**
	 * methode verifiant la réponse du joueur
	 * @param files correspond à un tableau des fichiers contenu dans le repertoire Archives
	 * @param reponse correspond à la reponse du joueur
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
	 * methode servant pour l'affaichage des pages d'aide pour le jeu 
	 * event associé aux boutons d'aide
	 */
	@FXML
	public void aide(MouseEvent event){
		Node  source = (Node)  event.getSource(); 
		Stage stage = (Stage) source.getScene().getWindow();
		String choice = ((Button) event.getSource()).getText();
		
		Affichage affichage = new InterfaceGraphique();
		switch(choice){
			case "SOGO SIMPLE" :
				((InterfaceGraphique)affichage).afficherPageInterface("SogoSimple",stage, 700,450);
				break;
			case "SOGO TOURNANT":
				((InterfaceGraphique)affichage).afficherPageInterface("SogoTournant",stage, 700,450);
				break;
			case "Sogo aide" : 
				((InterfaceGraphique)affichage).afficherPageInterface("menuHelp",stage, 700,400);
				break;
		}
	}
	@Override
	public int demandeQuartDeTour(MouseEvent event, Joueur j) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int localise(String chaine,String[]str){
		return 0;
	}
	@Override
	public void demanderNouvellePartie(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
