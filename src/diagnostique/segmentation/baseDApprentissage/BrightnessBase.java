package diagnostique.segmentation.baseDApprentissage;

import java.util.ArrayList;
import diagnostique.segmentation.Pixel;

public class BrightnessBase extends ArrayList<Pixel> {
	public BrightnessBase(){
		this(4);
	}
	
	public BrightnessBase(int parametre) {
		Pixel pp[] = new Pixel[parametre];
		int k = 0;
		for (int i = 0; i < parametre; i++) {
			pp[k] = new Pixel((float) .0, (float) .0,
					(float) ((i + 0.5) / parametre));
			pp[k].setGroupe(k);
			this.add(pp[k]);
			k++;
		}
	}
}