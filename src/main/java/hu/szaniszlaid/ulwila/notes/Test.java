package hu.szaniszlaid.ulwila.notes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.rest.EightRest;
import hu.szaniszlaid.ulwila.notes.rest.HalfRest;
import hu.szaniszlaid.ulwila.notes.rest.QuarterRest;
import hu.szaniszlaid.ulwila.notes.rest.SixteenthRest;
import hu.szaniszlaid.ulwila.notes.rest.WholeRest;
import hu.szaniszlaid.ulwila.notes.semi.EightSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.HalfSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.QuarterSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.SixteenthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.WholeSemiNote;
import hu.szaniszlaid.ulwila.notes.whole.EighthNote;
import hu.szaniszlaid.ulwila.notes.whole.HalfNote;
import hu.szaniszlaid.ulwila.notes.whole.QuarterNote;
import hu.szaniszlaid.ulwila.notes.whole.SixteenthNote;
import hu.szaniszlaid.ulwila.notes.whole.WholeNote;
import hu.szaniszlaid.ulwila.view.MidiFile;
import hu.szaniszlaid.ulwila.view.MidiTrack;
import hu.szaniszlaid.ulwila.view.MusicTrack;
import hu.szaniszlaid.ulwila.view.NoteDuration;
import hu.szaniszlaid.ulwila.view.TimeSignature;

public class Test extends JFrame {

	private JPanel mainPanel = new JPanel();

	private void initComponents() {

		// Set sizes of root frame
		setBounds(500, 500, 800, 500);

		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel menu = new JPanel();
		mainPanel.add(menu, BorderLayout.NORTH);

		JButton btnHha = new JButton("Uzsgyi");
		btnHha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();

					JPanel notesPanel = getMidiTrack(new File(selectedFile.getPath()));
					repaintNotesPanel(notesPanel);						

				}
			}
		});

		menu.add(btnHha);
		
		JButton btnSample = new JButton("Sample");
		btnSample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				repaintNotesPanel(getNotesPanelFromMap(getTestNotes()));
			}
		});
		menu.add(btnSample);

		setContentPane(mainPanel);
	}
	
	private void repaintNotesPanel(JPanel notesPanel){
		notesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		notesPanel.setPreferredSize(new Dimension(500, 1800));

		JScrollPane scrollPanel = new JScrollPane(notesPanel);
		mainPanel.add(scrollPanel, BorderLayout.CENTER);

		// set scroll speed TODO properties file
		scrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		
		mainPanel.revalidate();		

	}



	public static JPanel getMidiTrack(File info) {

		MidiFile f = new MidiFile(info);
		List<MidiTrack> tracks = f.getTracks();
		TimeSignature timeSignature = f.getTimesig();

		JPanel notesPanel = new JPanel();

		for (MidiTrack midiTrack : tracks) {
			MusicTrack musicTrack = new MusicTrack(midiTrack.getNotes(), timeSignature);

			for (MusicComponent component : musicTrack.getComponents()) {
			    System.out.println(component.getSize());
				notesPanel.add(component);
			}
		}

		return notesPanel;

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Test() {
		// set LookAndFeel to system default
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponents();

	}

	private static Map<Octave, List<Tone>> getTestNotes() {
		// Dummy notes for testing
		Map<Octave, List<Tone>> notes = new LinkedHashMap<>();
		notes.put(Octave.FIRST, new ArrayList<>());
		for (Tone tone : Tone.values()) {
			notes.get(Octave.FIRST).add(tone);
		}

		notes.put(Octave.SECOND, new ArrayList<>());
		for (Tone tone : Tone.values()) {
			notes.get(Octave.SECOND).add(tone);
		}

		notes.put(Octave.THIRD, new ArrayList<>());
		for (Tone tone : Tone.values()) {
			notes.get(Octave.THIRD).add(tone);
		}

		notes.put(Octave.FOURTH, new ArrayList<>());
		notes.get(Octave.FOURTH).add(Tone.C);

		return notes;
	}

	private static JPanel getNotesPanelFromMap(Map<Octave, List<Tone>> notes) {

		// Test notes
		JPanel notesPanel = new JPanel();

		// rests
		notesPanel.add(new SixteenthRest());
		notesPanel.add(new EightRest());
		notesPanel.add(new QuarterRest());
		notesPanel.add(new HalfRest());
		notesPanel.add(new WholeRest());

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					notesPanel.add(new SixteenthSemiNote(octave, tone));
				} else {
					notesPanel.add(new SixteenthNote(octave, tone));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					notesPanel.add(new EightSemiNote(octave, tone));
				} else {
					notesPanel.add(new EighthNote(octave, tone));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					notesPanel.add(new QuarterSemiNote(octave, tone));
				} else {
					notesPanel.add(new QuarterNote(octave, tone));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					notesPanel.add(new HalfSemiNote(octave, tone));
				} else {
					notesPanel.add(new HalfNote(octave, tone));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					notesPanel.add(new WholeSemiNote(octave, tone));
				} else {
					notesPanel.add(new WholeNote(octave, tone));
				}
			}
		}

		return notesPanel;
	}

}
