package diagnostique.segmentation;

import java.util.ArrayList;

import diagnostique.segmentation.baseDApprentissage.BrightnessBase;
import diagnostique.segmentation.distance.Distance;
import diagnostique.segmentation.distance.DistanceBrightness;

public class Global {
	public final static int isVoisin = 2;
	public final static boolean supprimer = true;
	public final static boolean afficherCercle = false;
	public final static boolean compterZones = false;

	public static ArrayList<Pixel> baseDApprentissage = new BrightnessBase();
	public final static Distance distance = new DistanceBrightness();

	public static double conditionDArret = 0.005;
	public static double ECARTYPE = 1.7D;
	public static int MINSIZE = 30;
	public static boolean afficherCompoCo = false;
}