package contrat;

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
	public ILemming[] getLemVivants() {
		checkInvariants();
		ILemming[] res = delegate.getLemVivants();
		checkInvariants();
		return res;
	}

	@Override
	public ILemming getLemVivantById(int id) {
		checkInvariants();
		if(! (0<id) ) throw new PreConditionError("id<=0");
		if(! (id<=getSizeColony()) ) throw new PreConditionError("id>getSizeColony");
		if(! (containsIdColony(id)) ) throw new PreConditionError("!containsIdColony("+id+")");
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
	public int getNbLemMorts() {
		checkInvariants();
		int res = delegate.getNbLemMorts();
		checkInvariants();
		return res;
	}

	@Override
	public int getNbLemVivants() {
		checkInvariants();
		int res = delegate.getNbLemVivants();
		checkInvariants();
		return res;
	}

	@Override
	public String getScore() {
		checkInvariants();
		if(! (gameOver()) ) throw new PreConditionError("gameOver");
		String res = delegate.getScore();
		checkInvariants();
		return res;
	}

	@Override
	public boolean isObstacle(int h, int w) {
		checkInvariants();
		if(! (0 <= h)) throw new PreConditionError("h<0");
		if(! (h < getLevel().getHeight()) ) throw new PreConditionError("h >= getLevel().getHeight()");
		if(! (0 <= w) ) throw new PreConditionError("w<0");
		if(! (w < getLevel().getWidth()) ) throw new PreConditionError("w >= getLevel().getWidth()");
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
		if(! (sizeColony > 0) ) throw new PreConditionError("sizeColony <= 0");
		if(! (spawnSpeed > 0) ) throw new PreConditionError("spawnSpeed <= 0");
		
		delegate.init(level, sizeColony, spawnSpeed);
		
		if(! (getLevel() == level) ) throw new PostConditionError("getLevel() != level");
		if(! (getSizeColony() == sizeColony) ) throw new PostConditionError("getSizeColony() != sizeColony");
		if(! (getSpawnSpeed() == spawnSpeed) ) throw new PostConditionError("getSpawnSpeed() != spawnSpeed");
		if(! (getNbLemVivants()==0) ) throw new PostConditionError("getNbLemVivants()!=0");
		if(! (getSpawned()==0) ) throw new PostConditionError("getSpawned()!=0");
		if(! (getNbTours()==0 ) ) throw new PostConditionError("getNbTours()!=0");
		if(! (getNbLemSauves()==0) ) throw new PostConditionError("getNbLemSauves()!=0");
		if(! (gameOver()==false) ) throw new PostConditionError("gameOver()==true");
		checkInvariants();
	}
	
	
	// ============= Operators ==============

	@Override
	public void addLemming(ILemming lem) {
		checkInvariants();
		if(! (getSpawned() < getSizeColony()) ) throw new PreConditionError("getSpawned() >= getSizeColony()");
		if(! (containsIdColony(lem.getId())==false) ) throw new PreConditionError("containsIdColony("+lem.getId()+")==true");
		
		int preGetSpawned = getSpawned();
		int preGetLemSize = getNbLemVivants();
		
		delegate.addLemming(lem);
		
		if(! (getSpawned() == preGetSpawned+1) ) throw new PostConditionError("getSpawned() != preGetSpawned+1");
		if(! (getNbLemVivants()==preGetLemSize+1) ) throw new PostConditionError("getNbLemVivants()!=preGetLemSize+1");
		if(! (containsIdColony(lem.getId())==true) ) throw new PreConditionError("containsIdColony("+lem.getId()+")==false");
		checkInvariants();
	}

	@Override
	public void killLemming(int idLem) {
		checkInvariants();
		if(! (getNbLemVivants()>0) ) throw new PreConditionError("getNbLemVivants()>0");
		if(! (containsIdColony(idLem)==true) ) throw new PreConditionError("containsIdColony("+idLem+")==false");
		
		int preGetLemVivantsSize = getNbLemVivants();
		delegate.killLemming(idLem);

		if(! (getNbLemVivants() == preGetLemVivantsSize-1) ) throw new PostConditionError("getNbLemVivants() != preGetLemVivantsSize-1");
		if(! (containsIdColony(idLem)==false) ) throw new PostConditionError("containsIdColony(idLem)==true");
		checkInvariants();
	}

	@Override
	public void saveLemming(int idLem) {
		checkInvariants();
		if(! (0 <= idLem) ) throw new PreConditionError("0 > idLem");
		if(! (idLem < getSizeColony()) ) throw new PreConditionError("idLem >= getSizeColony()");
		if(! (containsIdColony(idLem)==true) ) throw new PreConditionError("containsIdColony(idLem)==false");
		
		int preGetNbLemSauves = getNbLemSauves(); 
		int preGetLemVivantsSize = getNbLemVivants();
		delegate.saveLemming(idLem);

		if(! (getNbLemSauves()==preGetNbLemSauves+1) ) throw new PostConditionError("getNbLemSauves()!=preGetNbLemSauves+1");
		if(! (getNbLemVivants()==preGetLemVivantsSize-1) ) throw new PostConditionError("getNbLemVivants()!=preGetLemVivantsSize-1");
		checkInvariants();
	}

	@Override
	public void step() {
		checkInvariants();
		
		int preGetNbTours = getNbTours();
		delegate.step();

		if(! (getNbTours() == preGetNbTours + 1) ) throw new PostConditionError("getNbTours() != preGetNbTours + 1");
		checkInvariants();
	}

	@Override
	public boolean containsIdColony(int idLem) {
		checkInvariants();
		if(! (0 > idLem) ) throw new PreConditionError("0 <= idLem");
		if(! (idLem <= getSizeColony()) ) throw new PreConditionError("idLem > getSizeColony()");
		
		boolean res = delegate.containsIdColony(idLem);
		checkInvariants();
		return res;
	}
	
	@Override
	public void changeSpawnSpeed(int newSpawnSpeed) {
		checkInvariants();
		if(! (newSpawnSpeed > 0) ) throw new PreConditionError("newSpawnSpeed <= 0");
		
		delegate.changeSpawnSpeed(newSpawnSpeed);
		
		if(! (getSpawnSpeed() == newSpawnSpeed) ) throw new PostConditionError("getSpawnSpeed() != newSpawnSpeed");
		checkInvariants();
	}

	@Override
	public void changeSizeColony(int newSizeColony) {
		checkInvariants();
		if(! (newSizeColony > 0) ) throw new PreConditionError("newSizeColony <= 0");
		if(! (getLevel().getEditing()==true)) throw new PreConditionError("getLevel().getEditing()==false");
		
		delegate.changeSizeColony(newSizeColony);
		
		if(! (getSizeColony() == newSizeColony) ) throw new PostConditionError("getSizeColony() != newSizeColony");
		checkInvariants();
	}

	@Override
	public void reset() {
		init(getLevel(), getSizeColony(), getSpawnSpeed());
		checkInvariants();
	}
	
	@Override
	protected void checkInvariants(){
		if(delegate.gameOver()){
			if(delegate.getNbLemVivants()!= 0){
				throw new InvariantError("Gameover mais getNbLemVivants()!=0");
			} else if (delegate.gameOver() && delegate.getSpawned()!=delegate.getSizeColony()){
				throw new InvariantError("Gameover mais getSpawned() != getSizeColony()");
			}
		}
		if(delegate.getNbLemVivants() < 0) throw new InvariantError("getNbLemVivants() < 0");
		if(delegate.getNbLemMorts() < 0) throw new InvariantError("getNbLemMorts() < 0");
		if(delegate.getNbLemSauves() < 0) throw new InvariantError("getNbLemSauves() < 0");
		if(delegate.getSpawned() < 0) throw new InvariantError("getSpawned() < 0");
		if(delegate.getNbLemVivants()  > delegate.getSizeColony()) throw new InvariantError("getNbLemVivants() > getSizeColony()");
		if(delegate.getNbLemMorts() > delegate.getSizeColony()) throw new InvariantError("getNbLemVivants() > getSizeColony()");
		if(delegate.getNbLemSauves() > delegate.getSizeColony()) throw new InvariantError("getNbLemSauves() > getSizeColony()");
		if(delegate.getSpawned() > delegate.getSizeColony()) throw new InvariantError("getSpawned() > getSizeColony()");
		
		if(delegate.getSpawned() != delegate.getNbLemSauves() + delegate .getNbLemVivants() + delegate .getNbLemMorts()){
			throw new InvariantError("getSpawned() != getNbLemSauves() + getNbLemVivants() + getNbLemMorts()");
		}
		
		if(delegate.getNbTours()<0)	throw new InvariantError("getNbTours() < 0");
	}
	
	@Override
	public String toString() {
		return delegate.toString();
	}

}
