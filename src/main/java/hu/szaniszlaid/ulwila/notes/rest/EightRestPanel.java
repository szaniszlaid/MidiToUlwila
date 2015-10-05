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
public class EightRestPanel extends NotePanel {

    public EightRestPanel() {
        super(new NoteBuilder().setContainerWidth(45));
    }


    @Override
    public void draw(Graphics2D g) {
        g.drawPolygon(Components.getHalfHexagon(0, 0, 90, 100)); 
    }
}
