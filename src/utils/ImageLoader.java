package utils;

import javafx.scene.image.Image;

public abstract class ImageLoader {

	public static final Image METAL_IMG = getRessource("metal.png");
	public static final Image DIRT_IMG = getRessource("dirt.png");
	public static final Image LEM_RIGHT_IMG = getRessource("lem_right.png");
	public static final Image LEM_LEFT_IMG = getRessource("lem_left.png");
	public static final Image EMPTY_IMG = getRessource("empty.png");
	public static final Image ENTER_IMG = getRessource("enter.png");
	public static final Image EXIT_IMG = getRessource("exit.png");

	private static Image getRessource(String res){
		return new Image(ImageLoader.class.getResource("/"+res).toString());
	}
	
	public static Image getByRepresentation(String toString){
		if(toString==null) return null;
		
		toString = toString.replaceAll("[0-9]", "");
		switch (toString) {
		case "M":
			return METAL_IMG;

		case "D":
			return DIRT_IMG;
			
		case "LEMR":
			return LEM_RIGHT_IMG;
			
		case "LEML":
			return LEM_LEFT_IMG;
			
		case "E":
			return ENTER_IMG;
			
		case "S":
			return EXIT_IMG;
			
		default:
			return EMPTY_IMG;
		}
	}

}
