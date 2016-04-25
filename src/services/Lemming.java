package services;

public class Lemming implements ILemming {

	int width;
	int height;
	boolean droitier;
	EtatLemming etat;
	int id;
	IGameEng gameEng;
	int nbCaseFalling;
	
	
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean isDroitier() {
		return droitier;
	}

	@Override
	public EtatLemming getEtat() {
		return etat;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public IGameEng getGameEng() {
		return gameEng;
	}

	@Override
	public int nbCasesFalling() {
		return nbCaseFalling;
	}

	@Override
	public void init(IGameEng gameEng) {
		this.gameEng=gameEng;

	}

	@Override
	public void setWidth(int width) {
		if(width>0) this.width=width;

	}

	@Override
	public void setHeight(int height) {
		if(height>0) this.height=height;

	}

	@Override
	public void setDirection() {
		droitier=!isDroitier();
	}

	@Override
	public void setEtat(EtatLemming etat) {
		this.etat=etat;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

}
