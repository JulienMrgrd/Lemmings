package services;

import java.util.Set;

public interface IGameEng {

	/* Observators */
	public ILevel getLevel();
	
	/**
	 * pre : 0 <= h <= getLevel().getHeight() ^ 0 <= w <= getLevel().getWidth()
	 */
	public boolean isObstacle(int h, int w);
	public int getSizeColony();
	public int getSpawnSpeed();
	public boolean gameOver();
	
	/** pre : gameOver() */
	public double score();
	
	/** pre : getLevel().isEditing() == false */
	public int getNbTours();
	
	/** pre : !gameOver() */
	public Set<ILemming> getAllLemInLife();
	
	/** pre : !gameOver()
	 * pre : i in getNbLemInLife()
	 */
	public ILemming getLemming(int i);
	public int getNbLemSave();
	public int getNbLemDead();
	public int getNbLemInLife();
	public int getNbLemCreate();

	
	/* Constructors */
	/** pre : sizeColony > 0
	 * pre : spawnSpeed > 0
	 */
	public void init(int sizeColony, int spawnSpeed);


	/* Operators */
	
	/**
	 * pre : !gameOver()
	 * pre : getNbLemInLife() > 0
	 */
	public void step();
	public void tuerLemming(int i);
	public void sauverLemming(int i);


}
