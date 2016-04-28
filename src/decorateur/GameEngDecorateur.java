package decorateur;

import services.IGameEng;

public abstract class GameEngDecorateur extends Decorateur implements IGameEng {

	protected IGameEng delegate;
	
	public GameEngDecorateur(IGameEng delegate) {
		this.delegate = delegate;
	}
	
}
