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

//TODO choose instrument to play music
public class UlwilaPlayer {

	private MidiChannel channel; // 0 is a piano, 9 is percussion, other channels are for other instruments

	private int volume = 80; // between 0 and 127 TODO properties
	private int duration = 3200; // in milliseconds TODO properties

	private Synthesizer synth;

	private Player player;

	private static UlwilaPlayer instance = null;

	public static UlwilaPlayer getInstance() {
		if (instance == null) {
			instance = new UlwilaPlayer();
		}
		return instance;
	}

	private UlwilaPlayer() {

		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			channel = synth.getChannels()[0];
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play(UlwilaTrack ulwilaTrack) {
		if (player == null) {
			player = new Player(ulwilaTrack);
			player.play();
		} else {
			if (!ulwilaTrack.equals(player.getUlwilaTrack())) {
				player.stop();
				player = new Player(ulwilaTrack);
				player.play();
			} else {
				player.resume();
			}
		}
	}

	public void stop() {
		if (player != null) {
			player.stop();
			player = null;

			channel.allNotesOff();
		}
	}

	public void pause() {
		if (player != null) {
			player.pause();
		}
	}

	private class Player extends SwingWorker<Void, UlwilaComponent> {

		volatile boolean paused = false;
		volatile boolean stopped = false;

		volatile List<UlwilaComponent> ulwilaComponents;

		UlwilaTrack ulwilaTrack;

		public Player(UlwilaTrack ulwilaTrack) {
			ulwilaComponents = new ArrayList<>();
			setTrack(ulwilaTrack);
		}

		private void setTrack(UlwilaTrack ulwilaTrack) {
			this.ulwilaTrack = ulwilaTrack;
			for (UlwilaRow row : ulwilaTrack.getRows()) {
				for (UlwilaBar bar : row.getBars()) {
					for (UlwilaComponent component : bar.getComponents()) {
						ulwilaComponents.add(component);
					}
				}
			}
		}

		public UlwilaTrack getUlwilaTrack() {
			return ulwilaTrack;
		}

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
			ulwilaComponents.get(0).requestFocus();
			stopped = true;
		}

		@Override
		protected Void doInBackground() throws InterruptedException {
			while (lastPlayedNote < ulwilaComponents.size()) {
				if (!stopped) {
					if (!paused) {
						UlwilaComponent ulwilaComponent =  ulwilaComponents.get(lastPlayedNote);
						MusicComponent musicComponent = ulwilaComponent.getMusicComponent();

						lastPlayedNote++;

						publish(ulwilaComponent);

						boolean isNote = musicComponent instanceof MusicNote;


						if (isNote) {
							channel.noteOn(((MusicNote) musicComponent).getMidiNumber(), volume);
						}

						Thread.sleep((long) (musicComponent.getMusicalLength() * duration));

						if (isNote) {
							channel.noteOff(((MusicNote) musicComponent).getMidiNumber());
						}

					} else {
						Thread.sleep(200);
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
			try { // call get to catch if there were exceptions in background
				get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			UlwilaPlayer.this.stop();
			System.out.println("Playing stopped");

		}

		@Override
		protected void process(List<UlwilaComponent> chunks) {
			super.process(chunks);
			if (chunks != null && chunks.size() > 0) {
				// request current playing element to focus
				chunks.get(chunks.size() - 1).requestFocus();
			}
		}
	}
}
