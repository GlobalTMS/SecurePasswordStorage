package net.globaltms.security.test.unit;

import java.math.BigInteger;
import java.security.SecureRandom;

import static org.junit.Assert.fail;

import net.globaltms.security.dto.PassSaltDTO;
import net.globaltms.security.exception.PasswordStorageException;
import net.globaltms.security.impl.PasswordStorageService;

import org.junit.Test;

public class PasswordStorageTest {
	
	PasswordStorageService service = new PasswordStorageService();
	@Test
	public void createHash_withCorrectValues() {
		final String PASS = getRamdomPassword();
		PassSaltDTO result = new PassSaltDTO();
		try {
			result= service.createHashedPassword(PASS);
		} catch (PasswordStorageException e) {
			fail("");
		}
		
		assert(result!=null);
	}
	
	@Test
	public void createHash_samePassDiffentHash() {
		
		final String PASS = getRamdomPassword();
		PassSaltDTO firstResult = new PassSaltDTO("", "");
		PassSaltDTO secondResult = new PassSaltDTO("", "");
		try {
			firstResult= service.createHashedPassword(PASS);
			secondResult= service.createHashedPassword(PASS);
		} catch (PasswordStorageException e) {
			fail("");
		}
		
		assert(firstResult.getPass()!=secondResult.getPass());
		assert(firstResult.getSalt()!=secondResult.getSalt());
	}
	
	@Test
	public void createHash_verifyCorrectHash() {
		
		final String PASS = getRamdomPassword();
		PassSaltDTO firstResult = new PassSaltDTO();
		try {
			firstResult= service.createHashedPassword(PASS);
		} catch (PasswordStorageException e) {
			fail("");
		}
		boolean isCorrect=false;
		try {
			isCorrect = service.verifyPassword(PASS, firstResult);
		} catch (PasswordStorageException e) {
			fail();
		}
		
		assert(isCorrect);
	}
	
	
	private String getRamdomPassword() {

		SecureRandom random = new SecureRandom();
		
		return new BigInteger(130, random).toString(32);
	}
	
}
