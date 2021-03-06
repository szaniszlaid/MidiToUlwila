package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.MusicRest;

public class DottedEighthRest extends MusicRest {

	private static int width = QUARTER_NOTE_WIDTH - (QUARTER_NOTE_WIDTH / 10);

	@Override
	public void draw(Graphics2D g) {
		g.drawPolygon(Components.getHalfHexagon(0, 0, width, QUARTER_NOTE_HEIGHT));
		g.drawPolygon(Components.getQuarterHexagon(width / 2, 0, width, QUARTER_NOTE_HEIGHT));
	}

	@Override
	public double getMusicalLength() {
		return (double) 1.5 / 8;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width / 2 + width / 4, QUARTER_NOTE_HEIGHT);
	}
}
