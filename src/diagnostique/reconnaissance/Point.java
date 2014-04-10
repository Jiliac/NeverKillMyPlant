package diagnostique.reconnaissance;

import diagnostique.segmentation.Pixel;

public class Point {
	private int x, y;
	private int grIn, grOut;

	public Point(int x, int y, int grIn, int grOut) {
		this(x, y);
		this.grIn = grIn;
		this.grOut = grOut;
	}

	public Point(int x, int y, Pixel pixel) {
		this(x, y);
		this.grOut = pixel.getGroupe();
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equal(Point p) {
		if (this.grIn == p.getGrIn() && this.grOut == p.getGrOut())
			return true;
		else if (this.grIn == p.getGrOut() && this.grOut == p.getGrIn())
			return true;
		else
			return false;
	}

	/****** outil de determination de la connexitee *********/

	private int etiquette = -1;

	public void setEtiquette(Point voisin) {
		this.etiquette = voisin.getEtiquette();
	}

	public int getEtiquette() {
		return etiquette;
	}

	public void setEtiquette(int etiquette) {
		this.etiquette = etiquette;
	}

	/************ passage pixel -> point *********/
	public static Point[][] convertisseur(Pixel[][] pixels) {
		Point[][] retour = new Point[pixels.length][pixels[0].length];
		for (int x = 0; x < pixels.length; x++) {
			for (int y = 0; y < pixels[0].length; y++) {
				retour[x][y] = new Point(x, y, pixels[x][y]);
			}
		}
		return retour;
	}

	/************* getter **********/

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	public void setGrIn(int grIn) {
		this.grIn = grIn;
	}

	public int getGrIn() {
		return grIn;
	}

	public int getGrOut() {
		return grOut;
	}

}
