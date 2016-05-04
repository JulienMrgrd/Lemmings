package tests.bug.contrat;

import decorateur.JoueurDecorateur;
import enumeration.EtatLemming;
import services.IGameEng;
import tests.bug.servicesImplementations.JoueurBug;

public class JoueurContratBug extends JoueurDecorateur{
	
	public JoueurContratBug() {
		super(new JoueurBug());
	}

	@Override
	public int getNbTokens(EtatLemming etat) {
		checkInvariants();
		int res = delegate.getNbTokens(etat);
		checkInvariants();
		return res;
	}

	@Override
	public EtatLemming getEtat(EtatLemming etat) {
		checkInvariants();
		EtatLemming res = delegate.getEtat(etat);
		checkInvariants();
		return res;
	}

	@Override
	public IGameEng getGameEngine() {
		checkInvariants();
		IGameEng res = delegate.getGameEngine();
		checkInvariants();
		return res;
	}

	@Override
	public void init(IGameEng gE) {
		delegate.init(gE);
		checkInvariants();
	}

	@Override
	public void changeEtatLemming(int idLem, String etat) {
		checkInvariants();
		delegate.changeEtatLemming(idLem, etat);
		checkInvariants();
	}

	@Override
	public void changeSizeColony(int sizeColony) {
		checkInvariants();
		delegate.changeSizeColony(sizeColony);
		checkInvariants();
	}

	@Override
	public void destroyAllLem() {
		checkInvariants();
		delegate.destroyAllLem();
		checkInvariants();
	}

	@Override
	public void reset() {
		delegate.reset();
		checkInvariants();
	}
	
	@Override
	protected void checkInvariants() {
		// TODO Auto-generated method stub
	}
}
