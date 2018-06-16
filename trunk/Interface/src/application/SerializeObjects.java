package application;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
/**
 *classe permettant la serialisation d'un objet
*/
class SerializeObjects{
	/**
	*un @link nomtmp permet d'identifier un objet
	*/
	private String nomtmp;
	/**
	 * construction de l'identifiant d'un objet qui va être serialiser
	 * @param nomtmp correspond à l'identifiant de l'objet
	 */
	public SerializeObjects(String nomtmp){
		this.nomtmp = nomtmp;
	}
	/**
	 * methode servant à serialiser l'objet
	 * @param sogo l'objet à serialiser
	 */
	public void saveGame(Sauvegarde sogo){
		try{
			FileOutputStream file = new FileOutputStream("Partie"+this.nomtmp+".ser");
			ObjectOutputStream obj = new ObjectOutputStream(file);
			obj.writeObject(sogo);
			obj.close();
		}catch(IOException e){
			System.out.println(e.toString());
			System.out.print("Fichier inaccessible");
		}
	}

	
}