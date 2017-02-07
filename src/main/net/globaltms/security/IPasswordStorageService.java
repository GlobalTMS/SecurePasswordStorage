package net.globaltms.security;

import net.globaltms.security.dto.PassSaltDTO;
import net.globaltms.security.exception.PasswordStorageException;

public interface IPasswordStorageService {
	PassSaltDTO createHashedPassword(final String password) throws PasswordStorageException;
	boolean verifyPassword(final String password,final PassSaltDTO pair) throws PasswordStorageException;	
}
