package servicesImplementations;

import enumeration.Nature;
import services.ILevel;

public class Level implements ILevel {

	private int height;
	private int width;
	private int entranceHeight;
	private int entranceWidth;
	private int exitHeight;
	private int exitWidth;
	private boolean editing;
	private Nature[][] plateau;
	
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getEntranceHeight() {
		return entranceHeight;
	}

	@Override
	public int getEntranceWidth() {
		return entranceWidth;
	}

	@Override
	public int getExitHeight() {
		return exitHeight;
	}

	@Override
	public int getExitWidth() {
		return exitWidth;
	}

	@Override
	public boolean getEditing() {
		return editing;
	}

	@Override
	public Nature getNature(int h, int w) {
		return plateau[h][w];
	}

	@Override
	public void init(int h, int w) {
		height=h;
		width=w;
		editing=true;
		plateau = new Nature[h][w];
		for(int i=0; i<h; i++){
			for(int j=0; j<w; j++){
				if(i==0 || i==(h-1) || j==0 || j==(w-1)) plateau[i][j]=Nature.METAL;
				else plateau[i][j]=Nature.EMPTY;
			}
		}
	}

	@Override
	public void setNature(int h, int w, Nature nat) {
		plateau[h][w]=nat;
	}

	@Override
	public void goPlay(int entranceH, int entranceW, int exitH, int exitW) {
		entranceHeight=entranceH;
		entranceWidth=entranceW;
		exitHeight=exitH;
		exitWidth=exitW;
		editing=false;
	}

	@Override
	public void remove(int h, int w) {
		setNature(h, w, Nature.EMPTY);
	}

	@Override
	public void build(int h, int w) {
		setNature(h, w, Nature.DIRT);
	}
	
	@Override
	public void addStopper(int h, int w) {
		setNature(h, w, Nature.STOPPER);
	}
	
	@Override
	public void removeStopper(int h, int w) {
		setNature(h, w, Nature.EMPTY);
	}
	
	@Override
	public void addEnter(Integer height, Integer width) {
		entranceHeight=height;
		entranceWidth=width;
	}

	@Override
	public void addExit(Integer height, Integer width) {
		exitHeight=height;
		exitWidth=width;
	}
	
	@Override
	public void reset(){
		init(getHeight(), getWidth());
	}
}
