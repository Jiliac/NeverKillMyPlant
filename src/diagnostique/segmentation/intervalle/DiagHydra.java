package diagnostique.segmentation.intervalle;

import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import diagnostique.segmentation.Colour;
import diagnostique.segmentation.Global;
import diagnostique.segmentation.Pixel;
import diagnostique.reconnaissance.Ensemble;
import diagnostique.reconnaissance.Point;

public class DiagHydra {
	private Class diag;

	public DiagHydra(String nomFichierEntree) {
		// Creation d'une image BufferedImage

		Bitmap img = BitmapFactory.decodeFile(nomFichierEntree);

		MethodeSeuil analyse = new MethodeSeuil(Global.baseIntervalle);
		Point sortieATrier[][] = new Point[img.getWidth()][img.getHeight()];

		// segmentation
		ArrayList<Pixel> image = new ArrayList<Pixel>();
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				// analyse
				Colour c = new Colour(img.getPixel(x, y));
				Pixel pixel = new Pixel(c);
				analyse.setGroupe(pixel);

				// preparation de l'analyse
				sortieATrier[x][y] = new Point(x, y, pixel);
			}
		}

		// analyse
		Ensemble ensembleAAnlyser = new Ensemble(img, sortieATrier);
		diag = ensembleAAnlyser.diagnostique();
	}

	public Class diagnostique() {
		return diag;
	}
}