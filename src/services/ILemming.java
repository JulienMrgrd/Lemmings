package services;

import java.util.List;

import enumeration.EtatLemming;
import enumeration.Nature;

public interface ILemming {
	
	
	/*
	 * TODO : Invariants
	 */

	// ============= Observations ===============
	int getWidth();
	int getHeight();
	int getId();
	boolean isDroitier();
	List<EtatLemming> getEtat();
	IGameEng getGameEng();
	int getNbCasesFalling();
	int getNbToursBomber();
	int getNbTourBuilder();
	int getNbDallePose();
	int getNbCreuse();
	
	

	// ============= Constructors ============== 
	/** post : getGameEng() == gameEng 
	 * 			^ getWidth() == gameEng.getLevel().getEntranceWidth()
	 * 			^ getHeight() == gameEng.getLevel().getEntranceHeight()
	 * 			^ getId() == gameEng.getSpawned()
	 * 			^ isDroitier()
	 * 			^ getEtat().contains(EtatLemming.FALLER)
	 * 			^ getNbCasesFalling() == 0
	 *			^ getNbToursBomber() == 0
	 *			^ getNbTourBuilder() == 0
	 *			^ getNbDallePose() == 0
	 *			^ getNbCreuse() ==0
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
	
    /** post : getEtat().contains(etat) ^ 
     *         if(etat==EtatLemming.STOPPER){
     *         			gameEng.getLevel().getNature(this.height, this.width)==Nature.STOPPER;
	 *					gameEng.getLevel().getNature(this.height-1, this.width)==Nature.STOPPER;
     *         }
     *         
     */ //TODO MODIFIER AVEC STOPPER
	void setEtat(EtatLemming etat);
	
	
	//TODO PAR RAPPORT A LEMMING
	/**
	 * post: 
	 * boolean canMonter=false;
		


		if(getGameEng().getLevel().getExitHeight()@pre==getHeight()@pre && getGameEng().getLevel().getExitWidth()@pre==getWidth()@pre ){
			getGameEng().getNbLemSauves()== getGameEng().getNbLemSauves()@pre+1
		} else {
			if (getEtat().contains(EtatLemming.BOMBER)@pre){
				//TODO
				if(getNbToursBomber()==5){
					System.out.println();
					
					
					if(height==getGameEng().getLevel().getHeight()-2){//-1==METAL  -2 en bas du plateau
						
					}
					
					gameEng.killLemming(id);
					return;
				}
					
				
			}
			if (getEtat().contains(EtatLemming.CLIMBER)@pre){
				if(isDroitier()@pre){
					if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre!=Nature.EMPTY && 
							getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)@pre!=Nature.EMPTY  ){
						if(getHeight()@pre+2< getGameEng().getLevel().getHeight()@pre && getGameEng().getLevel().getNature(getHeight()@pre+2, getWidth()@pre)@pre==Nature.EMPTY){
							getWidth()==getWidth()@pre+1;
							canMonter=true;
						}else {
							isDroitier() == !isDroitier()@pre;
						}
					}
				}else {
					if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre!=Nature.EMPTY && 
							getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)@pre!=Nature.EMPTY  ){
						if(getHeight()@pre+2< getGameEng().getLevel().getHeight()@pre && getGameEng().getLevel().getNature(getHeight()@pre+2, getWidth()@pre)@pre==Nature.EMPTY){
							getHeight()==getHeight()@pre+1;
							canMonter=true;
						}else {
							isDroitier() == !isDroitier()@pre;
						}
					}
				}
	
			}else if(getEtat().contains(EtatLemming.WALKER)@pre&&!canMonter){
				if (getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre == Nature.EMPTY){ 
					getEtat().contains(EtatLemming.FALLER);
	
				} else if (isDroitier()@pre){
					if (getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre != Nature.EMPTY ||
							(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre != Nature.EMPTY &&
							getHeight()@pre-2>0 && 
							getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)@pre != Nature.EMPTY)){
						isDroitier() == false; 
	
					} else if (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre != Nature.EMPTY){
						isDroitier() == true;
						getWidth() == getWidth()@pre+1;
						getHeight() == getHeight()@pre-1;
	
					} else {
						isDroitier() == true;
						getWidth() == getWidth()@pre+1; 
					}
				}else {
					if (getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre != Nature.EMPTY ||
							(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre != Nature.EMPTY &&
							getHeight()@pre-2>0 && 
							getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)@pre != Nature.EMPTY)){
						isDroitier() == true; 
	
					} else if (getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre != Nature.EMPTY){
						isDroitier() == false;
						getWidth() == getWidth()@pre-1;
						getHeight() == getHeight()@pre-1;
	
					} else {
						isDroitier() == false;
						getWidth() == getWidth()@pre-1; 
					}
				}
			} else if (getEtat().contains(EtatLemming.FALLER)@pre&&!canMonter){
				
				if(!getEtat().contains(EtatLemming.FLOATER)@pre){
					if (getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre != Nature.EMPTY) {
						if (getNbCasesFalling()@pre < 8) {
							getEtat().contains(EtatLemming.WALKER);
							getNbCasesFalling()==0;
						}else {
							!getGameEng().containsIdColony(getId())
						}
					}else {
						getNbCasesFalling() = getNbCasesFalling()@pre+1;
						getHeight() = getHeight()@pre+1;
					}
				}else{
					if (getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre != Nature.EMPTY){
						 getEtat().contains(EtatLemming.WALKER);
					}else {
						if(getNbCasesFalling()@pre>0 && getNbCasesFalling()@pre%2==0) getHeight() = getHeight()@pre+1;
						getNbCasesFalling()==getNbCasesFalling()@pre+1;
					}
				}
				
			}else if(getEtat().contains(EtatLemming.DIGGER)@pre&&!canMonter){
				if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre==Nature.EMPTY) getEtat().contains(EtatLemming.FALLER);
				else if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre==Nature.METAL) getEtat().contains(EtatLemming.WALKER);
				else if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre==Nature.DIRT){
					getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)==Nature.EMPTY;
					if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)@pre==Nature.DIRT) getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)==Nature.EMPTY;
					if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)@pre==Nature.DIRT) getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)==Nature.EMPTY;
					getHeight()==getHeight()@pre+1;
				}
				
			}else if (getEtat().contains(EtatLemming.BUILDER)@pre&&!canMonter){//TODO
				
				
				
				
			}else if (getEtat().contains(EtatLemming.STOPPER)@pre&&!canMonter){
				
				//RIEN A FAIRE
				
				







			}else if (getEtat().contains(EtatLemming.BASHER)@pre&&!canMonter){ //DETRUIT LES MURS SUR SA LIGNE //TODO A MODIFIER SI MODIF LEMMING
				if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre==Nature.EMPTY) getEtat().contains(EtatLemming.FALLER);
				else {
					if(isDroitier()@pre){
						if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre!=Nature.METAL){
							if(getNbCreuse()@pre<20){
								if( getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)==Nature.EMPTY;
									getNbCreuse()==getNbCreuse()@pre+1;
								}
								if(getWidth()@pre+2 < getGameEng().getLevel().getWidth()@pre && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+2)@pre!=Nature.METAL){
									if(getNbCreuse()@pre<20){
										if( getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+2)@pre==Nature.DIRT){
											getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+2)==Nature.EMPTY;
											getNbCreuse()==getNbCreuse()@pre+1;
										}
										if(getWidth()@pre+3 < getGameEng().getLevel().getWidth()@pre && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+3)@pre!=Nature.METAL){
											if(getNbCreuse()@pre<20){
												if( getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+3)@pre==Nature.DIRT){
													getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+3)==Nature.EMPTY;
													getNbCreuse()==getNbCreuse()@pre+1;
												}
											}else{
												getEtat().contains(EtatLemming.WALKER);
											}
										}
									}else{
										getEtat().contains(EtatLemming.WALKER);
									}
								}
								getWidth()=getWidth()@pre+1;
							}else{
								getEtat().contains(EtatLemming.WALKER);
							}
						}
					} else {
						if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)!=Nature.METAL){
							if(getNbCreuse()@pre<20){
								if( getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)==Nature.EMPTY;
									getNbCreuse()==getNbCreuse()@pre+1;
								}
								if(getWidth()@pre-2 > 0 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-2)!=Nature.METAL){
									if(getNbCreuse()@pre<20){
										if( getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-2)==Nature.DIRT){
											getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-2)==Nature.EMPTY;
											getNbCreuse()==getNbCreuse()@pre+1;
										}
										if(getWidth()@pre-3 > 0 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-3)!=Nature.METAL){
											if(getNbCreuse()@pre<20){
												if( getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-3)==Nature.DIRT){
													getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-3)==Nature.EMPTY;
													getNbCreuse()==getNbCreuse()@pre+1;
												}
											}else{
												getEtat().contains(EtatLemming.WALKER);
											}
										}
									}else{
										getEtat().contains(EtatLemming.WALKER);
									}
								}
								getWidth()==getWidth()@pre-1;
							}else{
								getEtat().contains(EtatLemming.WALKER);
							}
						}
					}
				}
				



			}else if (getEtat().contains(EtatLemming.MINER)@pre&&!canMonter){
				if(isDroitier()@pre){
					if(getHeight()@pre+1< getGameEng().getLevel().getHeight()@pre &&
							getWidth()@pre+1< getGameEng().getLevel().getWidth()@pre &&
							getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)@pre==Nature.DIRT){
						getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)==Nature.EMPTY;
						getHeight()==getHeight()@pre+1;
					}else if(getHeight()@pre-1>0 &&
							getWidth()@pre+1< getGameEng().getLevel().getWidth()@pre &&
							getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre==Nature.DIRT){
						getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)==Nature.EMPTY;
						getHeight()==getHeight()@pre-1;
					}
					getWidth()==getWidth()@pre+1;
				}else{
					if(getHeight()@pre+1< getGameEng().getLevel().getHeight()@pre &&
							getWidth()@pre-1>0 &&
							getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)@pre==Nature.DIRT){
						getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)==Nature.EMPTY;
						getHeight()==getHeight()@pre+1;
					}else if(getHeight()@pre-1>0 &&
							getWidth()@pre-1>0 &&
							getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre==Nature.DIRT){
						getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)==Nature.EMPTY;
						getHeight()==getHeight()@pre-1;
					}
					getWidth()==getWidth()@pre-1;
				}
				
			}
		}
	 */
	void step();
	
}
