package servicesImplementations;

import java.util.ArrayList;
import java.util.List;

import enumeration.EtatLemming;
import services.IGameEng;
import services.IJoueur;
import services.ILemming;

public class Joueur implements IJoueur {
	
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
	public void changeEtatLemming(ILemming lem, EtatLemming etat) {
		EtatLemming etatLem = getEtat(etat);
		if(etatLem.getNbToken()>0){
			etatLem.reduceToken();
			lem.setEtat(etatLem);
		}
	}

	@Override
	public void changeSpawnSpeed(int speedSpawn) {
		gameEng.changeSpawnSpeed(speedSpawn);
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
