package servicesImplementations;

import java.util.List;

import services.IGameEng;
import services.ILemming;
import services.ILevel;

public class GameEng implements IGameEng {

	@Override
	public List<ILemming> getLemVivants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILemming getLemming(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSizeColonyMax() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSpawned() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSpawnSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ILevel getLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbTours() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbSauves() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isObstacle(int h, int w) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init(ILevel level, int sizeColony, int spawnSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLemming(ILemming lem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void killLemming(int idLem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveLemming(int idLem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsIdColony(int idLem) {
		// TODO Auto-generated method stub
		return false;
	}

		
	
}
