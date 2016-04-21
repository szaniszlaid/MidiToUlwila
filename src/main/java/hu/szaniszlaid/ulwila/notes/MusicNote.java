package hu.szaniszlaid.ulwila.notes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;

import static hu.szaniszlaid.ulwila.notes.util.Tone.*;

public abstract class MusicNote extends MusicComponent {

	private Octave octave;
	private Tone tone;

	public int getMidiNumber() {
		return Tone.BASEKEY + octave.getMidiOffset() + tone.getMidiOffset();
	}

	protected final static int offsetX = QUARTER_NOTE_WIDTH / 4 * 3;

	public MusicNote(Octave octave, Tone tone, PaintStyle paintStyle) {
		super(paintStyle);
		this.octave = octave;
		this.tone = tone;
	}

	/**
	 * @Deprecated use {@link #MusicNote(Octave, Tone, PaintStyle)} instead.
	 */
	@Deprecated
	public MusicNote(Octave octave, Tone tone) {
		this(octave, tone, PaintStyle.COLORED);
	}

	public MusicNote setOctave(Octave octave) {
		this.octave = octave;
		return this;
	}

	public Octave getOctave() {
		return octave;
	}

	public int getNthOffset(int n) {
		return n * (QUARTER_NOTE_WIDTH - (QUARTER_NOTE_WIDTH - offsetX));
	}

	@Override
	public void draw(Graphics2D g) {
		drawNote(g);
		drawOctave(g);
	}

	public abstract void drawNote(Graphics2D g);

	protected void drawOctave(Graphics2D g) {
		Color octaveColor = Color.BLACK;
		switch (octave) {
		case FIRST:
			octaveColor = Color.BLACK;
			break;
		case SECOND:
			return;// FIXME refactor, disgusting
		case THIRD:
			octaveColor = Color.WHITE;
			break;
		case FOURTH:
			octaveColor = Color.BLACK;
			break;
		}

		List<Shape> octaveShapes = getOctaveShapes();
		for (Shape shape : octaveShapes) {
			g.setColor(octaveColor);
			g.fill(shape);

			g.setColor(getOctaveBorderColor());
			g.draw(shape);
		}
	}

	private Color getOctaveBorderColor() {
		// set inverse border color if necessary
		if (tone.isSemiTone()) {
			if (getRightColor().equals(Color.BLACK)) {
				return Color.WHITE;
			} else {
				return Color.BLACK;
			}
		} else {
			if (getColor().equals(Color.BLACK)) {
				return Color.WHITE;
			} else {
				return Color.BLACK;
			}
		}
	}

	public abstract List<Shape> getOctaveShapes();

	public Tone getTone() {
		return tone;
	}

	public void setTone(Tone tone) {
		this.tone = tone;
	}

	public Color getColor() {
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		if (getPaintStyle().equals(PaintStyle.COLORED)) {
			if (getTone().isSemiTone()) {
				throw new UnsupportedOperationException("Use getLeftColor() or getRightColor() method if note is a semi note!");
			} else {
				//special
				if (getOctave() == Octave.FOURTH && getTone().equals(Tone.C)) {
					return Color.WHITE;
				}
				return ulwilaColor.getColorByTone(getTone());
			}
			
		} else {
			return Color.WHITE;
		}
	}

	public Color getLeftColor() {
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		if (getPaintStyle().equals(PaintStyle.COLORED)) {
			switch (getTone()) {
			case CIS:
				return ulwilaColor.getColorByTone(C);
			case DIS:
				return ulwilaColor.getColorByTone(D);
			case FIS:
				return ulwilaColor.getColorByTone(F);
			case GIS:
				return ulwilaColor.getColorByTone(G);
			case AIS:
				return ulwilaColor.getColorByTone(A);
			default:
				throw new UnsupportedOperationException("Use getColor() method if note is a whole note");
			}
		} else {
			return Color.WHITE;
		}
	}

	public Color getRightColor() {
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		if (getPaintStyle().equals(PaintStyle.COLORED)) {
			switch (getTone()) {
			case CIS:
				return ulwilaColor.getColorByTone(D);
			case DIS:
				return ulwilaColor.getColorByTone(E);
			case FIS:
				return ulwilaColor.getColorByTone(G);
			case GIS:
				return ulwilaColor.getColorByTone(A);
			case AIS:
				return ulwilaColor.getColorByTone(H);
			default:
				throw new UnsupportedOperationException("Use getColor() method if note is a whole note");
			}
		} else {
			return Color.WHITE;
		}
	}

	@Override
	public String toString() {
		return super.toString() + " | Octave: " + octave.name() + "   Tone: " + tone.name();
	}
}
