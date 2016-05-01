package services;

import enumeration.EtatLemming;

public interface IJoueur {

	// OBSERVATORS
	int getNbTokens(EtatLemming etat);
	EtatLemming getEtat(EtatLemming etat);
	IGameEng getGameEngine();

	// CONSTRUCTORS
	void init(IGameEng gE);

	// OPERATORS
	/** pre : changeEtatLemming(lem, etat) require getNbTokens(etat)>0 */
	void changeEtatLemming(ILemming lem, EtatLemming etat);
	void changeSpawnSpeed(int speedSpawn);
	void destroyAllLem();
	void reset();
}
