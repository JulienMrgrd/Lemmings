package errors;

public class InvariantError extends Error {

	private static final long serialVersionUID = 1L;

	public InvariantError(String method, String error) {
		super("InvariantError ("+method+") : "+error);
	}
}