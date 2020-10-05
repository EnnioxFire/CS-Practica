import java.io.*;
import javax.crypto.*;
import java.security.*;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encriptar {

	public static void main(final String[] args) {
		
		final String key = "AAAAAAAAAAAAAAAA";
		File inpt = new File("bungee.mp3");
		File outpt = new File("troleado");
		
		//aqui se haria la encriptacion
		try{
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			FileInputStream inputStream = new FileInputStream(inpt);
			byte[] inputBytes = new byte[(int) inpt.length()];
			inputStream.read(inputBytes);
				 
			byte[] outputBytes = cipher.doFinal(inputBytes);
				 
			FileOutputStream outputStream = new FileOutputStream(outpt);

			outputStream.write(cipher.getIV());
			
			
			outputStream.write(outputBytes);
				 

			System.out.print("el iv es tiene longitud " + cipher.getIV().length + " y es:");
			for(int i = 0;i<16;i++){
				System.out.print(cipher.getIV()[i]);
			}
			inputStream.close();
			outputStream.close();
	
		}catch(Exception e){
			System.out.print(System.lineSeparator() + "ERROR ENCRIPTAR : " + e.getMessage());
		}
	}

}
