package services;


public interface IGameEng {
	
	/*
	*   Service: GameEng
	*   Types: int, bool, double, ILemming, ILevel, ???List???
	*   
	* [invariants]
	* 	gameOver() min= (getNbLemVivants() == 0 ^ getSpawned() == getSizeColony())
	* 	0 <= getSpawned() <= getSizeColony()
	* 	0 <= getNbLemSauves() <= getSizeColony()
	*/
	
	
	
	// ============   Observation ============
	
	ILemming[] getLemVivants();
	
	/** pre : getLemVivantById(id) require 0 < id <= getSizeColony() ^ containsIdColony(id) */
	ILemming getLemVivantById(int id);
	int getSizeColony(); //Nombre de Lemming à créer
	int getSpawnSpeed(); //Vitesse d'apparition des Lemming
	ILevel getLevel();
	int getNbTours();
	int getSpawned(); //Nombre de Lemming créé
	int getNbLemSauves();
	int getNbLemMorts();
	int getNbLemVivants();
	
	/** pre : getScore() require gameOver() */
	double getScore();
	
	/** pre : isObstacle(h, w) require 0 <= h < getLevel().getHeight() ^ 0 <= w < getLevel().getWidth() */
	boolean isObstacle(int h, int w);
	
	boolean gameOver();
	
	
	// ============ CONSTRUCTORS =============
	
	/** pre : init(level, sizeColony, spawnSpeed) require sizeColony > 0 ^ spawnSpeed > 0 
	 *  <br>
	 *  post : getLevel() == level ^ getSizeColony() == sizeColony ^ getSpawnSpeed() == spawnSpeed
	 *  		^ getNbLemVivants()==0 ^ getSpawned()==0 ^ getNbTours()==0 
	 *  		^ getNbLemSauves()==0 ^ gameOver()==false
	 *  */
	void init(ILevel level, int sizeColony, int spawnSpeed);
	
	
	// ============= OPERATORS ==============

	/** pre : addLemming(lem) require getSpawned() < getSizeColony() ^ getLemVivants()[lem.getId()]==null
	 *  <br>
	 *  post : getSpawned() == getSpawned()@pre+1 ^ getNbLemVivants()==getNbLemVivants()@pre+1
	 * 		    ^ getLemVivants().contains(lem) */
	void addLemming(ILemming lem);

	/** pre : killLemming(idLem) require getNbLemVivants()>0 ^ containsIdColony(idLem)
	 *  <br>
	 *  post : getNbLemVivants() == getNbLemVivants()@pre-1 ^ getNbLemMorts() == getNbLemMorts()@pre+1 
	 *  		^ containsIdColony(idLem)==false */
	void killLemming(int idLem);

	/** pre : saveLemming(idLem) require 0 <= idLem < getSizeColony() ^ containsIdColony(idLem)
	 *  <br>
	 *  post : getNbLemSauves()==getNbLemSauves()@pre+1 ^ getNbLemVivants()==getNbLemVivants()@pre-1*/	
	void saveLemming(int idLem);
	
	/** post: getNbTours() == getNbTours()@pre + 1 */
	void step();
	
	/** pre : containsIdColony(idLem) require 0 < idLem <= getSizeColony() */
	boolean containsIdColony(int idLem);

}
