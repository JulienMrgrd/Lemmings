package contrat;

import decorateur.JoueurDecorateur;
import enumeration.EtatLemming;
import services.IGameEng;
import services.ILemming;
import servicesImplementations.Joueur;

public class JoueurContrat extends JoueurDecorateur{
	
	public JoueurContrat() {
		super(new Joueur());
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
	public void changeEtatLemming(ILemming lem, EtatLemming etat) {
		checkInvariants();
		delegate.changeEtatLemming(lem, etat);
		checkInvariants();
	}

	@Override
	public void changeSpawnSpeed(int speedSpawn) {
		checkInvariants();
		delegate.changeSpawnSpeed(speedSpawn);
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
