package utils;

import enumeration.Nature;
import services.ILevel;

public abstract class LevelChooser {

	/**
	 * Return an array (size 4) of the enter and the exit position (height and width)
	 */
	public static Integer[] constructLevel(ILevel level, int niveau){

		resetPlateau(level);
		switch (niveau) {
		case 1:
			return level1(level);
		case 2:
			return level2(level);
		case 3:
			return level3(level);
		}
		return null;
	}

	private static void resetPlateau(ILevel level) {
		for(int i=1; i<level.getWidth(); i++){
			for(int j=1; j<level.getHeight(); j++){
				Nature n = level.getNature(j, i);
				if(n==Nature.DIRT || n==Nature.STOPPER){
					level.setNature(j, i, Nature.EMPTY);
				}
			}
		}
	}

	public static Integer[] getHeigthAndWidth(int niveau) {
		switch (niveau) {
		case 1:
			return heigthWidthLevel1;
		case 2:
			return heigthWidthLevel2;
		case 3:
			return heigthWidthLevel3;
		}
		return null;
	}


	private static Integer[] heigthWidthLevel1 = {15,30};

	private static Integer[] level1(ILevel level){
		//					 EntH, EntW, SortH, SortW
		Integer[] enterAndExit = {6, 2, 13, 27};

		int cptH = enterAndExit[2];
		int cptW = enterAndExit[1];
		for(int i=2; i<level.getWidth()-3; i++){
			if(cptH>enterAndExit[0]){
				level.setNature(cptH, cptW, Nature.DIRT);
				cptH--;
				cptW++;
			} else {
				level.setNature(cptH, cptW++, Nature.DIRT);
			}
		}

		return enterAndExit;
	}

	private static Integer[] heigthWidthLevel2 = {15,30};

	private static Integer[] level2(ILevel level){
		//		 EntH, EntW, SortH, SortW
		Integer[] enterAndExit = {6, 2, 13, 26};

		int cptH = enterAndExit[2];
		int cptW = enterAndExit[1];
		for(int i=2; i<level.getWidth()-3; i++){
			if(cptH>enterAndExit[0]){
				level.setNature(cptH, cptW, Nature.DIRT);
				cptH--;
				cptW++;
			} else {
				level.setNature(cptH, cptW++, Nature.DIRT);
			}
		}

		level.setNature(9, 25, Nature.DIRT); // Mur du dessous
		level.setNature(9, 26, Nature.DIRT);
		level.setNature(9, 27, Nature.DIRT);
		level.setNature(9, 28, Nature.DIRT);

		level.setNature(13, 25, Nature.DIRT); // Mur a gauche de exit
		level.setNature(12, 25, Nature.DIRT);

		return enterAndExit;
	}

	private static Integer[] heigthWidthLevel3 = {15,30};

	private static Integer[] level3(ILevel level){

		Integer[] enterAndExit = {6, 2, 13, 27};

		for(int i=2; i<level.getWidth()-3; i++){
			for(int j=8; j<level.getHeight()-2;j++){
				level.setNature(j, i, Nature.DIRT);
			}
		}

		return enterAndExit;
	}

}
