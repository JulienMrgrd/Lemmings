package services;

import java.util.Set;

public class GameEng implements IGameEng {
	
	ILevel level;
	boolean obstacle;
	int sizeColony;
	int spawnSpeed;
	boolean gameOver;
	double score;
	int nbTours;
	Set<ILemming> allLemInLife;
	ILemming Lemming;
	int NbLemSave;
	int NbLemDead;
	int NbLemInLife;
	int NbLemCreate;
	

	@Override
	public ILevel getLevel() {
		return level;
	}

	@Override
	public boolean isObstacle(int h, int w) {
		if(0 <= h && h < getLevel().getHeight() 
				&& 0 <= w && w<getLevel().getWidth()){
			return getLevel().getNature(h, w) == Nature.DIRT ||
					getLevel().getNature(h, w)==Nature.METAL;
		}
		return false;
	}

	@Override
	public int getSizeColony() {
		return sizeColony;
	}

	@Override
	public int getSpawnSpeed() {
		return spawnSpeed;
	}

	@Override
	public boolean gameOver() {
		return gameOver;
	}

	@Override
	public double score() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbTours() {
		if(getLevel().getEditing()==false) return nbTours;
		return 0;
	}

	@Override
	public Set<ILemming> getAllLemInLife() {
		if(!gameOver) return allLemInLife;
		return null;
	}

	@Override
	public ILemming getLemming(int i) {
		if(!gameOver)
			for(ILemming lem : allLemInLife){
				if(lem.getId()==i) return lem;
			}
		return null;
	}

	@Override
	public int getNbLemSave() {
		return NbLemSave;
	}

	@Override
	public int getNbLemDead() {
		return NbLemDead;
	}

	@Override
	public int getNbLemInLife() {
		return NbLemInLife;
	}

	@Override
	public int getNbLemCreate() {
		return NbLemCreate;
	}

	@Override
	public void init(int sizeColony, int spawnSpeed) {
		if(sizeColony>0 && spawnSpeed>0){
			this.sizeColony=sizeColony;
			this.spawnSpeed=spawnSpeed;
		}
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tuerLemming(int i) {
		//TODO
	}

	@Override
	public void sauverLemming(int i) {
		// TODO Auto-generated method stub

	}

}
