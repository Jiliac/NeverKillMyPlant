package diagnostique.segmentation;

import java.util.ArrayList;
import diagnostique.segmentation.baseDApprentissage.*;
import diagnostique.segmentation.distance.*;
import diagnostique.segmentation.intervalle.Intervalle;
import diagnostique.segmentation.intervalle.ListeIntervalleTest;

public class Global {
	public final static int isVoisin = 2;
	public final static boolean supprimer = false;
	public final static ArrayList<Pixel> baseDApprentissage = new BrightnessBase();
	public final static Distance distance = new DistanceHue();
	public final static boolean afficherCercle = false;

	public final static boolean compterZones = true;
	// cette variable pourrait etre egale à l'inverse de supprimer, non?

	public final static ArrayList<Intervalle> baseIntervalle = new ListeIntervalleTest();
	public final static Intervalle intervalleACompter = baseIntervalle.get(1);
}