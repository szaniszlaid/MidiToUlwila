package hu.szaniszlaid.ulwila.notes;

import java.awt.Color;

public class UlwilaColor {

	private static UlwilaColor instance;

	private Color c;
	private Color d;
	private Color e;
	private Color f;
	private Color g;
	private Color a;
	private Color h;

	private UlwilaColor() {
		//set ulwila colors to default
		setC(Color.BLACK);
		setD(new Color(145, 75, 41));
		setE(new Color(0, 0, 255));
		setF(new Color(0, 170, 0));
		setG(Color.RED);
		setA(new Color(255, 153, 0));
		setH(Color.YELLOW);
	}

	public static UlwilaColor getInstance() {
		if (instance == null) {
			instance = new UlwilaColor();
		}
		return instance;
	}

	public Color getD() {
		return d;
	}

	public void setD(Color d) {
		this.d = d;
	}

	public Color getE() {
		return e;
	}

	public void setE(Color e) {
		this.e = e;
	}

	public Color getF() {
		return f;
	}

	public void setF(Color f) {
		this.f = f;
	}

	public Color getA() {
		return a;
	}

	public void setA(Color a) {
		this.a = a;
	}

	public Color getH() {
		return h;
	}

	public void setH(Color h) {
		this.h = h;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public Color getG() {
		return g;
	}

	public void setG(Color g) {
		this.g = g;
	}
}
