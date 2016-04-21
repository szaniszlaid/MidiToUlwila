package hu.szaniszlaid.ulwila.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hu.szaniszlaid.ulwila.notes.MusicNote;
import hu.szaniszlaid.ulwila.notes.UlwilaColor;
import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.QuarterNote;

public class UlwilaColorSettings extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public UlwilaColorSettings(JComponent parent) {
		int width = 360;
		double screenCenterX = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;

		setBounds((int) (screenCenterX - width / 2), 10, 364, 400);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		initNotes();

		//ok, cancel button
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.repaint();
				UlwilaColorSettings.this.dispatchEvent(new WindowEvent(UlwilaColorSettings.this, WindowEvent.WINDOW_CLOSING));

			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

	}

	private void initNotes() {
		contentPanel.setLayout(new GridLayout(4, 2));
		
		contentPanel.add(initNote(new QuarterNote(Octave.FIRST, Tone.C, PaintStyle.COLORED)));
		contentPanel.add(initNote(new QuarterNote(Octave.FIRST, Tone.D, PaintStyle.COLORED)));
		contentPanel.add(initNote(new QuarterNote(Octave.FIRST, Tone.E, PaintStyle.COLORED)));
		contentPanel.add(initNote(new QuarterNote(Octave.FIRST, Tone.F, PaintStyle.COLORED)));
		contentPanel.add(initNote(new QuarterNote(Octave.FIRST, Tone.G, PaintStyle.COLORED)));
		contentPanel.add(initNote(new QuarterNote(Octave.FIRST, Tone.A, PaintStyle.COLORED)));
		contentPanel.add(initNote(new QuarterNote(Octave.FIRST, Tone.H, PaintStyle.COLORED)));
		contentPanel.add(initNote(new QuarterNote(Octave.FIRST, Tone.C, PaintStyle.COLORED)));

	}

	private JPanel initNote(MusicNote note) {
		JPanel panel = new JPanel();
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();

		JButton btn = new JButton(note.getTone().name());

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
				Color colorOriginal = ulwilaColor.getColorByTone(note.getTone());
				chooser.setColor(colorOriginal);
				chooser.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						Color color = chooser.getColor();
						ulwilaColor.setColorByTone(note.getTone(), color);
						repaint();
					}
				});
				JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, chooser, null, new ActionListener() {

					//cancel
					@Override
					public void actionPerformed(ActionEvent e) {
						ulwilaColor.setColorByTone(note.getTone(), colorOriginal);
						repaint();
					}
				});
				dialog.setVisible(true);
			}
		});

		panel.add(btn);
		panel.add(note);

		return panel;}

}
