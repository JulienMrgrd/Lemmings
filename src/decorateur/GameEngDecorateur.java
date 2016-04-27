package decorateur;

import services.IGameEng;
import services.Service;

public abstract class GameEngDecorateur extends Decorateur implements IGameEng {

	private IGameEng delegate;
	
	public GameEngDecorateur(IGameEng delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public Service getDelegate(){
		return (Service) delegate;
	}
	
}
