package net.globaltms.security.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import net.globaltms.security.IPasswordStorageService;
import net.globaltms.security.dto.PassSaltDTO;
import net.globaltms.security.exception.PasswordStorageException;

public class PasswordStorageService implements IPasswordStorageService {
	
	
	private static final int HASH_SIZE = 2048;
	private static final int HASHING_ITERATIONS = 32000;

	@Override
	public PassSaltDTO createHashedPassword(final String password) throws PasswordStorageException {

		byte[] salt = generateSalt();
		byte[] hashedPassword = hashPassword(password, salt, HASHING_ITERATIONS, HASH_SIZE);

		return new PassSaltDTO(toString(hashedPassword),toString(salt));
	}
	
	@Override
	public boolean verifyPassword(final String password,final PassSaltDTO pair) throws PasswordStorageException {
		
        byte[] hashedPassword = toByte(pair.getPass());
        byte[] salt = toByte(pair.getSalt());
        
        return equalPasswords(password, salt, hashedPassword);
	}
	
	private boolean equalPasswords(final String password,final byte[] salt,final byte[] hashedPassword) throws PasswordStorageException {
		
		String hashedPass = toString(hashedPassword);
		String comparedWith = toString(hashPassword(password, salt, HASHING_ITERATIONS, HASH_SIZE));
		
        return hashedPass.equals(comparedWith);
    }
	
	private byte[] hashPassword(final String password, final byte[] salt, final int iterations, final int keyLength) throws PasswordStorageException {
		
		SecretKey key;
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
			key = skf.generateSecret(spec);			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new PasswordStorageException(e);
		}
		
		return key.getEncoded();
	}

	private byte[] generateSalt() {
		
		SecureRandom secureRandom = new SecureRandom();
		final byte[] salt = new byte[HASH_SIZE / 8];
		secureRandom.nextBytes(salt);
		
		return salt;
	}
	
	private String toString(final byte[] data) {
		
		return Base64.getEncoder().encodeToString(data);
	}
	
	private byte[] toByte(final String data) {
		
		return Base64.getDecoder().decode(data);
	}
}
