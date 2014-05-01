package javaClass;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ComExt {

	public static String getAdresseSante(Plant plant) {
		String adresse = "";
		adresse += ComExt.getServeurURL();
		adresse += ComExt.getUserId() + "/";
		adresse += "plants/";
		String sticker = plant.getSticker();
		if (sticker == "Sticker Jaune")
			adresse += "1/";
		else if (sticker == "Sticker Noir")
			adresse += "2/";
		adresse += "list?method=plain";
		return adresse;
	}

	/********* ecriture/lecture sur le fichier serveur.txt *********/

	public static void setUserId(String userid) {
		String filePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "neverkillmyplant"
				+ File.separator + "username.txt";
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			out.write(userid);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getUserId() {
		String filePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "neverkillmyplant"
				+ File.separator + "username.txt";
		File storageFile = new File(filePath);
		if (!storageFile.exists()) {
			try {
				BufferedWriter out = new BufferedWriter(
						new FileWriter(filePath));
				out.write("");
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String userName = null;
		try {
			BufferedReader ois = new BufferedReader(new FileReader(filePath));
			userName = ois.readLine();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
	}

	/********* ecriture/lecture sur le fichier serveur.txt *********/

	public static void setServeurURL(String serveurURL) {
		String filePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "neverkillmyplant"
				+ File.separator + "serveur.txt";
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			out.write(serveurURL);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getServeurURL() {
		String filePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "neverkillmyplant"
				+ File.separator + "serveur.txt";
		File storageFile = new File(filePath);
		if (!storageFile.exists()) {
			try {
				BufferedWriter out = new BufferedWriter(
						new FileWriter(filePath));
				out.write("https://www.google.fr/");
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String serveurURL = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			serveurURL = in.readLine();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serveurURL;
	}

	/********* ecriture/lecture sur le fichier serveur.txt *********/

	public static void setSanteInter(String santeInter) {
		String filePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "neverkillmyplant"
				+ File.separator + "sante.txt";
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			out.write(santeInter);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getSanteInter() {
		String filePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "neverkillmyplant"
				+ File.separator + "sante.txt";
		File storageFile = new File(filePath);
		if (!storageFile.exists()) {
			try {
				BufferedWriter out = new BufferedWriter(
						new FileWriter(filePath));
				out.write("bonne");
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String sante = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			sante = in.readLine();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sante;
	}
}
