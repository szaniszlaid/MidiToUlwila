package hu.szaniszlaid.ulwila.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class UlwilaComponent extends JPanel {

	private MusicComponent musicComponent;
	private UlwilaDrawing drawing;
	private String lyrics;

	public UlwilaComponent(MusicComponent musicComponent, String lyrics) {
		this.musicComponent = musicComponent;
		this.lyrics = lyrics;
		this.drawing = new UlwilaDrawing(musicComponent);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));			
		
		add(drawing);
		add(getLyricsField(lyrics));
	}
	
	public UlwilaComponent(MusicComponent musicComponent) {
		this(musicComponent, null);
	}
	
	public UlwilaDrawing getUlwilaDrawing() {
		return drawing;
	}
	
	@Override
	public void requestFocus() {
		drawing.requestFocus();
	};	
	
	private JTextField getLyricsField(String lyricsText) {
		JTextField lyricsField = new JTextField(lyricsText, Label.CENTER);
		lyricsField.setBorder(BorderFactory.createEmptyBorder());
		lyricsField.setHorizontalAlignment(JTextField.CENTER);
		setAlignmentX(Component.CENTER_ALIGNMENT);
		
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




class UlwilaDrawing extends JPanel implements FocusListener, MouseListener{
	
	MusicComponent component;
	JComponent roundedRect;
	
	public UlwilaDrawing(MusicComponent component) {
		this.component = component;
		this.roundedRect = getRoundedRect();
		
		setLayout(null);
		
		setPreferredSize(new Dimension(component.getWidth() + component.getHorizontalMargin(), component.getHeight() + component.getVerticalMargin()));
		setBorder(BorderFactory.createEmptyBorder(component.getMarginTop(), component.getMarginLeft(), component.getMarginBottom(), component.getMarginRight()));

		setFocusable(true);
		addFocusListener(this);
		addMouseListener(this);

		
		add(component);
		add(roundedRect);
		
		component.setBounds(component.getMarginLeft(), component.getMarginTop(), component.getWidth(), component.getHeight());
		roundedRect.setBounds(0, 0, component.getWidth() + component.getHorizontalMargin(), component.getHeight() + component.getVerticalMargin());
		
		setBackground(Color.WHITE);
	}	
	
	public MusicComponent getMusicComponent() {
		return component;
	}

	@Override
	public void mousePressed(MouseEvent me) {
		requestFocus();
	}

	@Override
	public void focusGained(FocusEvent e) {
		roundedRect.setVisible(true);
		((JComponent) getParent()).scrollRectToVisible(getBounds());
	}

	@Override
	public void focusLost(FocusEvent e) {
		roundedRect.setVisible(false);
	}
	
	private JComponent getRoundedRect() {
		JComponent rect = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(new Color(255, 157, 0, 128));
				g.fillRoundRect(0, 0, component.getWidth() + component.getHorizontalMargin(), 
						component.getHeight() + component.getVerticalMargin(), component.getHorizontalMargin(), component.getVerticalMargin());
				super.paintComponent(g);
			}
		};
		rect.setVisible(false);
		
		return rect;
	}

	@Override
	public void mouseReleased(MouseEvent me) {
	}

	@Override
	public void mouseClicked(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}	
}