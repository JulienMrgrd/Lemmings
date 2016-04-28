package contrat;

import decorateur.LemmingDecorateur;
import enumeration.EtatLemming;
import errors.InvariantError;
import services.IGameEng;
import servicesImplementations.Lemming;

public class LemmingContrat extends LemmingDecorateur{

	public LemmingContrat() {
		super(new Lemming());
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isDroitier() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EtatLemming getEtat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGameEng getGameEng() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nbCasesFalling() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init(IGameEng gameEng) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDirection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEtat(EtatLemming etat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void checkInvariants() throws InvariantError {
		// TODO Auto-generated method stub
		
	}

}
