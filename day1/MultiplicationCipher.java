package day1;

public class MultiplicationCipher {
	public String encrypt(String plaintext, int key){
	    plaintext=plaintext.replace(" ","");
	    plaintext=plaintext.toLowerCase();
	    
	    char[] arr=plaintext.toCharArray();
	    
	    for(int i=0;i<arr.length;i++){
	        arr[i]=(char)(arr[i]-'a');
	        arr[i]=(char)((arr[i]*key)%26);
	        arr[i]=(char)(arr[i] + 'a');
	    }
	    
	    return String.valueOf(arr);
	}
	
	public String decrypt(String ciphertext,int key){
	    char[] arr=ciphertext.toCharArray();
	    
	    int inverse=findInverse(key);
	    
	    for(int i=0;i<arr.length;i++){
	        arr[i]=(char)(arr[i]-'a');
	        
	        arr[i]=(char)((arr[i]*inverse)%26);
	        
	        arr[i]=(char)(arr[i] + 'a');
	    }
	    
	    return String.valueOf(arr);
	}
	
	public int findInverse(int key) {
		
		int val=0;
		
		for(int i=0;i<26;i++) {
			val=key*i;
			
			if(val%26==1)return i;
		}
		
		return 0;
	}
}
