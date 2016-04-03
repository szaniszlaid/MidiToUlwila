package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.midi.MidiNote;
import hu.szaniszlaid.ulwila.midi.TimeSignature;
import hu.szaniszlaid.ulwila.notes.MusicComponent;
import hu.szaniszlaid.ulwila.notes.NoteDuration;
import hu.szaniszlaid.ulwila.notes.rest.DottedEighthRest;
import hu.szaniszlaid.ulwila.notes.rest.DottedHalfRest;
import hu.szaniszlaid.ulwila.notes.rest.DottedQuarterRest;
import hu.szaniszlaid.ulwila.notes.rest.EighthRest;
import hu.szaniszlaid.ulwila.notes.rest.HalfRest;
import hu.szaniszlaid.ulwila.notes.rest.QuarterRest;
import hu.szaniszlaid.ulwila.notes.rest.SixteenthRest;
import hu.szaniszlaid.ulwila.notes.rest.WholeRest;
import hu.szaniszlaid.ulwila.notes.semi.DottedEighthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.DottedQuarterSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.DottedSemiHalfNote;
import hu.szaniszlaid.ulwila.notes.semi.EighthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.HalfSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.QuarterSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.SixteenthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.TripletSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.WholeSemiNote;
import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.DottedEighthNote;
import hu.szaniszlaid.ulwila.notes.whole.DottedHalfNote;
import hu.szaniszlaid.ulwila.notes.whole.DottedQuarterNote;
import hu.szaniszlaid.ulwila.notes.whole.EighthNote;
import hu.szaniszlaid.ulwila.notes.whole.HalfNote;
import hu.szaniszlaid.ulwila.notes.whole.QuarterNote;
import hu.szaniszlaid.ulwila.notes.whole.SixteenthNote;
import hu.szaniszlaid.ulwila.notes.whole.TripletNote;
import hu.szaniszlaid.ulwila.notes.whole.WholeNote;

public class MusicTrack {

	private List<MidiNote> midiNotes;
	private TimeSignature timeSignature;
	private PaintStyle paintStyle;

	public MusicTrack(List<MidiNote> midiNotes, TimeSignature timeSignature, PaintStyle paintStyle) {
		this.midiNotes = midiNotes;
		this.setTimeSignature(timeSignature);
		this.paintStyle = paintStyle;
	}

	public UlwilaTrack getUlwilaTrack() {
		List<MusicComponent> components = getComponents();
		List<UlwilaComponent> ulwilaComponents = new ArrayList<>();

		for (MusicComponent component : components) {
			ulwilaComponents.add(new UlwilaComponent(component, ""));
		}

		return new UlwilaTrack(ulwilaComponents, timeSignature);
	}

	public List<MusicComponent> getComponents() {
		List<MusicComponent> components = new ArrayList<>();
		int prevEnd = 0;
		int tripletCount = 0;
		for (MidiNote midiNote : midiNotes) {
			int startTime = midiNote.getStartTime();
			while (startTime > prevEnd) {
				int duration = startTime - prevEnd;
				
				NoteDuration restDuration = getTimeSignature().GetNoteDuration(duration);

				MusicComponent rest = getRestComponent(restDuration);
				components.add(rest);

				prevEnd += getTimeSignature().DurationToTime(restDuration);

			}
			prevEnd = midiNote.getEndTime();

			NoteDuration duration = getTimeSignature().GetNoteDuration(midiNote.getDuration());
			System.out.println(duration);

			MusicComponent comp = getNoteComponent(duration, midiNote.getOctave(), midiNote.getTone(), paintStyle);
			if (comp != null) {
				if (comp instanceof TripletNote) {
					if (tripletCount % 3 == 0) {
						((TripletNote) comp).setFirst(true);
					}
					tripletCount++;
				}
				components.add(comp);
			}
		}

		return components;
	}

	public MusicComponent getNoteComponent(NoteDuration noteDuration, Octave octave, Tone tone, PaintStyle paintStyle) {
		switch (noteDuration) {
		case Whole:
			if (tone.isSemiTone()) {
				return new WholeSemiNote(octave, tone, paintStyle);
			} else {
				return new WholeNote(octave, tone, paintStyle);
			}
		case Half:
			if (tone.isSemiTone()) {
				return new HalfSemiNote(octave, tone, paintStyle);
			} else {
				return new HalfNote(octave, tone, paintStyle);
			}
		case DottedHalf:
			if (tone.isSemiTone()) {
				return new DottedSemiHalfNote(octave, tone, paintStyle);
			} else {
				return new DottedHalfNote(octave, tone, paintStyle);
			}
		case Quarter:
			if (tone.isSemiTone()) {
				return new QuarterSemiNote(octave, tone, paintStyle);
			} else {
				return new QuarterNote(octave, tone, paintStyle);
			}
		case DottedQuarter:
			if (tone.isSemiTone()) {
				return new DottedQuarterSemiNote(octave, tone, paintStyle);
			} else {
				return new DottedQuarterNote(octave, tone, paintStyle);
			}
		case Eighth:
			if (tone.isSemiTone()) {
				return new EighthSemiNote(octave, tone, paintStyle);
			} else {
				return new EighthNote(octave, tone, paintStyle);
			}
		case DottedEighth:
			if (tone.isSemiTone()) {
				return new DottedEighthSemiNote(octave, tone, paintStyle);
			} else {
				return new DottedEighthNote(octave, tone, paintStyle);
			}
		case Triplet:
			if (tone.isSemiTone()) {
				return new TripletSemiNote(octave, tone, paintStyle);
			} else {
				return new TripletNote(octave, tone, paintStyle);
			}
		case Sixteenth:
			if (tone.isSemiTone()) {
				return new SixteenthSemiNote(octave, tone, paintStyle);
			} else {
				return new SixteenthNote(octave, tone, paintStyle);
			}

		default:
			return null;
		}

	}

	public static MusicComponent getRestComponent(NoteDuration duration) {
		switch (duration) {
		case Whole:
			return new WholeRest();
		case Half:
			return new HalfRest();
		case DottedHalf:
			return new DottedHalfRest();
		case Quarter:
			return new QuarterRest();
		case DottedQuarter:
			return new DottedQuarterRest();
		case Eighth:
			return new EighthRest();
		case DottedEighth:
			return new DottedEighthRest();
		case Sixteenth:
			return new SixteenthRest();
		default:
			return null;
		}
	}

	public TimeSignature getTimeSignature() {
		return timeSignature;
	}

	public void setTimeSignature(TimeSignature timeSignature) {
		this.timeSignature = timeSignature;
	}

}
