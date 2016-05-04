package contrat;

import decorateur.JoueurDecorateur;
import enumeration.EtatLemming;
import errors.PostConditionError;
import errors.PreConditionError;
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
	public void changeEtatLemming(int idLem, String etat) {
		checkInvariants();
		if(! (getGameEngine().containsIdColony(idLem)) ) throw new PreConditionError("getGameEngine().containsIdColony(idLem)==false");
		if(! (getNbTokens(EtatLemming.getEtatByName(etat))>0) ) throw new PreConditionError("getNbTokens(EtatLemming.getEtatByName(etat))<=0");
		
		delegate.changeEtatLemming(idLem, etat);
		checkInvariants();
	}

	@Override
	public void changeSizeColony(int sizeColony) {
		checkInvariants();
		if(! (sizeColony > 0) ) throw new PreConditionError("newSpawnSpeed <= 0");
		
		delegate.changeSizeColony(sizeColony);
		checkInvariants();
	}

	@Override
	public void destroyAllLem() {
		checkInvariants();
		delegate.destroyAllLem();
		
		for(ILemming lem : getGameEngine().getLemVivants()){
			if(!lem.getEtats().contains(EtatLemming.BOMBER)){
				throw new PostConditionError("lemming is not a BOMBER");
			}
		}
		checkInvariants();
	}

	@Override
	public void reset() {
		delegate.reset();
		checkInvariants();
	}
	
	@Override
	protected void checkInvariants() {
		// aucun pour l'instant
	}
}
