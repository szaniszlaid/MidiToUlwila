package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class SixteenthRest extends MusicComponent {

    @Override
    public Dimension draw(Graphics2D g) {
        int width = QUARTER_NOTE_WIDTH - (QUARTER_NOTE_HEIGHT / 10);
        g.drawPolygon(Components.getQuarterHexagon(0, 0, width, QUARTER_NOTE_HEIGHT));

        return new Dimension(width / 4, QUARTER_NOTE_HEIGHT);
    }
}
