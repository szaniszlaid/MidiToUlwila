/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.NotePanel;

/**
 *
 * @author Franklin
 */
public class WholeRestPanel extends NotePanel {

    public WholeRestPanel() {
        super(new NoteBuilder()
                .setnWidth(90)
                .setContainerWidth(360)
                .setContainerHeight(100));
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawPolygon(Components.getHexagon(0, 0, getNWidth(), getNHeight()));
        g.drawPolygon(Components.getHexagon(getNWidth(), 0, getNWidth(), getNHeight()));
        g.drawPolygon(Components.getHexagon(2 * getNWidth(), 0, getNWidth(), getNHeight()));
        g.drawPolygon(Components.getHexagon(3 * getNWidth(), 0, getNWidth(), getNHeight()));
    }
}
