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
	/** pre : changeEtatLemming(idLem, etat) require getGameEngine().containsIdColony(idLem)==true
	 * 											   ^ getNbTokens(etat)>0 */
	void changeEtatLemming(int idLem, String etat);
	
	/** pre : changeSizeColony(sizeColony) require sizeColony>0 */
	void changeSizeColony(int sizeColony);
	
	/** post : for(int i=0; i<getNbLemVivants(); i++){
	 * 				getAllVivants()[i].getEtats().contains(EtatLemming.BOMBER);
	 * 		   } */
	void destroyAllLem();
	
	void reset();
	
}
