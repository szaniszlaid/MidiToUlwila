package hu.szaniszlaid.ulwila.notes;

import java.awt.Polygon;

/**
 * Utility class to draw Polygons
 */
public class Components {
	
	private Components(){
		throw new AssertionError("Instantiating utility class.");
	}

	
	public static Polygon getHexagon(int x, int y, int Width, int Height) {
		Polygon hexagon = new Polygon();

		int xPoints[] = { 0, Width / 2, Width, Width, Width / 2, 0 };
		int yPoints[] = { Height / 4, 0, Height / 4, Height / 4 * 3, Height, Height / 4 * 3 };
		// Add x 
		for (int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x;
		}
		// Add y 
		for (int i = 0; i < yPoints.length; i++) {
			yPoints[i] += y;
		}

		for (int i = 0; i < xPoints.length; i++) {
			hexagon.addPoint(xPoints[i], yPoints[i]);
		}
		return hexagon;
	}

	public static Polygon getHalfHexagon(int x, int y, int Width, int Height) {
		Polygon polygon = new Polygon();

		int xPoints[] = { 0, Width / 2, Width / 2, 0 };
		int yPoints[] = { Height / 4, 0, Height, Height / 4 * 3 };
		// Add x gaps
		for (int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x;
		}
		// Add y gaps
		for (int i = 0; i < yPoints.length; i++) {
			yPoints[i] += y;
		}

		for (int i = 0; i < xPoints.length; i++) {
			polygon.addPoint(xPoints[i], yPoints[i]);
		}
		return polygon;
	}

	public static Polygon getQuarterHexagon(int x, int y, int Width, int Height) {
		Polygon polygon = new Polygon();

		int xPoints[] = { 0, Width / 4, Width / 4, 0 };
		int yPoints[] = { Height / 8, 0, Height, (int) Math.floor((float) Height / 8 * 7) };
		// Add x gaps
		for (int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x;
		}
		// Add y gaps
		for (int i = 0; i < yPoints.length; i++) {
			yPoints[i] += y;
		}

		for (int i = 0; i < xPoints.length; i++) {
			polygon.addPoint(xPoints[i], yPoints[i]);
		}
		return polygon;
	}
}
