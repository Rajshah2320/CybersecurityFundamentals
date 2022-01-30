package day3;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC {

	private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
	private static String algorithm="HmacSHA256";
	
	public static String getHmac( String data, String key)
			  throws NoSuchAlgorithmException, InvalidKeyException {
			    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
			    Mac mac = Mac.getInstance(algorithm);
			    mac.init(secretKeySpec);
			    return bytesToHex(mac.doFinal(data.getBytes()));
			}
	

	public static String bytesToHex(byte[] bytes) {
	    byte[] hexChars = new byte[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars, StandardCharsets.UTF_8);
	}
}
