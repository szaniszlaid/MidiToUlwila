package hu.szaniszlaid.ulwila.notes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;

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
			switch (getTone()) {
			case C:
				if (getOctave() == Octave.FOURTH) {
					return Color.WHITE;
				}
				return ulwilaColor.getC();
			case D:
				return ulwilaColor.getD();
			case E:
				return ulwilaColor.getE();
			case F:
				return ulwilaColor.getF();
			case G:
				return ulwilaColor.getG();
			case A:
				return ulwilaColor.getA();
			case H:
				return ulwilaColor.getH();
			default:
				throw new UnsupportedOperationException("Use getLeftColor() or getRightColor() method if note is a semi note!");
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
				return ulwilaColor.getC();
			case DIS:
				return ulwilaColor.getD();
			case FIS:
				return ulwilaColor.getF();
			case GIS:
				return ulwilaColor.getG();
			case AIS:
				return ulwilaColor.getA();
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
				return ulwilaColor.getD();
			case DIS:
				return ulwilaColor.getE();
			case FIS:
				return ulwilaColor.getG();
			case GIS:
				return ulwilaColor.getA();
			case AIS:
				return ulwilaColor.getH();
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
