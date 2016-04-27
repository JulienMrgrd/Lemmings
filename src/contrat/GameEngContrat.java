package contrat;

import java.util.List;

import decorateur.GameEngDecorateur;
import services.IGameEng;
import services.ILemming;
import services.ILevel;
import servicesImplementations.GameEng;

public class GameEngContrat extends GameEngDecorateur{

	public GameEngContrat() {
		super(new GameEng());
	}
	
	@Override
	public List<ILemming> getLemVivants() {
		return ((IGameEng) getDelegate()).getLemVivants();
	}

	@Override
	public ILemming getLemVivantById(int id) {
		return ((IGameEng) getDelegate()).getLemVivantById(id);
	}

	@Override
	public int getSizeColony() {
		return ((IGameEng) getDelegate()).getSizeColony();
	}

	@Override
	public int getSpawned() {
		return ((IGameEng) getDelegate()).getSpawned();
	}

	@Override
	public int getSpawnSpeed() {
		return ((IGameEng) getDelegate()).getSpawnSpeed();
	}

	@Override
	public ILevel getLevel() {
		return ((IGameEng) getDelegate()).getLevel();
	}

	@Override
	public int getNbTours() {
		return ((IGameEng) getDelegate()).getNbTours();
	}

	@Override
	public int getNbLemSauves() {
		return ((IGameEng) getDelegate()).getNbLemSauves();
	}

	@Override
	public double getScore() {
		return ((IGameEng) getDelegate()).getScore();
	}

	@Override
	public boolean isObstacle(int h, int w) {
		return ((IGameEng) getDelegate()).isObstacle(h, w);
	}

	@Override
	public boolean gameOver() {
		return ((IGameEng) getDelegate()).gameOver();
	}

	@Override
	public void init(ILevel level, int sizeColony, int spawnSpeed) {
		((IGameEng) getDelegate()).init(level, sizeColony, spawnSpeed);
	}

	@Override
	public void addLemming(ILemming lem) {
		((IGameEng) getDelegate()).addLemming(lem);
	}

	@Override
	public void killLemming(int idLem) {
		((IGameEng) getDelegate()).killLemming(idLem);
	}

	@Override
	public void saveLemming(int idLem) {
		((IGameEng) getDelegate()).saveLemming(idLem);
	}

	@Override
	public void step() {
		((IGameEng) getDelegate()).step();
	}

	@Override
	public boolean containsIdColony(int idLem) {
		return ((IGameEng) getDelegate()).containsIdColony(idLem);
	}

}
