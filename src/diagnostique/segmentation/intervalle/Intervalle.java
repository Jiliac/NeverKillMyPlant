package diagnostique.segmentation.intervalle;

public class Intervalle {
	public double a, b;

	public Intervalle(double a, double b) {
		if (a <= b) {
			this.a = a;
			this.b = b;
		} else {
			this.a = b;
			this.a = b;
		}
	}

	public boolean isInIntervalle(double c) {
		boolean retour = false;
		if (c >= a && c <= b)
			retour = true;
		return retour;
	}
	
	/***** les groupes  ******/
	
	private int groupe = -1;

	public int getGroupe() {
		return groupe;
	}
	public void setGroupe(int groupe) {
		this.groupe = groupe;
	}	
}
