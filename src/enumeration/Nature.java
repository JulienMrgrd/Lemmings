package enumeration;

public enum Nature {
	EMPTY, DIRT, METAL, STOPPER;
	
	public static Nature getNatureByName(String name){
		try{
			return Enum.valueOf(Nature.class, name.toUpperCase());
		} catch (Exception e){
			return null;
		}
	}
}
