package diagnostique.segmentation.intervalle;

import java.util.ArrayList;
import diagnostique.segmentation.Pixel;

public class MethodeSeuil {

	public void setGroupe(Pixel p) {
		int groupe = -1;
		for (Intervalle intervalle : baseIntervalle) {
			if (intervalle.isInIntervalle(p.getHue()))
				groupe = intervalle.getGroupe();
		}
		if (groupe != -1)
			p.setGroupe(groupe);
		else
			System.out
					.println("ATTENTION, ce pixel n'est dans aucun groupe, mauvaise base");
		// il faudra faire attention a bien faire les bases d'intervalles pour
		// eviter ce probleme
	}

	private ArrayList<Intervalle> baseIntervalle = new ArrayList<Intervalle>();

	public MethodeSeuil() {
		System.out
				.println("MAUVAIS CONSTRUCTEUR. En passant par ici ca va bugger");
	}

	public MethodeSeuil(ArrayList<Intervalle> baseIntervalle) {
		this.baseIntervalle = baseIntervalle;
	}
}
