import java.io.*;
import javax.crypto.*;
import java.security.*;
import java.util.*;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encriptar {

	public static String encode64(String value) throws Exception {
		return  Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
	 }


	private static ArrayList<String> leeFicheros(){
		ArrayList<String> ret;
		String ruta = "claves.txt";
		File archivo = new File(ruta);
		archivo.delete();
		try{
			BufferedReader reader = new BufferedReader(new FileReader("Ficheros.txt"));
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
	
	private static String generaKey() throws IOException {

		char[] chars = "QWERTYUIOPLKJHGFDSAZXCVBNMabcdefghijklmnopqrstuvwxyz1234567890!#$%&'()*+,-./:;<=>?@[]^_`{}|~".toCharArray();
        StringBuilder sb = new StringBuilder(16);
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
		//System.out.println(output);

		/*for(int i=0; i<output.length(); i++) {

			System.out.println("caracter -- " + output.charAt(i) + " pesa: " + (byte)output.charAt(i));
		}*/

		//output = encode(output);
		//output = Base64.getEncoder().encodeToString(output.getBytes(), StandardCharsets.UTF_8.name());
		
		String ruta = "claves.txt";
		File archivo = new File(ruta);
		BufferedWriter bw;

		bw = new BufferedWriter(new FileWriter(archivo, true));
		bw.write(output);
		bw.newLine();

		bw.close();

		return output;
	}

	private static void CifraFichero(String fichero) throws IOException {

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
				System.out.println(System.lineSeparator() + "ENCRIPTADO! : " + fichero);
			}
			
			inputStream.close();
			outputStream.close();
	
		}catch(Exception e){
			System.out.print(System.lineSeparator() + "ERROR ENCRIPTAR : " + e.getMessage());
			
		}
	}

	public static void main(final String[] args) throws IOException {
		
		ArrayList<String> ficheros = leeFicheros();


		//System.out.println(ficheros.toString());
		for(int i = 0;i<ficheros.size();i++){
			CifraFichero(ficheros.get(i));
		}

		System.out.println();

	}

	public static String encode(String value) {

		try {
			return  Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {

			System.out.println("Error: " + e.toString());
			return null;
		}
	 }
}
