package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.MusicRest;

public class SixteenthRest extends MusicRest {
	private static int width = QUARTER_NOTE_WIDTH - (QUARTER_NOTE_WIDTH / 10);

	@Override
	public void draw(Graphics2D g) {
		g.drawPolygon(Components.getQuarterHexagon(MARGIN_HORIZONTAL, MARGIN_VERTICAL, width, QUARTER_NOTE_HEIGHT));
	}

	@Override
	public double getMusicalLength() {
		return (double) 1 / 16;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width / 4, QUARTER_NOTE_HEIGHT);
	}
}
