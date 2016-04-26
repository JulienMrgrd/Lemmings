package services;

import java.util.List;

public interface IGameEng {
	
	/**
	*   Service: GameEng
	*   Types: int, bool, double, ILemming
	*/
	/**
	 * [invariants]
	 * 	gameOver() min= (getColony().size() == 0 ^ getSpawned() == getSizeColony())
	 * 	0 <= getSpawned() <= getSizeColony()
	 * 	0 <= getNbSauves() <= getSizeColony()
	 */
	
	
	
	/* ============   Observators ============ */
	List<ILemming> getColony();
	/** pre : getLemm(int ln) require 0 < ln <= getSizeColony() */
	ILemming getLemming(int ln);
	int getSizeColony(); //Nombre de Lemming a créer
	int getSpawned(); //Nombre de Lemming créé
	int getSpawnSpeed(); //Vitesse d'apparition des Lemming
	ILevel getLevel();
	int getNbTours(); //INVARIANTS
	int getNbSauves(); //INVARIANTS
	
	/** pre : getScore() require gameOver() */
	double getScore();
	
	/**
	 * pre : obstacle(h, w) require 0 <= h < level().getHeight() 
	 * 		                    ^ 0 <= w < level().getWidth()
	 */
	boolean isObstacle(int h, int w);
	
	/**
	 * invariants: gameOver() == min(getColony()==0) && getSpawned()>0  
	 */
	boolean gameOver(); //INVARIANTS
	
	// CONSTRUCTORS
	/** pre : init(lvl, sizeColony, spawnSpeed) require sizeColony > 0 ^ spawnSpeed > 0 
	 *  post : getLevel == level ^ getSizeColony() == sizeColony 
	 *  						 ^ getSpawnSpeed() == spawnSpeed
	 *  						 ^ getColony() == new ArrayList() ??
	 *  						 ^ getSpawned() == 0
	 *  						 ^ getNbTours() == 0
	 *  						 ^ getNbSauves() == 0
	 *  						 ^ getScore() == 0
	 *  						 ^ gameOver() == false
	 *  */
	void init(ILevel level, int sizeColony, int spawnSpeed);
	
	// OPERATORS
	

	/**
	 * pre : addLemming(lem) require getSpawned() < getSizeColony()
	 * post: getSpawned() == getSpawned()@pre + 1 ^
	 * 	     getColony().size() == getColony().size()@pre + 1 ^
	 * 		 getColony.add(l) 
	 * 		 getSizeColony()== getSizeColony()@pre
	 */
	void addLemming(ILemming l);

	/**
	 * pre : killLemming(idLem) require getColony().size()>0 ^ containsIdColony(idLem)
	 * post: getColony().size() == getColony().size()@pre - 1 ^
	 * 		 getSizeColony()== getSizeColony()@pre 
	 * 		 for(int i=0; i < getColony().size()@pre; i++)
	 * 			if(getColony().get(i).getId()==idLem) getColony.remove(i)
	 */
	void killLemming(int idLem);

	/**
	 * pre : saveLemming(idLem) require 0 <= idLem < getSizeColony() ^ containsIdColony(idLem)
	 * post: getNbSauves() == getNbSauves()@pre + 1 ^
	 * 		 getColony().size() == getColony().size()@pre - 1 ^
	 * 		 for(int i=0; i < getColony().size()@pre; i++)
	 *			if(getColony().get(i).getId()==idLem) getColony.remove(i)
	 */	
	void saveLemming(int idLem);
	
	/**
	 * post: for(int i=0; i < getColony().size()@pre; i++)
	 * 			  getColony.set(i)=getColony().get(i).step()
	 * 		^ getNbTours() == getNbTours()@pre+1
	 */
	void step();
	
	/**
	 * pre : loadLevel(level, sizeColony, spawnSpeed) require sizeColony > 0 ^ spawnSpeed > 0 ^ level != null
	 * post: getSpawnSpeed() == spawnSpeed ^
	 * 	     getSizeColony() == sizeColony ^
	 * 		 getLevel() == level ^
	 * 		 getSpawned() == 0 ^
	 *  	 getNbTours() == 0 ^
	 *  	 getNbSauves() == 0 ^
	 *  	 getScore() == 0 ^
	 *  	 gameOver() == false
	 */
	void loadLevel(ILevel level, int sizeColony, int spawnSpeed);
	
	boolean containsIdColony(int idLem);

}
