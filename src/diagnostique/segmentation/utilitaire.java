package diagnostique.segmentation;

import android.graphics.Bitmap;

public class utilitaire {

	static public void Normalize(Bitmap img) {
		float minH = 1f, maxH = 0f, minS = 1f, maxS = 0f, minB = 1f, maxB = 0f;

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Colour c = new Colour(img.getPixel(i, j));
				int r = c.getRed();
				int g = c.getGreen();
				int bb = c.getBlue();

				float[] hsb = Colour.RGBtoHSB(r, g, bb, null);
				float h = hsb[0], s = hsb[1], b = hsb[2];

				if (h < minH)
					minH = h;
				else if (h > maxH)
					maxH = h;

				if (s < minS)
					minS = s;
				else if (s > maxS)
					maxS = s;

				if (b < minB)
					minB = b;
				else if (b > maxB)
					maxB = b;
			}
		}

		for (int k = 0; k < img.getWidth(); k++) {
			for (int l = 0; l < img.getHeight(); l++) {
				Colour c = new Colour(img.getPixel(k, l));
				int r = c.getRed();
				int g = c.getGreen();
				int bb = c.getBlue();

				float[] hsb = Colour.RGBtoHSB(r, g, bb, null);
				float h = hsb[0], s = hsb[1], b = hsb[2];

				h = (h - minH) / (maxH - minH);
				s = (s - minS) / (maxS - minS);
				b = (b - minS) / (maxS - minS);
				
				int rgb = Colour.HSBtoRGB(h, s, b);
				img.setPixel(k, l, rgb);
			}
		}
	}
}
