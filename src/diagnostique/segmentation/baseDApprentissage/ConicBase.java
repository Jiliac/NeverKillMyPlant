package diagnostique.segmentation.baseDApprentissage;

import java.util.ArrayList;
import diagnostique.segmentation.Pixel;

public class ConicBase extends ArrayList<Pixel>{

	public ConicBase(){
		Pixel pp[] = new Pixel[165];

		pp[0] = new Pixel(0, 0, 0);
		pp[0].setGroupe(1);
		this.add(pp[0]);

		int k = 1;
		for (float saturation = (float) 0.25; saturation <= 1; saturation += 0.25) {
			for (float hue = 0; hue <= 1; hue += 0.1) {
				for (float brightness = (float) 0.25; brightness <= 1; brightness += 0.25) {
					pp[k] = new Pixel(hue, saturation, brightness);
					pp[k].setGroupe(k);
					this.add(pp[k]);
					k++;
				}
			}
		}
		for (float brightness = (float) 0.25; brightness <= 1; brightness += 0.25) {
			pp[k] = new Pixel((float) .0, (float) .0, brightness);
			pp[k].setGroupe(k);
			this.add(pp[k]);
			k++;
		}
	}
	
}
