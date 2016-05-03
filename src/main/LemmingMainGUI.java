package main;

import java.io.IOException;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.IGameEng;
import services.IJoueur;
import services.ILevel;
import utils.ImageLoader;
import utils.LevelChooser;

public class LemmingMainGUI extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	private GridPane grid;
	private ImageView[][] allCases;
	private Label resume; 
	private TextArea sizeColonyText;
	private Button playResetButton;
	private Button boomButton, minusButton, plusButton;
	private Button diggerButton, climberButton, builderButton,
					floaterButton, bomberButton, stopperButton,
					basherButton, minerButton;
	private Button refreshButton;
	private TextArea level;

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
			changeEnterAndExit(enterAndExit[0],enterAndExit[1],enterAndExit[2],enterAndExit[3]);
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
						if(!sizeColonyText.getText().isEmpty() && sizeColonyText.getText().matches("[0-9]*")){
							sizeColony = Integer.parseInt(sizeColonyText.getText());
							joueur.changeSizeColony(sizeColony);
						}
						joueur.getGameEngine().getLevel().goPlay(entH,entW, exitH, exitW);
						playUI();
						displayPlateau();

					} else {
						notPlayUI();
						joueur.reset();

						Integer[] enterAndExit = LevelChooser.constructLevel(joueur.getGameEngine().getLevel(), niveau);
						if(enterAndExit!=null){
							changeEnterAndExit(enterAndExit[0],enterAndExit[1],enterAndExit[2],enterAndExit[3]);
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
			
			level = (TextArea) scene.lookup("#level");
			
			refreshButton = (Button) scene.lookup("#refreshButton");
			refreshButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					if(level.getText().matches("[0-9]")){
						int niv = Integer.parseInt(level.getText());
						if(niv!=niveau){
							Integer[] enterAndExit = LevelChooser.constructLevel(joueur.getGameEngine().getLevel(), niv);
							if(enterAndExit!=null){
								if(enterAndExit!=null){
									changeEnterAndExit(enterAndExit[0],enterAndExit[1],enterAndExit[2],enterAndExit[3]);
								}
								changePlateau(joueur.getGameEngine().toString());
								niveau = niv;
								level.setStyle("-fx-text-fill: black");
							} else {
								level.setStyle("-fx-text-fill: red");
							}
						}
					} else {
						level.setStyle("-fx-text-fill: red");
					}
				}
			});
			
			diggerButton = (Button) scene.lookup("#diggerButton");
			diggerButton.setOnAction(lemChange(diggerButton));
			
			climberButton = (Button) scene.lookup("#climberButton");
			climberButton.setOnAction(lemChange(climberButton));
			
			builderButton = (Button) scene.lookup("#builderButton");
			builderButton.setOnAction(lemChange(builderButton));
			
			floaterButton = (Button) scene.lookup("#floaterButton");
			floaterButton.setOnAction(lemChange(floaterButton));
			
			bomberButton = (Button) scene.lookup("#bomberButton");
			bomberButton.setOnAction(lemChange(bomberButton));
			
			stopperButton = (Button) scene.lookup("#stopperButton");
			stopperButton.setOnAction(lemChange(stopperButton));
			
			basherButton = (Button) scene.lookup("#basherButton");
			basherButton.setOnAction(lemChange(basherButton));
			
			minerButton = (Button) scene.lookup("#minerButton");
			minerButton.setOnAction(lemChange(minerButton));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private EventHandler<ActionEvent> lemChange(Button button) {
		return new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				// TODO : action du bouton
			}
		};
	}

	private void playUI() {
		playResetButton.setText(textButtonReset);
		toggleButtons();
	}

	private void notPlayUI() {
		playResetButton.setText(textButtonPlay);
		toggleButtons();
	}
	
	private void toggleButtons(){
		diggerButton.setDisable(!diggerButton.isDisabled());
		climberButton.setDisable(!climberButton.isDisabled());
		builderButton.setDisable(!builderButton.isDisabled());
		floaterButton.setDisable(!floaterButton.isDisable());
		bomberButton.setDisable(!bomberButton.isDisable());
		stopperButton.setDisable(!stopperButton.isDisable());
		basherButton.setDisable(!basherButton.isDisable());
		minerButton.setDisable(!minerButton.isDisable());
		boomButton.setDisable(!boomButton.isDisable());
		sizeColonyText.setDisable(!sizeColonyText.isDisable());
		level.setDisable(!level.isDisable());
		refreshButton.setDisable(!refreshButton.isDisable());
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
						if(playResetButton.getText().equals(textButtonPlay) ||
								joueur.getGameEngine().gameOver()){
							timer.cancel();
							return;
						} else {
	
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
								return;
							}
	
							if(period != speedGame){
								displayPlateau(); // Recharge Timer avec le nouveau temps (changé par "+" et "-")
								timer.cancel();
								return;
							}
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
			
			allCases = new ImageView[lines.length][countColumn+1];
		}
		
		for(int i=0; i<lines.length; i++){
			String[] cols = lines[i].split(":");
			for(int j=0; j<cols.length; j++){

				ImageView oneCase = allCases[i][j];
				
				if(oneCase==null || !oneCase.getAccessibleText().equals(cols[j])){ // si non-existant ou si on détecte une différence
					if(oneCase!=null) grid.getChildren().remove(allCases[i][j]); // Si case existante
					oneCase = new ImageView(ImageLoader.getByRepresentation(cols[j]));
					oneCase.setPreserveRatio(true);
					oneCase.setFitHeight(23);
					oneCase.setFitWidth(30);
					oneCase.setAccessibleText(cols[j]);
					allCases[i][j] = oneCase;
					grid.add(oneCase, j, i);
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
	
	private void changeEnterAndExit(int entH, int entW, int exitH, int exitW) {
		this.entH=entH;
		this.entW=entW;
		this.exitH=exitH;
		this.exitW=exitW;
		joueur.getGameEngine().getLevel().addEnter(entH, entW);
		joueur.getGameEngine().getLevel().addExit(exitH, exitW);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
