import java.io.*;
import javax.crypto.*;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class Desencriptar {
	public static String decode64(String value) throws Exception {
		byte[] decodedValue = Base64.getDecoder().decode(value);  // Basic Base64 decoding
		return new String(decodedValue, StandardCharsets.UTF_8);
	 }

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

	private static String leeKey(String fichero, int indice) {

		FileReader archivo = null;
		BufferedReader lectura = null;
		String linea = null;
		int contador = 0;

		try {

			archivo = new FileReader("claves.txt");
			lectura = new BufferedReader(archivo);

			linea = lectura.readLine();
			contador++;

			while(contador <= indice) {

				linea = lectura.readLine();
				contador++;
			}

			//System.out.println(linea);
			
			//linea = decode(linea);
			//linea = new String(Base64.getDecoder().decode(linea, StandardCharsets.UTF_8.name()));

			/*for(int i=0; i<linea.length(); i++) {

				System.out.println("caracter -- " + linea.charAt(i) + " pesa: " + (byte)linea.charAt(i));
			}*/

			//System.out.println(linea);
			
		} catch(IOException e) {

			System.out.print(System.lineSeparator() + "ERROR: " + e.getMessage());
		}

		return linea;
	}


	private static void DescifraFichero(String fichero, int indice){
		String cifradoNombre = fichero.split("\\.")[0];
		cifradoNombre+=".troleado";
		File outpt = new File(fichero);
		File inpt = new File(cifradoNombre);
		
		//falta leer y descifrar key
		String key=leeKey(fichero, indice);
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
			//System.out.print("el iv es tiene longitud " + ivbytes.length + " y es:");
			
			byte[] outputBytes = cipher.doFinal(Arrays.copyOfRange(inputBytes, 16, inputBytes.length));
			FileOutputStream outputStream = new FileOutputStream(outpt);
			outputStream.write(outputBytes);
			inputStream.close();
			outputStream.close();
			System.out.println(System.lineSeparator() + "EXITO AL DESCIFRAR! : DESCIFRADO: " + fichero);
		}catch(Exception e){
			System.out.println(System.lineSeparator() + "ERROR DESENCRIPTAR : " + e.getMessage() + " ----- NO SE PUDO DESENCRIPTAR " + fichero);
		}
	}

	public static void main(String[] args) {
		
		ArrayList<String> ficheros = leeFicheros();
		//System.out.println(ficheros.toString());
		for(int i = 0;i<ficheros.size();i++){

			DescifraFichero(ficheros.get(i), i);
		}
	}


	public static String decode(String value) {

		try {
			byte[] decodedValue = Base64.getDecoder().decode(value);  // Basic Base64 decoding
			return new String(decodedValue, StandardCharsets.UTF_8);

		} catch (Exception e) {

			System.out.println("Error: " + e.toString());
			return null;
		}
	 }
}
