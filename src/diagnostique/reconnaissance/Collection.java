package diagnostique.reconnaissance;

import java.util.ArrayList;
import diagnostique.segmentation.Global;

public class Collection extends ArrayList<Point> {

	/*********** analyse d'une collection *************/
	public double getEcartType() {
		double sommeCarreRayon = 0;
		Point centre = this.getCentre();
		for (Point point : this)
			sommeCarreRayon += this.distance(point, centre)
					* this.distance(point, centre);
		int size = this.size();
		double carreRayon = sommeCarreRayon / size;

		return Math.sqrt(carreRayon - this.getRayon() * this.getRayon());
	}

	public Point getCentre() {
			int sommeX = 0, sommeY = 0;
			for (Point point : this) {
				sommeX += point.getPosX();
				sommeY += point.getPosY();
			}
			int size = this.size();
			return new Point((int) (sommeX / size), (int) (sommeY / size),
					this.getGrIn(), this.getGrOut());
	}

	public double getRayon() {
		double sommeRayon = 0;
		Point centre = this.getCentre();
		for (Point point : this)
			sommeRayon += this.distance(point, centre);
		int size = this.size();
		return sommeRayon / size;
	}

	public double distance(Point p1, Point p2) {
		double retour = Math.sqrt((p1.getPosX() - p2.getPosX())
				* (p1.getPosX() - p2.getPosX()) + (p1.getPosY() - p2.getPosY())
				* (p1.getPosY() - p2.getPosY()));
		return retour;
	}

	/************ traitement des collections **********/

	public boolean equal(Point p) {
		if (p.equal(this.get(0)))
			return true;
		else
			return false;
	}

	public boolean add(Point p) {
		if (super.isEmpty())
			return super.add(p);
		else {
			if (p.equal(super.get(0)))
				return super.add(p);
			else
				return false;
		}
	}

	public boolean isVoisin(Point moi, Point potentielVoisin) {
		if (this.distance(moi, potentielVoisin) < Global.isVoisin)
			return true;
		else
			return false;
	}

	/******** getter ***********/

	public int getGrIn() {
		return this.get(0).getGrIn();
	}

	public int getGrOut() {
		return this.get(0).getGrOut();
	}

	public String toString() {
		String str = super.toString();
		str += "\nje suis une collection de centre ("
				+ this.getCentre().getPosX() + "," + this.getCentre().getPosY()
				+ ")";
		Point p = this.get(0);
		str += "\ngrIn = " + p.getGrIn() + " et grOut = " + p.getGrOut();
		str += "\nde rayon " + this.getRayon() + " et d'ecartType "
				+ this.getEcartType();
		str += "\net j'ai " + this.size() + " elements";
		str += "\n";
		return str;
	}
}