package day2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class DES {
	private static Cipher encrypt;
	// creating an instance of the Cipher class for decryption
	private static Cipher decrypt;
	// initializing vector
	private static final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };

	// main() method
	
	public void encrypt(String plaintextPath, String encryptedPath) {
		try {
			// generating keys by using the KeyGenerator class
			SecretKey scrtkey = KeyGenerator.getInstance("DES").generateKey();
			AlgorithmParameterSpec aps = new IvParameterSpec(initialization_vector);
			// setting encryption mode
			encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
			encrypt.init(Cipher.ENCRYPT_MODE, scrtkey, aps);
			// setting decryption mode
			decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
			decrypt.init(Cipher.DECRYPT_MODE, scrtkey, aps);
			// calling encrypt() method to encrypt the file
			encryption(new FileInputStream(plaintextPath), new FileOutputStream(encryptedPath));
		
		}
		
		catch (Exception e) {
			// prints the message (if any) related to exceptions
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}
		
		System.out.println("The encrypted file has been created successfully.");
	}
	
	public void decrypt(String encryptedPath, String decryptedPath) {
		try {
			decryption(new FileInputStream(encryptedPath), new FileOutputStream(decryptedPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("The decrypted file has been created successfully.");
	}

	// method for encryption
	private static void encryption(InputStream input, OutputStream output) throws IOException {
		output = new CipherOutputStream(output, encrypt);
		// calling the writeBytes() method to write the encrypted bytes to the file
		writeBytes(input, output);
	}

	// method for decryption
	private static void decryption(InputStream input, OutputStream output) throws IOException {
		input = new CipherInputStream(input, decrypt);
		// calling the writeBytes() method to write the decrypted bytes to the file
		writeBytes(input, output);
	}

	// method for writing bytes to the files
	private static void writeBytes(InputStream input, OutputStream output) throws IOException {
		byte[] writeBuffer = new byte[512];
		int readBytes = 0;
		while ((readBytes = input.read(writeBuffer)) >= 0) {
			output.write(writeBuffer, 0, readBytes);
		}
		// closing the output stream
		output.close();
		// closing the input stream
		input.close();
	}
}
