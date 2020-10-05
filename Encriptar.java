import java.io.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.SecretKeySpec;

public class Encriptar {

	public static void main(final String[] args) {
		
		final String key = "AAAAAAAAAAAAAAAA";
		File inpt = new File("bungee.mp3");
		File outpt = new File("bungee.troleado");
		
		//aqui se haria la encriptacion
		try{
		Cipher cipher = Cipher.getInstance("AES");
		Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		/*
		FileInputStream inputStream = new FileInputStream(inpt);
        byte[] inputBytes = new byte[(int) inpt.length()];
        inputStream.read(inputBytes);
             
        byte[] outputBytes = cipher.doFinal(inputBytes);
             
        FileOutputStream outputStream = new FileOutputStream(outpt);
        outputStream.write(outputBytes);
			 
        inputStream.close();
		outputStream.close();
		*/





		FileInputStream inputStream = new FileInputStream(inpt);
		FileOutputStream outputStream = new FileOutputStream(outpt);
		byte[] buffer = new byte[8192];
		int count;
		while ((count = inputStream.read(buffer)) > 0)
		{
		    byte[] output = cipher.update(buffer, 0, count);
 		   outputStream.write(output);
		}
		outputStream.write(cipher.doFinal());
		outputStream.close();
		inputStream.close();




		}catch(Exception e){

		}
	}

}
