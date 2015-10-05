/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Color;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.NotePanel;

/**
 *
 * @author Franklin
 */
public class SixteenthRestPanel extends NotePanel {

    /**
     * Creates new form SixteenthRestPanel
     */
    public SixteenthRestPanel() {
        super(new NoteBuilder().setContainerWidth(25));
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0,  getNWidth()/4, getNHeight());
    }
}
