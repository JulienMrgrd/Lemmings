package services;

import enumeration.Nature;

public interface ILevel {
	
	/* Observateur */
	public int getHeight();
	public int getWidth();
	public int getInH();
	public int getInW();
	public int getOutH();
	public int getOutW();
	public boolean getEditing();
	
	/**pre: getNature(h,w) require h>=0 ^ w>=0 ^ h<=getHeight() ^ w<=getWidth()
	 */
	public Nature getNature(int h, int w);
	

	
	/* Constructors */
	
	/** 
	 * pre : init(h,w,inH,inW,outH,outW) require h>0 ^ w>0 ^ inH>0 ^ inW>0 ^ outH>0 ^ outW>0
	 * post : getHeight() == h
	 * post : getWidth() == w
	 * post : getEditing();
	 * post : getInH()==inH ^ getInW()==inW ^ getOutX()==outX ^ getOutW()==outW
	 */
	public void init(int h, int w, int inH, int inW, int outH, int outW);
	
	
	/* Operators */
	
	/** pre: setNature(h,w,nat) require getEditing==true ^ getNature(h,w)!=nat
	 *  post : getNature(h,y) == nat
	 */
	public void setNature(int h, int w, Nature nat);

	
	/**pre: for(i=0; i<getHeight; i++){
	 * 			getNature(i, 0)==Nature.METAL;
	 * 			getNature(i, getWidth())==Nature.METAL;
	 * 		}
	 * 
	 * pre: for(i=0; i<getWidth; i++){
	 * 			getNature(0, i)==Nature.METAL;
	 * 			getNature(getHeight()-1, i)==Nature.METAL;
	 * 		}
	 * pre: getEditing()
	 * Post : !getEditing()
	 */
	public void goPlay();

	/** pre : remove(h,w) require getNature(h,w)==DIRT ^ !getEditing()
	 * post : getNature(h,w) == EMPTY;
	 */
	public void remove(int h, int w);

	/** pre : build(h,w) require getNature(h,w)==EMPTY ^ !getEditing()
	 * post : getNature(h,w) == DIRT;
	 */
	public void build(int h, int w);
	
	/** pre : edit(h,w,nat) require getEditing()
	 * post : getNature(h,w)==nat
	 */
	public void edit(int h, int w,Nature nat);

	
}
