package hu.szaniszlaid.ulwila.notes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import hu.szaniszlaid.ulwila.view.TimeSignature;
import hu.szaniszlaid.ulwila.view.UlwilaRow;
import javax.swing.BoxLayout;

public class Test extends JFrame {

	private JPanel mainPanel = new JPanel();
	private JScrollPane scrollPanel;

	private void initComponents() {

		// Set sizes of root frame
		setSize(800, 500);

		setLocationRelativeTo(null);

		JPanel menu = new JPanel();
		
		scrollPanel = new JScrollPane();
		scrollPanel.setColumnHeaderView(menu);	
		
		// set scroll speed TODO properties file
		scrollPanel.getVerticalScrollBar().setUnitIncrement(24);
		
		mainPanel.add(scrollPanel, BorderLayout.CENTER);
		
		JPanel ulwilaSheet = new JPanel();
		ulwilaSheet.setLayout(new BoxLayout(ulwilaSheet, BoxLayout.Y_AXIS));



		JButton btnOpenFile = new JButton("Uzsgyi");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();

					List<JPanel> rows = getRows(getMusicTrack(new File(selectedFile.getPath())).get(0));
					
					for (JPanel row : rows) {
						ulwilaSheet.add(row);						
					}
					
					scrollPanel.setViewportView(ulwilaSheet);	

				}
			}
		});

		menu.add(btnOpenFile);
		
		JButton btnSample = new JButton("Sample");
		btnSample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				scrollPanel.setViewportView(getNotesPanelFromMap(getTestNotes()));
			}
		});
		
		menu.add(btnSample);

		setContentPane(scrollPanel);
	}
	



	public static JPanel getNotesPanel(MusicTrack musicTrack) {

		JPanel notesPanel = new JPanel();
		notesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		for (MusicComponent component : musicTrack.getComponents()) {
			notesPanel.add(component);
		}

		return notesPanel;

	}
	
	private static List<MusicTrack> getMusicTrack(File file){

		MidiFile f = new MidiFile(file);
		List<MidiTrack> tracks = f.getTracks();
		TimeSignature timeSignature = f.getTimesig();
		
		List<MusicTrack> musicTracks = new ArrayList<>();

		System.out.println("TimeSignature: " + timeSignature.getNumerator() + "/" + timeSignature.getDenominator());
		

		for (MidiTrack midiTrack : tracks) {
			musicTracks.add(new MusicTrack(midiTrack.getNotes(), timeSignature));
		}
		
		return musicTracks;
	}
	
	
	
	public static List<JPanel> getRows(MusicTrack musicTrack){
		List<JPanel> rows = new ArrayList<>();
		UlwilaRow row = new UlwilaRow(musicTrack.getTimeSignature());
		for (MusicComponent component: musicTrack.getComponents()) {
			if (row.canFit(component)){
				row.add(component);
			} else { //TODO nem tetszik
				rows.add(row);
				row = new UlwilaRow(musicTrack.getTimeSignature());
				row.add(component);
			}
		}
		
		rows.add(row);
		
		return rows;
		
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
