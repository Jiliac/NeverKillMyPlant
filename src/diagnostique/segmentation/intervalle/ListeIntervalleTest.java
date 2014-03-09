package diagnostique.segmentation.intervalle;

import java.util.ArrayList;

public class ListeIntervalleTest extends ArrayList<Intervalle> {
	public ListeIntervalleTest() {
		Intervalle inter1 = new Intervalle(0, 32. / 360), inter2 = new Intervalle(
				32. / 360, 72. / 360), inter3 = new Intervalle(72. / 360, 1);
		inter1.setGroupe(1);
		inter2.setGroupe(2);
		inter3.setGroupe(3);

		this.add(inter1);
		this.add(inter2);
		this.add(inter3);
	}
}
