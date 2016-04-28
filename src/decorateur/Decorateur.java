package decorateur;

import errors.InvariantError;

public abstract class Decorateur {
	
	protected abstract void checkInvariants() throws InvariantError;

}
