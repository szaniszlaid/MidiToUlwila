package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.MusicRest;

public class WholeRest extends MusicRest {

	private static int width = QUARTER_NOTE_WIDTH - QUARTER_NOTE_WIDTH / 10;
	private static int height = QUARTER_NOTE_HEIGHT;

	@Override
	public void draw(Graphics2D g) {
		g.drawPolygon(Components.getHexagon(MARGIN_LEFT, MARGIN_TOP, width, height));
		g.drawPolygon(Components.getHexagon(width + MARGIN_LEFT, MARGIN_TOP, width, height));
		g.drawPolygon(Components.getHexagon(2 * width + MARGIN_LEFT, MARGIN_TOP, width, height));
		g.drawPolygon(Components.getHexagon(3 * width + MARGIN_LEFT, MARGIN_TOP, width, height));
	}

	@Override
	public double getMusicalLength() {
		return (double) 1;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width * 4, height);
	}
}
