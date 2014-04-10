package diagnostique.segmentation.distance;

import diagnostique.segmentation.Pixel;

public class DistanceNG implements Distance {

	@Override
	public double distance(Pixel p1, Pixel p2) {
		double n1 = p1.getBrightness(), g1 = ((double) p1.getColor().getGreen()) / 256;
		double n2 = p2.getBrightness(), g2 = ((double) p2.getColor().getGreen()) / 256;

		return Math.sqrt((n1 - n2) *(n1 - n2) + (g1 - g2) *(g1 - g2));
	}
}
