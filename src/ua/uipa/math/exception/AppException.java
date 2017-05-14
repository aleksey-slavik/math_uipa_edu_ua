package ua.uipa.math.exception;

public class AppException extends Exception {

	private static final long serialVersionUID = 6314191893096685412L;

	public AppException(){
		super();
	}
	
	public AppException(String message){
		super(message);
	}
	
	public AppException(String message, Throwable cause){
		super(message,cause);
	}
}
