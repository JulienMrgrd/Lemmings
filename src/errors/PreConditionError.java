package errors;
public class PreConditionError extends Error{
	
	private static final long serialVersionUID = 1L;

	public PreConditionError(String method, String error) {
		super("PreConditionError ("+method+") : "+error);
	}
}