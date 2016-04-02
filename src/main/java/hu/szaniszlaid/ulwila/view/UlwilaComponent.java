package hu.szaniszlaid.ulwila.view;

import java.awt.Label;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class UlwilaComponent extends JPanel {

	private MusicComponent musicComponent;
	private String lyrics;

	public UlwilaComponent(MusicComponent musicComponent, String lyrics) {
		this.musicComponent = musicComponent;
		this.lyrics = lyrics;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(musicComponent);
		add(getLyricsField(lyrics));
		
	}
	
	public UlwilaComponent(MusicComponent musicComponent) {
		this(musicComponent, null);
	}
	
	
	private JTextField getLyricsField(String lyricsText) {
		JTextField lyricsField = new JTextField(lyricsText, Label.CENTER);
		lyricsField.setHorizontalAlignment(JTextField.CENTER);
		
		lyricsField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				lyrics = lyricsField.getText();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				//do nothing, text will be saved onFocusLost
			}
		});
		
		return lyricsField;
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
