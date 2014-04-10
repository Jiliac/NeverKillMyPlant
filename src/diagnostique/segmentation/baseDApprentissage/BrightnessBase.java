package diagnostique.segmentation.baseDApprentissage;

import java.util.ArrayList;
import diagnostique.segmentation.Pixel;

public class BrightnessBase extends ArrayList<Pixel> {
	public BrightnessBase(){
		//this(20);
		
		Pixel p = new Pixel(0f,0f,0.1f);
		p.setGroupe(0);
		this.add(p);
		
		p = new Pixel(0f,0f,0.3f);
		p.setGroupe(1);
		this.add(p);
		
		
		
		p = new Pixel(0f,0f,0.6f);
		p.setGroupe(2);
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