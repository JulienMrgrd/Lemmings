package services;

import java.util.List;

public interface IGameEng {
	
	/**
	*   Service: GameEng
	*   Types: int, bool, double, ILemming, ILevel, ???List???
	*   
	* [invariants]
	* 	gameOver() min= (getLemVivants().size() == 0 ^ getSpawned() == getSizeColony())
	* 	0 <= getSpawned() <= getSizeColony()
	* 	0 <= getNbLemSauves() <= getSizeColony()
	*/
	
	
	
	// ============   Observation ============
	
	List<ILemming> getLemVivants();
	
	/** pre : getLemVivantById(id) require 0 < id <= getSizeColony() ^ containsIdColony(id) */
	ILemming getLemVivantById(int id);
	int getSizeColony(); //Nombre de Lemming à créer
	int getSpawned(); //Nombre de Lemming créé
	int getSpawnSpeed(); //Vitesse d'apparition des Lemming
	ILevel getLevel();
	int getNbTours();
	int getNbLemSauves();
	
	/** pre : getScore() require gameOver() */
	double getScore();
	
	/** pre : isObstacle(h, w) require 0 <= h < getLevel().getHeight() ^ 0 <= w < getLevel().getWidth() */
	boolean isObstacle(int h, int w);
	
	// TODO : voir invariants
	/**
	 * invariants: gameOver() == min(getLemVivants()==0) && getSpawned()>0  
	 */
	boolean gameOver();
	
	
	// ============ CONSTRUCTORS =============
	
	/** pre : init(level, sizeColony, spawnSpeed) require sizeColony > 0 ^ spawnSpeed > 0 
	 *  <br>
	 *  post : getLevel() == level ^ getSizeColony() == sizeColony ^ getSpawnSpeed() == spawnSpeed
	 *  		^ getLemVivants().size()==0 ^ getSpawned()==0 ^ getNbTours()==0 
	 *  		^ getNbLemSauves()==0 ^ getScore()==0 ^ gameOver()==false
	 *  */
	void init(ILevel level, int sizeColony, int spawnSpeed);
	
	
	// ============= OPERATORS ==============

	/** pre : addLemming(lem) require getSpawned() < getSizeColony() ^ getLemVivants().contains(lem)==false
	 *  <br>
	 *  post : getSpawned() == getSpawned()@pre+1 ^ getLemVivants().size()==getLemVivants().size()@pre+1
	 * 		    ^ getLemVivants().contains(lem) */
	void addLemming(ILemming lem);

	/** pre : killLemming(idLem) require getLemVivants().size()>0 ^ containsIdColony(idLem)
	 *  <br>
	 *  post : getLemVivants().size() == getLemVivants().size()@pre-1
	 * 		    ^ containsIdColony(idLem)==false */
	void killLemming(int idLem);

	/** pre : saveLemming(idLem) require 0 <= idLem < getSizeColony() ^ containsIdColony(idLem)
	 *  <br>
	 *  post : getNbLemSauves()==getNbLemSauves()@pre + 1 ^ getLemVivants().size()==getLemVivants().size()@pre-1*/	
	void saveLemming(int idLem);
	
	/** post: getNbTours() == getNbTours()@pre + 1 */
	void step();
	
	/** pre : containsIdColony(idLem) require 0 < idLem <= getSizeColony() */
	boolean containsIdColony(int idLem);

}
