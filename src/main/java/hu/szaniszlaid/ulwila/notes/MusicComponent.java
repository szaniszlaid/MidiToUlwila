package hu.szaniszlaid.ulwila.notes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import hu.szaniszlaid.ulwila.notes.util.PaintStyle;

public abstract class MusicComponent extends JComponent  {

	public static final int QUARTER_NOTE_WIDTH = 70;
	public static final int QUARTER_NOTE_HEIGHT = 70;

	private static final int MARGIN = 10;

	public static final int MARGIN_HORIZONTAL = MARGIN / 2;

	public static final int MARGIN_VERTICAL = MARGIN / 2;



	private PaintStyle paintStyle;

	public MusicComponent(PaintStyle paintStyle) {
		this.paintStyle = paintStyle;
		
		setPreferredSize(new Dimension(getWidth(), getHeight()));
	}

	public int getMarginTop() {
		return MARGIN_VERTICAL / 2;
	}

	public int getMarginLeft() {
		return MARGIN_HORIZONTAL / 2;
	}

	public int getMarginBottom() {
		return MARGIN_VERTICAL / 2;
	}

	public int getMarginRight() {
		return MARGIN_HORIZONTAL / 2;
	}

	@Override
	public void paint(Graphics g) {
		paintComponent((Graphics2D) g);
		super.paint(g);
	}

	private void paintComponent(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		draw(g);
	}


	public int getHorizontalMargin() {
		return getMarginLeft() + getMarginRight();
	}

	public int getVerticalMargin() {
		return getMarginBottom() + getMarginTop();
	}

	public abstract void draw(Graphics2D g);

	public abstract double getMusicalLength();

	@Override
	public abstract Dimension getSize();

	@Override
	public int getWidth() {
		return getSize().width + 1;
	}

	@Override
	public int getHeight() {
		return getSize().height + 1;
	}

	public PaintStyle getPaintStyle() {
		return paintStyle;
	}

	public void setPaintStyle(PaintStyle paintStyle) {
		this.paintStyle = paintStyle;
	}
	



}
