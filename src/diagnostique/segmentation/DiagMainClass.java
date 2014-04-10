package diagnostique.segmentation;

import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import diagnostique.reconnaissance.Ensemble;
import diagnostique.reconnaissance.Point;

public class DiagMainClass {
	public DiagMainClass(String nomFichierEntree) {
		// Creation d'une image BufferedImage

		Bitmap img = BitmapFactory.decodeFile(nomFichierEntree);
		int density = img.getDensity();
		img.setDensity(density/10);

		Kppv analyse = new Kppv();
		ArrayList<Pixel> baseDApprentissage = analyse.getBase();

		Point sortieATrier[][] = new Point[img.getWidth()][img.getHeight()];

		// segmentation
		ArrayList<Pixel> image = new ArrayList<Pixel>();
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				// analyse
				Colour c = new Colour(img.getPixel(x, y));
				Pixel pixel = new Pixel(c);
				analyse.kppv(pixel);

				// on affiche le resultat de la segmentation
				System.out.println(pixel.getGroupe());
				if (pixel.getGroupe() > 0) {
					Colour newColor = baseDApprentissage.get(
							pixel.getGroupe() - 1).getColor();
					int rgb = newColor.getRGB();
					img.setPixel(x, y, Colour.RGBtoARGB(rgb));
				}

				// preparation de l'analyse
				sortieATrier[x][y] = new Point(x, y, pixel);
			}
		}

		// analyse
		Ensemble ensembleAAnlyser = new Ensemble(img, sortieATrier);
	}

}