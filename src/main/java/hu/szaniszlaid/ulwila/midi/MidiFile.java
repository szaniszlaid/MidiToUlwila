package hu.szaniszlaid.ulwila.midi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MidiFile {

	
	private String filename;
	/** The Midi file name */
	private ArrayList<ArrayList<MidiEvent>> allevents;
	/** The raw MidiEvents, one list per track */
	private ArrayList<MidiTrack> tracks;
	/** The tracks of the midifile that have notes */
	private short trackmode;
	/** 0 (single track), 1 (simultaneous tracks) 2 (independent tracks) */
	private TimeSignature timesig;
	/** The time signature */
	private int quarternote;
	/** The number of pulses per quarter note */
	private int totalpulses;
	/** The total length of the song, in pulses */
	private boolean trackPerChannel;
	/** True if we've split each channel into a track */

	/* The list of Midi Events */
	public static final byte EventNoteOff = (byte) 0x80;
	public static final byte EventNoteOn = (byte) 0x90;
	public static final byte EventKeyPressure = (byte) 0xA0;
	public static final byte EventControlChange = (byte) 0xB0;
	public static final byte EventProgramChange = (byte) 0xC0;
	public static final byte EventChannelPressure = (byte) 0xD0;
	public static final byte EventPitchBend = (byte) 0xE0;
	public static final byte SysexEvent1 = (byte) 0xF0;
	public static final byte SysexEvent2 = (byte) 0xF7;
	public static final byte MetaEvent = (byte) 0xFF;

	/* The list of Meta Events */
	public static final byte MetaEventSequence = (byte) 0x0;
	public static final byte MetaEventText = (byte) 0x1;
	public static final byte MetaEventCopyright = (byte) 0x2;
	public static final byte MetaEventSequenceName = (byte) 0x3;
	public static final byte MetaEventInstrument = (byte) 0x4;
	public static final byte MetaEventLyric = (byte) 0x5;
	public static final byte MetaEventMarker = (byte) 0x6;
	public static final byte MetaEventEndOfTrack = (byte) 0x2F;
	public static final byte MetaEventTempo = (byte) 0x51;
	public static final byte MetaEventSMPTEOffset = (byte) 0x54;
	public static final byte MetaEventTimeSignature = (byte) 0x58;
	public static final byte MetaEventKeySignature = (byte) 0x59;
	
    /** Create a new MidiFile from the byte[] */
    public MidiFile(byte[] rawdata, String filename) {
        this.filename = filename;
        parse(rawdata);
    }
    
    /** Create a new MidiFile from the byte[] */
    public MidiFile(File file) {
        this(readFile(file), file.getName());
    }
    
    private static byte[] readFile(File file) {
        byte[] data = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getCanonicalPath());

            data = new byte[(int) file.length()];
            int offset = 0;
            int len = (int) file.length();
            while (true) {
                if (offset == len)
                    break;
                int n = fileInputStream.read(data, offset, len - offset);
                if (n <= 0)
                    break;
                offset += n;
            }
            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return data;
    }

	/**
	 * Parse the given Midi file, and return an instance of this MidiFile class. After reading the midi file, this object will contain: - The raw list of midi events - The Time
	 * Signature of the song - All the tracks in the song which contain notes. - The number, starttime, and duration of each note.
	 */
	private void parse(byte[] rawdata) {
		String id;
		int len;

		tracks = new ArrayList<MidiTrack>();
		trackPerChannel = false;

		MidiFileReader file = new MidiFileReader(rawdata);
		id = file.ReadAscii(4);
		if (!id.equals("MThd")) {
			throw new MidiFileException("Doesn't start with MThd", 0);
		}
		len = file.ReadInt();
		if (len != 6) {
			throw new MidiFileException("Bad MThd header", 4);
		}
		trackmode = (short) file.ReadShort();
		int num_tracks = file.ReadShort();
		quarternote = file.ReadShort();

		allevents = new ArrayList<ArrayList<MidiEvent>>();
		for (int tracknum = 0; tracknum < num_tracks; tracknum++) {
			allevents.add(ReadTrack(file));
			MidiTrack track = new MidiTrack(allevents.get(tracknum), tracknum);
			if (track.getNotes().size() > 0) {
				tracks.add(track);
			}
		}

		/* Get the length of the song in pulses */
		for (MidiTrack track : tracks) {
			MidiNote last = track.getNotes().get(track.getNotes().size() - 1);
			if (this.totalpulses < last.getStartTime() + last.getDuration()) {
				this.totalpulses = last.getStartTime() + last.getDuration();
			}
		}

		/*
		 * If we only have one track with multiple channels, then treat each channel as a separate track.
		 */
		if (tracks.size() == 1 && HasMultipleChannels(tracks.get(0))) {
			tracks = SplitChannels(tracks.get(0), allevents.get(tracks.get(0).trackNumber()));
			trackPerChannel = true;
		}

		CheckStartTimes(tracks);

		/* Determine the time signature */
		int tempoCount = 0;
		long tempo = 0;
		int numer = 0;
		int denom = 0;
		for (ArrayList<MidiEvent> list : allevents) {
			for (MidiEvent mevent : list) {
				if (mevent.Metaevent == MetaEventTempo) {
					// Take average of all tempos
					tempo += mevent.Tempo;
					tempoCount++;
				}
				if (mevent.Metaevent == MetaEventTimeSignature && numer == 0) {
					numer = mevent.Numerator;
					denom = mevent.Denominator;
				}
			}
		}
		if (tempo == 0) {
			tempo = 500000; /* 500,000 microseconds = 0.05 sec */
		} else {
			tempo = tempo / tempoCount;
		}
		if (numer == 0) {
			numer = 4;
			denom = 4;
		}
		setTimesig((new TimeSignature(numer, denom, quarternote, (int) tempo)));
	}

	/**
	 * Parse a single Midi track into a list of MidiEvents. Entering this function, the file offset should be at the start of the MTrk header. Upon exiting, the file offset should
	 * be at the start of the next MTrk header.
	 */
	private ArrayList<MidiEvent> ReadTrack(MidiFileReader file) {
		ArrayList<MidiEvent> result = new ArrayList<MidiEvent>(20);
		int starttime = 0;
		String id = file.ReadAscii(4);

		if (!id.equals("MTrk")) {
			throw new MidiFileException("Bad MTrk header", file.GetOffset() - 4);
		}
		int tracklen = file.ReadInt();
		int trackend = tracklen + file.GetOffset();

		byte eventflag = 0;

		while (file.GetOffset() < trackend) {

			// If the midi file is truncated here, we can still recover.
			// Just return what we've parsed so far.

			int startoffset, deltatime;
			byte peekevent;
			try {
				startoffset = file.GetOffset();
				deltatime = file.ReadVarlen();
				starttime += deltatime;
				peekevent = file.Peek();
			} catch (MidiFileException e) {
				return result;
			}

			MidiEvent mevent = new MidiEvent();
			result.add(mevent);
			mevent.DeltaTime = deltatime;
			mevent.StartTime = starttime;

			// if (peekevent >= EventNoteOff) {
			if (peekevent < 0) {
				mevent.HasEventflag = true;
				eventflag = file.ReadByte();
			}

			// Log.e("debug", "offset " + startoffset +
			// " event " + eventflag + " " + EventName(eventflag) +
			// " start " + starttime + " delta " + mevent.DeltaTime);

			if (eventflag >= EventNoteOn && eventflag < EventNoteOn + 16) {
				mevent.EventFlag = EventNoteOn;
				mevent.Channel = ((byte) (eventflag - EventNoteOn));
				mevent.Notenumber = file.ReadByte();
				mevent.Velocity = file.ReadByte();
			} else if (eventflag >= EventNoteOff && eventflag < EventNoteOff + 16) {
				mevent.EventFlag = EventNoteOff;
				mevent.Channel = ((byte) (eventflag - EventNoteOff));
				mevent.Notenumber = file.ReadByte();
				mevent.Velocity = file.ReadByte();
			} else if (eventflag >= EventKeyPressure && eventflag < EventKeyPressure + 16) {
				mevent.EventFlag = EventKeyPressure;
				mevent.Channel = ((byte) (eventflag - EventKeyPressure));
				mevent.Notenumber = file.ReadByte();
				mevent.KeyPressure = file.ReadByte();
			} else if (eventflag >= EventControlChange && eventflag < EventControlChange + 16) {
				mevent.EventFlag = EventControlChange;
				mevent.Channel = ((byte) (eventflag - EventControlChange));
				mevent.ControlNum = file.ReadByte();
				mevent.ControlValue = file.ReadByte();
			} else if (eventflag >= EventProgramChange && eventflag < EventProgramChange + 16) {
				mevent.EventFlag = EventProgramChange;
				mevent.Channel = ((byte) (eventflag - EventProgramChange));
				mevent.Instrument = file.ReadByte();
			} else if (eventflag >= EventChannelPressure && eventflag < EventChannelPressure + 16) {
				mevent.EventFlag = EventChannelPressure;
				mevent.Channel = ((byte) (eventflag - EventChannelPressure));
				mevent.ChanPressure = file.ReadByte();
			} else if (eventflag >= EventPitchBend && eventflag < EventPitchBend + 16) {
				mevent.EventFlag = EventPitchBend;
				mevent.Channel = ((byte) (eventflag - EventPitchBend));
				mevent.PitchBend = (short) file.ReadShort();
			} else if (eventflag == SysexEvent1) {
				mevent.EventFlag = SysexEvent1;
				mevent.Metalength = file.ReadVarlen();
				mevent.Value = file.ReadBytes(mevent.Metalength);
			} else if (eventflag == SysexEvent2) {
				mevent.EventFlag = SysexEvent2;
				mevent.Metalength = file.ReadVarlen();
				mevent.Value = file.ReadBytes(mevent.Metalength);
			} else if (eventflag == MetaEvent) {
				mevent.EventFlag = MetaEvent;
				mevent.Metaevent = file.ReadByte();
				mevent.Metalength = file.ReadVarlen();
				mevent.Value = file.ReadBytes(mevent.Metalength);
				if (mevent.Metaevent == MetaEventTimeSignature) {
					if (mevent.Metalength < 2) {
						throw new MidiFileException("Meta Event Time Signature len == " + mevent.Metalength + " != 4", file.GetOffset());
					} else {
						mevent.Numerator = ((byte) mevent.Value[0]);
						mevent.Denominator = ((byte) Math.pow(2, mevent.Value[1]));
					}
				} else if (mevent.Metaevent == MetaEventTempo) {
					if (mevent.Metalength != 3) {
						throw new MidiFileException("Meta Event Tempo len == " + mevent.Metalength + " != 3", file.GetOffset());
					}
					mevent.Tempo = ((mevent.Value[0] & 0xFF) << 16) | ((mevent.Value[1] & 0xFF) << 8) | (mevent.Value[2] & 0xFF);
				} else if (mevent.Metaevent == MetaEventEndOfTrack) {
					/* break; */
				}
			} else {
				throw new MidiFileException("Unknown event " + mevent.EventFlag, file.GetOffset() - 1);
			}
		}

		return result;
	}

	/**
	 * Return true if this track contains multiple channels. If a MidiFile contains only one track, and it has multiple channels, then we treat each channel as a separate track.
	 */
	static boolean HasMultipleChannels(MidiTrack track) {
		int channel = track.getNotes().get(0).getChannel();
		for (MidiNote note : track.getNotes()) {
			if (note.getChannel() != channel) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Split the given track into multiple tracks, separating each channel into a separate track.
	 */
	private static ArrayList<MidiTrack> SplitChannels(MidiTrack origtrack, ArrayList<MidiEvent> events) {

		/* Find the instrument used for each channel */
		int[] channelInstruments = new int[16];
		for (MidiEvent mevent : events) {
			if (mevent.EventFlag == EventProgramChange) {
				channelInstruments[mevent.Channel] = mevent.Instrument;
			}
		}
		channelInstruments[9] = 128; /* Channel 9 = Percussion */

		ArrayList<MidiTrack> result = new ArrayList<MidiTrack>();
		for (MidiNote note : origtrack.getNotes()) {
			boolean foundchannel = false;
			for (MidiTrack track : result) {
				if (note.getChannel() == track.getNotes().get(0).getChannel()) {
					foundchannel = true;
					track.AddNote(note);
				}
			}
			if (!foundchannel) {
				MidiTrack track = new MidiTrack(result.size() + 1);
				track.AddNote(note);
//				track.setInstrument(channelInstruments[note.getChannel()]);
				result.add(track);
			}
		}
//TODO lyrics
//		ArrayList<MidiEvent> lyrics = origtrack.getLyrics();
//		if (lyrics != null) {
//			for (MidiEvent lyricEvent : lyrics) {
//				for (MidiTrack track : result) {
//					if (lyricEvent.Channel == track.getNotes().get(0).getChannel()) {
//						track.AddLyric(lyricEvent);
//					}
//				}
//			}
//		}
		return result;	
	}
	
    /** Check that the MidiNote start times are in increasing order.
     * This is for debugging purposes.
     */
    private static void CheckStartTimes(ArrayList<MidiTrack> tracks) {
        for (MidiTrack track : tracks) {
            int prevtime = -1;
            for (MidiNote note : track.getNotes()) {
                if (note.getStartTime() < prevtime) {
                    throw new MidiFileException("Internal parsing error", 0);
                }
                prevtime = note.getStartTime();
            }
        }
    }

	public TimeSignature getTimesig() {
		return timesig;
	}

	public void setTimesig(TimeSignature timesig) {
		this.timesig = timesig;
	}
	
    /** Get the list of tracks */
    public ArrayList<MidiTrack> getTracks() { return tracks; }
}