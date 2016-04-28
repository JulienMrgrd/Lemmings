package contrat;

import java.util.List;

import decorateur.GameEngDecorateur;
import errors.InvariantError;
import services.ILemming;
import services.ILevel;
import servicesImplementations.GameEng;

public class GameEngContrat extends GameEngDecorateur{

	public GameEngContrat() {
		super(new GameEng());
	}
	
	@Override
	public List<ILemming> getLemVivants() {
		return delegate.getLemVivants();
	}

	@Override
	public ILemming getLemVivantById(int id) {
		return delegate.getLemVivantById(id);
	}

	@Override
	public int getSizeColony() {
		return delegate.getSizeColony();
	}

	@Override
	public int getSpawned() {
		return delegate.getSpawned();
	}

	@Override
	public int getSpawnSpeed() {
		return delegate.getSpawnSpeed();
	}

	@Override
	public ILevel getLevel() {
		return delegate.getLevel();
	}

	@Override
	public int getNbTours() {
		return delegate.getNbTours();
	}

	@Override
	public int getNbLemSauves() {
		return delegate.getNbLemSauves();
	}

	@Override
	public double getScore() {
		return delegate.getScore();
	}

	@Override
	public boolean isObstacle(int h, int w) {
		return delegate.isObstacle(h, w);
	}

	@Override
	public boolean gameOver() {
		return delegate.gameOver();
	}

	@Override
	public void init(ILevel level, int sizeColony, int spawnSpeed) {
		delegate.init(level, sizeColony, spawnSpeed);
	}

	@Override
	public void addLemming(ILemming lem) {
		delegate.addLemming(lem);
	}

	@Override
	public void killLemming(int idLem) {
		delegate.killLemming(idLem);
	}

	@Override
	public void saveLemming(int idLem) {
		delegate.saveLemming(idLem);
	}

	@Override
	public void step() {
		delegate.step();
	}

	@Override
	public boolean containsIdColony(int idLem) {
		return delegate.containsIdColony(idLem);
	}
	
	@Override
	protected void checkInvariants() throws InvariantError {
		// TODO Auto-generated method stub
		
	}

}
