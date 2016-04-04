package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.MusicRest;

public class DottedHalfRest  extends MusicRest {
	private static int width = QUARTER_NOTE_WIDTH - QUARTER_NOTE_WIDTH / 10;
	private static int height = QUARTER_NOTE_HEIGHT;

	@Override
	public void draw(Graphics2D g) {
		g.drawPolygon(Components.getHexagon(MARGIN_HORIZONTAL, MARGIN_VERTICAL, width, height));
		g.drawPolygon(Components.getHexagon(width + MARGIN_HORIZONTAL, MARGIN_VERTICAL, width, height));
		g.drawPolygon(Components.getHexagon(width*2 + MARGIN_HORIZONTAL, MARGIN_VERTICAL, width, height));
	}

	@Override
	public double getMusicalLength() {
		return (double) 1.5 / 2;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width * 3, height);
	}
}
