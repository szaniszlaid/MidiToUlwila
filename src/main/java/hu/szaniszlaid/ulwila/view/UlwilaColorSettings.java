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
		contentPanel.add(initC());
		contentPanel.add(initD());
		contentPanel.add(initE());
		contentPanel.add(initF());
		contentPanel.add(initG());
		contentPanel.add(initA());
		contentPanel.add(initH());
		contentPanel.add(initCOctave());

	}

	private JPanel initC() {
		JPanel panelC = new JPanel();
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		MusicNote noteC = new QuarterNote(Octave.FIRST, Tone.C, PaintStyle.COLORED);

		JButton btnC = new JButton("C");

		btnC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
				Color colorOriginal = ulwilaColor.getC();
				chooser.setColor(ulwilaColor.getC());
				chooser.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						Color color = chooser.getColor();
						ulwilaColor.setC(color);
						repaint();
					}
				});
				JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, chooser, null, new ActionListener() {

					//cancel
					@Override
					public void actionPerformed(ActionEvent e) {
						ulwilaColor.setC(colorOriginal);
						repaint();
					}
				});
				dialog.setVisible(true);
			}
		});

		panelC.add(btnC);
		panelC.add(noteC);

		return panelC;
	}

	private JPanel initD() {
		JPanel panelD = new JPanel();
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		MusicNote noteD = new QuarterNote(Octave.FIRST, Tone.D, PaintStyle.COLORED);

		JButton btnD = new JButton("D");

		btnD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
				Color colorOriginal = ulwilaColor.getD();
				chooser.setColor(ulwilaColor.getD());
				chooser.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						Color color = chooser.getColor();
						ulwilaColor.setD(color);
						repaint();
					}
				});
				JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, chooser, null, new ActionListener() {

					//cancel
					@Override
					public void actionPerformed(ActionEvent e) {
						ulwilaColor.setD(colorOriginal);
						repaint();
					}
				});
				dialog.setVisible(true);
			}
		});

		panelD.add(btnD);
		panelD.add(noteD);

		return panelD;
	}

	private JPanel initE() {
		JPanel panelE = new JPanel();
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		MusicNote noteE = new QuarterNote(Octave.FIRST, Tone.E, PaintStyle.COLORED);

		JButton btnE = new JButton("E");

		btnE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
				Color colorOriginal = ulwilaColor.getE();
				chooser.setColor(ulwilaColor.getE());
				chooser.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						Color color = chooser.getColor();
						ulwilaColor.setE(color);
						repaint();
					}
				});
				JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, chooser, null, new ActionListener() {

					//cancel
					@Override
					public void actionPerformed(ActionEvent e) {
						ulwilaColor.setE(colorOriginal);
						repaint();
					}
				});
				dialog.setVisible(true);
			}
		});

		panelE.add(btnE);
		panelE.add(noteE);

		return panelE;
	}

	private JPanel initF() {
		JPanel panelF = new JPanel();
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		MusicNote noteF = new QuarterNote(Octave.FIRST, Tone.F, PaintStyle.COLORED);

		JButton btnF = new JButton("F");

		btnF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
				Color colorOriginal = ulwilaColor.getF();
				chooser.setColor(ulwilaColor.getF());
				chooser.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						Color color = chooser.getColor();
						ulwilaColor.setF(color);
						repaint();
					}
				});
				JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, chooser, null, new ActionListener() {

					//cancel
					@Override
					public void actionPerformed(ActionEvent e) {
						ulwilaColor.setF(colorOriginal);
						repaint();
					}
				});
				dialog.setVisible(true);
			}
		});

		panelF.add(btnF);
		panelF.add(noteF);

		return panelF;
	}

	private JPanel initG() {
		JPanel panelG = new JPanel();
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		MusicNote noteG = new QuarterNote(Octave.FIRST, Tone.G, PaintStyle.COLORED);

		JButton btnG = new JButton("G");

		btnG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
				Color colorOriginal = ulwilaColor.getG();
				chooser.setColor(ulwilaColor.getG());
				chooser.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						Color color = chooser.getColor();
						ulwilaColor.setG(color);
						repaint();
					}
				});
				JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, chooser, null, new ActionListener() {

					//cancel
					@Override
					public void actionPerformed(ActionEvent e) {
						ulwilaColor.setG(colorOriginal);
						repaint();
					}
				});
				dialog.setVisible(true);
			}
		});

		panelG.add(btnG);
		panelG.add(noteG);

		return panelG;
	}

	private JPanel initA() {
		JPanel panelA = new JPanel();
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		MusicNote noteA = new QuarterNote(Octave.FIRST, Tone.A, PaintStyle.COLORED);

		JButton btnA = new JButton("A");

		btnA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
				Color colorOriginal = ulwilaColor.getA();
				chooser.setColor(ulwilaColor.getA());
				chooser.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						Color color = chooser.getColor();
						ulwilaColor.setA(color);
						repaint();
					}
				});
				JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, chooser, null, new ActionListener() {

					//cancel
					@Override
					public void actionPerformed(ActionEvent e) {
						ulwilaColor.setA(colorOriginal);
						repaint();
					}
				});
				dialog.setVisible(true);
			}
		});

		panelA.add(btnA);
		panelA.add(noteA);

		return panelA;
	}

	private JPanel initH() {
		JPanel panelH = new JPanel();
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		MusicNote noteH = new QuarterNote(Octave.FIRST, Tone.H, PaintStyle.COLORED);

		JButton btnH = new JButton("H");

		btnH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
				Color colorOriginal = ulwilaColor.getH();
				chooser.setColor(ulwilaColor.getH());
				chooser.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						Color color = chooser.getColor();
						ulwilaColor.setH(color);
						repaint();
					}
				});
				JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, chooser, null, new ActionListener() {

					//cancel
					@Override
					public void actionPerformed(ActionEvent e) {
						ulwilaColor.setH(colorOriginal);
						repaint();
					}
				});
				dialog.setVisible(true);
			}
		});

		panelH.add(btnH);
		panelH.add(noteH);

		return panelH;
	}

	private JPanel initCOctave() {
		JPanel panelC = new JPanel();
		UlwilaColor ulwilaColor = UlwilaColor.getInstance();
		MusicNote noteC = new QuarterNote(Octave.THIRD, Tone.C, PaintStyle.COLORED);

		JButton btnC = new JButton("C'");

		btnC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
				Color colorOriginal = ulwilaColor.getC();
				chooser.setColor(ulwilaColor.getC());
				chooser.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						Color color = chooser.getColor();
						ulwilaColor.setC(color);
						repaint();
					}
				});
				JDialog dialog = JColorChooser.createDialog(null, "Color Chooser", true, chooser, null, new ActionListener() {

					//cancel
					@Override
					public void actionPerformed(ActionEvent e) {
						ulwilaColor.setC(colorOriginal);
						repaint();
					}
				});
				dialog.setVisible(true);
			}
		});

		panelC.add(btnC);
		panelC.add(noteC);

		return panelC;
	}



}
