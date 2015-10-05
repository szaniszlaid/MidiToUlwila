/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notes;

import java.awt.Graphics2D;

/**
 *
 * @author Franklin
 */
public class QuarterRestPanel extends NotePanel {

    @Override
    public void draw(Graphics2D g) {
        g.drawPolygon(Components.getHexagon(0, 0, 100, 100));
    }
}
