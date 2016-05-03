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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	private Label[][] allCases;
	
	private TextArea sizeColonyText;
	private Button playResetButton;
	private Button boomButton, minusButton, plusButton;
	private Button diggerButton, climberButton, builderButton,
					floaterButton, bomberButton, stopperButton,
					basherButton, minerButton;
	private Button refreshButton;
	private TextArea level;
	
	private Label sauvesVsCreesLabel,sauvesVsSizeColonyCpt,mortsVsVivantsLabel,
					mortsVsVivantsCpt,tours,score;
	private Button changeButton;
	private TextField lemChange;

	private IJoueur joueur;
	private int sizeColony=10, spawnSpeed=2;
	private int height, width;
	private int entH, entW, exitH, exitW;
	private int speedGame = 300; //ms
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
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LemmingMainGUI.class.getResource("gui/view/PageView.fxml"));
			rootLayout = (AnchorPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			grid = (GridPane) scene.lookup("#grid");
			grid.getColumnConstraints().remove(0);
			grid.getRowConstraints().remove(0);

			lemChange = (TextField) scene.lookup("#lemChange");
			changeButton = (Button) scene.lookup("#changeButton");
			changeButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					changeLemming(lemChange.getText());
				}

			});
			
			sauvesVsCreesLabel = (Label) scene.lookup("#sauvesVsCreesLabel");
			sauvesVsSizeColonyCpt = (Label) scene.lookup("#sauvesVsSizeColonyCpt");
			mortsVsVivantsLabel = (Label) scene.lookup("#mortsVsVivantsLabel");
			mortsVsVivantsCpt = (Label) scene.lookup("#mortsVsVivantsCpt");
			tours = (Label) scene.lookup("#tours");
			score = (Label) scene.lookup("#score");
			
			sizeColonyText = (TextArea) scene.lookup("#sizeColony");

			playResetButton = (Button) scene.lookup("#playButton");
			playResetButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					if(playResetButton.getText().equals("Play")){
						if(!sizeColonyText.getText().isEmpty() && sizeColonyText.getText().matches("[0-9]*")){
							sizeColony = Integer.parseInt(sizeColonyText.getText());
							joueur.changeSizeColony(sizeColony);
						}
						
						if(joueur.getGameEngine().gameOver()==true){
							joueur.reset();
							Integer[] enterAndExit = LevelChooser.constructLevel(joueur.getGameEngine().getLevel(), niveau);
							if(enterAndExit!=null){
								changeEnterAndExit(enterAndExit[0],enterAndExit[1],enterAndExit[2],enterAndExit[3]);
							}
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
			
			notPlayUI();

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
	
	private void changeLemming(String text) {
		try{
			if(text.contains("/")){
				String[] split = text.split("/");
				int id = Integer.parseInt(split[1]);
				if(split.length==2 && !split[0].isEmpty() && !split[1].isEmpty()){
					joueur.changeEtatLemming(id, split[0]);
					lemChange.setStyle("-fx-text-fill: black");
				}
			}
		} catch (NumberFormatException e){
			lemChange.setStyle("-fx-text-fill: red");
		}
	}

	private void playUI() {
		playResetButton.setText(textButtonReset);
		toggleButtons(true);
	}

	private void notPlayUI() {
		playResetButton.setText(textButtonPlay);
		lemChange.setText("");
		tours.setText("");
		score.setText("");
		mortsVsVivantsCpt.setText("");
		sauvesVsSizeColonyCpt.setText("");
		toggleButtons(false);
	}
	
	private void toggleButtons(boolean value){
		diggerButton.setDisable(!value);
		climberButton.setDisable(!value);
		builderButton.setDisable(!value);
		floaterButton.setDisable(!value);
		bomberButton.setDisable(!value);
		stopperButton.setDisable(!value);
		basherButton.setDisable(!value);
		minerButton.setDisable(!value);
		boomButton.setDisable(!value);
		
		sauvesVsCreesLabel.setDisable(!value);
		mortsVsVivantsLabel.setDisable(!value);
		changeButton.setDisable(!value);
		lemChange.setDisable(!value);
		
		sizeColonyText.setDisable(value);
		level.setDisable(value);
		refreshButton.setDisable(value);
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
							notPlayUI();
							timer.cancel();
							return;
						} else {
							joueur.getGameEngine().step();
							tours.setText("Tours n°"+joueur.getGameEngine().getNbTours());
							mortsVsVivantsCpt.setText(joueur.getGameEngine().getNbLemMorts()
									+" / "+joueur.getGameEngine().getNbLemVivants());
							sauvesVsSizeColonyCpt.setText(joueur.getGameEngine().getNbLemSauves()
									+" / "+sizeColony);
							if(!joueur.getGameEngine().gameOver()){
								changePlateau(joueur.getGameEngine().toString());
							} else {
								score.setText("Score : "+joueur.getGameEngine().getScore());
								notPlayUI();
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
		}, 0, getSpeedGame()); // SpeedGame peut changer

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
				ImageView image;
				
				if(oneCase==null || !oneCase.getText().equals(cols[j])){ // si non-existant ou si on détecte une différence
					if(oneCase!=null) grid.getChildren().remove(allCases[i][j]); // Si case existante
					
					image = new ImageView(ImageLoader.getByRepresentation(cols[j]));
					image.setFitHeight(28);
					image.setFitWidth(30);
					
					if(cols[j].startsWith("LEM")){
						image.setPreserveRatio(true);
						oneCase = new Label(cols[j].replaceAll("[a-zA-Z]*", ""), image);
					} else {
						oneCase = new Label("", image);
					}
					
					oneCase.setContentDisplay(ContentDisplay.LEFT);
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
