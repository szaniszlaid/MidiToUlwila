package hu.szaniszlaid.ulwila.notes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;

public abstract class MusicNote extends MusicComponent {

    private Octave octave;
	private Tone tone;

    public final static int offsetX = QUARTER_NOTE_WIDTH / 3 * 2;

    public MusicNote(Octave octave, Tone tone) {
        super();
        this.octave = octave;
        this.tone = tone;
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
            return;//FIXME refactor, disgusting
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
        //set inverse border color if necessary
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
        switch (getTone()) {
        case C:
            if (getOctave() == Octave.FOURTH) {
                return Color.WHITE;
            }
            return Color.BLACK;
        case D:
            return new Color(145, 75, 41);
        case E:
            return new Color(0, 0, 255);
        case F:
            return new Color(0, 170, 0);
        case G:
            return Color.RED;
        case A:
            return new Color(255, 153, 0);
        case H:
            return Color.YELLOW;
        default:
            throw new UnsupportedOperationException("Use getLeftColor() or getRightColor() method if note is a semi note!");
        }
    }

    public Color getLeftColor() {
        switch (getTone()) {
        case CIS:
            return Color.BLACK;
        case DIS:
            return new Color(145, 75, 41);
        case FIS:
            return new Color(0, 170, 0);
        case GIS:
            return Color.RED;
        case AIS:
            return new Color(255, 153, 0);
        default:
            throw new UnsupportedOperationException("Use getColor() method if note is a whole note");
        }
    }

    public Color getRightColor() {
        switch (getTone()) {
        case CIS:
            return new Color(140, 80, 60);
        case DIS:
            return Color.BLUE;
        case FIS:
            return Color.RED;
        case GIS:
            return new Color(255, 153, 0);
        case AIS:
            return Color.YELLOW;
        default:
            throw new UnsupportedOperationException("Use getColor() method if note is a whole note");
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Octave: " + octave.name() + "   Tone: " + tone.name();
    }
}
