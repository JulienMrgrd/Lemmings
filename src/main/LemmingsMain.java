package main;

import contrat.GameEngContrat;
import contrat.LevelContrat;
import services.IGameEng;
import services.ILevel;

public class LemmingsMain {

	public static void main(String[] args) {
		ILevel level=new LevelContrat();
		IGameEng gameEng=new GameEngContrat();
		
		int height=5, width=10;
		int sizeColony=8, spawnSpeed=2;
		int entH=2, entW=2, sortH=3, sortW=7;
		System.out.printf("Hauteur %s, largeur %s\n", height, width);
		System.out.printf("SizeColony %s, spawnSpeed %s\n", sizeColony, spawnSpeed);
		System.out.printf("Entrée (%s,%s), sortie (%s, %s)\n", entH,entW,sortH,sortW);
		
		level.init(height, width);
		gameEng.init(level, sizeColony, spawnSpeed);
		level.goPlay(entH,entW, sortH, sortW);
		while(!gameEng.gameOver()) {
			gameEng.step();
			if(!gameEng.gameOver()){
				System.out.println("Tours n°"+gameEng.getNbTours());
				System.out.println(gameEng);
			} else {
				System.out.println("Tours n°"+gameEng.getNbTours()+" : GameOver !");
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("\nScore : \nLemmings sauvés = %s, en %s tours\n", gameEng.getScore().split("-")[0], gameEng.getScore().split("-")[1]);
	}

}
