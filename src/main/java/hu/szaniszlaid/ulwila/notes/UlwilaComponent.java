package hu.szaniszlaid.ulwila.notes;

import java.awt.Label;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UlwilaComponent extends JPanel {

	private MusicComponent musicComponent;
	private String lyrics;

	public UlwilaComponent(MusicComponent musicComponent, String lyrics) {
		this.musicComponent = musicComponent;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(musicComponent);

		JTextField lyricsField = new JTextField(lyrics, Label.CENTER);
		lyricsField.setHorizontalAlignment(JTextField.CENTER);
		add(lyricsField);
	}

	public MusicComponent getMusicComponent() {
		return musicComponent;
	}

	public void setMusicComponent(MusicComponent musicComponent) {
		this.musicComponent = musicComponent;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

}
