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
public class HalfRestPanel extends NotePanel {

    
    /**
     * Creates new form HalfRestPanel
     */
    public HalfRestPanel() {
        super(new NoteBuilder()
                .setContainerWidth(180)
                .setnWidth(90));
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawPolygon(Components.getHexagon(0, 0, getNWidth(), getNHeight()));
        g.drawPolygon(Components.getHexagon(getNWidth(), 0, getNWidth(), getNHeight()));
    }
}
