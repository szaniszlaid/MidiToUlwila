package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class SixteenthRest extends MusicComponent {

    @Override
    public Dimension draw(Graphics2D g) {
        int width = QUARTER_NOTE_WIDTH / 4;
        int height = QUARTER_NOTE_HEIGHT;

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, height);

        return new Dimension(width, height);
    }
}
