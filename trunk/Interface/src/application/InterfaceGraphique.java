package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * class gérant la partie interface graphique
 */
public class InterfaceGraphique implements Affichage{

  public void afficherPlateau(Plateau plateau){



  }

  public void afficherMessage(String str){
	  
  }
  /**
   * methode servant pour l'affichage d'une page fxml
   * @param str correspond au nom de la page
   * @param stage correspond à la scene
   * @param largeur correspond à la largeur de la page 
   * @param hauteur correspond à la hauteur de la page
   */
  public void afficherPageInterface(String str,Stage stage,int largeur, int hauteur){
	  Listener SaveStage = new Listener();
	  try {
			Parent root  = FXMLLoader.load(getClass().getResource("/application/FXMLFILE/"+str+".fxml"));
			Scene scene = new Scene(root,largeur,hauteur);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setScene(scene);
			stage.show();
			SaveStage.setStage(stage);
		} catch(Exception e) {
			e.printStackTrace();
		}
  }

}
