package errors;

public class InvariantError extends Error {

	private static final long serialVersionUID = 1L;

	public InvariantError(String error){
		super(error);
	}
}