package hu.szaniszlaid.ulwila.notes.rest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.Components;
import hu.szaniszlaid.ulwila.notes.MusicRest;

public class QuarterRest extends MusicRest {
	private static int width = QUARTER_NOTE_WIDTH - QUARTER_NOTE_WIDTH / 10;
	private static int height = QUARTER_NOTE_HEIGHT;

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawPolygon(Components.getHexagon(0, 0, width, height));
	}

	@Override
	public double getMusicalLength() {
		return (double) 1 / 4;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width, height);
	}
}
