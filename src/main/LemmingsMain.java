package main;

import contrat.GameEngContrat;
import contrat.LevelContrat;
import enumeration.Nature;
import services.IGameEng;
import services.ILevel;

public class LemmingsMain {

	public static void main(String[] args) {
		ILevel level=new LevelContrat();
		IGameEng gameEng=new GameEngContrat();
		
		int height=15, width=30;
		int sizeColony=5, spawnSpeed=2;
		int entH=6, entW=8, sortH=13, sortW=27;
		System.out.printf("Hauteur %s, largeur %s\n", height, width);
		System.out.printf("SizeColony %s, spawnSpeed %s\n", sizeColony, spawnSpeed);
		System.out.printf("Entrée (%s,%s), sortie (%s, %s)\n", entH,entW,sortH,sortW);
		
		level.init(height, width);
		gameEng.init(level, sizeColony, spawnSpeed);
		
		/////////////// EDITING /////////////
		int cptH = sortH;
		int cptW = entW;
		/*for(int i=2; i<width-3; i++){
			if(cptH>entH){
				level.setNature(cptH, cptW, Nature.METAL);
				cptH--;
				cptW++;
			} else {
				level.setNature(cptH, cptW++, Nature.DIRT);
			}
			
		}*/
		
		for(int i=4; i<=width-5; i++){
			for(int j=4; j<level.getHeight()-1;j++){
				if(j!=7&&j!=5) level.setNature(j, i, Nature.DIRT);
			}
		}
		
		////////////// FIN EDITING //////////
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
