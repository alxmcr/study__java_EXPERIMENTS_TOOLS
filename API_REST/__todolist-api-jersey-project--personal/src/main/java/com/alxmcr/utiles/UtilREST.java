package com.alxmcr.utiles;

import java.math.BigInteger;
import java.security.SecureRandom;

public class UtilREST {

	public static String nextSessionId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(36);
	}
}
