package servicesImplementations.bug;

import java.util.ArrayList;
import java.util.List;

import enumeration.EtatLemming;
import services.IGameEng;
import services.IJoueur;
import services.ILemming;

public class JoueurBug implements IJoueur {
	
	List<EtatLemming> allTokens;
	IGameEng gameEng;
	
	@Override
	public int getNbTokens(EtatLemming etat) {
		for(EtatLemming etatLem : allTokens){
			if(etatLem==etat) return etatLem.getNbToken();
		}
		return 0;
	}
	
	@Override
	public EtatLemming getEtat(EtatLemming etat) {
		for(EtatLemming etatLem : allTokens){
			if(etatLem==etat) return etatLem;
		}
		return null;
	}

	@Override
	public IGameEng getGameEngine() {
		return gameEng;
	}

	@Override
	public void init(IGameEng gE) {
		this.gameEng = gE;
		allTokens=new ArrayList<>();
		for(EtatLemming etat : EtatLemming.values()){
			allTokens.add(etat);
		}
	}

	@Override
	public void changeEtatLemming(int idLem, String etat) {
		EtatLemming etatLem = EtatLemming.getEtatByName(etat);
		etatLem = getEtat(etatLem);
		if(etatLem.getNbToken()>0){
			etatLem.reduceToken();
			getGameEngine().getLemVivantById(idLem).setEtat(etatLem);
		}
	}

	@Override
	public void changeSizeColony(int sizeColony) {
		gameEng.changeSizeColony(sizeColony);
	}

	@Override
	public void destroyAllLem() {
		for(ILemming lem : gameEng.getLemVivants()){
			gameEng.killLemming(lem.getId());
		}
	}
	
	@Override
	public void reset() {
		destroyAllLem();
		for(EtatLemming etat : allTokens){
			etat.reset();
		}
		gameEng.reset();
	}

}
