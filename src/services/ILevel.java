package services;

import enumeration.Nature;

public interface ILevel {
	
	/*
	 * TODO : Invariants
	 */
	
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
	 * post : getHeight() == h
	 * 		^ getWidth() == w
	 * 		^ getEditing()
	 */
	public void init(int h, int w);
	
	
	//	============= Operators ==============
	
	/** pre: setNature(h,w,nat) require h>=0 ^ w>=0 ^ h<=getHeight() ^ w<=getWidth() ^ getEditing()
	 *  post : getNature(h,w) == nat
	 */
	public void setNature(int h, int w, Nature nat);

	
	/**pre: goPlay(entranceH, entranceW, exitH, exitW) require getEditing() ^ 
	 * 		entranceH>=0 ^ entranceW>=0 ^ entranceH<=getHeight() ^ entranceW<=getWidth() ^
	 * 		exitH>=0 ^ exitW>=0 ^ exitH<=getHeight() ^ exitW<=getWidth() ^
	 * 		for(i=0; i<getHeight(); i++){
	 * 			getNature(i, 0)==Nature.METAL;
	 * 			getNature(i, getWidth()-1)==Nature.METAL;
	 * 		} ^
	 *      for(i=0; i<getWidth(); i++){
	 * 			getNature(0, i)==Nature.METAL;
	 * 			getNature(getHeight()-1, i)==Nature.METAL;
	 * 		} ^ 
	 * 		getNature(entranceH-1, entranceW)==Nature.EMPTY ^
	 * 		getNature(entranceH+1, entranceW)==Nature.EMPTY ^
	 * 		getNature(exitH-1, exitW)==Nature.EMPTY ^
	 * 		getNature(exitH+1, exitW)==Nature.METAL 
	 * 
	 * post : !getEditing() ^ getEntranceHeight()==entranceH ^  getEntranceWidth()==entranceW ^
	 * 		  getExitHeight()==exitH ^ getExitWidth()==exitW 
	 */
	public void goPlay(int entranceH, int entranceW, int exitH, int exitW);

	/** pre : remove(h,w) require getNature(h,w)==Nature.DIRT ^ !getEditing()
	 *  post : getNature(h,w) == EMPTY;
	 */
	public void remove(int h, int w);

	/** pre : build(h,w) require getNature(h,w)==Nature.EMPTY ^ !getEditing()
	 * post : getNature(h,w) == DIRT;
	 */
	public void build(int h, int w);
	
}
