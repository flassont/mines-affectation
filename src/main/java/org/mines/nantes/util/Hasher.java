package org.mines.nantes.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Florian on 30/05/2015.
 */
public class Hasher {
	private static Hasher instance = new Hasher();

	public static final String CHARSET = "UTF-8";
	private static final String SALT = "M!n3s";
	public static final String ALGORITHM = "SHA-1";

	public static Hasher getInstance() {
		return instance;
	}

	private Hasher() {
	}

	public String hash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
		digest.reset();
		digest.update(SALT.getBytes(CHARSET));
		byte[] input = digest.digest(password.getBytes(CHARSET));
		return new String(input, CHARSET);
	}
}
