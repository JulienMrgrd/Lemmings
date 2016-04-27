package services;

import enumeration.EtatLemming;

public interface ILemming {
	
	
	/*
	 * TODO : Invariants
	 */

	// ============= Observations ===============
	int getWidth();
	int getHeight();
	int getId();
	boolean isDroitier();
	EtatLemming getEtat();
	IGameEng getGameEng();
	int nbCasesFalling();
	
	
	// ============= Constructors ============== 
	/** post : getGameEng() == gameEng 
	 * 			^ getWidth() == gameEng.getLevel().getEntranceWidth()
	 * 			^ getHeight() == gameEng.getLevel().getEntranceHeight()
	 * 			^ getId() == gameEng.getSpawned()
	 * 			^ isDroitier()
	 * 			^ getEtat() == EtatLemming.FALLER
	 * 			^ nbCasesFalling() == 0
	 */
	void init(IGameEng gameEng);
	
	
	// ============= Operators ============= 
	/** pre : setHeight(height) require height>0 ^ height<getGameEng().getLevel().getHeight()
     * post : getHeight() == height */
	void setHeight(int height);

	/** pre : setWidth(width) require width>0 ^ width<getGameEng().getLevel().getWidth()
     *  post : getWidth() == width */
	void setWidth(int width);
	
	/**  post : isDroitier() != isDroitier()@pre */
	void setDirection();
	
    /** post : getEtat() == etat */
	void setEtat(EtatLemming etat);
	
	/** post :
	 *  if(getGameEng().getLevel().getExitHeight()==getHeight() && getGameEng().getLevel().getExitWidth()==getWidth() ){
	 *  			 getGameEng().saveLemming(getId());
	 *  }else if(getEtat()@pre == EtatLemming.WALKER){
	 * 		if (getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre == Nature.EMPTY){ 
     *          getEtat() == EtatLemming.FALLER; 
     *          getWidth() == getWidth()@pre; 
     *          getHeight() == getHeight()@pre;
     *      
     *      } else if (isDroitier()@pre){
     *          if (getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre != Nature.EMPTY ||
     *              (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre != Nature.EMPTY &&
     *              getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)@pre != Nature.EMPTY)){
     *             isDroitier() == false; 
     *             getWidth() == getWidth()@pre; 
     *             getHeight() == getHeight()@pre;
     *          
     *          } else if (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre != Nature.EMPTY){
     *          	isDroitier() == true;
     *          	getWidth()==getWidth()@pre+1;
     *          	getHeight()==getHeight()@pre-1;
     *          
     *          } else {
     *             isDroitier() == true;
     *             getWidth() == getWidth()@pre+1; 
     *             getHeight() == getHeight()@pre; 
     *          }
     *      }else {
     *         if (getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre != Nature.EMPTY ||
     *              (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre != Nature.EMPTY &&
     *              getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)@pre != Nature.EMPTY)){
     *             isDroitier() == true; 
     *             getWidth() == getWidth()@pre; 
     *             getHeight() == getHeight()@pre;
     *          
     *          } else if (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre != Nature.EMPTY){
     *          	isDroitier() == false;
     *          	getWidth() == getWidth()@pre-1;
     *          	getHeight() == getHeight()@pre-1;
     *          
     *          } else {
     *             isDroitier() == false;
     *             getWidth() == getWidth()@pre-1; 
     *             getHeight() == getHeight()@pre; 
     *          }
	 *  	}
	 *  } else if (getEtat() == EtatLemming.FALLER){
	 *  	if (getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre != Nature.EMPTY) {
     *          if (nbCasesFalling() < 8) {
     *             getEtat() == EtatLemming.WALKER; 
     *             getWidth() == getWidth()@pre; 
     *             getHeight() == getHeight()@pre;
     *          } else {
     *             getGameEng().killLemming(getId());
     *          }
     *      } else {
     *      	nbCasesFalling() == nbCasesFalling()@pre+1;
     *          getWidth() == getWidth()@pre; 
     *          getHeight() == getHeight()@pre+1;
     *      }
	 *  }
	 */
	void step();
	
}
