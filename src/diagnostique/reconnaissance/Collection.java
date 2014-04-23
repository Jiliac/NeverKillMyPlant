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

	public void reSetPoint() { // on fait le tour et on prend les points les
								// plus éloignés
		if (this.size() != 0) {
			Point centre = this.getCentre();

			Point[] signature = new Point[90];
			for (int i = 0; i < 90; i++)
				signature[i] = centre;

			for (Point point : this) {
				double rho = this.distance(point, centre);

				double dx = point.getPosX() - centre.getPosX(), dy = point
						.getPosY() - centre.getPosY();
				double theta = 2 * Math.atan(dy / (dx + rho));
				theta = Math.toDegrees(theta);
				int angle = ((int) (theta - 0.5) + 1) % 360;
				angle = (angle + 360) % 360;
				angle = angle / 4;

				Point ptPrecedent = signature[angle];
				if (rho > this.distance(centre, ptPrecedent))
					signature[angle] = point;
			}

			this.clear();
			for (Point point : signature)
				if (point != centre)
					super.add(point);
		}
	}

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
		String str = "";
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