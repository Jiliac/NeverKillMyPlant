package diagnostique.xiao;

import java.util.ArrayList;

import com.example.neverkillmyplant.test.*;

import diagnostique.segmentation.Colour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Xiao {
	private Bitmap img;
	private Class diag = DefaultPage.class;

	public Xiao(String path) {
		img = BitmapFactory.decodeFile(path);
	}

	public Xiao(Bitmap img) {
		this.img = img;
	}

	private void analyse() {
		int i = 0;
		int TR = 0;
		int MF = 0;
		int j = 0;
		int p = 0;

		ArrayList<Float> list1 = new ArrayList<Float>();
		ArrayList<Float> listJ = new ArrayList<Float>();

		int W = img.getWidth();
		int H = img.getHeight();

		for (int x = (9 * W / 20); x < (11 * W / 20); x++) {
			for (int y = (H / 20); y < (9 * H / 10); y++) {// parcourir l'image
				Colour c = new Colour(img.getPixel(x, y));// prendre des valeurs
															// RGB
															// de chaque pixel
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();

				float[] hsb = Colour.RGBtoHSB(r, g, b, null); // convertir RGB
																// en
																// HSB
				float h = hsb[0] * 360; // prendre la valeur teinte
				list1.add(h);

				// int rgb = new Color(0, 0, 0).getRGB();
				// img.setRGB(x, y, rgb); //remplir cette zone par une couleur
				// homogèn

				if ((list1.get(i) >= 32) && (list1.get(i) <= 70)) { // partie
																	// jaune
					listJ.add(list1.get(i));

				}

				/*
				 * if (i>1 && (list1.get(i)>=32) && (list1.get(i)<=70) &&
				 * (list1.get(i-1)>85)){ TR = 1; }
				 */

				i = i + 1;

			}

		}

		int J = listJ.size();
		int L = list1.size();

		// manque de fer
		if (J >= (L / 2)) {
			diag = DiagFer.class;
			MF = 1;
		}

		// pour savoir s'il y a du marron dans la partie jaune
		if (MF == 0) {
			for (p = 0; p < J; p++) {

				for (j = 0; j < J; j++) {

					if (listJ.get(p) - listJ.get(j) > 17)
						TR = 1;
					// System.out.println((listJ.get(p) - listJ.get(j)));
					break;

				}
			}
		}

		// problème d'arrossage
		if (J <= (L / 2) && TR == 0) {
			diag = DiagHyd.class;
		}

		// temperature trop haute
		if (TR == 1) {
			diag = DiagSec.class;
		}

	}

	public Class diagnostique() {
		this.analyse();
		return this.diag;
	}
}
