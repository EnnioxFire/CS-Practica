import java.io.*;
import javax.crypto.*;
import java.security.*;
import java.util.Arrays;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;




public class Desencriptar {

	public static void main(String[] args) {
		
		
		final String key = "AAAAAAAAAAAAAAAA";
		File outpt = new File("bungee.mp3");
		File inpt = new File("troleado");
		
		//aqui se haria la encriptacion
		try{
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
			//byte[] buffer = new byte[8192];

		
			FileInputStream inputStream = new FileInputStream(inpt);
		
        	byte[] inputBytes = new byte[(int) inpt.length()];
			inputStream.read(inputBytes);
			byte[] ivbytes = Arrays.copyOfRange(inputBytes,0,16);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivbytes));
			System.out.print("el iv es tiene longitud " + ivbytes.length + " y es:");
			for(int i = 0;i<16;i++){
				System.out.print(ivbytes[i]);
			}
        	byte[] outputBytes = cipher.doFinal(Arrays.copyOfRange(inputBytes, 16, inputBytes.length));
             
        	FileOutputStream outputStream = new FileOutputStream(outpt);
       		outputStream.write(outputBytes);
             
        	inputStream.close();
			outputStream.close();

		}catch(Exception e){
			System.out.print(System.lineSeparator() + "ERROR DESENCRIPTAR : " + e.getMessage());
		}
	}

}
