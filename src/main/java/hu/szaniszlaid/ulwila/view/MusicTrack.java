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
import hu.szaniszlaid.ulwila.notes.semi.WholeSemiNote;
import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.DottedEighthNote;
import hu.szaniszlaid.ulwila.notes.whole.DottedHalfNote;
import hu.szaniszlaid.ulwila.notes.whole.DottedQuarterNote;
import hu.szaniszlaid.ulwila.notes.whole.EighthNote;
import hu.szaniszlaid.ulwila.notes.whole.HalfNote;
import hu.szaniszlaid.ulwila.notes.whole.QuarterNote;
import hu.szaniszlaid.ulwila.notes.whole.SixteenthNote;
import hu.szaniszlaid.ulwila.notes.whole.WholeNote;

public class MusicTrack {

	private List<MidiNote> midiNotes;
	private TimeSignature timeSignature;

	public MusicTrack(List <MidiNote> midiNotes, TimeSignature timeSignature){
		this.midiNotes = midiNotes;
		this.setTimeSignature(timeSignature);
	}

	public List<MusicComponent> getComponents(){
		List <MusicComponent> components = new ArrayList<>();
		int prevEnd = 0;
		for (MidiNote midiNote : midiNotes) {
			int startTime = midiNote.getStartTime();
			while (startTime > prevEnd){
				int duration = startTime - prevEnd;

				NoteDuration restDuration = getTimeSignature().GetNoteDuration(duration);

				MusicComponent rest = getRestComponent(restDuration);
				components.add(rest);

				prevEnd += getTimeSignature().DurationToTime(restDuration);


			}
			prevEnd = midiNote.getEndTime();


			NoteDuration duration = getTimeSignature().GetNoteDuration(midiNote.getDuration());

			MusicComponent comp = getNoteComponent(duration, midiNote.getOctave(), midiNote.getTone());
			if (comp != null){
				components.add(comp);
			}
		}

		return components;
	}

	public MusicComponent getNoteComponent(NoteDuration noteDuration, Octave octave, Tone tone) {
		switch (noteDuration) {
		case Whole:
			if (tone.isSemiTone()) {
				return new WholeSemiNote(octave, tone);
			} else {
				return new WholeNote(octave, tone);
			}
		case Half:
			if (tone.isSemiTone()) {
				return new HalfSemiNote(octave, tone);
			} else {
				return new HalfNote(octave, tone);
			}
		case DottedHalf:
			if (tone.isSemiTone()) {
				return new DottedSemiHalfNote(octave, tone);
			} else {
				return new DottedHalfNote(octave, tone);
			}
		case Quarter:
			if (tone.isSemiTone()) {
				return new QuarterSemiNote(octave, tone);
			} else {
				return new QuarterNote(octave, tone);
			}
		case DottedQuarter:
			if (tone.isSemiTone()) {
				return new DottedQuarterSemiNote(octave, tone);
			} else {
				return new DottedQuarterNote(octave, tone);
			}
		case Eighth:
			if (tone.isSemiTone()) {
				return new EighthSemiNote(octave, tone);
			} else {
				return new EighthNote(octave, tone);
			}
		case DottedEighth:
			if (tone.isSemiTone()) {
				return new DottedEighthSemiNote(octave, tone);
			} else {
				return new DottedEighthNote(octave, tone);
			}
		case Sixteenth:
			if (tone.isSemiTone()) {
				return new SixteenthSemiNote(octave, tone);
			} else {
				return new SixteenthNote(octave, tone);
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
