package contrat;

import decorateur.LemmingDecorateur;
import enumeration.EtatLemming;
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
	public EtatLemming getEtat() {
		checkInvariants();
		EtatLemming res = delegate.getEtat();
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
	public int nbCasesFalling() {
		checkInvariants();
		int res = delegate.nbCasesFalling();
		checkInvariants();
		return res;
	}

	@Override
	public void init(IGameEng gameEng) {
		// TODO Auto-generated method stub
		
		checkInvariants();
		
		delegate.init(gameEng);
		
		//TEST POSTCONDITION
		if(!(getGameEng() == gameEng))  throw new PostConditionError(" width!=getWidth()");
		if(!(getWidth() == gameEng.getLevel().getEntranceWidth()))  throw new PostConditionError(" width!=getWidth()");
		if(!(getHeight() == gameEng.getLevel().getEntranceHeight()))  throw new PostConditionError(" width!=getWidth()");
		if(!(getId() == gameEng.getSpawned()))  throw new PostConditionError(" width!=getWidth()");
		if(!(isDroitier())) throw new PostConditionError(" width!=getWidth()");
		if(!(getEtat() == EtatLemming.FALLER))  throw new PostConditionError(" width!=getWidth()");
		if(!(nbCasesFalling() == 0))  throw new PostConditionError(" width!=getWidth()");

		checkInvariants();
		
	}

	@Override
	public void setHeight(int height) {
		
		checkInvariants();
		//TESTER LES PRECONDITION
		if(!(height>0)) throw new PreConditionError("setHeight"," height<0");
		if(!(height<getGameEng().getLevel().getHeight()))  throw new PreConditionError("setHeight"," height<getGameEng().getLevel().getHeight()");
		
		//APPEL METHODE
		delegate.setHeight(height);
		
		//TEST POSTCONDITION
		if(!(height==getHeight())) throw new PostConditionError("setHeight"," height!=getHeight()");
		checkInvariants();
		
	}

	@Override
	public void setWidth(int width) {
		
		checkInvariants();
		//TESTER LES PRECONDITION
		if(!(width>0)) throw new PreConditionError("setWidth"," width<0");
		if(!(width<getGameEng().getLevel().getWidth()))  throw new PreConditionError("setWidth"," width<getGameEng().getLevel().getWidth()");
		
		//APPEL METHODE
		delegate.setWidth(width);
		
		//TEST POSTCONDITION
		if(!(width==getWidth())) throw new PostConditionError("setWidth"," width!=getWidth()");
		checkInvariants();
		
	}

	@Override
	public void setDirection() {
		
		checkInvariants();
		
		boolean dir=isDroitier();
		
		delegate.setDirection();
		
		//TEST POSTCONDITION
		if(!(dir!=isDroitier())) throw new PostConditionError("setDirection"," isDroitier==isDroitier()@pre");
		
		checkInvariants();
		
	}

	@Override
	public void setEtat(EtatLemming etat) {
		
		checkInvariants();
		
		delegate.setEtat(etat);
		
		//TEST POSTCONDITION
		if(!(etat==getEtat())) throw new PostConditionError("setEtat"," etat != getEtat()");
		
		checkInvariants();
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		checkInvariants();
		
		EtatLemming etatPre = getEtat();
		int heightPre = getHeight();
		int widthPre = getWidth();
		boolean isDroitierPre=isDroitier();
		
		delegate.step();
		
		//TEST POSTCONDITION
		if(getGameEng().getLevel().getExitHeight()==getHeight() && delegate.getGameEng().getLevel().getExitWidth()==getWidth() ){ 
			getGameEng().getNbLemSauves()@pre+1
		} else if(etatPre == EtatLemming.WALKER){ 
			if (getGameEng().getLevel().getNature(heightPre+1, widthPre)@pre == Nature.EMPTY){ 
				if(!(getEtat() == EtatLemming.FALLER)); 
				if(!(getWidth() == widthPre)); 
				if(!(getHeight() == heightPre)); 

			} else if (isDroitierPre){ 
			if (getGameEng().getLevel().getNature(heightPre-1, widthPre+1)@pre != Nature.EMPTY || 
					(getGameEng().getLevel().getNature(heightPre, widthPre+1)@pre != Nature.EMPTY && 
					getGameEng().getLevel().getNature(heightPre-2, widthPre+1)@pre != Nature.EMPTY)){ 
				if(!(isDroitier() == false)); 
				if(!(getWidth() == widthPre)); 
				if(!(getHeight() == heightPre)); 

			} else if (getGameEng().getLevel().getNature(heightPre, widthPre+1)@pre != Nature.EMPTY){ 
				if(!(isDroitier() == true)); 
				if(!(getWidth()==widthPre+1)); 
				if(!(getHeight()==heightPre-1)); 

			} else { 
				if(!(isDroitier() == true)); 
				if(!(getWidth() == widthPre+1)); 
				if(!(getHeight() == heightPre)); 
			} 
			}else { 
			if (getGameEng().getLevel().getNature(heightPre-1, widthPre-1)@pre != Nature.EMPTY || 
					(getGameEng().getLevel().getNature(heightPre, widthPre-1)@pre != Nature.EMPTY && 
					getGameEng().getLevel().getNature(heightPre-2, widthPre-1)@pre != Nature.EMPTY)){ 
				if(!(isDroitier() == true)); 
				if(!(getWidth() == widthPre)); 
				if(!(getHeight() == heightPre)); 

			} else if (getGameEng().getLevel().getNature(heightPre, widthPre-1)@pre != Nature.EMPTY){ 
				if(!(isDroitier() == false)); 
				if(!(getWidth() == widthPre-1)); 
				if(!(getHeight() == heightPre-1)); 

			} else { 
				if(!(isDroitier() == false)); 
				if(!(getWidth() == widthPre-1)); 
				if(!(getHeight() == heightPre)); 
			} 
			} 
			} else if (getEtat() == EtatLemming.FALLER){ 
			if (getGameEng().getLevel().getNature(heightPre+1, widthPre)@pre != Nature.EMPTY) { 
				if (nbCasesFalling() < 8) { 
					if(!(getEtat() == EtatLemming.WALKER)); 
					if(!(getWidth() == widthPre)); 
					if(!(getHeight() == heightPre)); 
				} 
			} else { 
				if(!(nbCasesFalling() == nbCasesFalling()@pre+1)); 
				if(!(getWidth() == widthPre)); 
				if(!(getHeight() == heightPre+1)); 
			} 
		}
		
	}
	
	@Override
	protected void checkInvariants(){
		// TODO Auto-generated method stub
		
	}

}
