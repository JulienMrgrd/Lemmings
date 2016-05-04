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
	public int getNbToursBuilder() {
		checkInvariants();
		int res = delegate.getNbToursBuilder();
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
		if(! (getNbToursBomber() == 0))  throw new PostConditionError("getNbToursBomber() != 0");
		if(! (getNbToursBuilder() == 0))  throw new PostConditionError("getNbToursBuilder() != 0");
		if(! (getNbDallePose() == 0))  throw new PostConditionError("getNbDallePose() != 0");
		if(! (getNbCreuse() == 0))  throw new PostConditionError("getNbCreuse() != 0");
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
		List<EtatLemming> etatPre = getEtat();
		delegate.setEtat(etat);
		if(etat==EtatLemming.STOPPER){
			if(!(getGameEng().getLevel().getNature(getHeight(), getWidth())==Nature.STOPPER))throw new PostConditionError("getGameEng().getLevel().getNature(getHeight(), getWidth())!=Nature.STOPPER");
			if(!(getGameEng().getLevel().getNature(getHeight()-1, getWidth())==Nature.STOPPER))throw new PostConditionError("getGameEng().getLevel().getNature(getHeight()-1, getWidth())!=Nature.STOPPER");
		}
		if(etatPre.contains(EtatLemming.FLOATER)){
			if(! (getEtat().contains(EtatLemming.FLOATER))) throw new PostConditionError("!getEtat().contains(EtatLemming.FLOATER)");
		}
		if(etatPre.contains(EtatLemming.BOMBER)){
			if(! (getEtat().contains(EtatLemming.BOMBER))) throw new PostConditionError("!getEtat().contains(EtatLemming.BOMBER)");	
		}
		if(etatPre.contains(EtatLemming.CLIMBER)){
			if(! (getEtat().contains(EtatLemming.CLIMBER))) throw new PostConditionError("!getEtat().contains(EtatLemming.CLIMBER)");
		}
		if(! (getEtat().contains(etat))) throw new PostConditionError("!getEtat().contains(etat)");
		checkInvariants();
	}

	@Override
	public void step() {
		checkInvariants();

		List<EtatLemming> etatPre = getEtat();
		int heightPre = getHeight();
		int widthPre = getWidth();
		int heightPreLevel= getGameEng().getLevel().getHeight();
		int widthPreLevel= getGameEng().getLevel().getWidth();
		int nbCaseFallingPre=getNbCasesFalling();
		boolean isDroitierPre=isDroitier();
		int nbSavePre=getGameEng().getNbLemSauves();
		int nbCreusePre=getNbCreuse();
		int nbToursBomberPre=getNbToursBomber();
		
		//P1 == +1;  M1 == -1
		Nature hPreWPreP1 = null;
		Nature hPreWPreP2 = null;
		Nature hPreWPreP3 = null;
		Nature hPreWPreM1 = null;
		Nature hPreWPreM2 = null;
		Nature hPreWPreM3 = null;
		
		Nature hPreP1WPre = null;
		Nature hPreP1WPreP1 = null;
		Nature hPreP1WPreP2 =null;
		Nature hPreP1WPreM1 = null;
		Nature hPreP1WPreM2 = null;
		
		Nature hPreM2WPre = null;
		Nature hPreM2WPreP1 = null;
		Nature hPreM2WPreP2 =null;
		Nature hPreM2WPreM1 = null;
		Nature hPreM2WPreM2 = null;
		
		Nature hPreM1WPreP1 = null;
		Nature hPreM1WPreP2 = null;
		Nature hPreM1WPreM1 = null;
		Nature hPreM1WPreM2 = null;
		
		
		
		
		if(heightPre<heightPreLevel){
			if(widthPre+1<widthPreLevel){
				hPreWPreP1 = getGameEng().getLevel().getNature(heightPre, widthPre+1);
			}
			if(widthPre+2<widthPreLevel){
				hPreWPreP2 = getGameEng().getLevel().getNature(heightPre, widthPre+2);
			}
			if(widthPre+3<widthPreLevel){
				hPreWPreP3 = getGameEng().getLevel().getNature(heightPre, widthPre+3);
			}
			if(widthPre-3>=0){
				hPreWPreM3 = getGameEng().getLevel().getNature(heightPre, widthPre-3);
			}
			if(widthPre-2>=0){
				hPreWPreM2 = getGameEng().getLevel().getNature(heightPre, widthPre-2);
			}
			if(widthPre-1>=0){
				hPreWPreM1 = getGameEng().getLevel().getNature(heightPre, widthPre-1);
			}
			if(heightPre+1<heightPreLevel){
				if(widthPre<widthPreLevel){
					hPreP1WPre = getGameEng().getLevel().getNature(heightPre+1, widthPre);
				}
				if(widthPre+1<widthPreLevel){
					hPreP1WPreP1 = getGameEng().getLevel().getNature(heightPre+1, widthPre+1);
				}
				if(widthPre+2<widthPreLevel){
					hPreP1WPreP2 = getGameEng().getLevel().getNature(heightPre+1, widthPre+2);
				}
				if(widthPre-1>=0){
					hPreP1WPreM1 = getGameEng().getLevel().getNature(heightPre+1, widthPre-1);
				}
				if(widthPre-2>=0){
					hPreP1WPreM2 = getGameEng().getLevel().getNature(heightPre+1, widthPre-2);
				}
			}
			
		}
		if(heightPre-1>=0){
			if(widthPre+1<widthPreLevel){
				hPreM1WPreP1 = getGameEng().getLevel().getNature(heightPre-1, widthPre+1);
			}
			if(widthPre+2<widthPreLevel){
				hPreM1WPreP2 = getGameEng().getLevel().getNature(heightPre-1, widthPre+2);
			}
			if(widthPre-1>=0){
				hPreM1WPreM1 = getGameEng().getLevel().getNature(heightPre-1, widthPre-1);
			}
			if(widthPre-2>=0){
				hPreM1WPreM2 = getGameEng().getLevel().getNature(heightPre-1, widthPre-2);
			}
		}
		if(heightPre-2>=0){
			if(widthPre<widthPreLevel){
				hPreM2WPre = getGameEng().getLevel().getNature(heightPre+2, widthPre);
			}
			if(widthPre+1<widthPreLevel){
				hPreM2WPreP1 = getGameEng().getLevel().getNature(heightPre+2, widthPre+1);
			}
			if(widthPre+2<widthPreLevel){
				hPreM2WPreP2 = getGameEng().getLevel().getNature(heightPre+2, widthPre+2);
			}
			if(widthPre-1>=0){
				hPreM2WPreM1 = getGameEng().getLevel().getNature(heightPre+2, widthPre-1);
			}
			if(widthPre-2>=0){
				hPreM2WPreM2 = getGameEng().getLevel().getNature(heightPre+2, widthPre-2);
			}
		}
		
		int nBTourBuilderPre=getNbToursBuilder();
		int nbDallesPosesPre=getNbDallePose();
		
		delegate.step();

		boolean canMonter=false;
		
		if(getGameEng().getLevel().getExitHeight()==getHeight() && getGameEng().getLevel().getExitWidth()==getWidth() // Lemming sur le exit
				&& (!(getGameEng().getNbLemSauves() == nbSavePre+1))){ 
			throw new PostConditionError("getGameEng().getNbLemSauves() != getNbLemSauves()@pre+1");
		
		
		} else {
			if (etatPre.contains(EtatLemming.BOMBER)){
				if(nbToursBomberPre==5){
					if( widthPre-2>=0){
						if(widthPre+2< widthPreLevel){
							if(hPreWPreM2==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre, widthPre-2)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre-2)==Nature.EMPTY");
								}
							}
							if(hPreWPreM1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY");
								}
							}
							if(hPreWPreP1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY");
								}
							}
							if(hPreWPreP2==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre, widthPre+2)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre+2)==Nature.EMPTY");
								}
							}
							if(hPreM1WPreM2==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre-2)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre-2)==Nature.EMPTY");
								}
							}
							if(hPreM1WPreM1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY");
								}
							}
							if(hPreM1WPreP1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY");
								}
							}
							if(hPreM1WPreP2==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre+2)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre+2)==Nature.EMPTY");
								}
							}
							if(hPreP1WPreM1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY");
								}
							}
							if(hPreP1WPreP1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY");
								}
							}
							if(hPreP1WPre==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre)==Nature.EMPTY");
								}
							}
							if(hPreM2WPreM1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY");
								}
							}
							if(hPreM2WPreP1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY");
								}
							}
							if(hPreM2WPre==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre)==Nature.EMPTY");
								}
							}
							//FIN DU CAS DE BASE
						}else{
							if(hPreWPreM2==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre, widthPre-2)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre-2)==Nature.EMPTY");
								}
							}
							if(hPreWPreM1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY");
								}
							}
							if(hPreWPreP1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY");
								}
							}
							if(hPreM1WPreM2==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre-2)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre-2)==Nature.EMPTY");
								}
							}
							if(hPreM1WPreM1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY");
								}
							}
							if(hPreM1WPreP1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY");
								}
							}
							if(hPreP1WPreM1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY");
								}
							}
							if(hPreP1WPreM2==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre-2)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre-2)==Nature.EMPTY");
								}
							}
							if(hPreP1WPreP1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY");
								}
							}
							if(hPreP1WPre==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre)==Nature.EMPTY");
								}
							}
							if(hPreM2WPreM1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY");
								}
							}
							if(hPreM2WPreM2==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre-2)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre-2)==Nature.EMPTY");
								}
							}
							if(hPreM2WPreP1==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY");
								}
							}
							if(hPreM2WPre==Nature.DIRT){
								if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre)==Nature.EMPTY)){
									throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre)==Nature.EMPTY");
								}
							}
							//FIN DU CAS 1 CASE A DROITE SEULEMENT
						}
					}else{
						//width+2<gameEng.getLevel().getWidth() CAR TAILLE DU PLATEAU DE 4 AU MOINS width>=4
						if(hPreWPreM1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY");
							}
						}
						if(hPreWPreP1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY");
							}
						}
						if(hPreWPreP2==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre, widthPre+2)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre, widthPre+2)==Nature.EMPTY");
							}
						}
						if(hPreM1WPreM1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY");
							}
						}
						if(hPreM1WPreP1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY");
							}
						}
						if(hPreM1WPreP2==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre+2)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-1, widthPre+2)==Nature.EMPTY");
							}
						}
						if(hPreP1WPreM1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY");
							}
						}
						if(hPreP1WPreP1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY");
							}
						}
						if(hPreP1WPreP2==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre+2)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre+2)==Nature.EMPTY");
							}
						}
						if(hPreP1WPre==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre+1, widthPre)==Nature.EMPTY");
							}
						}
						if(hPreM2WPreM1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY");
							}
						}
						if(hPreM2WPreP1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY");
							}
						}
						if(hPreM2WPreP2==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre+2)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre+2)==Nature.EMPTY");
							}
						}
						if(hPreM2WPre==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre)==Nature.EMPTY)){
								throw new PostConditionError("!getGameEng().getLevel().getNature(heightPre-2, widthPre)==Nature.EMPTY");
							}
						}
						//FIN DU CAS 1 CASE A GAUCHE SEULEMENT
					}
					if(! (!getGameEng().containsIdColony(getId()))) throw new PostConditionError("getGameEng().containsIdColony(getId())");
					return;
				}
				if(! (getNbToursBomber() == nbToursBomberPre+1)) throw new PostConditionError("getNbToursBomber() != nbToursBomberPre+1");  
			}
			
			
			
			if (etatPre.contains(EtatLemming.CLIMBER)){
				if(isDroitierPre){
					if(hPreWPreP1 != Nature.EMPTY 
							&& hPreM1WPreP1!=Nature.EMPTY){
						if(heightPre-2 >=0
								&& hPreM2WPre==Nature.EMPTY){
							if(!(getHeight()==heightPre-1)) throw new PostConditionError("getHeight()!=heightPre-1"); 
							canMonter=true;
						}else{
							if(! (getEtat().contains(EtatLemming.FALLER))) throw new PostConditionError("getNbToursBomber() != nbToursBomberPre+1");  
							if(!(isDroitier()!=isDroitierPre)) throw new PostConditionError("isDroitier()==isDroitierPre");
						}
					}else{}
				}else{
					if(hPreWPreM1 != Nature.EMPTY 
							&& hPreM1WPreM1!=Nature.EMPTY){
						if(heightPre-2 >=0
								&& hPreM2WPre==Nature.EMPTY){
							if(!(getHeight()==heightPre-1)) throw new PostConditionError("getHeight()!=heightPre-1"); 
							canMonter=true;
						}else{
							if(! (getEtat().contains(EtatLemming.FALLER))) throw new PostConditionError("getNbToursBomber() != nbToursBomberPre+1");  
							if(!(isDroitier()!=isDroitierPre)) throw new PostConditionError("isDroitier()==isDroitierPre");
						}
					}else{}
					
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
							if(! (isDroitier() == true)) throw new PostConditionError("isDroitier()==true");  
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
								if(! (!getGameEng().containsIdColony(getId()))) throw new PostConditionError("getGameEng().containsIdColony(getId())");
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
					}else if(hPreP1WPre==Nature.METAL || hPreP1WPre==Nature.STOPPER){
						if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
					}else if(hPreP1WPre==Nature.DIRT){
						if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre)!=Nature.EMPTY");
						if(hPreP1WPreP1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre+1)!=Nature.EMPTY");
						}
						if(hPreP1WPreM1==Nature.DIRT){
							if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre-1)!=Nature.EMPTY");
						}
						if(! (getHeight() == heightPre+1)) throw new PostConditionError("getHeight() != getHeight()@pre+1"); 
					}
				
				}else if(etatPre.contains(EtatLemming.BUILDER)){
					if(isDroitierPre){
						 if( widthPre+3<widthPreLevel && heightPre-2>=0
								 && hPreM2WPreP2==Nature.EMPTY
								 && hPreM2WPreP1==Nature.EMPTY
								 && hPreWPreP1==Nature.EMPTY
								 && hPreWPreP2==Nature.EMPTY
								 && hPreWPreP3==Nature.EMPTY){
							 if( nBTourBuilderPre%3==0 ){
								 if(!(getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.DIRT)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre+1)!=Nature.DIRT");
								 if(!(getGameEng().getLevel().getNature(heightPre, widthPre+2)==Nature.DIRT)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre+2)!=Nature.DIRT");
								 if(!(getGameEng().getLevel().getNature(heightPre, widthPre+3)==Nature.DIRT)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre+3)!=Nature.DIRT");
								 if(!(getNbDallePose()==nbDallesPosesPre+1)) throw new PostConditionError("getNbDallePose()!=nbDallesPosesPre+1");
								 if(!(getHeight()==heightPre-1))  throw new PostConditionError("getHeight()!=heightPre-1");
								 if(!(getWidth()==widthPre+2))  throw new PostConditionError("getWidth()!=widthPre+2");
							 }
							 if(!(getNbToursBuilder()==nBTourBuilderPre+1)) throw new PostConditionError("getNbToursBuilder()!=nbDallesPosesPre+1");
						 }else{
							 if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
						 }
					}else{
						 if( widthPre-3>=0 && heightPre-2>=0
								 && hPreM2WPreM2==Nature.EMPTY
								 && hPreM2WPreM1==Nature.EMPTY
								 && hPreWPreM1==Nature.EMPTY
								 && hPreWPreM2==Nature.EMPTY
								 && hPreWPreM3==Nature.EMPTY){
							 if(nBTourBuilderPre%3==0){
								 if(!(getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.DIRT)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre-1)!=Nature.DIRT");
								 if(!(getGameEng().getLevel().getNature(heightPre, widthPre-2)==Nature.DIRT)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre-2)!=Nature.DIRT");
								 if(!(getGameEng().getLevel().getNature(heightPre, widthPre-3)==Nature.DIRT)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre-3)!=Nature.DIRT");
								 if(!(getNbDallePose()==nbDallesPosesPre+1)) throw new PostConditionError("getNbDallePose()!=nbDallesPosesPre+1");
								 if(!(getHeight()==heightPre-1))  throw new PostConditionError("getHeight()!=heightPre-1");
								 if(!(getWidth()==widthPre-2))  throw new PostConditionError("getWidth()!=widthPre-2");
							 }
							 if(!(getNbToursBuilder()==nBTourBuilderPre+1)) throw new PostConditionError("getNbToursBuilder()!=nbDallesPosesPre+1");
 						 }else{
 							if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
						 }
					}
				
				}else if(etatPre.contains(EtatLemming.STOPPER)){
					if( hPreP1WPre==Nature.EMPTY){
						if(!(getEtat().contains(EtatLemming.FALLER))) throw new PostConditionError("!getEtat().contains(EtatLemming.FALLER)");
					}
					
				}else if (etatPre.contains(EtatLemming.BASHER)){ //DETRUIT LES MURS SUR SA LIGNE
					if(hPreP1WPre==Nature.EMPTY){
						if(!(getEtat().contains(EtatLemming.FALLER))){
							throw new PostConditionError("!getEtat().contains(EtatLemming.FALLER)");
						}
					}else {
						if(isDroitierPre){
							if(heightPre-2>=0 && hPreWPreP1 !=Nature.METAL
									&& hPreM2WPreP1 !=Nature.METAL
									&& hPreM1WPreP1 !=Nature.METAL
									&& hPreWPreM1 !=Nature.STOPPER
									&& hPreM2WPreP1 !=Nature.STOPPER
									&& hPreM1WPreP1 !=Nature.STOPPER){
								if(nbCreusePre<20){
									if( hPreWPreP1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre+1)!=Nature.EMPTY");
									}
									if( hPreM2WPreP1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-2, widthPre+1)!=Nature.EMPTY");
									}
									if( hPreM1WPreP1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-1, widthPre+1)!=Nature.EMPTY");
									}
									if(!(getWidth()==widthPre+1)) throw new PostConditionError("getWidth()!=widthPre+1");
									if(!(getNbCreuse()==nbCreusePre+1)) throw new PostConditionError("getNbCreuse()!=nbCreusePre+1");
								}else{
									if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
									if(!(getNbCreuse()==0)) throw new PostConditionError("getNbCreuse()!=0");
								}
							}else{
								if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
								if(!(getNbCreuse()==0)) throw new PostConditionError("getNbCreuse()!=0");
							}
						} else {
							if(heightPre-2>=0 && hPreWPreM1 !=Nature.METAL
									&& hPreM2WPreM1 !=Nature.METAL
									&& hPreM1WPreM1 !=Nature.METAL
									&& hPreWPreM1 !=Nature.STOPPER
									&& hPreM2WPreM1 !=Nature.STOPPER
									&& hPreM1WPreM1 !=Nature.STOPPER){
								if(nbCreusePre<20){
									if( hPreWPreM1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre-1)!=Nature.EMPTY");
									}
									if( hPreM2WPreM1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-2, widthPre-1)!=Nature.EMPTY");
									}
									if( hPreM1WPreM1==Nature.DIRT){
										if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY)) throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-1, widthPre-1)!=Nature.EMPTY");
									}
									if(!(getWidth()==widthPre-1)) throw new PostConditionError("getWidth()!=widthPre-1");
									if(!(getNbCreuse()==nbCreusePre+1)) throw new PostConditionError("getNbCreuse()!=nbCreusePre+1");
								}else{
									if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
									if(!(getNbCreuse()==0)) throw new PostConditionError("getNbCreuse()!=0");
								}
							}else{
								if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
								if(!(getNbCreuse()==0)) throw new PostConditionError("getNbCreuse()!=0");
							}
						}
					}
				
				}else if (etatPre.contains(EtatLemming.MINER)){ // CREUSEUR EN DIAGONALE
					if(hPreP1WPre==Nature.EMPTY){
						if(!(getEtat().contains(EtatLemming.FALLER))) throw new PostConditionError("!getEtat().contains(EtatLemming.FALLER)");
					}
					else{
						if(isDroitierPre){
							
							if(hPreP1WPreP1!=Nature.METAL
									&&hPreP1WPreP1!=Nature.STOPPER
									&&hPreWPreP1!=Nature.METAL
									&&hPreWPreP1!=Nature.STOPPER){
								if( hPreP1WPreP1==Nature.DIRT){
									if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre+1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre+1)!=Nature.EMPTY");
									}
								}
								if( hPreWPreP1==Nature.DIRT){
									if(!(getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre+1)!=Nature.EMPTY");
									}
								}
								if(!(getHeight()==heightPre+1)) throw new PostConditionError("getHeight()!=heightPre+1");
								if(!(getWidth()==widthPre+1)) throw new PostConditionError("getWidth()!=widthPre+1");
							
							
							}else if(heightPre-2>=0
									 && hPreM2WPreP1!=Nature.METAL
									 && hPreM2WPreP1!=Nature.STOPPER
									 && hPreM1WPreP1!=Nature.METAL
									 && hPreM1WPreP1!=Nature.STOPPER){
								if( hPreM2WPreP1==Nature.DIRT){
									if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre+1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-2, widthPre+1)!=Nature.EMPTY");
									}
								}
								if( hPreM1WPreP1==Nature.DIRT){
									if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre+1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-1, widthPre+1)!=Nature.EMPTY");
									}
								}
								if( hPreWPreP1!=Nature.EMPTY){ 
									if(!(getGameEng().getLevel().getNature(heightPre, widthPre+1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre+1)!=Nature.EMPTY");
									}
								}
								if(!(getWidth()==widthPre+1)) throw new PostConditionError("getWidth()!=widthPre+1");
							}else{
								if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
							}
							
						}else{
							if(hPreP1WPreM1!=Nature.METAL
									&&hPreP1WPreM1!=Nature.STOPPER
									&&hPreWPreM1!=Nature.METAL
									&&hPreWPreM1!=Nature.STOPPER){
								if( hPreP1WPreM1==Nature.DIRT){
									if(!(getGameEng().getLevel().getNature(heightPre+1, widthPre-1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre+1, widthPre-1)!=Nature.EMPTY");
									}
								}
								if( hPreWPreM1==Nature.DIRT){
									if(!(getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre-1)!=Nature.EMPTY");
									}
								}
								if(!(getHeight()==heightPre+1)) throw new PostConditionError("getHeight()!=heightPre+1");
								if(!(getWidth()==widthPre-1)) throw new PostConditionError("getWidth()!=widthPre-1");
							
							
							}else if(heightPre-2>=0
									 && hPreM2WPreM1!=Nature.METAL
									 && hPreM2WPreM1!=Nature.STOPPER
									 && hPreM1WPreM1!=Nature.METAL
									 && hPreM1WPreM1!=Nature.STOPPER){
								if( hPreM2WPreM1==Nature.DIRT){
									if(!(getGameEng().getLevel().getNature(heightPre-2, widthPre-1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-2, widthPre-1)!=Nature.EMPTY");
									}
								}
								if( hPreM1WPreM1==Nature.DIRT){
									if(!(getGameEng().getLevel().getNature(heightPre-1, widthPre-1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre-1, widthPre-1)!=Nature.EMPTY");
									}
								}
								if( hPreWPreM1!=Nature.EMPTY){ 
									if(!(getGameEng().getLevel().getNature(heightPre, widthPre-1)==Nature.EMPTY)){
										throw new PostConditionError("getGameEng().getLevel().getNature(heightPre, widthPre-1)!=Nature.EMPTY");
									}
								}
								if(!(getWidth()==widthPre-1)) throw new PostConditionError("getWidth()!=widthPre-1");
							}else{
								if(!(getEtat().contains(EtatLemming.WALKER))) throw new PostConditionError("!getEtat().contains(EtatLemming.WALKER)");
							}
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
