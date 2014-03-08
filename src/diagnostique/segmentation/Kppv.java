package diagnostique.segmentation;

import java.util.ArrayList;
import diagnostique.segmentation.baseDApprentissage.*;

public class Kppv {
	private ArrayList<Pixel> baseDApprentissage = Global.baseDApprentissage;
	private int k = 1;

	public void kppv(Pixel p) {
		// on copie la base dans un tableau
		int n = baseDApprentissage.size();
		Pixel voisins[] = new Pixel[n];
		for (int i = 0; i < n; i++)
			voisins[i] = baseDApprentissage.get(i);

		// on ordonne le tableau
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				Pixel p1 = voisins[j], p2 = voisins[j + 1];
				if (p1.distance(p) > p2.distance(p)) {
					Pixel pixel = p1;
					voisins[j] = p2;
					voisins[j + 1] = pixel;
				}
			}
		}

		// on prend les k premiers elements du tableau

		Pixel kVoisins[] = new Pixel[k];
		for (int i = 0; i < k; i++)
			kVoisins[i] = voisins[i]; // IL FAUT k <= n

		// on decide le groupe du pixel
		float groupe = 0;
		for (Pixel pixel : kVoisins) {
			groupe += pixel.getnumGroupe();
		}
		groupe = groupe / k;
		if (groupe < ((int) groupe) + .5)
			p.setGroupe((int) groupe);
		else
			p.setGroupe((int) groupe + 1);
	}

	/******************* constructeurs ************/

	public Kppv() {
	}

	public Kppv(int k) {
		this.k = k;
	}

	public Kppv(ArrayList<Pixel> baseDApprentissage) {
		this.baseDApprentissage = baseDApprentissage;
	}

	public Kppv(ArrayList<Pixel> baseDApprentissage, int k) {
		this.baseDApprentissage = baseDApprentissage;
		this.k = k;
	}

	/********** la base d'apprentissage **************/

	public void setBase(ArrayList<Pixel> base) {
		this.baseDApprentissage = base;
	}

	public ArrayList<Pixel> getBase() {
		return this.baseDApprentissage;
	}

}