package services;

import java.util.List;

import enumeration.EtatLemming;

public interface ILemming {
	
	
	// ============= Observations ===============
	int getWidth();
	int getHeight();
	int getId();
	boolean isDroitier();
	List<EtatLemming> getEtats();
	IGameEng getGameEng();
	int getNbCasesFalling();
	int getNbToursBomber();
	int getNbToursBuilder();
	int getNbDallePose();
	int getNbCreuse();
	
	

	// ============= Constructors ============== 
	/** post : getGameEng() == gameEng 
	 * 			^ getWidth() == gameEng.getLevel().getEntranceWidth()
	 * 			^ getHeight() == gameEng.getLevel().getEntranceHeight()
	 * 			^ getId() == gameEng.getSpawned()
	 * 			^ isDroitier()
	 * 			^ getEtats().contains(EtatLemming.FALLER)
	 * 			^ getNbCasesFalling() == 0
	 *			^ getNbToursBomber() == 0
	 *			^ getNbToursBuilder() == 0
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
	
    /** post : getEtats().contains(etat) ^ 
     *         if(etat==EtatLemming.STOPPER){
     *         			gameEng.getLevel().getNature(height, width)==Nature.STOPPER;
	 *					gameEng.getLevel().getNature(height-1, width)==Nature.STOPPER;
     *         }
     *         if(getEtats()@pre.contains(EtatLemming.FLOATER)){
     *         		getEtats().contains(EtatLemming.FLOATER)
     *         }
     *         if(getEtats()@pre.contains(EtatLemming.BOMBER)){
     *         		getEtats().contains(EtatLemming.BOMBER)
     *         }
     *         if(getEtats()@pre.contains(EtatLemming.CLIMBER)){
     *         		getEtats().contains(EtatLemming.CLIMBER)
     *         } 
     */
	void setEtat(EtatLemming etat);
	
	
	/**
	 * post: 
		if(getGameEng().getLevel().getExitHeight()@pre==getHeight()@pre && getGameEng().getLevel().getExitWidth()@pre==getWidth()@pre ){
			getGameEng().getNbLemSauves()== getGameEng().getNbLemSauves()@pre+1
		} else {
			if (getEtats().contains(EtatLemming.BOMBER)@pre){
				if(getNbToursBomber()@pre==5){
					
					if(getWidth()@pre-2>=0){
						if(getWidth()@pre+2< getGameEng().getLevel().getWidth()@pre){
							for(int i=getWidth()@pre-2;i<=getWidth()@pre+2;i++){
								if(getGameEng().getLevel().getNature(getHeight()@pre, i)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre, i)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre-1, i)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre-1, i)==Nature.EMPTY;
								}
							}
							for(int i=width-1;i<=width+1;i++){
								if(getGameEng().getLevel().getNature(getHeight()@pre-2, i)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre-2, i)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre+1, i)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre+1, i)==Nature.EMPTY;
								}
							}
						}else{
							for(int i=getWidth()@pre-2;i<=getWidth()@pre+1;i++){
								if(getGameEng().getLevel().getNature(getHeight()@pre+1, i)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre+1, i)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre, i)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre, i)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre-1, i)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre-1, i)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre-2, i)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre-2, i)==Nature.EMPTY;
								}
							}
						}
					}else{
						//width+2<gameEng.getLevel().getWidth() CAR TAILLE DU PLATEAU DE 4 AU MOINS width>=4
						for(int i=width@pre-1;i<=width@pre+2;i++){
							if(getGameEng().getLevel().getNature(getHeight()@pre+1, i)@pre==Nature.DIRT){
								getGameEng().getLevel().getNature(getHeight()@pre+1, i)==Nature.EMPTY;
							}
							if(getGameEng().getLevel().getNature(getHeight()@pre, i)@pre==Nature.DIRT){
								getGameEng().getLevel().getNature(getHeight()@pre, i)==Nature.EMPTY;
							}
							if(getGameEng().getLevel().getNature(getHeight()@pre-1, i)@pre==Nature.DIRT){
								getGameEng().getLevel().getNature(getHeight()@pre-1, i)==Nature.EMPTY;
							}
							if(getGameEng().getLevel().getNature(getHeight()@pre-2, i)@pre==Nature.DIRT){
								getGameEng().getLevel().getNature(getHeight()@pre-2, i)==Nature.EMPTY;
							}
						}
					}
					!getGameEng().containsIdColony(getId())
					return;
				}
				getNbToursBomber()=getNbToursBomber()@pre+1
			}
			
			
			if (getEtats().contains(EtatLemming.CLIMBER)@pre){
				if(isDroitier()@pre){
					if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre!=Nature.EMPTY 
							&& getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre!=Nature.EMPTY){
						if(getHeight()@pre-2>=0
							&& getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre)@pre==Nature.EMPTY){
							getHeight()==getHeight()@pre-1;
							canMonter=true;
						}else{
							getEtats().contains(EtatLemming.FALLER);
							isDroitier() == !isDroitier()@pre;
						}
					}else{}
				}else {  
					if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre!=Nature.EMPTY
							&& getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre!=Nature.EMPTY){
						if(getHeight()@pre-2>=0
							&& getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre)@pre==Nature.EMPTY){
							getHeight()==getHeight()@pre-1;
							canMonter=true;
						}else{
							getEtats().contains(EtatLemming.FALLER);
							isDroitier() == !isDroitier()@pre;
						}
					}else{}
				}
	
			}
			if(!canMonter){
				if(getEtats().contains(EtatLemming.WALKER)@pre){
					if (getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre == Nature.EMPTY){ 
						getEtats().contains(EtatLemming.FALLER);
		
					} else if (isDroitier()@pre){
						if (getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre != Nature.EMPTY ||
								(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre != Nature.EMPTY &&
								getHeight()@pre-2>=0 && 
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
								getHeight()@pre-2>=0 && 
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
					
				} else if (getEtats().contains(EtatLemming.FALLER)@pre){
					
					if(!getEtats().contains(EtatLemming.FLOATER)@pre){
						if (getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre != Nature.EMPTY) {
							if (getNbCasesFalling()@pre < 8) {
								getEtats().contains(EtatLemming.WALKER);
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
							getNbCasesFalling()==0;
							getEtats().contains(EtatLemming.WALKER);
						}else {
							if(getNbCasesFalling()@pre%2==0) getHeight() = getHeight()@pre+1;
							getNbCasesFalling()==getNbCasesFalling()@pre+1;
						}
					}
					
				}else if(getEtats().contains(EtatLemming.DIGGER)@pre){
					if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre==Nature.EMPTY){
					 	getEtats().contains(EtatLemming.FALLER);
				 	}
					else if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre==Nature.METAL
							||getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre==Nature.STOPPER){
					 	getEtats().contains(EtatLemming.WALKER);
				 	}
					else if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre==Nature.DIRT){
						getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)==Nature.EMPTY;
						if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)@pre==Nature.DIRT) getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)==Nature.EMPTY;
						if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)@pre==Nature.DIRT) getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)==Nature.EMPTY;
						getHeight()==getHeight()@pre+1;
					}
					
				}else if (getEtats().contains(EtatLemming.BUILDER)@pre){
					
					if(isDroitier()@pre){
						 if(getWidth()@pre+3<getGameEng().getLevel().getWidth()@pre && getHeight()@pre-2>=0
								 && getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+2)==Nature.EMPTY
								 && getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)==Nature.EMPTY
								 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)==Nature.EMPTY
								 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+2)==Nature.EMPTY
								 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+3)==Nature.EMPTY){
							 if(getNbToursBuilder()@pre%3==0){
								 getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)==Nature.DIRT;
								 getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+2)==Nature.DIRT;
								 getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+3)==Nature.DIRT;
								 getNbDallePose()==getNbDallePose()@pre+1;
								 getHeight()@pre==getHeight()@pre-1;
								 getWidth()==getWidth()@pre+2;
							 }
							 getNbToursBuilder()==getNbToursBuilder()@pre+1;
						 }else{
							 getEtats().contains(EtatLemming.WALKER);
						 }
					}else{
						 if(getWidth()@pre-3>=0 && getHeight()@pre-2>=0
								 && getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-2)==Nature.EMPTY
								 && getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)==Nature.EMPTY
								 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)==Nature.EMPTY
								 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-2)==Nature.EMPTY
								 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-3)==Nature.EMPTY){
							 if(getNbToursBuilder()@pre%3==0){
							 	 getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)==Nature.DIRT;
								 getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-2)==Nature.DIRT;
								 getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-3)==Nature.DIRT;
								 getNbDallePose()==getNbDallePose()@pre+1;
								 getHeight()@pre==getHeight()@pre-1;
								 getWidth()==getWidth()@pre-2;
							 }
							  getNbToursBuilder()==getNbToursBuilder()@pre+1;
  						 }else{
							 getEtats().contains(EtatLemming.WALKER);
						 }
					}
					
				}else if (getEtats().contains(EtatLemming.STOPPER)@pre){
					if( getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)==Nature.EMPTY){
						getEtats().contains(EtatLemming.FALLER))
					}
					
				}else if (getEtats().contains(EtatLemming.BASHER)@pre){ //DETRUIT LES MURS SUR SA LIGNE
					if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)@pre==Nature.EMPTY) getEtats().contains(EtatLemming.FALLER);
					else {
						if(isDroitier()@pre){
							if(getHeight()-2>=0 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre!=Nature.STOPPER
									&&getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)@pre!=Nature.STOPPER
									&&getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre!=Nature.STOPPER){
								if(getNbCreuse()@pre<20){
									if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre==Nature.DIRT){
										getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)==Nature.EMPTY
									}
									if(getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)@pre==Nature.DIRT){
										getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)==Nature.EMPTY
									}
									if(getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre==Nature.DIRT){
										getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)==Nature.EMPTY
									}
									getWidth()=getWidth()@pre+1;
									getNbCreuse()=getNbCreuse()@pre+1;
								}else{
									getEtats().contains(EtatLemming.WALKER);
									getNbCreuse()==0;
								}
							}else{
								getEtats().contains(EtatLemming.WALKER);
								getNbCreuse()==0;
							}
						} else {
							if(getHeight()-2>=0 && getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre!=Nature.STOPPER
									&&getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)@pre!=Nature.STOPPER
									&&getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre!=Nature.STOPPER){
								if(getNbCreuse()@pre<20){
									if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre==Nature.DIRT){
										getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)==Nature.EMPTY
									}
									if(getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)@pre==Nature.DIRT){
										getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)==Nature.EMPTY
									}
									if(getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre==Nature.DIRT){
										getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre==Nature.EMPTY
									}
									getWidth()=getWidth()@pre-1;
									getNbCreuse()=getNbCreuse()@pre+1;
								}else{
									getEtats().contains(EtatLemming.WALKER);
									getNbCreuse()==0;
								}
							}else{
								getEtats().contains(EtatLemming.WALKER);
								getNbCreuse()==0;
							}
						}
					}
					
				}else if (getEtats().contains(EtatLemming.MINER)@pre){ // CREUSEUR EN DIAGONALE
					if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre)==Nature.EMPTY) {
						getEtat.contains(EtatLemming.FALLER);
					}else{
						if(isDroitier()@pre){
						
							if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)@pre!=Nature.STOPPER
									&&getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre!=Nature.STOPPER){
								if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre+1)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)@pre==Nature.DIRT){
									getGameEng().getLevel()(getHeight()@pre, getWidth()@pre+1)==Nature.EMPTY;
								}
								getHeight()=getHeight()@pre+1;
								getWidth()=getWidth()@pre+1;
							}else if(getHeight()@pre-2>=0
									 && getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)@pre!=Nature.METAL
									 && getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)@pre!=Nature.STOPPER
									 && getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre!=Nature.METAL
									 && getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre!=Nature.STOPPER){
								if(getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre+1)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre+1)@pre==Nature.DIRT){
									getGameEng().getLevel()(getHeight()@pre-1, getWidth()@pre+1)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)!=Nature.EMPTY){ 
									getHeight()=getHeight()@pre-1;
								}
								getWidth()=getWidth()@pre+1;
							}else{
								getEtats().contains(EtatLemming.WALKER);
							}
						}else{
						
							if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)@pre!=Nature.STOPPER
									&&getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre!=Nature.METAL
									&&getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre!=Nature.STOPPER){
								if(getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre+1, getWidth()@pre-1)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-1)@pre==Nature.DIRT){
									getGameEng().getLevel()(getHeight()@pre, getWidth()@pre-1)==Nature.EMPTY;
								}
								getHeight()=getHeight()@pre+1;
								getWidth()=getWidth()@pre-1;
							}else if(getHeight()@pre-2>=0
									 && getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)@pre!=Nature.METAL
									 && getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)@pre!=Nature.STOPPER
									 && getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre!=Nature.METAL
									 && getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre!=Nature.STOPPER){
								if(getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)@pre==Nature.DIRT){
									getGameEng().getLevel().getNature(getHeight()@pre-2, getWidth()@pre-1)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre-1, getWidth()@pre-1)@pre==Nature.DIRT){
									getGameEng().getLevel()(getHeight()@pre-1, getWidth()@pre-1)==Nature.EMPTY;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre+1)!=Nature.EMPTY){ 
									getWidth()=getWidth()@pre-1;
								}
								if(getGameEng().getLevel().getNature(getHeight()@pre, getWidth()@pre-11)!=Nature.EMPTY){ 
									getHeight()=getHeight()@pre-1;
								}
								getWidth()=getWidth()@pre-1;
							}else{
								getEtats().contains(EtatLemming.WALKER);
							}
						}
					}
				}
			}
	 */
	void step();
	
}
