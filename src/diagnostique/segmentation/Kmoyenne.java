package diagnostique.segmentation;

import java.util.ArrayList;

import diagnostique.segmentation.distance.*;

import android.graphics.Bitmap;

public class Kmoyenne {
	private ArrayList<Pixel> base;
	private int size;

	private Bitmap img;

	public Kmoyenne(ArrayList<Pixel> base, Bitmap img) {
		this.base = base;
		this.img = img;
		this.size = base.size();
	}

	public Pixel[][] analyse() {
		int width = img.getWidth(), height = img.getHeight();
		Pixel[][] pixels = new Pixel[width][height];

		// calcule les groupes de chaque Pixel
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				pixels[x][y] = new Pixel(new Colour(img.getPixel(x, y)));

				double distances[] = new double[size];

				int i = 0;
				for (Pixel centroide : base) {
					distances[i] = centroide.distance(pixels[x][y]);
					i++;
				}

				Double minDistance = Double.MAX_VALUE;
				i = 0;
				for (double distance : distances) {
					if (distance < minDistance) {
						minDistance = distance;
						pixels[x][y].setGroupe(i);
					}
				}
			}
		}

		// calcule les nouveau centroide
		Pixel centroides[] = this.setCentroide(pixels);

		// on verifie la condition d'arret
		boolean tests[] = new boolean[size];
		int i = 0;
		for (Pixel centroide : centroides) {
			if (centroide.distance(base.get(i)) < 0.1)
				tests[i] = true;
			else
				tests[i] = false;
			i++;
		}

		// si on ne s'arrete pas, on reset les centroide et on recommence
		if (allTrue(tests)) {
			return pixels;
		} else {
			base = new ArrayList<Pixel>();
			for (Pixel centroide : centroides) {
				base.add(centroide);
			}
			return analyse();
		}

	}

	private Pixel[] setCentroide(Pixel[][] pixels) {
		if (Global.distance instanceof DistanceBrightness)
			return this.setCentroBrightness(pixels);
		else if (Global.distance instanceof DistanceHue)
			return this.setCentroHue(pixels);
		else if (Global.distance instanceof DistanceCarre)
			return this.setCentroRGB(pixels);
		else
			return null;
	}

	private Pixel[] setCentroBrightness(Pixel[][] pixels) {
		// initialise
		Pixel[] centroides = new Pixel[size];
		int[] compteur = new int[size];
		for (int i = 0; i < size; i++) {
			centroides[i] = new Pixel(0, 0, 0);
			compteur[i] = 0;
		}

		// calcule
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				int groupe = pixels[x][y].getGroupe();
				float brightness = pixels[x][y].getBrightness();
				brightness = brightness * compteur[groupe]
						/ (compteur[groupe] + 1);
				pixels[x][y].setBrightness(brightness);
				compteur[groupe]++;
			}
		}

		return centroides;
	}

	private Pixel[] setCentroHue(Pixel[][] pixels) {
		return null;
	}

	private Pixel[] setCentroRGB(Pixel[][] pixels) {
		return null;
	}

	private boolean allTrue(boolean[] tests) {
		boolean retour = true;
		for (boolean test : tests) {
			if (!test)
				retour = false;
		}
		return retour;
	}
}
