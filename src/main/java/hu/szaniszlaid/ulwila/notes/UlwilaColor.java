package hu.szaniszlaid.ulwila.notes;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import hu.szaniszlaid.ulwila.notes.util.Tone;

public class UlwilaColor {

	private static UlwilaColor instance;
	
	private Map<Tone, Color> colors;

	private UlwilaColor() {
		colors = new HashMap<>();
		//set ulwila colors to default
		colors.put(Tone.C, Color.BLACK);
		colors.put(Tone.D, new Color(145, 75, 41));
		colors.put(Tone.E, new Color(0, 0, 255));
		colors.put(Tone.F, new Color(0, 170, 0));
		colors.put(Tone.G, Color.RED);
		colors.put(Tone.A, new Color(255, 153, 0));
		colors.put(Tone.H, Color.YELLOW);
	}

	public static UlwilaColor getInstance() {
		if (instance == null) {
			instance = new UlwilaColor();
		}
		return instance;
	}
	
	public Color getColorByTone(Tone tone) {
		return colors.get(tone);
	}
	
	public void setColorByTone(Tone tone, Color color) {
		colors.put(tone, color);
	}

}
