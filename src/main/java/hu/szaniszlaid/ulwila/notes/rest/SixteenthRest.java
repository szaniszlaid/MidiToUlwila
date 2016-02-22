package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class SixteenthRest extends MusicComponent {
	private int width = QUARTER_NOTE_WIDTH - (QUARTER_NOTE_HEIGHT / 10);

	@Override
	public void draw(Graphics2D g) {
		g.drawPolygon(Components.getQuarterHexagon(MARGIN_LEFT, MARGIN_TOP, width, QUARTER_NOTE_HEIGHT));
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
