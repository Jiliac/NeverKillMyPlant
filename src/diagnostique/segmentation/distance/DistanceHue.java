package diagnostique.segmentation.distance;

import diagnostique.segmentation.Pixel;

public class DistanceHue implements Distance{
	public double distance(Pixel p1, Pixel p2){
		return Math.abs(p1.getHue()-p2.getHue());
	}
}
