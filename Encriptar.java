import java.io.*;
import javax.crypto.*;
import java.security.*;
import java.util.ArrayList;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encriptar {

	private static ArrayList<String> leeFicheros(){
		ArrayList<String> ret;
		try{
			BufferedReader reader =new BufferedReader(new FileReader("Ficheros.txt"));
			String linea=reader.readLine();
			ret = new ArrayList<String>();
			while(linea!=null){
				ret.add(linea);
				linea=reader.readLine();
			}
			reader.close();
		}catch(Exception e){
			ret= null;
		}
		return ret;
	}
	
	private static String generaKey(){
		return "AAAAAAAAAAAAAAAA";
	}

	private static void CifraFichero(String fichero){
		String cifradoNombre = fichero.split("\\.")[0];
		cifradoNombre+=".troleado";
		final String key = generaKey();
		File inpt = new File(fichero);
		File outpt = new File(cifradoNombre);
		
		if(!inpt.exists()){
			System.out.print(System.lineSeparator() + "ERROR ENCRIPTAR : " + fichero);
			return;
		}

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
				 

			//System.out.print("el iv es tiene longitud " + cipher.getIV().length + " y es:");
			/*for(int i = 0;i<16;i++){
				System.out.print(cipher.getIV()[i]);
			}*/
			if(outpt.exists()){
				System.out.print(System.lineSeparator() + "ENCRIPTADO! : " + fichero);
			}
			
			inputStream.close();
			outputStream.close();
	
		}catch(Exception e){
			System.out.print(System.lineSeparator() + "ERROR ENCRIPTAR : " + e.getMessage());
			
		}
	}

	public static void main(final String[] args) {
		
		ArrayList<String> ficheros = leeFicheros();


		//System.out.println(ficheros.toString());
		for(int i = 0;i<ficheros.size();i++){
			CifraFichero(ficheros.get(i));
		}

		System.out.println();




	}

}
