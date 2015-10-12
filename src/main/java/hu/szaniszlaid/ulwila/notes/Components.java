/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notes;

import java.awt.Polygon;

/**
 * Utility class to draw Polygons
 */
public class Components {
    public static Polygon getHexagon(int x, int y, int Width, int Height) {
        Polygon hexagon = new Polygon();

        int xPoints[] = { 0, Width / 2, Width, Width, Width / 2, 0 };
        int yPoints[] = { Height / 4, 0, Height / 4, Height / 4 * 3, Height, Height / 4 * 3 };
        //Add x gaps
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] += x;
        }
        //Add y gaps
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
        //Add x gaps
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] += x;
        }
        //Add y gaps
        for (int i = 0; i < yPoints.length; i++) {
            yPoints[i] += y;
        }

        for (int i = 0; i < xPoints.length; i++) {
            polygon.addPoint(xPoints[i], yPoints[i]);
        }
        return polygon;
    }
}
