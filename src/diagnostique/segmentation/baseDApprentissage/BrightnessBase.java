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
		ArrayList<Float> vecteursInit = new ArrayList<Float>();
		vecteursInit.add(.2f);
		vecteursInit.add(.4f);
		vecteursInit.add(.7f);
		vecteursInit.add(.8f);
		vecteursInit.add(.9f);
		
		int size = vecteursInit.size();
		for (int i = 0; i < size; i++) {
			float luminance = vecteursInit.get(i);
			Pixel p = new Pixel(0f, 0f, luminance);
			p.setGroupe(i);
			this.add(p);
		}
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