package hu.szaniszlaid.ulwila.notes.util;

public enum PaintStyle {
	COLORED, SKETCH;
	
	public PaintStyle inverse() {
		switch (this) {
		case COLORED:
			return SKETCH;
		case SKETCH:
			return COLORED;
		default:
			return null;
		}
	}
}
