package contrat;

import java.util.List;

import decorateur.LemmingDecorateur;
import enumeration.EtatLemming;
import enumeration.Nature;
import errors.InvariantError;
import errors.PostConditionError;
import errors.PreConditionError;
import services.IGameEng;
import servicesImplementations.Lemming;

public class LemmingContrat extends LemmingDecorateur{

	public LemmingContrat() {
		super(new Lemming());
	}

	@Override
	public int getWidth() {
		checkInvariants();
		int res = delegate.getWidth();
		checkInvariants();
		return res;
	}

	@Override
	public int getHeight() {
		checkInvariants();
		int res = delegate.getHeight();
		checkInvariants();
		return res;
	}

	@Override
	public int getId() {
		checkInvariants();
		int res = delegate.getId();
		checkInvariants();
		return res;
	}

	@Override
	public boolean isDroitier() {
		checkInvariants();
		boolean res = delegate.isDroitier();
		checkInvariants();
		return res;
	}

	@Override
	public List<EtatLemming> getEtat() {
		checkInvariants();
		List<EtatLemming> res = delegate.getEtat();
		checkInvariants();
		return res;
	}

	@Override
	public IGameEng getGameEng() {
		checkInvariants();
		IGameEng res = delegate.getGameEng();
		checkInvariants();
		return res;
	}

	@Override
	public int getNbCasesFalling() {
		checkInvariants();
		int res = delegate.getNbCasesFalling();
		checkInvariants();
		return res;
	}
	
	@Override
	public int getNbToursBomber() {
		checkInvariants();
		int res = delegate.getNbToursBomber();
		checkInvariants();
		return res;
	}
	
	@Override
	public int getNbTourBuilder() {
		checkInvariants();
		int res = delegate.getNbTourBuilder();
		checkInvariants();
		return res;
	}
	
	@Override
	public int getNbDallePose() {
		checkInvariants();
		int res = delegate.getNbDallePose();
		checkInvariants();
		return res;
	}
	
	@Override
	public int getNbCreuse() {
		checkInvariants();
		int res = delegate.getNbCreuse();
		checkInvariants();
		return res;
	}

	@Override
	public void init(IGameEng gameEng) {
		checkInvariants();

		delegate.init(gameEng);

		if(! (getGameEng() == gameEng)) throw new PostConditionError("getGameEng() != gameEng");
		if(! (getWidth() == gameEng.getLevel().getEntranceWidth())){
			throw new PostConditionError("getWidth() != gameEng.getLevel().getEntranceWidth()");
		}
		if(! (getHeight() == gameEng.getLevel().getEntranceHeight())){
			throw new PostConditionError("getHeight() != gameEng.getLevel().getEntranceHeight()");
		}
		if(! (getId() == gameEng.getSpawned())) throw new PostConditionError("getId() != gameEng.getSpawned()");
		if(! (isDroitier()==true)) throw new PostConditionError("isDroitier()==false");
		if(! (getEtat().contains(EtatLemming.FALLER))) throw new PostConditionError("!getEtat().contains(EtatLemming.FALLER)");
		if(! (getNbCasesFalling() == 0))  throw new PostConditionError("getNbCasesFalling() != 0");
		checkInvariants();
	}

	@Override
	public void setHeight(int height) {
		checkInvariants();
		if(! (height>0)) throw new PreConditionError("height<=0");
		if(! (height<getGameEng().getLevel().getHeight())) throw new PreConditionError("height>=getGameEng().getLevel().getHeight()");

		delegate.setHeight(height);

		if(! (height==getHeight())) throw new PostConditionError("height!=getHeight()");
		checkInvariants();
	}

	@Override
	public void setWidth(int width) {
		checkInvariants();
		if(! (width>0)) throw new PreConditionError("width<=0");
		if(! (width<getGameEng().getLevel().getWidth()))  throw new PreConditionError("width>=getGameEng().getLevel().getWidth()");

		delegate.setWidth(width);

		if(! (width==getWidth())) throw new PostConditionError("width!=getWidth()");
		checkInvariants();
	}

	@Override
	public void setDirection() {
		checkInvariants();
		boolean dir=isDroitier();

		delegate.setDirection();

		if(! (dir!=isDroitier())) throw new PostConditionError("isDroitier==isDroitier()@pre");
		checkInvariants();
	}

	@Override
	public void setEtat(EtatLemming etat) {
		checkInvariants();
		delegate.setEtat(etat);

		if(! (getEtat().contains(etat))) throw new PostConditionError("!getEtat().contains(etat))");
		checkInvariants();
	}

	@Override
	public void step() {
		checkInvariants();

		List<EtatLemming> etatPre = getEtat();
		int heightPre = getHeight();
		int widthPre = getWidth();
		int nbCaseFallingPre=getNbCasesFalling();
		boolean isDroitierPre=isDroitier();
		int nbSavePre=getGameEng().getNbLemSauves();
		int nbCreusePre=getNbCreuse();
		
		//P1 == +1;  M1 == -1
		Nature hPreP1WPre = getGameEng().getLevel().getNature(heightPre+1, widthPre);
		Nature hPreM1WPreP1 = getGameEng().getLevel().getNature(heightPre-1, widthPre+1);
		Nature hPreP1WPreP1 = getGameEng().getLevel().getNature(heightPre+1, widthPre+1);
		Nature hPreP1WPreM1 = getGameEng().getLevel().getNature(heightPre+1, widthPre-1);
		Nature hPreWPreP1 = getGameEng().getLevel().getNature(heightPre, widthPre+1);
		Nature hPreM2WPreP1 = getGameEng().getLevel().getNature(heightPre-2, widthPre+1);
		Nature hPreM1WPreM1 = getGameEng().getLevel().getNature(heightPre-1, widthPre-1);
		Nature hPreWPreM1 = getGameEng().getLevel().getNature(heightPre, widthPre-1);
		Nature hPreM2WPreM1 = getGameEng().getLevel().getNature(heightPre-2, widthPre-1);
		Nature hPreM2WPre = getGameEng().getLevel().getNature(getHeight()-2, getWidth());
		delegate.step();

		boolean canMonter=false;
		
		if(getGameEng().getLevel().getExitHeight()==getHeight() && getGameEng().getLevel().getExitWidth()==getWidth() // Lemming sur le exit
				&& (!(getGameEng().getNbLemSauves() == nbSavePre+1))){ 
			throw new PostConditionError("getGameEng().getNbLemSauves() != getNbLemSauves()@pre+1");
		
		
		
		} else {
			/*if (getEtat().contains(EtatLemming.BOMBER)@pre){
				//TODO
				if(getNbToursBomber()@pre==5){
					System.out.println();
					
					
					if(getHeight()@pre==getGameEng().getLevel().getHeight()@pre-2){//-1==METAL  -2 en bas du plateau
						
					}
					
					gameEng.killLemming(id);
					return;
				}
					
				
			}*/
			
			
			
			if (etatPre.contains(EtatLemming.CLIMBER)){
				if(isDroitierPre){
					if(hPreWPreP1!=Nature.EMPTY && 
							hPreM1WPreP1!=Nature.EMPTY &&
							heightPre-2>=0 &&
							hPreM2WPre==Nature.EMPTY){
						if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre");  
						if(! (getHeight() == heightPre-1)) throw new PostConditionError("getHeight() != getHeight()@pre-1");   
						canMonter=true;
					}else {
						if(!(getEtat().contains(EtatLemming.FALLER))) throw new PostConditionError("!getEtat().contains(EtatLemming.FALLER)");
						if(!(isDroitier()!=isDroitierPre)) throw new PostConditionError("isDroitier()==isDroitierPre");
					}
				}else {
					if(hPreWPreM1!=Nature.EMPTY && 
							hPreM1WPreM1!=Nature.EMPTY &&
							heightPre-2>=0 &&
							hPreM2WPre==Nature.EMPTY){
						if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre");  
						if(! (getHeight() == heightPre-1)) throw new PostConditionError("getHeight() != getHeight()@pre-1");   
						canMonter=true;
					}else {
						if(!(getEtat().contains(EtatLemming.FALLER))) throw new PostConditionError("!getEtat().contains(EtatLemming.FALLER)");
						if(!(isDroitier()!=isDroitierPre)) throw new PostConditionError("isDroitier()==isDroitierPre");
					}
				}
			}
			if(!canMonter){
				if(etatPre.contains(EtatLemming.WALKER)){ 
					if (hPreP1WPre == Nature.EMPTY){ 
						if(! (getEtat().contains(EtatLemming.FALLER))) throw new PostConditionError("getEtat() != EtatLemming.FALLER"); 
						if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre");  
						if(! (getHeight() == heightPre)) throw new PostConditionError("getHeight() != getHeight()@pre");   
		
					} else if (isDroitierPre){ 
						if ( hPreM1WPreP1 != Nature.EMPTY || (hPreWPreP1 != Nature.EMPTY && hPreM2WPreP1 != Nature.EMPTY)){ 
							if(! (isDroitier() == false)) throw new PostConditionError("isDroitier()==true");  
							if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre");  
							if(! (getHeight() == heightPre)) throw new PostConditionError("getHeight() != getHeight()@pre");  
		
						} else if (hPreWPreP1 != Nature.EMPTY){ 
							if(! (isDroitier() == true)) throw new PostConditionError("isDroitier()==false");  
							if(! (getWidth()==widthPre+1)) throw new PostConditionError("getWidth() != getWidth()@pre+1");  
							if(! (getHeight()==heightPre-1)) throw new PostConditionError("getHeight() != getHeight()@pre-1"); 
		
						} else { 
							if(! (isDroitier() == true)) throw new PostConditionError("isDroitier()==false");  
							if(! (getWidth() == widthPre+1)) throw new PostConditionError("getWidth() != getWidth()@pre+1");   
							if(! (getHeight() == heightPre)) throw new PostConditionError("getHeight() != getHeight()@pre");  
						} 
					}else { 
						if (hPreM1WPreM1 != Nature.EMPTY || (hPreWPreM1 != Nature.EMPTY && hPreM2WPreM1 != Nature.EMPTY)){ 
							if(! (isDroitier() == true)) throw new PostConditionError("isDroitier()==false"); 
							if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre"); 
							if(! (getHeight() == heightPre)) throw new PostConditionError("getHeight() != getHeight()@pre");  
		
						} else if (hPreWPreM1 != Nature.EMPTY){ 
							if(! (isDroitier() == false)) throw new PostConditionError("isDroitier()==true");  
							if(! (getWidth() == widthPre-1)) throw new PostConditionError("getWidth() != getWidth()@pre-1");  
							if(! (getHeight() == heightPre-1)) throw new PostConditionError("getHeight() != getHeight()@pre-1");  
		
						} else { 
							if(! (isDroitier() == false)) throw new PostConditionError("isDroitier()==true");   
							if(! (getWidth() == widthPre-1)) throw new PostConditionError("getWidth() != getWidth()@pre-1"); 
							if(! (getHeight() == heightPre)) throw new PostConditionError("getHeight() != getHeight()@pre"); 
						} 
					} 
					
				} else if (etatPre.contains(EtatLemming.FALLER)){ 
					if(!etatPre.contains(EtatLemming.FLOATER)){
						if (hPreP1WPre != Nature.EMPTY) { 
							if (nbCaseFallingPre < 8 ) { 
								if(! (getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("getEtat() != EtatLemming.WALKER"); 
								if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre");  
								if(! (getHeight() == heightPre)) throw new PostConditionError("getHeight() != getHeight()@pre"); 
								if(! (getNbCasesFalling()==0)) throw new PostConditionError("getNbCasesFalling() != 0"); 
							} else{
								if(! (!getGameEng().containsIdColony(getId()))) throw new PostConditionError("getHeight() != getHeight()@pre");
							} 
						} else { 
							if(! (getNbCasesFalling() == nbCaseFallingPre+1)) throw new PostConditionError("getNbCasesFalling() != getNbCasesFalling()@pre+1");  
							if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre");   
							if(! (getHeight() == heightPre+1)) throw new PostConditionError("getHeight() != getHeight()@pre+1"); 
						} 
					}else{ //CAS DU FLOTTEUR
						if(hPreP1WPre != Nature.EMPTY){
							if(! (getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("getEtat() != EtatLemming.WALKER"); 
							if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre");  
							if(! (getHeight() == heightPre)) throw new PostConditionError("getHeight() != getHeight()@pre"); 
							if(! (getNbCasesFalling()==0)) throw new PostConditionError("getNbCasesFalling() != 0"); 
						} else {
							if( nbCaseFallingPre>0 && nbCaseFallingPre%2==0){
								if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre");  
								if(! (getHeight() == heightPre+1)) throw new PostConditionError("getHeight() != getHeight()@pre+1"); 
							}
							if(! (getNbCasesFalling() == nbCaseFallingPre+1)) throw new PostConditionError("getNbCasesFalling() != getNbCasesFalling()@pre+1"); 
						}
					}
				
				}else if(etatPre.contains(EtatLemming.DIGGER)){
					if(hPreP1WPre==Nature.EMPTY) {
						if(!(getEtat().contains(EtatLemming.FALLER))) throw new PostConditionError("!getEtat().contains(EtatLemming.FALLER)");
					}else if(hPreP1WPre==Nature.METAL){
						if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
					}else if(hPreP1WPre==Nature.DIRT){
						if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre)!=Nature.EMPTY");
						if(hPreP1WPreP1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre+1)!=Nature.EMPTY");
						}
						if(hPreP1WPreM1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre-1)!=Nature.EMPTY");
						}
						if(! (getWidth() == widthPre)) throw new PostConditionError("getWidth() != getWidth()@pre");  
						if(! (getHeight() == heightPre+1)) throw new PostConditionError("getHeight() != getHeight()@pre+1"); 
					}
				
				
				}else if(etatPre.contains(EtatLemming.BUILDER)){
					
					//TODO
					
				
				}else if(etatPre.contains(EtatLemming.STOPPER)){
					
					//RIEN A FAIRE
					
				
					
					
				}else if (etatPre.contains(EtatLemming.BASHER)){ //DETRUIT LES MURS SUR SA LIGNE
					if(hPreP1WPre==Nature.EMPTY){
						if(!(getEtat().contains(EtatLemming.FALLER))){
							throw new PostConditionError("!getEtat().contains(EtatLemming.FALLER)");
						}
					}else {
						if(isDroitierPre){
							if(heightPre-2>=0 && hPreWPreP1 !=Nature.METAL
									&& hPreP1WPreP1 !=Nature.METAL
									&& hPreM1WPreP1 !=Nature.METAL
									&& hPreWPreM1 !=Nature.STOPPER
									&& hPreP1WPreP1 !=Nature.STOPPER
									&& hPreM1WPreP1 !=Nature.STOPPER){
								if(nbCreusePre<20){
									if( hPreWPreP1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre+1)!=Nature.EMPTY");
									}
									if( hPreP1WPreP1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-2, widthPre+1)!=Nature.EMPTY");
									}
									if( hPreM1WPreP1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-1, widthPre+1)!=Nature.EMPTY");
									}
									if(!(getWidth()==widthPre+1)) throw new PostConditionError("getWidth()!=widthPre+1");
									if(!(getNbCreuse()==nbCreusePre+1)) throw new PostConditionError("getNbCreuse()!=nbCreusePre+1");
								}else{
									if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
								}
							}else{
								if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
							}
						} else {
							if(heightPre-2>=0 && hPreWPreM1 !=Nature.METAL
									&& hPreP1WPreM1 !=Nature.METAL
									&& hPreM1WPreM1 !=Nature.METAL
									&& hPreWPreM1 !=Nature.STOPPER
									&& hPreP1WPreM1 !=Nature.STOPPER
									&& hPreM1WPreM1 !=Nature.STOPPER){
								if(nbCreusePre<20){
									if( hPreWPreM1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre-1)!=Nature.EMPTY");
									}
									if( hPreP1WPreM1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-2, widthPre-1)!=Nature.EMPTY");
									}
									if( hPreM1WPreM1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-1, widthPre-1)!=Nature.EMPTY");
									}
									if(!(getWidth()==widthPre-1)) throw new PostConditionError("getWidth()!=widthPre-1");
									if(!(getNbCreuse()==nbCreusePre+1)) throw new PostConditionError("getNbCreuse()!=nbCreusePre+1");
								}else{
									if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
								}
							}else{
								if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
							}
						}
					}
				
				
				
				}else if (etatPre.contains(EtatLemming.MINER)){ // CREUSEUR EN DIAGONALE
					if(isDroitierPre){
						if(hPreP1WPreP1 == Nature.DIRT 
								&& hPreWPreP1==Nature.DIRT ){
							
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre+1)!=Nature.EMPTY");
							if(!(getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre+1)!=Nature.EMPTY");

							if(! (getWidth() == widthPre+1)) throw new PostConditionError("getWidth() != getWidth()@pre+1");  
							if(! (getHeight() == heightPre+1)) throw new PostConditionError("getHeight() != getHeight()@pre+1"); 
							
						}else if(heightPre-2<=0 && hPreM1WPreP1==Nature.DIRT
								&& hPreM2WPreP1 ==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-1, widthPre+1)!=Nature.EMPTY");
							if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-2, widthPre+1)!=Nature.EMPTY");
							
							if(! (getWidth() == widthPre+1)) throw new PostConditionError("getWidth() != getWidth()@pre+1");  
							if(! (getHeight() == heightPre-1)) throw new PostConditionError("getHeight() != getHeight()@pre-1"); 
							
						}else{
							if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
						}
					}else{
						if(hPreP1WPreM1==Nature.DIRT 
								&& hPreWPreM1==Nature.DIRT ){
							
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre-1)!=Nature.EMPTY");
							if(!(getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre-1)!=Nature.EMPTY");
							
							if(! (getWidth() == widthPre-1)) throw new PostConditionError("getWidth() != getWidth()@pre-1");  
							if(! (getHeight() == heightPre+1)) throw new PostConditionError("getHeight() != getHeight()@pre+1"); 
							
						}else if(heightPre-2<=0 && hPreM1WPreM1==Nature.DIRT
								&& hPreM2WPreM1==Nature.DIRT){
							
							if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-1, widthPre-1)!=Nature.EMPTY");
							if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-2, widthPre-1)!=Nature.EMPTY");
							
							if(! (getWidth() == widthPre-1)) throw new PostConditionError("getWidth() != getWidth()@pre-1");  
							if(! (getHeight() == heightPre-1)) throw new PostConditionError("getHeight() != getHeight()@pre-1"); 
							
						}else{
							if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
						}
					}
					
				}
				
			}
		}

	}

	@Override
	protected void checkInvariants(){
		if(! (delegate.getWidth()<=delegate.getGameEng().getLevel().getWidth())) throw new InvariantError("getWidth() > gameEng().getLevel().getWidth()");
		if(! (delegate.getHeight()<=delegate.getGameEng().getLevel().getHeight())) throw new InvariantError("getHeight() > gameEng().getLevel().getWidth()");
		if(! (delegate.getWidth()>=0)) throw new InvariantError("getWidth() < 0");
		if(! (delegate.getHeight()>=0)) throw new InvariantError("getHeight() < 0");
		if(! (delegate.getId()<=delegate.getGameEng().getSizeColony())) throw new InvariantError("getId() > getGameEng().getSizeColony()");
		if(! (delegate.getNbCasesFalling()<=delegate.getGameEng().getLevel().getHeight())){
			throw new InvariantError("getNbCasesFalling() > getGameEng().getLevel().getHeight()");
		}
	}

}
