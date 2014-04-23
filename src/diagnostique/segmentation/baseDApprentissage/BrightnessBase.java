package diagnostique.segmentation.baseDApprentissage;

import java.util.ArrayList;
import diagnostique.segmentation.Pixel;

public class BrightnessBase extends ArrayList<Pixel> {
	public BrightnessBase(ArrayList<Float> alf) {
		int size = alf.size();
		for (int i = 0; i < size; i++) {
			float luminance = alf.get(i);
			Pixel p = new Pixel(0f, 0f, luminance);
			p.setGroupe(i);
			this.add(p);
		}
	}

	public BrightnessBase() {
		this(0.15f, 0.7f);
	}

	public BrightnessBase(float b1, float b2) {
		Pixel p = new Pixel(0f, 0f, b1);
		p.setGroupe(0);
		this.add(p);

		p = new Pixel(0f, 0f, b2);
		p.setGroupe(1);
		this.add(p);
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