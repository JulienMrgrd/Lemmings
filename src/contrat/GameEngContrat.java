package contrat;

import java.util.List;

import decorateur.GameEngDecorateur;
import errors.InvariantError;
import errors.PostConditionError;
import errors.PreConditionError;
import services.ILemming;
import services.ILevel;
import servicesImplementations.GameEng;

public class GameEngContrat extends GameEngDecorateur{

	public GameEngContrat() {
		super(new GameEng());
	}
	
	// ============ Observations ============
	
	@Override
	public List<ILemming> getLemVivants() {
		checkInvariants();
		List<ILemming> res = delegate.getLemVivants();
		checkInvariants();
		return res;
	}

	@Override
	public ILemming getLemVivantById(int id) {
		checkInvariants();
		if(! (0<id) ) throw new PreConditionError("getLemVivantById","id<0");
		if(! (id<=getSizeColony()) ) throw new PreConditionError("getLemVivantById","id>getSizeColony");
		if(! (containsIdColony(id)) ) throw new PreConditionError("getLemVivantById","!containsIdColony("+id+")");
		ILemming res = delegate.getLemVivantById(id);
		checkInvariants();
		return res;
	}

	@Override
	public int getSizeColony() {
		checkInvariants();
		int res = delegate.getSizeColony();
		checkInvariants();
		return res;
	}

	@Override
	public int getSpawned() {
		checkInvariants();
		int res = delegate.getSpawned();
		checkInvariants();
		return res;
	}

	@Override
	public int getSpawnSpeed() {
		checkInvariants();
		int res = delegate.getSpawnSpeed();
		checkInvariants();
		return res;
	}

	@Override
	public ILevel getLevel() {
		checkInvariants();
		ILevel res = delegate.getLevel();
		checkInvariants();
		return res;
	}

	@Override
	public int getNbTours() {
		checkInvariants();
		int res = delegate.getNbTours();
		checkInvariants();
		return res;
	}

	@Override
	public int getNbLemSauves() {
		checkInvariants();
		int res = delegate.getNbLemSauves();
		checkInvariants();
		return res;
	}

	@Override
	public double getScore() {
		checkInvariants();
		if(! (gameOver()) ) throw new PreConditionError("getScore","gameOver");
		double res = delegate.getScore();
		checkInvariants();
		return res;
	}

	@Override
	public boolean isObstacle(int h, int w) {
		checkInvariants();
		if(! (0 <= h)) throw new PreConditionError("isObstacle","h<0");
		if(! (h < getLevel().getHeight()) ) throw new PreConditionError("isObstacle","h >= getLevel().getHeight()");
		if(! (0 <= w) ) throw new PreConditionError("isObstacle","w<0");
		if(! (w < getLevel().getWidth()) ) throw new PreConditionError("isObstacle","w >= getLevel().getWidth()");
		boolean res = delegate.isObstacle(h, w);
		checkInvariants();
		return res;
	}

	@Override
	public boolean gameOver() {
		checkInvariants();
		boolean res = delegate.gameOver();
		checkInvariants();
		return res;
	}
	
	
	// ============ Constructors =============

	@Override
	public void init(ILevel level, int sizeColony, int spawnSpeed) {
		checkInvariants();
		if(! (sizeColony > 0) ) throw new PreConditionError("init","sizeColony <= 0");
		if(! (spawnSpeed > 0) ) throw new PreConditionError("init","spawnSpeed <= 0");
		
		delegate.init(level, sizeColony, spawnSpeed);
		
		if(! (getLevel() == level) ) throw new PostConditionError("init","getLevel() != level");
		if(! (getSizeColony() == sizeColony) ) throw new PostConditionError("init","getSizeColony() != sizeColony");
		if(! (getSpawnSpeed() == spawnSpeed) ) throw new PostConditionError("init","getSpawnSpeed() != spawnSpeed");
		if(! (getLemVivants().size()==0) ) throw new PostConditionError("init","getLemVivants().size()!=0");
		if(! (getSpawned()==0) ) throw new PostConditionError("init","getSpawned()!=0");
		if(! (getNbTours()==0 ) ) throw new PostConditionError("init","getNbTours()!=0");
		if(! (getNbLemSauves()==0) ) throw new PostConditionError("init","getNbLemSauves()!=0");
		if(! (getScore()==0) ) throw new PostConditionError("init","getScore()!=0");
		if(! (gameOver()==false) ) throw new PostConditionError("init","gameOver()==true");
		checkInvariants();
	}
	
	
	// ============= Operators ==============

	@Override
	public void addLemming(ILemming lem) {
		checkInvariants();
		if(! (getSpawned() < getSizeColony()) ) throw new PreConditionError("addLemming","getSpawned() >= getSizeColony()");
		if(! (getLemVivants().contains(lem)==false) ) throw new PreConditionError("addLemming","getLemVivants().contains(lem)==true");
		
		int preGetSpawned = getSpawned();
		int preGetLemSize = getLemVivants().size();
		
		delegate.addLemming(lem);
		
		if(! (getSpawned() == preGetSpawned+1) ) throw new PostConditionError("addLemming","getSpawned() != preGetSpawned+1");
		if(! (getLemVivants().size()==preGetLemSize+1) ) throw new PostConditionError("addLemming","getLemVivants().size()!=preGetLemSize+1");
		if(! (getLemVivants().contains(lem)) ) throw new PostConditionError("addLemming","!getLemVivants().contains(lem)");
		
		checkInvariants();
	}

	@Override
	public void killLemming(int idLem) {
		checkInvariants();
		if(! (getLemVivants().size()>0) ) throw new PreConditionError("killLemming","getLemVivants().size()>0");
		if(! (containsIdColony(idLem)) ) throw new PreConditionError("killLemming","!containsIdColony("+idLem+")");
		
		int preGetLemVivantsSize = getLemVivants().size();
		delegate.killLemming(idLem);

		if(! (getLemVivants().size() == preGetLemVivantsSize-1) ) throw new PostConditionError("killLemming","getLemVivants().size() != preGetLemVivantsSize-1");
		if(! (containsIdColony(idLem)==false) ) throw new PostConditionError("killLemming","containsIdColony(idLem)==true");
		checkInvariants();
	}

	@Override
	public void saveLemming(int idLem) {
		checkInvariants();
		if(! (0 <= idLem) ) throw new PreConditionError("saveLemming","0 > idLem");
		if(! (idLem < getSizeColony()) ) throw new PreConditionError("saveLemming","idLem >= getSizeColony()");
		if(! (containsIdColony(idLem)) ) throw new PreConditionError("saveLemming","!containsIdColony(idLem)");
		
		int preGetNbLemSauves = getNbLemSauves(); 
		int preGetLemVivantsSize = getLemVivants().size();
		delegate.saveLemming(idLem);

		if(! (getNbLemSauves()==preGetNbLemSauves+1) ) throw new PostConditionError("saveLemming","getNbLemSauves()!=preGetNbLemSauves+1");
		if(! (getLemVivants().size()==preGetLemVivantsSize-1) ) throw new PostConditionError("saveLemming","getLemVivants().size()!=preGetLemVivantsSize-1");
		checkInvariants();
	}

	@Override
	public void step() {
		checkInvariants();
		
		int preGetNbTours = getNbTours();
		delegate.step();

		if(! (getNbTours() == preGetNbTours + 1) ) throw new PostConditionError("step","getNbTours() != preGetNbTours + 1");
		checkInvariants();
	}

	@Override
	public boolean containsIdColony(int idLem) {
		checkInvariants();
		if(! (0 > idLem) ) throw new PreConditionError("containsIdColony","0 <= idLem");
		if(! (idLem <= getSizeColony()) ) throw new PreConditionError("containsIdColony","idLem > getSizeColony()");
		
		boolean res = delegate.containsIdColony(idLem);
		checkInvariants();
		return res;
	}
	
	@Override
	protected void checkInvariants() throws InvariantError {
		// TODO Auto-generated method stub
		
	}

}
