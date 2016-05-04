package services;

import enumeration.Nature;

public interface ILevel{
	
	//	============= Observations ==============
	public int getHeight();
	public int getWidth();
	public int getEntranceHeight();
	public int getEntranceWidth();
	public int getExitHeight();
	public int getExitWidth();
	public boolean getEditing();
	
	/**pre: getNature(h,w) require h>=0 ^ w>=0 ^ h<=getHeight() ^ w<=getWidth() */
	public Nature getNature(int h, int w);
	
	
	//	============= Constructors ==============
	/** 
	 * pre : init(h,w) require h>=5 ^ w>=4
	 * <br>
	 * post : getHeight() == h
	 * 		^ getWidth() == w
	 * 		^ getEditing()
	 */
	public void init(int h, int w);
	
	
	//	============= Operators ==============
	
	/** pre: setNature(h,w,nat) require h>=0 ^ w>=0 ^ h<=getHeight() ^ w<=getWidth() ^ getEditing()
	 *  <br>
	 *  post : getNature(h,w) == nat
	 */
	public void setNature(int h, int w, Nature nat);

	
	/**pre: goPlay(entranceH, entranceW, exitH, exitW) require getEditing() ^ 
	 * 		entranceH>=0 ^ entranceW>=0 ^ entranceH<=getHeight() ^ entranceW<=getWidth() ^
	 * 		exitH>=0 ^ exitW>=0 ^ exitH<=getHeight() ^ exitW<=getWidth() ^
	 * <br>	
	 * 		for(i=0; i < getHeight(); i++){
	 * 			getNature(i, 0)==Nature.METAL;
	 * 			getNature(i, getWidth()-1)==Nature.METAL;
	 * 		} ^
	 * <br>
	 *      for(i=0; i < getWidth(); i++){
	 * 			getNature(0, i)==Nature.METAL;
	 * 			getNature(getHeight()-1, i)==Nature.METAL;
	 * 		} ^ 
	 * <br>
	 * 		getNature(entranceH-1, entranceW)==Nature.EMPTY ^
	 * 		getNature(entranceH+1, entranceW)==Nature.EMPTY ^
	 * 		getNature(exitH-1, exitW)==Nature.EMPTY ^
	 * 		getNature(exitH+1, exitW)==Nature.METAL 
	 * <br>
	 * post : !getEditing() ^ getEntranceHeight()==entranceH ^  getEntranceWidth()==entranceW ^
	 * 		  getExitHeight()==exitH ^ getExitWidth()==exitW 
	 */
	public void goPlay(int entranceH, int entranceW, int exitH, int exitW);

	/** pre : remove(h,w) require getNature(h,w)==Nature.DIRT ^ !getEditing()
	 *  <br>
	 *  post : getNature(h,w) == EMPTY;
	 */
	public void remove(int h, int w);

	/** pre : build(h,w) require getNature(h,w)==Nature.EMPTY ^ !getEditing()
	 * <br>
	 * post : getNature(h,w) == DIRT;
	 */
	public void build(int h, int w);
	
	public void reset();
	
	/** pre : addStopper(h,w) require getNature(h,w)==Nature.EMPTY ^ !getEditing()
	 * <br>
	 * post : getNature(h,w) == STOPPER;
	 */
	void addStopper(int h, int w);
	
	/** pre : removeStopper(h,w) require getNature(h,w)==Nature.STOPPER ^ !getEditing()
	 *  <br>
	 *  post : getNature(h,w) == EMPTY;
	 */
	void removeStopper(int h, int w);
	
	public void addEnter(Integer height, Integer width);
	public void addExit(Integer height, Integer width);
	
}
