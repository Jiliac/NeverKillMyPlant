package diagnostique.segmentation.distance;

import diagnostique.segmentation.Pixel;

public interface Distance {
	public double distance(Pixel p1, Pixel p2);
}
