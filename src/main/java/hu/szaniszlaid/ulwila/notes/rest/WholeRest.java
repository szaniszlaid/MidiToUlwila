package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class WholeRest extends MusicComponent {

    @Override
    public Dimension draw(Graphics2D g) {
        int width = QUARTER_NOTE_WIDTH - QUARTER_NOTE_WIDTH / 10;
        int height = QUARTER_NOTE_HEIGHT;
        g.drawPolygon(Components.getHexagon(MARGIN_LEFT, MARGIN_TOP, width, height));
        g.drawPolygon(Components.getHexagon(width + MARGIN_LEFT, MARGIN_TOP, width, height));
        g.drawPolygon(Components.getHexagon(2 * width + MARGIN_LEFT, MARGIN_TOP, width, height));
        g.drawPolygon(Components.getHexagon(3 * width + MARGIN_LEFT, MARGIN_TOP, width, height));

        return new Dimension(width * 4, height);
    }

	@Override
	public double getMusicalLength() {
		return (double) 1;
	}
}
