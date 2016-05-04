package tests.bug.servicesImplementations;

import java.util.ArrayList;
import java.util.List;

import enumeration.EtatLemming;
import enumeration.Nature;
import services.IGameEng;
import services.ILemming;

public class LemmingBug implements ILemming {

	private int width;
	private int height;
	private int id;
	private boolean isDroitier;
	private List<EtatLemming> etat;
	private IGameEng gameEng;
	private int nbCasesFalling; 
	private int nbTourBomber;
	private int nbTourBuilder;
	private int nbDallePose;
	private int nbCreuse;

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public boolean isDroitier() {
		return isDroitier;
	}

	@Override
	public List<EtatLemming> getEtat() {
		return etat;
	}

	@Override
	public IGameEng getGameEng() {
		return gameEng;
	}

	@Override
	public int getNbCasesFalling() {
		return nbCasesFalling;
	}
	
	@Override
	public int getNbToursBomber() {
		return nbTourBomber;
	}
	
	@Override
	public int getNbToursBuilder() {
		return nbTourBuilder;
	}

	@Override
	public int getNbDallePose() {
		return nbDallePose;
	}
	
	@Override
	public int getNbCreuse() {
		return nbCreuse;
	}

	@Override
	public void init(IGameEng gameEng) {
		this.gameEng=gameEng;
		width=gameEng.getLevel().getEntranceWidth();
		height=gameEng.getLevel().getEntranceHeight();
		id=gameEng.getSpawned();
		isDroitier=true;
		etat=new ArrayList<EtatLemming>();
		etat.add(EtatLemming.FALLER);
		nbCasesFalling=0;
		nbTourBomber=0;
		nbTourBuilder=0;
		nbDallePose=0;
		nbCreuse=0;
	}

	@Override
	public void setHeight(int height) {
		this.height=height;
	}

	@Override
	public void setWidth(int width) {
		this.width=width;
	}

	@Override
	public void setDirection() {
		isDroitier = !isDroitier;
	}

	@Override
	public void setEtat(EtatLemming etat) {
		if(this.etat.contains(etat)){
			return;
		}
		if(etat==EtatLemming.FLOATER 
				|| etat==EtatLemming.BOMBER 
				|| etat==EtatLemming.CLIMBER){
			this.etat.add(etat);
		}else{
			for(int i=0;i<this.etat.size();i++){
				if(this.etat.get(i)!=EtatLemming.FLOATER 
						&& this.etat.get(i)!=EtatLemming.BOMBER 
						&& this.etat.get(i)!=EtatLemming.CLIMBER){
					if(this.etat.get(i)==EtatLemming.STOPPER){
						gameEng.getLevel().removeStopper(height, width);
						gameEng.getLevel().removeStopper(height-1, width);
					}
					this.etat.remove(i);
				}
			}
		}
		if(!(this.etat.contains(etat))) {
			this.etat.add(etat);
		}
		if(etat==EtatLemming.STOPPER){
			gameEng.getLevel().addStopper(height, width);
			gameEng.getLevel().addStopper(height-1, width);
		}
	}

	@Override
	public void step() {
		boolean canMonter=false;
		if(gameEng.getLevel().getExitHeight() == height && gameEng.getLevel().getExitWidth() == width ){
			gameEng.saveLemming(id);
		}else {
			
			if (getEtat().contains(EtatLemming.BOMBER)){ //OK
				
				if(getNbToursBomber()==5){
					
					if(width-2>=0){
						if(width+2<gameEng.getLevel().getWidth()){
							for(int i=width-2;i<=width+2;i++){
								if(gameEng.getLevel().getNature(height, i)==Nature.DIRT){
									gameEng.getLevel().remove(height, i);
								}
								if(gameEng.getLevel().getNature(height-1, i)==Nature.DIRT){
									gameEng.getLevel().remove(height-1, i);
								}
							}
							for(int i=width-1;i<=width+1;i++){
								if(gameEng.getLevel().getNature(height-2, i)==Nature.DIRT){
									gameEng.getLevel().remove(height-2, i);
								}
								if(gameEng.getLevel().getNature(height+1, i)==Nature.DIRT){
									gameEng.getLevel().remove(height+1, i);
								}
							}
						}else{
							for(int i=width-2;i<=width+1;i++){
								if(gameEng.getLevel().getNature(height+1, i)==Nature.DIRT){
									gameEng.getLevel().remove(height+1, i);
								}
								if(gameEng.getLevel().getNature(height, i)==Nature.DIRT){
									gameEng.getLevel().remove(height, i);
								}
								if(gameEng.getLevel().getNature(height-1, i)==Nature.DIRT){
									gameEng.getLevel().remove(height-1, i);
								}
								if(gameEng.getLevel().getNature(height-2, i)==Nature.DIRT){
									gameEng.getLevel().remove(height-2, i);
								}
							}
						}
					}else{
						//width+2<gameEng.getLevel().getWidth() CAR TAILLE DU PLATEAU DE 4 AU MOINS width>=4
						for(int i=width-1;i<=width+2;i++){
							if(gameEng.getLevel().getNature(height+1, i)==Nature.DIRT){
								gameEng.getLevel().remove(height+1, i);
							}
							if(gameEng.getLevel().getNature(height, i)==Nature.DIRT){
								gameEng.getLevel().remove(height, i);
							}
							if(gameEng.getLevel().getNature(height-1, i)==Nature.DIRT){
								gameEng.getLevel().remove(height-1, i);
							}
							if(gameEng.getLevel().getNature(height-2, i)==Nature.DIRT){
								gameEng.getLevel().remove(height-2, i);
							}
						}
					}
					gameEng.killLemming(id);
					return;
				}
				nbTourBomber++;
			}


			if (getEtat().contains(EtatLemming.CLIMBER)){ //GRIMPEUR
				if(isDroitier){
					if(gameEng.getLevel().getNature(height, width+1)!=Nature.EMPTY
							&& gameEng.getLevel().getNature(height-1, width+1)!=Nature.EMPTY){
						if(height-2>=0 
							&& gameEng.getLevel().getNature(height-2, width)==Nature.EMPTY){
							height--;
							canMonter=true;
						}else{
							setEtat(EtatLemming.FALLER);
							setDirection();
						}
					}else{}
				}else {
					if(gameEng.getLevel().getNature(height, width-1)!=Nature.EMPTY
							&&gameEng.getLevel().getNature(height-1, width-1)!=Nature.EMPTY){
						if(height-2>=0 
							&& gameEng.getLevel().getNature(height-2, width)==Nature.EMPTY){
							height--;
							canMonter=true;
						}else{
							setEtat(EtatLemming.FALLER);
							setDirection();
						}
					}else{}
				}
				
			}
			if(!canMonter){
				if(etat.contains(EtatLemming.WALKER)){ //MARCHEUR //OK
					if (gameEng.getLevel().getNature(height+1, width) == Nature.EMPTY){ 
						setEtat(EtatLemming.FALLER);
		
					} else if (isDroitier){ 
						if (gameEng.getLevel().getNature(height-1, width+1) != Nature.EMPTY ||
								(gameEng.getLevel().getNature(height, width+1) != Nature.EMPTY &&
								height-2>=0 && 
								gameEng.getLevel().getNature(height-2, width+1) != Nature.EMPTY)){
							isDroitier = false; 
		
						} else if (gameEng.getLevel().getNature(height, width+1) != Nature.EMPTY){
							isDroitier = true;
							width = width+1;
							height = height-1;
		
						} else {
							isDroitier = true;
							width = width+1; 
						}
					}else {
						if (gameEng.getLevel().getNature(height-1, width-1) != Nature.EMPTY ||
								(gameEng.getLevel().getNature(height, width-1) != Nature.EMPTY &&
								height-2>=0 && 
								gameEng.getLevel().getNature(height-2, width-1) != Nature.EMPTY)){
							isDroitier = true; 
		
						} else if (gameEng.getLevel().getNature(height, width-1) != Nature.EMPTY){
							isDroitier = false;
							width = width-1;
							height = height-1;
		
						} else {
							isDroitier = false;
							width = width-1; 
						}
					}
				} else if (getEtat().contains(EtatLemming.FALLER)){ //TOMBEUR //OK
					
					if(!getEtat().contains(EtatLemming.FLOATER)){  // Lemming pas FLOTTEUR //OK
						if (gameEng.getLevel().getNature(height+1, width) != Nature.EMPTY) {
							if (getNbCasesFalling() < 8) {
								setEtat(EtatLemming.WALKER);
								nbCasesFalling=0;
							}else {
								gameEng.killLemming(id);
							}
						}else {
							nbCasesFalling = nbCasesFalling+1;
							height = height+1;
						}
					}else{	//FLOTTEUR
						if (gameEng.getLevel().getNature(height+1, width) != Nature.EMPTY) {
							nbCasesFalling=0;
							setEtat(EtatLemming.WALKER);
						}else {
							if(nbCasesFalling%2==0) height = height+1;
							nbCasesFalling++;
						}
					}
					
				}else if(getEtat().contains(EtatLemming.DIGGER)){//CREUSEUR FAIRE LE BAS
					if(gameEng.getLevel().getNature(height+1, width)==Nature.EMPTY){
						setEtat(EtatLemming.FALLER);
					}
					else if(gameEng.getLevel().getNature(height+1, width)==Nature.METAL
							|| gameEng.getLevel().getNature(height+1, width)==Nature.STOPPER){
						setEtat(EtatLemming.WALKER);
					}
					else if(gameEng.getLevel().getNature(height+1, width)==Nature.DIRT){
						gameEng.getLevel().remove(height+1, width);
						if(gameEng.getLevel().getNature(height+1, width+1)==Nature.DIRT) gameEng.getLevel().remove(height+1, width+1);
						if(gameEng.getLevel().getNature(height+1, width-1)==Nature.DIRT) gameEng.getLevel().remove(height+1, width-1);
						height++;
					}
					
				}else if (getEtat().contains(EtatLemming.BUILDER)){
					if(isDroitier){
						 if(width+3<gameEng.getLevel().getWidth() && height-2>=0
								 && gameEng.getLevel().getNature(height-2, width+2)==Nature.EMPTY
								 && gameEng.getLevel().getNature(height-2, width+1)==Nature.EMPTY
								 && gameEng.getLevel().getNature(height, width+1)==Nature.EMPTY
								 && gameEng.getLevel().getNature(height, width+2)==Nature.EMPTY
								 && gameEng.getLevel().getNature(height, width+3)==Nature.EMPTY){
							 if(nbTourBuilder%3==0){
								 gameEng.getLevel().build(height, width+1);
								 gameEng.getLevel().build(height, width+2);
								 gameEng.getLevel().build(height, width+3);
								 nbDallePose++;
								 height--;
								 width+=2;
							 }
							 nbTourBuilder++;
						 }else{
							 setEtat(EtatLemming.WALKER);
						 }
					}else{
						 if(width-3>=0 && height-2>=0
								 && gameEng.getLevel().getNature(height-2, width-2)==Nature.EMPTY
								 && gameEng.getLevel().getNature(height-2, width-1)==Nature.EMPTY
								 && gameEng.getLevel().getNature(height, width-1)==Nature.EMPTY
								 && gameEng.getLevel().getNature(height, width-2)==Nature.EMPTY
								 && gameEng.getLevel().getNature(height, width-3)==Nature.EMPTY){
							 if(nbTourBuilder%3==0){
								 gameEng.getLevel().build(height, width-1);
								 gameEng.getLevel().build(height, width-2);
								 gameEng.getLevel().build(height, width-3);
								 nbDallePose++;
								 height--;
								 width-=2;
							 }
							 nbTourBuilder++;
						 }else{
							 setEtat(EtatLemming.WALKER);
						 }
					}
					
				}else if (getEtat().contains(EtatLemming.STOPPER)){//STOPPER
					if(gameEng.getLevel().getNature(height+1, width)==Nature.EMPTY){
						setEtat(EtatLemming.FALLER);
					}
					
				}else if (getEtat().contains(EtatLemming.BASHER)){ //DETRUIT LES MURS DANS SA DIRECTION
					if(gameEng.getLevel().getNature(height+1, width)==Nature.EMPTY) setEtat(EtatLemming.FALLER);
					else {
						if(isDroitier){
							if(height-2>=0 && gameEng.getLevel().getNature(height, width+1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height-2, width+1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height-1, width+1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height, width+1)!=Nature.STOPPER
									&&gameEng.getLevel().getNature(height-2, width+1)!=Nature.STOPPER
									&&gameEng.getLevel().getNature(height-1, width+1)!=Nature.STOPPER){
								if(nbCreuse<20){
									if( gameEng.getLevel().getNature(height, width+1)==Nature.DIRT){
										gameEng.getLevel().remove(height, width+1);
									}
									if( gameEng.getLevel().getNature(height-2, width+1)==Nature.DIRT){
										gameEng.getLevel().remove(height-2, width+1);
									}
									if( gameEng.getLevel().getNature(height-1, width+1)==Nature.DIRT){
										gameEng.getLevel().remove(height-1, width+1);
									}
									nbCreuse++;
									width++;
								}else{
									nbCreuse=0;
									setEtat(EtatLemming.WALKER);
								}
							}else{
								nbCreuse=0;
								setEtat(EtatLemming.WALKER);
							}
						} else {
							if(height-2>=0 && gameEng.getLevel().getNature(height, width-1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height-2, width-1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height-1, width-1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height, width-1)!=Nature.STOPPER
									&&gameEng.getLevel().getNature(height-2, width-1)!=Nature.STOPPER
									&&gameEng.getLevel().getNature(height-1, width-1)!=Nature.STOPPER){
								if(nbCreuse<20){
									if( gameEng.getLevel().getNature(height, width-1)==Nature.DIRT){
										gameEng.getLevel().remove(height, width-1);
									}
									if( gameEng.getLevel().getNature(height-2, width-1)==Nature.DIRT){
										gameEng.getLevel().remove(height-2, width-1);
									}
									if( gameEng.getLevel().getNature(height-1, width-1)==Nature.DIRT){
										gameEng.getLevel().remove(height-1, width-1);
									}
									nbCreuse++;
									width--;
								}else{
									setEtat(EtatLemming.WALKER);
									nbCreuse=0;
								}
							}else{
								setEtat(EtatLemming.WALKER);
								nbCreuse=0;
							}
						}
					}
					
				}else if (getEtat().contains(EtatLemming.MINER)){ // CREUSEUR EN DIAGONALE
					if(gameEng.getLevel().getNature(height+1, width)==Nature.EMPTY) {
						setEtat(EtatLemming.FALLER);
					}else{
						if(isDroitier){
							if(gameEng.getLevel().getNature(height+1, width+1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height+1, width+1)!=Nature.STOPPER
									&&gameEng.getLevel().getNature(height, width+1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height, width+1)!=Nature.STOPPER){
								if(gameEng.getLevel().getNature(height+1, width+1)==Nature.DIRT){
									gameEng.getLevel().remove(height+1, width+1);
								}
								if(gameEng.getLevel().getNature(height, width+1)==Nature.DIRT){
									gameEng.getLevel().remove(height, width+1);
								}
								height++;
								width++;
							}else if(height-2>=0
									&& gameEng.getLevel().getNature(height-2, width+1)!=Nature.METAL
									&& gameEng.getLevel().getNature(height-2, width+1)!=Nature.STOPPER
									&& gameEng.getLevel().getNature(height-1, width+1)!=Nature.METAL
									&& gameEng.getLevel().getNature(height-1, width+1)!=Nature.STOPPER){
								if(gameEng.getLevel().getNature(height-2, width+1)==Nature.DIRT)
									gameEng.getLevel().remove(height-2, width+1);
								if(gameEng.getLevel().getNature(height-1, width+1)==Nature.DIRT)
									gameEng.getLevel().remove(height-1, width+1);
								if(gameEng.getLevel().getNature(height, width+1)!=Nature.EMPTY)
									height--;
								width++;
							}else{
								this.setEtat(EtatLemming.WALKER);
							}
						}else{
							if(gameEng.getLevel().getNature(height+1, width-1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height+1, width-1)!=Nature.STOPPER
									&&gameEng.getLevel().getNature(height, width-1)!=Nature.METAL
									&&gameEng.getLevel().getNature(height, width-1)!=Nature.STOPPER){
								if(gameEng.getLevel().getNature(height+1, width-1)==Nature.DIRT)
									gameEng.getLevel().remove(height+1, width-1);
								if(gameEng.getLevel().getNature(height, width-1)==Nature.DIRT)
									gameEng.getLevel().remove(height, width-1);
								height++;
								width--;
							}else if(height-2>=0
									&& gameEng.getLevel().getNature(height-2, width-1)!=Nature.METAL
									&& gameEng.getLevel().getNature(height-2, width-1)!=Nature.STOPPER
									&& gameEng.getLevel().getNature(height-1, width-1)!=Nature.METAL
									&& gameEng.getLevel().getNature(height-1, width-1)!=Nature.STOPPER){
								if(gameEng.getLevel().getNature(height-2, width-1)==Nature.DIRT)
									gameEng.getLevel().remove(height-2, width-1);
								if(gameEng.getLevel().getNature(height-1, width-1)==Nature.DIRT)
									gameEng.getLevel().remove(height-1, width-1);
								if(gameEng.getLevel().getNature(height, width-1)!=Nature.EMPTY)
									height--;
								width--;
							}else{
								this.setEtat(EtatLemming.WALKER);
							}
						}
					}
				}
			}
		}
	}

}
