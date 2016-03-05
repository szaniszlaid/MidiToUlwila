package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class QuarterRest extends MusicComponent {
	private static int width = QUARTER_NOTE_WIDTH - QUARTER_NOTE_WIDTH / 10;
	private static int height = QUARTER_NOTE_HEIGHT;

    @Override
    public void draw(Graphics2D g) {
        g.drawPolygon(Components.getHexagon(MARGIN_LEFT, MARGIN_TOP, width, height));
    }

	@Override
	public double getMusicalLength() {
		return (double) 1/4;
	}

	@Override
	public Dimension getSize() {
        return new Dimension(width, height);
	}
}
