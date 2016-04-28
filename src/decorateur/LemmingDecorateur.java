package decorateur;

import services.ILemming;

public abstract class LemmingDecorateur extends Decorateur implements ILemming {
	
	protected ILemming delegate;

	public LemmingDecorateur(ILemming delegate) {
		this.delegate = delegate;
	}
	
}
