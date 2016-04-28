package decorateur;

import services.ILevel;

public abstract class LevelDecorateur extends Decorateur implements ILevel{
	
	protected ILevel delegate;
	
	public LevelDecorateur(ILevel delegate) {
		this.delegate = delegate;
	}
	
}
