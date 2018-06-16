package application;

import java.io.*;
import java.util.*;
/**
 * classe servant pour les écritures dans un fichier 
 */
class Write{
	private PrintWriter file;
	private String nomtmp;
	/**
	 * construction d'un objet write avec son identifiant
	 * @param nomtmp correspond à l'identiant du fichier
	 */
	public Write(String nomtmp){
		this.nomtmp = nomtmp;
	}
	/**
	 * methode permettant l'écriture d'une des chaines des caractères dans un fichier
	 * @param str correspond à la ligne écrite
	 */
	public void write(String str){
		File f = new File("Tmp"+this.nomtmp+".txt");
		try{
			this.file = new PrintWriter(new BufferedWriter(new FileWriter(f,true)));
			this.file.println(str);
			this.file.close();
		}catch(IOException e){
			System.out.println ("Erreur lors de la lecture : " + e.getMessage());
		}
		//this.file.println(str);
	}
	/**
	 * getter retournant le nom d'un fichier
	 */
	public String getName(){
		return("Tmp"+this.nomtmp+".txt");
	}
}