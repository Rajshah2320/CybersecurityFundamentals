package day1;

public class AdditionCipher {

	public String encrypt(String plaintext, int key){
	    
	    plaintext=plaintext.replace(" ","");
	    plaintext=plaintext.toLowerCase();
	    
	    char[] arr=plaintext.toCharArray();
	    
	    for(int i=0;i<arr.length;i++){
	        arr[i]=(char)(arr[i]-'a');
	    }
	    
	    for(int i=0;i<arr.length;i++){
	        arr[i]=(char)(arr[i] + key);
	        
	        arr[i]=(char)(((int)arr[i])%26);
	        
	        arr[i]=(char)(arr[i] + 'a');
	    }
	    
	    return String.valueOf(arr);
	}
	
	public String decrypt(String ciphertext, int key){
	    
	    char[] arr=ciphertext.toCharArray();
	    
	    for(int i=0;i<arr.length;i++){
	        arr[i]=(char)(arr[i]-'a');
	    }
	    
	    for(int i=0;i<arr.length;i++){
	        arr[i]=(char)((arr[i] - key + 26)%26);
	        
	        arr[i]=(char)(arr[i]+'a');
	    }
	    
	    return String.valueOf(arr);
	}
}
