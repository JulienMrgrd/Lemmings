package servicesImplementations;

import enumeration.Nature;
import services.IGameEng;
import services.ILemming;
import services.ILevel;

public class GameEng implements IGameEng {

	private ILemming[] lemVivants;
	private int sizeColony;

	private int spawnedSpeed;
	private ILevel level;
	private int nbTours = 0;
	private String score;
	private int spawned; // lemmings crées
	private int nbLemVivants; // lemmings sauvés
	private int nbLemSauves; // lemmings sauvés
	private boolean gameOver;


	// ============   Observateurs ============
	@Override
	public ILemming[] getLemVivants() {
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
		return getSizeColony()-(getSizeColony()-getSpawned())-getNbLemSauves()-getNbLemVivants();
	}

	@Override
	public int getNbLemVivants() {
		return nbLemVivants;
	}

	@Override
	public String getScore() {
		score = getNbLemSauves() + "-" + getNbTours();
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

		lemVivants = new ILemming[sizeColony];
		spawned = 0;
		nbTours = 0;
		score = "";
		nbLemVivants = 0;
		nbLemSauves = 0;
		gameOver = false;
	}


	// ============= OPERATORS ==============

	@Override
	public void addLemming(ILemming lem) {
		lemVivants[spawned] = lem;
		spawned++;
		nbLemVivants++;
	}

	@Override
	public void killLemming(int idLem) {
		lemVivants[idLem] = null;
		nbLemVivants--;
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
			if(getSpawned()!=getSizeColony() && nbTours%getSpawnSpeed()==0){
				Lemming newLem = new Lemming();
				newLem.init(this);
				addLemming(newLem);
			}
			for(ILemming lem : lemVivants) {
				if(lem!=null){
					lem.step();
				}
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

	@Override
	public String toString() {
		String [][] res = new String [getLevel().getHeight()][getLevel().getWidth()];
		for (int i = 0; i < getLevel().getHeight(); i++)
			for (int j = 0; j < getLevel().getWidth(); j++) {
				switch (getLevel().getNature(i, j)) {
				case EMPTY:
					res[i][j] = " ";
					break;
				case METAL:
					res[i][j] = "M";
					break;
				case DIRT:
					res[i][j] = "D";
				case STOPPER:
					res[i][j] = "S";
					break;
				}
			}
		for (ILemming l : getLemVivants()){
			if(l!=null) res[l.getHeight()][l.getWidth()] = Integer.toString(l.getId());
		}
		res[getLevel().getEntranceHeight()][getLevel().getEntranceWidth()] = "E";
		res[getLevel().getExitHeight()][getLevel().getExitWidth()] = "S";
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < getLevel().getHeight(); i++) {
			for (int j = 0; j < getLevel().getWidth(); j++) {
				b.append(res[i][j]);
			}
			b.append("\n");
		}
		return b.toString();
	}

	@Override
	public void changeSpawnSpeed(int newSpawnSpeed) {
		spawnedSpeed = newSpawnSpeed;
	}
	
	@Override
	public void changeSizeColony(int newSizeColony) {
		sizeColony = newSizeColony;
	}
	
	@Override
	public void reset(){
		init(getLevel(), getSizeColony(), getSpawnSpeed());
	}


}
