package application;

import java.io.Serializable;

public class Sauvegarde implements Serializable{
	private String typeSogoEnCours;
	private Plateau plateau;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurActuel;
    private Affichage affichage;
    private Input input;
    private int nombreDePions;
    private int nbPartiesGagneesJoueur1 = 0;
    private int nbPartiesGagneesJoueur2 = 0;

	/**
	 * methode permmettant la serialisation d'un objet de type Sogo
	 * @param typeSogoEnCours
	 * @param plateau
	 * @param joueur1
	 * @param joueur2
	 * @param joueurActuel
	 * @param affichage
	 * @param input
	 * @param nombreDePions
	 * @param nbPartiesGagneesJoueur1
     * @param nbPartiesGagneesJoueur2
     */
    public void setSauveOBJ(String typeSogoEnCours,Plateau plateau, Joueur joueur1, Joueur joueur2, Joueur joueurActuel
    		,Affichage affichage,Input input,int nombreDePions, int nbPartiesGagneesJoueur1, int nbPartiesGagneesJoueur2){
    	this.typeSogoEnCours = typeSogoEnCours;
    	this.plateau = plateau;
    	this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.joueurActuel = joueurActuel;
		this.affichage = affichage;
		this.input = input;
		this.nombreDePions = nombreDePions;
		this. nbPartiesGagneesJoueur1 =  nbPartiesGagneesJoueur1;
    	this.nbPartiesGagneesJoueur2 = nbPartiesGagneesJoueur2;
    }
    /**
	    * methode remettant un Sogo à son état d'origine
	    * @return Renvoie l'objet Sogo 
	    */
    public Sogo sogoEtatOrigine(){
    	Sogo sogo = null;
    	if(this.typeSogoEnCours.equals("Simple")){
    		sogo = new Sogo();
    	}else{
    		sogo = new SogoTournant(this.affichage,this.input);
    	}
    	sogo.setSauveOBJ(this.plateau, this.joueur1, this.joueur2, this.joueurActuel, this.affichage, 
    			this.input, this.nombreDePions, this.nbPartiesGagneesJoueur1, this.nbPartiesGagneesJoueur2);
    	return sogo;
    }
}
