package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.SwingWorker;

import hu.szaniszlaid.ulwila.notes.MusicComponent;
import hu.szaniszlaid.ulwila.notes.MusicNote;

//TODO choice instrument to play
public class UlwilaPlayer {

	private List<MusicComponent> musicComponents = new ArrayList<>();

	private MidiChannel channel; // 0 is a piano, 9 is percussion, other channels are for other instruments

	private int volume = 80; // between 0 and 127 TODO properties
	private int duration = 1600; // in milliseconds TODO properties

	private Synthesizer synth;

	private Player player;

	public UlwilaPlayer(UlwilaTrack track) {
		setTrack(track);
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			channel = synth.getChannels()[0];
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (player == null) {
			player = new Player();
			player.play();
		} else {
			player.resume();
		}
	}

	public void stop() {
		if (player != null) {
			player.stop();
			player = null;
	
			channel.allNotesOff();
			musicComponents.get(0).requestFocus();
		} 
	}

	public void pause() {
		if (player != null) {
			player.pause();
		}
	}

	private void setTrack(UlwilaTrack track) {
		for (UlwilaRow row : track.getRows()) {
			for (UlwilaBar bar : row.getBars()) {
				for (UlwilaComponent component : bar.getComponents()) {
					musicComponents.add(component.getMusicComponent());
				}
			}
		}
	}

	private class Player extends SwingWorker<Void, MusicComponent> {

		volatile boolean paused = false;
		volatile boolean stopped = false;

		int lastPlayedNote = 0;

		void play() {
			if (paused) {
				resume();
			} else {
				execute();
			}
		}

		void resume() {
			paused = false;
		}

		void pause() {
			paused = true;
		}

		void stop() {
			stopped = true;
		}

		@Override
		protected Void doInBackground() throws Exception {
			System.out.println("start playing in background");
			while(lastPlayedNote < musicComponents.size()) {
				if (!stopped) {
					if (!paused) {
						MusicComponent musicComponent = musicComponents.get(lastPlayedNote);
						lastPlayedNote++;

						publish(musicComponent);

						boolean isNote = musicComponent instanceof MusicNote;

						if (isNote) {
							channel.noteOn(((MusicNote) musicComponent).getMidiNumber(), volume);
						}

						Thread.sleep((long) (musicComponent.getMusicalLength() * duration));

						if (isNote) {
							channel.noteOff(((MusicNote) musicComponent).getMidiNumber());
						}

					} else {
						System.out.println("playing in doInBackground is paused");
						Thread.sleep(500);
					}
				} else {
					return null;
				}
			}

			return null;
		}

		@Override
		protected void done() {
			super.done();
			try { //call get to catch if there were exceptions in background
				get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

			System.out.println("Playing stopped");

		}

		@Override
		protected void process(List<MusicComponent> chunks) {
			super.process(chunks);
			//request current playing element to focus
			chunks.get(0).requestFocus();
		}
	}
}
