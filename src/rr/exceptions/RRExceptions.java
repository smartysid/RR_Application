package rr.exceptions;

public class RRExceptions extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RRExceptions(String error)
	{
		super(error);
	}

	public RRExceptions(String error, Throwable cause)
	{
		super(error, cause);
	}
}
