package servicesImplementations;

import java.util.ArrayList;
import java.util.List;

import enumeration.EtatLemming;
import enumeration.Nature;
import services.IGameEng;
import services.ILemming;

public class Lemming implements ILemming {

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
	public int getNbTourBuilder() {
		return nbTourBomber;
	}

	@Override
	public int getNbDallePose() {
		return nbTourBuilder;
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
		if(etat==EtatLemming.FLOATER || etat==EtatLemming.BOMBER || etat==EtatLemming.CLIMBER) this.etat.add(etat);
		else{
			for(int i=0;i<this.etat.size();i++){
				if(this.etat.get(i)!=EtatLemming.FLOATER || this.etat.get(i)!=EtatLemming.BOMBER || this.etat.get(i)!=EtatLemming.CLIMBER){
					if(this.etat.get(i)==EtatLemming.STOPPER){
						gameEng.getLevel().setNature(this.height, this.width, Nature.EMPTY);
						gameEng.getLevel().setNature(this.height-1, this.width, Nature.EMPTY);
					}
					this.etat.set(i, etat);
				}
			}
			if(!this.etat.contains(etat)) this.etat.add(etat);
		}
		if(etat==EtatLemming.STOPPER){
			gameEng.getLevel().setNature(this.height, this.width, Nature.STOPPER);
			gameEng.getLevel().setNature(this.height-1, this.width, Nature.STOPPER);
		}
	}

	@Override
	public void step() {
		boolean canMonter=false;
		if(gameEng.getLevel().getExitHeight() == height && gameEng.getLevel().getExitWidth() == width ){
			gameEng.saveLemming(id);
		}else {
			if (getEtat().contains(EtatLemming.BOMBER)){
				//TODO
				if(getNbToursBomber()==5){
					System.out.println();
					
					
					if(height==gameEng.getLevel().getHeight()-2){//-1==METAL  -2 en bas du plateau
						
					}
					
					gameEng.killLemming(id);
					return;
				}
					
				
			}
			if (getEtat().contains(EtatLemming.CLIMBER)){ //GRIMPEUR
				if(isDroitier){
					if(gameEng.getLevel().getNature(height, width+1)!=Nature.EMPTY && 
							gameEng.getLevel().getNature(height-1, width+1)!=Nature.EMPTY &&
							height-2>=0 &&
							gameEng.getLevel().getNature(height-2, width)==Nature.EMPTY){
						height--;
						canMonter=true;
					}else{
						setEtat(EtatLemming.FALLER);
						setDirection();
						}
				}else {
					if(gameEng.getLevel().getNature(height, width-1)!=Nature.EMPTY && 
							gameEng.getLevel().getNature(height-1, width-1)!=Nature.EMPTY &&
							height-2>=0 &&
							gameEng.getLevel().getNature(height-2, width)==Nature.EMPTY){
						height--;
						canMonter=true;
					}else{
						setEtat(EtatLemming.FALLER);
						setDirection();
					}
				}
				
			}else if(etat.contains(EtatLemming.WALKER)&&!canMonter){ //MARCHEUR
				if (gameEng.getLevel().getNature(height+1, width) == Nature.EMPTY){ 
					setEtat(EtatLemming.FALLER);
	
				} else if (isDroitier){
					if (gameEng.getLevel().getNature(height-1, width+1) != Nature.EMPTY ||
							(gameEng.getLevel().getNature(height, width+1) != Nature.EMPTY &&
							height-2>0 && 
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
							height-2>0 && 
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
			} else if (getEtat().contains(EtatLemming.FALLER)&&!canMonter){ //TOMBEUR
				
				if(!getEtat().contains(EtatLemming.FLOATER)){  // Lemming pas FLOTTEUR
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
					if (gameEng.getLevel().getNature(height+1, width) != Nature.EMPTY) setEtat(EtatLemming.WALKER);
					else {
						if(nbCasesFalling>0 && nbCasesFalling%2==0) height = height+1;
						nbCasesFalling++;
					}
				}
				
			}else if(getEtat().contains(EtatLemming.DIGGER)&&!canMonter){//CREUSEUR FAIRE LE BAS
				if(gameEng.getLevel().getNature(height+1, width)==Nature.EMPTY) setEtat(EtatLemming.FALLER);
				else if(gameEng.getLevel().getNature(height+1, width)==Nature.METAL) setEtat(EtatLemming.WALKER);
				else if(gameEng.getLevel().getNature(height+1, width)==Nature.DIRT){
					gameEng.getLevel().remove(height+1, width);
					if(gameEng.getLevel().getNature(height+1, width+1)==Nature.DIRT) gameEng.getLevel().remove(height+1, width+1);
					if(gameEng.getLevel().getNature(height+1, width-1)==Nature.DIRT) gameEng.getLevel().remove(height+1, width-1);
					height++;
				}
				
			}else if (getEtat().contains(EtatLemming.BUILDER)&&!canMonter){//TODO CONSTRUCTEUR
				
				
				
				
			}else if (getEtat().contains(EtatLemming.STOPPER)&&!canMonter){//STOPPER
				
				//RIEN A FAIRE
				
				
			}else if (getEtat().contains(EtatLemming.BASHER)&&!canMonter){ //DETRUIT LES MURS DANS SA DIRECTION
				//TODO A VERIFIER
				if(gameEng.getLevel().getNature(height+1, width)==Nature.EMPTY) setEtat(EtatLemming.FALLER);
				else {
					if(isDroitier){
						if(gameEng.getLevel().getNature(height, width+1)!=Nature.METAL
								&&gameEng.getLevel().getNature(height+1, width+1)!=Nature.METAL
								&&gameEng.getLevel().getNature(height-1, width+1)!=Nature.METAL){
							if(nbCreuse<20){
								if( gameEng.getLevel().getNature(height, width+1)==Nature.DIRT){
									gameEng.getLevel().remove(height, width+1);
								}
								if( gameEng.getLevel().getNature(height+1, width+1)==Nature.DIRT){
									gameEng.getLevel().remove(height+1, width+1);
								}
								if( gameEng.getLevel().getNature(height-1, width+1)==Nature.DIRT){
									gameEng.getLevel().remove(height-1, width+1);
								}
								nbCreuse++;
								width++;
							}else{
								setEtat(EtatLemming.WALKER);
							}
						}else{
							setEtat(EtatLemming.WALKER);
						}
					} else {
						if(gameEng.getLevel().getNature(height, width-1)!=Nature.METAL
								&&gameEng.getLevel().getNature(height+1, width-1)!=Nature.METAL
								&&gameEng.getLevel().getNature(height-1, width-1)!=Nature.METAL){
							if(nbCreuse<20){
								if( gameEng.getLevel().getNature(height, width-1)==Nature.DIRT){
									gameEng.getLevel().remove(height, width-1);
								}
								if( gameEng.getLevel().getNature(height+1, width-1)==Nature.DIRT){
									gameEng.getLevel().remove(height+1, width-1);
								}
								if( gameEng.getLevel().getNature(height-1, width-1)==Nature.DIRT){
									gameEng.getLevel().remove(height-1, width-1);
								}
								nbCreuse++;
								width--;
							}else{
								setEtat(EtatLemming.WALKER);
							}
						}else{
							setEtat(EtatLemming.WALKER);
						}
					}
				}
				
				
			}else if (getEtat().contains(EtatLemming.MINER)&&!canMonter){ // CREUSEUR EN DIAGONALE
				if(isDroitier){
					if(height+1<gameEng.getLevel().getHeight() &&
							width+1<gameEng.getLevel().getWidth() &&
							gameEng.getLevel().getNature(height+1, width+1)==Nature.DIRT){
						gameEng.getLevel().remove(height+1, width+1);
						height++;
						width++;
					}else if(height-1>0 &&
							width+1<gameEng.getLevel().getWidth() &&
							gameEng.getLevel().getNature(height-1, width+1)==Nature.DIRT){
						gameEng.getLevel().remove(height-1, width+1);
						height--;
						width++;
					}else{
						setEtat(EtatLemming.WALKER);
					}
				}else{
					if(height+1<gameEng.getLevel().getHeight() &&
							width-1>0 &&
							gameEng.getLevel().getNature(height+1, width-1)==Nature.DIRT){
						gameEng.getLevel().remove(height+1, width-1);
						height++;
						width--;
					}else if(height-1>0 &&
							width-1>0 &&
							gameEng.getLevel().getNature(height-1, width-1)==Nature.DIRT){
						gameEng.getLevel().remove(height-1, width-1);
						height--;
						width--;
					}else{
						setEtat(EtatLemming.WALKER);
					}
				}
				
			}
		}
	}

}
