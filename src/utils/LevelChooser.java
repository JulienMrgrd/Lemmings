package utils;

import enumeration.Nature;
import services.ILevel;

public abstract class LevelChooser {
	
	/**
	 * Return an array (size 4) of the enter and the exit position (height and width)
	 */
	public static Integer[] constructLevel(ILevel level, int niveau){
		switch (niveau) {
		case 1:
			return level1(level);
			
		case 2:
			return level2(level);

		}
		return new Integer[]{};
	}
	
	public static Integer[] getHeigthAndWidth(int niveau) {
		switch (niveau) {
		case 1:
			return heigthWidthLevel1;
		case 2:
			return heigthWidthLevel2;
		}
		return new Integer[]{};
	}

	
	private static Integer[] heigthWidthLevel1 = {15,30};
	
	private static Integer[] level1(ILevel level){
		//					 EntH, EntW, SortH, SortW
		Integer[] enterAndExit = {6, 2, 13, 27};
		
		int cptH = enterAndExit[2];
		int cptW = enterAndExit[1];
		for(int i=2; i<level.getWidth()-3; i++){
			if(cptH>enterAndExit[0]){
				level.setNature(cptH, cptW, Nature.METAL);
				cptH--;
				cptW++;
			} else {
				level.setNature(cptH, cptW++, Nature.METAL);
			}
		}
		
		return enterAndExit;
	}
	
	private static Integer[] heigthWidthLevel2 = {15,30};
	
	private static Integer[] level2(ILevel level){
		// TODO:
		return null;
	}

}
