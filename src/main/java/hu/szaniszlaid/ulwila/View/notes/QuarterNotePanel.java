/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.View.notes;

import java.awt.Color;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.note.util.Octave;

/**
 *
 * @author Franklin
 */
public class QuarterNotePanel extends MusicNote {

    /**
     * Creates new form QuarterNote
     *
     * @param color
     * @param pitch
     */
    public QuarterNotePanel(Color color, Octave pitch) {
        super(new MusicComponent.NoteBuilder().setColor(color), pitch);
        setContainerWidth(getNHeight());
    }

//    @Override
//    public void draw(Graphics2D g) {
//        int centerX = getNWidth() / 2 - getNWidth() / 10;
//        int centerY = getNHeight() / 2 - getNHeight() / 10;
//        switch (getPitch()) {
//            case BASS:
//                if (getColor().equals(Color.BLACK)) {
//                    int whiteWidth = Math.round(getNWidth() / (float) 1.5);
//                    int whiteHeight = Math.round(getNHeight() / (float) 1.5);
//                    int whiteX = getNWidth() / 2 - whiteWidth / 2;
//                    int whiteY = getNHeight() / 2 - whiteHeight / 2;
//                    g.fillOval(0, 0, getNWidth(), getNHeight());
//                    g.setColor(Color.WHITE);
//                    g.fillOval(whiteX, whiteY, whiteWidth, whiteHeight);
//                } else {
//                    g.setColor(getColor());
//                    g.fillOval(0, 0, getNWidth(), getNHeight());
//                }
//                g.setColor(Color.BLACK);
//                g.drawOval(0, 0, getNWidth(), getNHeight());
//                g.fillOval(centerX, centerY, getNWidth() / 5, getNHeight() / 5);
//                break;
//            case MIDDLE:
//                g.setColor(getColor());
//                g.fillOval(0, 0, getNWidth(), getNHeight());
//                g.setColor(Color.BLACK);
//                g.drawOval(0, 0, getNWidth(), getNHeight());
//                break;
//            case HIGH:
//                g.setColor(getColor());
//                g.fillOval(0, 0, getNWidth(), getNHeight());
//                g.setColor(Color.BLACK);
//                g.drawOval(0, 0, getNWidth(), getNHeight());
//                g.setColor(Color.WHITE);
//                g.fillOval(centerX, centerY, getNWidth() / 5, getNHeight() / 5);
//                //TODO 3 vonalas C
//                break;
//        }
//
//    }

    @Override
    public void drawNote(Graphics2D g) {
        g.setColor(getColor());
        g.fillOval(0, 0, getNWidth(), getNHeight());
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, getNWidth(), getNHeight());
    }

    @Override
    public void drawOctave(Graphics2D g) {
        int centerX = getNWidth() / 2 - getNWidth() / 10;
        int centerY = getNHeight() / 2 - getNHeight() / 10;

        g.fillOval(centerX, centerY, getNWidth() / 5, getNHeight() / 5);
        g.setColor(Color.BLACK);
        g.drawOval(centerX, centerY, getNWidth() / 5, getNHeight() / 5);
    }
}
