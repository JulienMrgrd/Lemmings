package services;

public interface ILemming {

	/* Observations */
	int getWidth();
	int getHeight();
	boolean isDroitier();
	EtatLemming getEtat();
	int getId();
	IGameEng getGameEng();
	int nbCasesFalling();
	
	
	/* Constructors */
	void init(IGameEng gameEng);
	
	/* Operators */
	/** Pre : setWidth(width) require width>0 
     * Post : getWidth() == width */
	void setWidth(int width);
	
	/** Pre : setHeight(height) require height>0 
     * Post : getHeight() == height */
	void setHeight(int height);
	
	/**  Post : isDroitier()= !@pre */
	void setDirection();
	
    /** Post :  getEtat() == etat */
	void setEtat(EtatLemming etat);
	
	/** Post :
	 * 	if(getEtat() == WALKER){
	 * 		if (getGameEng().getLevel().getNature(getWidth()@pre, getHeight()@pre+1) == EMPTY){ 
     *          getEtat() == FALLER; 
     *          getWidth() == getWidth()@pre; 
     *          getHeight() == getHeight()@pre;
     *      
     *      } else if (isDroitier()@pre){
     *          if (getGameEng().getLevel().getNature(getWidth()@pre+1, getHeight()@pre) != EMPTY &&
     *              getGameEng().getLevel().getNature(getWidth()@pre+1, getHeight()@pre-1) != EMPTY){
     *             isDroitier() == false; 
     *             getWidth() == getWidth()@pre; 
     *             getHeight() == getHeight()@pre;
     *             
     *          } else {
     *             getWidth() == getWidth()@pre+1; 
     *             getHeight() == getHeight()@pre; 
     *          }
     *      }else {
     *          if (getGameEng().getLevel().getNature(getWidth()@pre-1, getHeight()@pre) != EMPTY &&
     *              getGameEng().getLevel().getNature(getWidth()@pre-1, getHeight()@pre-1) != EMPTY){
     *             isDroitier() == false; 
     *             getWidth() == getWidth()@pre; 
     *             getHeight() == getHeight()@pre;
     *          
     *          } else {
     *             getWidth() == getWidth()@pre-1; 
     *             getHeight() == getHeight()@pre; 	
     *          }
	 *  	}
	 *  } else if (getEtat() == WALKER){
	 *  	if (getGameEng().getLevel().getNature(getWidth()@pre, getHeight()@pre+1) != EMPTY) {
     *          if (nbCasesFalling() < 8) {
     *             getEtat = WALKER; 
     *             getWidth() == getWidth()@pre; 
     *             getHeight() == getHeight()@pre;
     *          } else {
     *             getGameEng().getAllLemInLife()@pre.size() == getGameEng().getAllLemInLife().size()-1;
     *          }
     *      } else {
     *          getWidth() == getWidth()@pre; 
     *          getHeight() == getHeight()@pre+1;
     *      }
	 *  }
	 */
	void step();
}
