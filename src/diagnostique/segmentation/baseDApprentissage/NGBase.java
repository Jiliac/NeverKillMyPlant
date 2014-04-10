package diagnostique.segmentation.baseDApprentissage;

import java.util.ArrayList;

import diagnostique.segmentation.Pixel;

public class NGBase extends ArrayList<Pixel> {
	public NGBase() {
		Pixel p = new Pixel(60, 92, 68);
		p.setGroupe(0);
		this.add(p);

		p = new Pixel(86, 146, 56);
		p.setGroupe(1);
		this.add(p);
	}
}
