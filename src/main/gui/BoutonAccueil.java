package main.gui;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class BoutonAccueil extends Parent{

	Rectangle fond_clavier;
	private static final int width = 200, height = 100;


	public BoutonAccueil (Scene scene){

		fond_clavier = new Rectangle();
		fond_clavier.setWidth(width);
		fond_clavier.setHeight(height);
		fond_clavier.setArcWidth(30);
		fond_clavier.setArcHeight(30);
		fond_clavier.setFill( //on remplie notre rectangle avec un dégradé
				new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
						new Stop[] {
								new Stop(0, Color.web("#333333")),
								new Stop(1, Color.web("#000000"))
				}
						)
				);
		Reflection r = new Reflection();//on applique un effet de réflection
		r.setFraction(0.15);
		r.setBottomOpacity(0);
		r.setTopOpacity(0.4);
		fond_clavier.setEffect(r);

		this.setTranslateX((scene.getWidth()-width)/2);
		this.setTranslateY((scene.getHeight()-height)/2);
		this.getChildren().add(fond_clavier);

		Text lettre_touche = new Text();
		lettre_touche.setText("START");
		lettre_touche.setTextAlignment(TextAlignment.CENTER);
		lettre_touche.setFont(new Font(25));
		lettre_touche.setFill(Color.WHITE);
		lettre_touche.setX(lettre_touche.getX()+(width/3));
		lettre_touche.setY(lettre_touche.getY()+(height/1.8));
		this.getChildren().add(lettre_touche);

		this.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				fond_clavier.setFill( //on remplie notre rectangle avec un dégradé
					new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
							new Stop[] {
									new Stop(0, Color.web("#555555")),
									new Stop(1, Color.web("#333333"))
							}
					)
				);
			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				fond_clavier.setFill(new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
							new Stop[] {
									new Stop(0, Color.web("#333333")),
									new Stop(1, Color.web("#000000"))
							}
				));

			}
		});

		this.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				appuyer();
			}
		});

		this.setOnMouseReleased(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				relacher();
			}
		});

	}

	public void appuyer(){
		fond_clavier.setFill(Color.DARKGREY);
		this.setTranslateY(getTranslateY()+2);
	}

	public void relacher(){
		fond_clavier.setFill(new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
				new Stop[] {
						new Stop(0, Color.web("#333333")),
						new Stop(1, Color.web("#000000"))
				}
		));
		this.setTranslateY(getTranslateY()-2);
	}
}
