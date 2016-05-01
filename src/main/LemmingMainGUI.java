package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.gui.BoutonAccueil;

public class LemmingMainGUI extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Lemmings !");

		Group root = new Group();
		Scene scene = new Scene(root, 700, 500, Color.WHITE);
		BoutonAccueil mon_clavier = new BoutonAccueil(scene);//on créé un objet clavier
		root.getChildren().add(mon_clavier);//on l'ajoute à notre groupe root

		stage.setScene(scene);
		stage.show();
	}

}