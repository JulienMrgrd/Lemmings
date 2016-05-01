package decorateur;

import services.IJoueur;

public abstract class JoueurDecorateur extends Decorateur implements IJoueur {

	protected IJoueur delegate;
	
	public JoueurDecorateur(IJoueur delegate) {
		this.delegate = delegate;
	}
	
}
