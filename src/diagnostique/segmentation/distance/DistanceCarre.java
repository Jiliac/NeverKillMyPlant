package diagnostique.segmentation.distance;

import diagnostique.segmentation.Pixel;

public class DistanceCarre implements Distance {
	public double distance(Pixel p1, Pixel p2) {
		return Math.sqrt((p1.getColor().getRed() - p2.getColor().getRed())
				* (p1.getColor().getRed() - p2.getColor().getRed())
				* (p1.getColor().getRed() - p2.getColor().getRed())
				* (p1.getColor().getRed() - p2.getColor().getRed())
				+ (p1.getColor().getGreen() - p2.getColor().getGreen())
				* (p1.getColor().getGreen() - p2.getColor().getGreen())
				+ (p1.getColor().getBlue() - p2.getColor().getBlue())
				* (p1.getColor().getBlue() - p2.getColor().getBlue()));
	}
}
