package enumeration;

public enum EtatLemming {
	WALKER(0), 
	FALLER(1), 
	DIGGER(2), // Creuseur
	CLIMBER(3), 
	BUILDER(4), 
	FLOATER(5), 
	BOMBER(6), 
	STOPPER(7), 
	BASHER(8), 
	MINER(9);
	
	private int indexToken;
	
	private EtatLemming(int token) {
		setIndexToken(token);
	}

	public int getIndexToken() {
		return indexToken;
	}

	public void setIndexToken(int indexToken) {
		this.indexToken = indexToken;
	}
}
