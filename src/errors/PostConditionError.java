package errors;
public class PostConditionError extends Error{
	
	private static final long serialVersionUID = 1L;
	
	public PostConditionError(String method, String error) {
		super("PostConditionError ("+method+") : "+error);
	}
	
}