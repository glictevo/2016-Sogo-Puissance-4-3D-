package application;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * classe permettant la déserialisation d'un objet
 */
class DeserializeObjects{
	/**
	    * methode permmettant la deserialisation d'un objet serialiser
	    * en interface la class intermédiare Sauvegarde est utilisé pour la déserialisation
	    * @param nomfichier faisant référence au fichier contenant l'objet serialisé
	    * @return Renvoie l'objet Sogo deserialiser//Sauvegarde
	    */
	public Sauvegarde suiteGame(String nomfichier){
		try{
			FileInputStream file = new FileInputStream(nomfichier);
			ObjectInputStream obj = new ObjectInputStream(file);
			return((Sauvegarde)(obj.readObject()));
		}catch(IOException e){
			ScannerInput partieTerminal = new ScannerInput();
			Listener partieInterface = new Listener();
			if(partieTerminal.getTypeDePartie().equals("Interface")){
				Stage stage = partieInterface.getStage();
		    	Node n = stage.getScene().lookup("#ContinuePartieCommunication");
		    	((Label)n).setVisible(true);
		    	((Label)n).setText("Aucune partie en cours");
			}else{
				System.out.println("Aucune partie en cours");
				Test tmp = new Test();
				tmp.main(new String[1]);
			}
		}catch(ClassNotFoundException e){
			System.out.println("Classe Sogo non trouvé");
		}
		return (null);
		
	}
}