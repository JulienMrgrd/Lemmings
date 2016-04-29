package servicesImplementations;

import enumeration.EtatLemming;
import enumeration.Nature;
import services.IGameEng;
import services.ILemming;

public class Lemming implements ILemming {

	private int width;
	private int height;
	private int id;
	private boolean isDroitier;
	private EtatLemming etat;
	private IGameEng gameEng;
	private int nbCasesFalling;


	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public boolean isDroitier() {
		return isDroitier;
	}

	@Override
	public EtatLemming getEtat() {
		return etat;
	}

	@Override
	public IGameEng getGameEng() {
		return gameEng;
	}

	@Override
	public int getNbCasesFalling() {
		return nbCasesFalling;
	}

	@Override
	public void init(IGameEng gameEng) {
		this.gameEng=gameEng;
		width=gameEng.getLevel().getEntranceWidth();
		height=gameEng.getLevel().getEntranceHeight();
		id=gameEng.getSpawned();
		isDroitier=true;
		etat=EtatLemming.FALLER;
		nbCasesFalling=0;
	}

	@Override
	public void setHeight(int height) {
		this.height=height;
	}

	@Override
	public void setWidth(int width) {
		this.width=width;
	}

	@Override
	public void setDirection() {
		isDroitier = !isDroitier;
	}

	@Override
	public void setEtat(EtatLemming etat) {
		this.etat=etat;
	}

	@Override
	public void step() {
		if(gameEng.getLevel().getExitHeight() == height && gameEng.getLevel().getExitWidth() == width ){
			gameEng.saveLemming(id);
		}else if(etat == EtatLemming.WALKER){
			if (gameEng.getLevel().getNature(height+1, width) == Nature.EMPTY){ 
				etat = EtatLemming.FALLER; 

			} else if (isDroitier){
				if (gameEng.getLevel().getNature(height-1, width+1) != Nature.EMPTY ||
						(gameEng.getLevel().getNature(height, width+1) != Nature.EMPTY &&
						gameEng.getLevel().getNature(height-2, width+1) != Nature.EMPTY)){
					isDroitier = false; 

				} else if (gameEng.getLevel().getNature(height, width+1) != Nature.EMPTY){
					isDroitier = true;
					width = width+1;
					height = height-1;

				} else {
					isDroitier = true;
					width = width+1; 
				}
			}else {
				if (gameEng.getLevel().getNature(height-1, width-1) != Nature.EMPTY ||
						(gameEng.getLevel().getNature(height, width-1) != Nature.EMPTY &&
						gameEng.getLevel().getNature(height-2, width-1) != Nature.EMPTY)){
					isDroitier = true; 

				} else if (gameEng.getLevel().getNature(height, width-1) != Nature.EMPTY){
					isDroitier = false;
					width = width-1;
					height = height-1;

				} else {
					isDroitier = false;
					width = width-1; 
				}
			}
		} else if (getEtat() == EtatLemming.FALLER){
			if (gameEng.getLevel().getNature(height+1, width) != Nature.EMPTY) {
				if (getNbCasesFalling() < 8) {
					etat = EtatLemming.WALKER; 
				}else {
					gameEng.killLemming(id);
				}
			}else {
				nbCasesFalling = nbCasesFalling+1;
				height = height+1;
			}
		}
	}

}
