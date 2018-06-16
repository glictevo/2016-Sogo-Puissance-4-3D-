import java.io.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args){
        Write gameFile = null;
        Read lire= new Read();
        Affichage affichage = new Terminal();
        Input input = new ScannerInput();
        String nomfichier = null;
        Sogo sogo = null;

        if(listener.demanderVersion(affichage).equals("1")){
            String rep = input.demanderTypePartie(affichage);
            if(rep.equals("1")){
                if(input.demanderNouvellePartie(affichage,"")){
                    rep = input.demanderSogo(affichage);
                    if(rep.equals("1")){
                        input.clear("1");
                        File f = new File("Tmp1.txt");
                        f.delete();
                        gameFile = new Write("1");
                        gameFile.write("Sogo : simple");
                        sogo = new Sogo(affichage, input);
                        sogo.deroulement(gameFile,"écriture","");
                    }else{
                        input.clear("2");
                        File f = new File("Tmp2.txt");
                        f.delete();
                        gameFile = new Write("2");
                        gameFile.write("Sogo : Tournant");
                        sogo = new SogoTournant(affichage, input);
                        sogo.deroulement(gameFile,"écriture","");
                    }
                }else{
                    main(args);
                }
            }else if(rep.equals("2")){
                DeserializeObjects obj = new DeserializeObjects();
                if(input.suitePartie(affichage).equals("1")){
                    sogo = obj.suiteGame("Partie1.ser");
                }else{
                    sogo = obj.suiteGame("Partie2.ser");
                }
                gameFile = new Write((sogo instanceof Sogo)?"1":"2");
                sogo.deroulement(gameFile,"écriture","");
            }else{
                nomfichier = input.lsFichiers(affichage);
                if(nomfichier.equals("")){
                    affichage.afficherMessage("Archives vide, veuillez lancer une nouvelle partie");
                    main(args);
                }else{
                    nomfichier = "Archives//"+nomfichier;
                    rep = lire.readLine(nomfichier);
                    if(rep.equals("simple")){
                        sogo = new Sogo(affichage,input);
                    }else{
                        sogo = new SogoTournant(affichage,input);
                    }
                    sogo.deroulement(null,"lecture",nomfichier);
                }
            }
        }else{
            //dans la version interface
            Application.launch(Test.class, args);

        }
    }

    public void start(final Stage primaryStage) {

        final Write[] gameFile = {null};
        Read lire= new Read();
        Affichage affichage = new Terminal();
        Input input = new ScannerInput();
        String nomfichier = null;
        final Sogo[] sogo = {null};

        Group root1 =new Group();

        Text txt1 = new Text("Que voulez vous :");
        Button btnNvPartie = new Button("Nouvelle partie");



        Button btnContinuer = new Button("Continuer partie");
        Button btnLire = new Button("Lire une partie");
        btnNvPartie.setMaxWidth(Double.MAX_VALUE);
        btnContinuer.setMaxWidth(Double.MAX_VALUE);
        btnLire.setMaxWidth(Double.MAX_VALUE);
        VBox box = new VBox();
        box.setSpacing(10);
        box.setPadding(new Insets(50, 50, 50, 90));
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(txt1, btnNvPartie, btnContinuer, btnLire);
        root1.getChildren().add(box);

        Group root2 =new Group();

        Text txt2 = new Text("Choisissez un Sogo pour la nouvelle partie :");
        Button btnSogoSimple = new Button("Sogo Simple");
        Button btnSogoTournant = new Button("Sogo tournant");
        btnSogoSimple.setMaxWidth(Double.MAX_VALUE);
        btnSogoTournant.setMaxWidth(Double.MAX_VALUE);
        VBox box2 = new VBox();
        box2.setSpacing(10);
        box2.setPadding(new Insets(70, 50, 50, 15));
        box2.setAlignment(Pos.CENTER);
        box2.getChildren().addAll(txt2, btnSogoSimple, btnSogoTournant);
        root2.getChildren().add(box2);

        Group root3 =new Group();

        Text txt3 = new Text("Quel type de partie aviez vous commencé ? :");
        Button btnContSogoSimple = new Button("Sogo Simple");
        Button btnContSogoTournant = new Button("Sogo tournant");
        btnContSogoSimple.setMaxWidth(Double.MAX_VALUE);
        btnContSogoTournant.setMaxWidth(Double.MAX_VALUE);
        VBox box3 = new VBox();
        box3.setSpacing(10);
        box3.setPadding(new Insets(70, 50, 50, 15));
        box3.setAlignment(Pos.CENTER);
        box3.getChildren().addAll(txt3, btnContSogoSimple, btnContSogoTournant);
        root3.getChildren().add(box3);

        primaryStage.setTitle("Sogo!");
        primaryStage.setScene(new Scene(root1,300, 275));
        primaryStage.show();
        //primaryStage.hide();


        btnNvPartie.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {


                primaryStage.setScene(new Scene(root2,300, 275));

            }
        });

        btnContinuer.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {


                primaryStage.setScene(new Scene(root3,300, 275));

            }
        });

        btnSogoSimple.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {


                input.clear("1");
                File f = new File("Tmp1.txt");
                f.delete();
                gameFile[0] = new Write("1");
                gameFile[0].write("Sogo : simple");
                sogo[0] = new Sogo(affichage, input);
                sogo[0].deroulement(gameFile[0],"écriture","");

            }
        });

        btnSogoTournant.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {


                input.clear("2");
                File f = new File("Tmp2.txt");
                f.delete();
                gameFile[0] = new Write("2");
                gameFile[0].write("Sogo : Tournant");
                sogo[0] = new SogoTournant(affichage, input);
                sogo[0].deroulement(gameFile[0],"écriture","");

            }
        });

        btnContSogoSimple.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {


                DeserializeObjects obj = new DeserializeObjects();
                sogo[0]= obj.suiteGame("Partie1.ser");
                gameFile[0] = new Write((sogo instanceof Sogo[])?"1":"2");
                sogo[0].deroulement(gameFile[0],"écriture","");

            }
        });

        btnContSogoTournant.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {


                DeserializeObjects obj = new DeserializeObjects();
                sogo[0]= obj.suiteGame("Partie2.ser");
                gameFile[0] = new Write((sogo instanceof Sogo[])?"1":"2");
                sogo[0].deroulement(gameFile[0],"écriture","");

            }
        });


    }
}
