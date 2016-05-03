package enumeration;

public enum EtatLemming {
	
	WALKER(EnumUtils.LIMITED), 
	FALLER(!EnumUtils.LIMITED), // Tombeur tokens illimités
	DIGGER(EnumUtils.LIMITED), // Creuseur
	CLIMBER(EnumUtils.LIMITED), 
	BUILDER(EnumUtils.LIMITED), 
	FLOATER(EnumUtils.LIMITED), 
	BOMBER(EnumUtils.LIMITED), 
	STOPPER(EnumUtils.LIMITED), 
	BASHER(EnumUtils.LIMITED), 
	MINER(EnumUtils.LIMITED);
	
	private int nbToken;
	private boolean isLimited;
	
	private EtatLemming(boolean limited) {
		isLimited = limited;
		if(limited) setNbToken(EnumUtils.TOKENDEBASE);
		else setNbToken(EnumUtils.MAXTOKEN);
	}
	
	public void reset(){
		if(isLimited) setNbToken(EnumUtils.TOKENDEBASE);
		else setNbToken(EnumUtils.MAXTOKEN);
	}

	public int getNbToken() {
		return nbToken;
	}
	
	public void setNbToken(int nbToken) {
		this.nbToken = nbToken;
	}
	
	public void addToken(){
		nbToken++;
	}
	
	public void reduceToken(){
		nbToken--;
	}
	
	private static class EnumUtils {
		private static final boolean LIMITED = true;
		private static final int TOKENDEBASE = 5;
		private static final int MAXTOKEN = Integer.MAX_VALUE;
	}
	
	public static EtatLemming getEtatByName(String name){
		try{
			return Enum.valueOf(EtatLemming.class, name.toUpperCase());
		} catch (Exception e){
			return null;
		}
	}

}
