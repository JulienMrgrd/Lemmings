package decorateur;

import services.ILemming;
import services.Service;

public abstract class LemmingDecorateur extends Decorateur implements ILemming {
	
	private ILemming delegate;

	public LemmingDecorateur(ILemming delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public Service getDelegate(){
		return (Service) delegate;
	}

}
