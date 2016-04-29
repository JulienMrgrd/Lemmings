package servicesImplementations;

import java.util.ArrayList;
import java.util.List;

import enumeration.Nature;
import services.IGameEng;
import services.ILemming;
import services.ILevel;

public class GameEng implements IGameEng {

	private List<ILemming> lemVivants;
	private int sizeColony;
	
	private int spawnedSpeed;
	private ILevel level;
	private int nbTours = 0;
	private double score;
	private int spawned; // lemmings crées
	private int nbLemSauves; // lemmings sauvés
	private boolean gameOver;

	
	// ============   Observateurs ============
	@Override
	public List<ILemming> getLemVivants() {
		return lemVivants;
	}

	@Override
	public ILemming getLemVivantById(int id) {
		for(ILemming lem : lemVivants){
			if(lem.getId()==id) return lem;
		}
		return null;
	}

	@Override
	public int getSizeColony() {
		return sizeColony;
	}

	@Override
	public int getSpawned() {
		return spawned;
	}

	@Override
	public int getSpawnSpeed() {
		return spawnedSpeed;
	}

	@Override
	public ILevel getLevel() {
		return level;
	}

	@Override
	public int getNbTours() {
		return nbTours;
	}

	@Override
	public int getNbLemSauves() {
		return nbLemSauves;
	}
	
	@Override
	public int getNbLemMorts() {
		return getSizeColony()-getNbLemSauves()-getNbLemVivants();
	}

	@Override
	public int getNbLemVivants() {
		return getLemVivants().size();
	}

	@Override
	public double getScore() {
		score = getNbLemSauves() / getSizeColony();
		return score;
	}

	@Override
	public boolean isObstacle(int h, int w) {
		if(level.getNature(h, w).equals(Nature.EMPTY)){
			return false;
		}
		return true;
	}

	@Override
	public boolean gameOver() {
		return gameOver;
	}
	
	
	// ============ CONSTRUCTORS =============

	@Override
	public void init(ILevel level, int sizeColony, int spawnSpeed) {
		this.level = level;
		this.sizeColony = sizeColony;
		this.spawnedSpeed = spawnSpeed;
		
		lemVivants = new ArrayList<>();
		spawned = 0;
		nbTours = 0;
		score = 0;
		nbLemSauves = 0;
		gameOver = false;
	}
	
	
	// ============= OPERATORS ==============

	@Override
	public void addLemming(ILemming lem) {
		lemVivants.add(lem);
		spawned++;
	}

	@Override
	public void killLemming(int idLem) {
		lemVivants.remove(getLemVivantById(idLem));
	}

	@Override
	public void saveLemming(int idLem) {
		killLemming(idLem);
		nbLemSauves++;
	}

	@Override
	public void step() {
		if(gameOver) return;
		
		nbTours++;
		if(getSpawned()==getSizeColony() && getNbLemVivants()==0){ // fin du jeu
			gameOver = true;
		} else {
			for(ILemming lem : lemVivants){
				lem.step();
			}
		}
	}

	@Override
	public boolean containsIdColony(int idLem) {
		for(ILemming lem : lemVivants){
			if(lem.getId()==idLem) return true;
		}
		return false;
	}

	
}
