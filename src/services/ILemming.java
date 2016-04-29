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
	int getNbCasesFalling();
	
	
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
	/** pre : setHeight(height) require height>0 ^ height< getGameEng().getLevel().getHeight()
     * <br>
     * post : getHeight() == height */
	void setHeight(int height);

	/** pre : setWidth(width) require width>0 ^ width< getGameEng().getLevel().getWidth()
     *  <br>
     *  post : getWidth() == width */
	void setWidth(int width);
	
	/**  post : isDroitier() != isDroitier()@pre */
	void setDirection();
	
    /** post : getEtat() == etat */
	void setEtat(EtatLemming etat);
	
	/** post :
	 *  <br>if(getGameEng().getLevel().getExitHeight()==getHeight() && getGameEng().getLevel().getExitWidth()==getWidth() ){
	 *  <br>		getGameEng().getNbLemSauves()== getGameEng().getNbLemSauves()@pre+1
	 *  <br>} else if(getEtat()@pre == EtatLemming.WALKER){
	 * 	<br>	if (getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre == Nature.EMPTY){ 
     *  <br>        getEtat() == EtatLemming.FALLER; 
     *  <br>        getWidth() == getWidth()@pre; 
     *  <br>        getHeight() == getHeight()@pre;
     *  <br>    
     *  <br>    } else if (isDroitier()@pre){
     *  <br>        if (getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre != Nature.EMPTY ||
     *  <br>            (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre != Nature.EMPTY &&
     *  <br>            getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)@pre != Nature.EMPTY)){
     *  <br>           isDroitier() == false; 
     *  <br>           getWidth() == getWidth()@pre; 
     *  <br>           getHeight() == getHeight()@pre;
     *  <br>        
     *  <br>        } else if (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre != Nature.EMPTY){
     *  <br>        	isDroitier() == true;
     *  <br>        	getWidth()==getWidth()@pre+1;
     *  <br>        	getHeight()==getHeight()@pre-1;
     *  <br>        
     *  <br>        } else {
     *  <br>           isDroitier() == true;
     *  <br>           getWidth() == getWidth()@pre+1; 
     *  <br>           getHeight() == getHeight()@pre; 
     *  <br>        }
     *  <br>    }else {
     *  <br>       if (getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre != Nature.EMPTY ||
     *  <br>            (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre != Nature.EMPTY &&
     *  <br>            getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)@pre != Nature.EMPTY)){
     *  <br>           isDroitier() == true; 
     *  <br>           getWidth() == getWidth()@pre; 
     *  <br>           getHeight() == getHeight()@pre;
     *  <br>        
     *  <br>        } else if (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre != Nature.EMPTY){
     *  <br>        	isDroitier() == false;
     *  <br>        	getWidth() == getWidth()@pre-1;
     *  <br>        	getHeight() == getHeight()@pre-1;
     *  <br>        
     *  <br>        } else {
     *  <br>           isDroitier() == false;
     *  <br>           getWidth() == getWidth()@pre-1; 
     *  <br>           getHeight() == getHeight()@pre; 
     *  <br>        }
	 *  <br>	}
	 *  <br>} else if (getEtat() == EtatLemming.FALLER){
	 *  <br>	if (getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre != Nature.EMPTY) {
     *  <br>        if (nbCasesFalling() < 8) {
     *  <br>           getEtat() == EtatLemming.WALKER; 
     *  <br>           getWidth() == getWidth()@pre; 
     *  <br>           getHeight() == getHeight()@pre;
     *  <br>        } else {
     *  <br>           !getGameEng().containsIdColony(getId())
     *  <br>        }
     *  <br>    } else {
     *  <br>    	nbCasesFalling() == nbCasesFalling()@pre+1;
     *  <br>        getWidth() == getWidth()@pre; 
     *  <br>        getHeight() == getHeight()@pre+1;
     *  <br>    }
	 *  <br>}
	 */
	void step();
	
}
