package main;

import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import contrat.GameEngContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import services.IGameEng;
import services.IJoueur;
import services.ILevel;
import utils.LevelChooser;

public class LemmingMainGUI extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	private Map<String, Object> fxmlNamespace;
	
	private GridPane grid;
	private Label[][] allCases;
	private Label resume; 
	private TextArea sizeColonyText;
	private Button playResetButton;
	private Button boomButton, minusButton, plusButton;

	private IJoueur joueur;
	private int sizeColony=10, spawnSpeed=2;
	private int height, width;
	private int entH, entW, exitH, exitW;
	private int speedGame = 400; //ms
	private int niveau = 1;

	private String textButtonReset = "Reset";
	private String textButtonPlay = "Play";


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Lemmings !");

		initRootLayout();
		initGame();
	}

	private void initGame() {
		ILevel level=new LevelContrat();
		IGameEng gameEng=new GameEngContrat();
		joueur=new JoueurContrat();

		Integer[] heigthWidthLevel = LevelChooser.getHeigthAndWidth(niveau);
		height = heigthWidthLevel[0];
		width = heigthWidthLevel[1];
		level.init(height, width);
		gameEng.init(level, sizeColony, spawnSpeed);
		joueur.init(gameEng);

		/////////////// EDITING /////////////
		Integer[] enterAndExit = LevelChooser.constructLevel(level, niveau);
		if(enterAndExit!=null){
			entH=enterAndExit[0];
			entW=enterAndExit[1];
			exitH=enterAndExit[2];
			exitW=enterAndExit[3];
			level.addEnter(entH, entW);
			level.addExit(exitH, exitW);
		}

		System.out.printf("Hauteur %s, largeur %s\n", height, width);
		System.out.printf("SizeColony %s, spawnSpeed %s\n", sizeColony, spawnSpeed);
		System.out.printf("Entrée (%s,%s), sortie (%s, %s)\n", entH,entW,exitH,exitW);

		changePlateau(gameEng.toString());
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LemmingMainGUI.class.getResource("gui/view/PageView.fxml"));
			rootLayout = (AnchorPane) loader.load();
			fxmlNamespace = loader.getNamespace();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			grid = (GridPane) scene.lookup("#grid");
			grid.getColumnConstraints().remove(0);
			grid.getRowConstraints().remove(0);

			resume = (Label) scene.lookup("#resume");
			sizeColonyText = (TextArea) scene.lookup("#sizeColony");

			playResetButton = (Button) scene.lookup("#playButton");
			playResetButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					if(playResetButton.getText().equals("Play")){
						joueur.getGameEngine().getLevel().goPlay(entH,entW, exitH, exitW);
						playUI();
						displayPlateau();

					} else {
						notPlayUI();
						joueur.reset();

						Integer[] enterAndExit = LevelChooser.constructLevel(joueur.getGameEngine().getLevel(), niveau);
						if(enterAndExit!=null){
							joueur.getGameEngine().getLevel().addEnter(enterAndExit[0], enterAndExit[1]);
							joueur.getGameEngine().getLevel().addExit(enterAndExit[2], enterAndExit[3]);
						}
						changePlateau(joueur.getGameEngine().toString());
					}

				}
			});

			boomButton = (Button) scene.lookup("#boomButton");
			boomButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					joueur.destroyAllLem();
				}
			});

			plusButton = (Button) scene.lookup("#plusButton");
			plusButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					speedUpSpeedGame();
				}
			});
			minusButton = (Button) scene.lookup("#minusButton");
			minusButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					slowDownSpeedGame();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void playUI() {
		playResetButton.setText(textButtonReset);
	}

	private void notPlayUI() {
		playResetButton.setText(textButtonPlay);
	}

	protected void displayPlateau() {
		final int period = speedGame;

		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run(){
				javafx.application.Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if(playResetButton.getText().equals(textButtonPlay)) timer.cancel();

						joueur.getGameEngine().step();
						if(!joueur.getGameEngine().gameOver()){
							System.out.println("Tours n°"+joueur.getGameEngine().getNbTours());
							changePlateau(joueur.getGameEngine().toString());
						} else {
							System.out.println("Tours n°"+joueur.getGameEngine().getNbTours()+" : GameOver !");
							System.out.printf("\nScore : \nLemmings sauvés = %s, en %s tours\n", 
									joueur.getGameEngine().getScore().split("-")[0], joueur.getGameEngine().getScore().split("-")[1]);
							changeResume(String.format("Score : Lemmings sauvés = %s, en %s tours", 
									joueur.getGameEngine().getScore().split("-")[0], joueur.getGameEngine().getScore().split("-")[1]));
							timer.cancel();
						}

						if(period != speedGame){
							displayPlateau(); // Recharge Timer avec le nouveau temps (changé par "+" et "-")
							timer.cancel();
						}
					}
				});
			}
		}, 0, getSpeedGame());

	}

	private void changeResume(String text) {
		resume.setText(text);
	}

	private void changePlateau(String texte){
		String[] lines = texte.split("\r\n");
		int countColumn = lines[0].length() - lines[0].replace(":", "").length();
		
		if(allCases == null || allCases.length!=lines.length 
  		   || allCases[0].length!=(countColumn+1)){
			
			allCases = new Label[lines.length][countColumn+1];
		}
		
		
		for(int i=0; i<lines.length; i++){
			String[] cols = lines[i].split(":");
			for(int j=0; j<cols.length; j++){

				Label oneCase = allCases[i][j];
				if(oneCase==null){
					oneCase = new Label(cols[j]);
					allCases[i][j] = oneCase;
					oneCase.setPrefSize(27, 20);
					oneCase.setTextAlignment(TextAlignment.RIGHT);
					grid.add(oneCase, j, i);
				} else if(oneCase.getText()!=cols[j]){
					oneCase.setText(cols[j]);
				}
			}
		}
	}

	private int getSpeedGame(){
		return speedGame;
	}

	private void slowDownSpeedGame(){
		speedGame+=50;
	}

	private void speedUpSpeedGame(){
		if(speedGame>100) speedGame-=50;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
