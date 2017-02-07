package net.globaltms.security.exception;

public class PasswordStorageException extends Exception{

	private static final long serialVersionUID = 4612276809883856993L;

	public PasswordStorageException(Exception e) {
		super(e.getMessage());
	}

}
