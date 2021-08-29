package Gui;

public class SchachmattException extends Exception{
	
	public SchachmattException() 
	{
		super();
	}
	
	public SchachmattException(String message)
	{ 
		super(message); 
	}
	
	public SchachmattException(String message, Throwable cause) 
	{
		super(message, cause); 
	}
	
	public SchachmattException(Throwable cause) 
	{
		super(cause); 
	}

}
