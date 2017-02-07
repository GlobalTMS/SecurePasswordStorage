package net.globaltms.security.dto;

public class PassSaltDTO {
	private String pass;
	private String salt;
	
	public PassSaltDTO() {
		super();
	}
	
	public PassSaltDTO(String pass, String salt) {
		super();
		this.pass = pass;
		this.salt = salt;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
