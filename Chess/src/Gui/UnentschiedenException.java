package Gui;

public class UnentschiedenException extends Exception{
	
	public UnentschiedenException() 
	{
		super();
	}
	
	public UnentschiedenException(String message)
	{ 
		super(message); 
	}
	
	public UnentschiedenException(String message, Throwable cause) 
	{
		super(message, cause); 
	}
	
	public UnentschiedenException(Throwable cause) 
	{
		super(cause); 
	}

}
