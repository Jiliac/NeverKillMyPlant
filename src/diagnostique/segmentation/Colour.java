package diagnostique.segmentation;

import android.graphics.Color;

public class Colour {
	int r, g, b;
	int rgb;

	/************ constructeur ************/
	
	public Colour(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;

		this.rgb = Color.rgb(r, g, b);
	}

	public Colour(int rgb) {
		this.r = Color.red(rgb);
		this.g = Color.green(rgb);
		this.b = Color.blue(rgb);

		this.rgb = Color.rgb(r, g, b);
	}
	
	/********** getter **********/
	
	public int getRed() {
		return r;
	}

	public int getGreen() {
		return g;
	}

	public int getBlue() {
		return b;
	}
	
	/************ methodes tampon entre Color et Colour **************/

	public static float[] RGBtoHSB(int red, int green, int blue, Object arg0) {
		float[] hsb = new float[3];
		Color.colorToHSV(Color.rgb(red, green, blue), hsb);
		hsb[0] = hsb[0] / 360;
		return hsb;
	}

	public static int HSBtoRGB(float h, float s, float b) {
		float hsv[] = { h * 360, s, b };
		int RGB = Color.HSVToColor(hsv);
		return Color.argb(0, Color.red(RGB), Color.green(RGB), Color.blue(RGB));
	}

	public int getRGB() {
		return Color.argb(0, r, g, b);
	}

	public static int RGBtoARGB(int RGB){
		return Color.argb(255, Color.red(RGB), Color.green(RGB), Color.blue(RGB));

	}
}
