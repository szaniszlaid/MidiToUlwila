package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.midi.MidiNote;
import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.MusicComponent;
import hu.szaniszlaid.ulwila.notes.rest.EighthRest;
import hu.szaniszlaid.ulwila.notes.rest.HalfRest;
import hu.szaniszlaid.ulwila.notes.rest.QuarterRest;
import hu.szaniszlaid.ulwila.notes.rest.SixteenthRest;
import hu.szaniszlaid.ulwila.notes.rest.WholeRest;
import hu.szaniszlaid.ulwila.notes.semi.EighthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.HalfSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.QuarterSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.SixteenthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.WholeSemiNote;
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
		case Eighth:
			if (tone.isSemiTone()) {
				return new EighthSemiNote(octave, tone);
			} else {
				return new EighthNote(octave, tone);
			}
		case Half:
			if (tone.isSemiTone()) {
				return new HalfSemiNote(octave, tone);
			} else {
				return new HalfNote(octave, tone);
			}
		case Quarter:
			if (tone.isSemiTone()) {
				return new QuarterSemiNote(octave, tone);
			} else {
				return new QuarterNote(octave, tone);
			}
		case Sixteenth:
			if (tone.isSemiTone()) {
				return new SixteenthSemiNote(octave, tone);
			} else {
				return new SixteenthNote(octave, tone);
			}
		case Whole:
			if (tone.isSemiTone()) {
				return new WholeSemiNote(octave, tone);
			} else {
				return new WholeNote(octave, tone);
			}
		default:
			return null;
		}

	}
	
	public static MusicComponent getRestComponent(NoteDuration duration) {
		switch (duration) {
		case Eighth:
			return new EighthRest();
		case Half:
			return new HalfRest();
		case Quarter:
			return new QuarterRest();
		case Sixteenth:
			return new SixteenthRest();
		case Whole:
			return new WholeRest();
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
