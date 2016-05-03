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
	/** pre : changeEtatLemming(idLem, etat) require getNbTokens(etat)>0 */
	void changeEtatLemming(int id, String etat);
	void destroyAllLem();
	void reset();
	
	/** pre : changeSizeColony(sizeColony) require sizeColony>0 */
	void changeSizeColony(int sizeColony);
}
