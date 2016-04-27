package decorateur;

import services.ILevel;
import services.Service;

public abstract class LevelDecorateur extends Decorateur implements ILevel{
	
	private ILevel delegate;
	
	public LevelDecorateur(ILevel delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public Service getDelegate(){
		return delegate;
	}
	
}
