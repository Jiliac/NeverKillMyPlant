package diagnostique.segmentation;

import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import diagnostique.reconnaissance.Collection;
import diagnostique.reconnaissance.Ensemble;
import diagnostique.reconnaissance.Point;
import diagnostique.segmentation.Global;
import diagnostique.segmentation.Kmoyenne;
import diagnostique.segmentation.Pixel;

public class Main {

	private static void mainAlgo(String str) {
		Bitmap img = BitmapFactory.decodeFile(str);


		Kmoyenne kmoyenne = new Kmoyenne(Global.baseDApprentissage, img);
		Pixel[][] pixels = kmoyenne.analyse();

		Ensemble ensemble = new Ensemble(img, Point.convertisseur(pixels));
		ArrayList<Collection> alc = ensemble.CompoCo();
	}
}