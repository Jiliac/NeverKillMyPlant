package diagnostique.segmentation;

import java.util.ArrayList;

import diagnostique.segmentation.baseDApprentissage.*;
import diagnostique.segmentation.distance.*;
import diagnostique.segmentation.intervalle.Intervalle;
import diagnostique.segmentation.intervalle.ListeIntervalleTest;

public class Global {
	public final static boolean supprimer = true;
	public final static boolean afficherCercle = false;
	public final static boolean compterZones = false;

	public final static int isVoisin = 2;
	public final static double conditionDArret = 0.05;

	public final static ArrayList<Pixel> baseDApprentissage = new BrightnessBase();
	public final static Distance distance = new DistanceBrightness();

	public static boolean supression(int size, double rayon, int width,
			int height, double ecartType) {
		if (size < 50)
			return true;
		return (ecartType > 2 || rayon > width / 5 || rayon > height / 5);
	}

	// inutile maintenant
	public final static ArrayList<Intervalle> baseIntervalle = new ListeIntervalleTest();
	public final static Intervalle intervalleACompter = baseIntervalle.get(1);
}