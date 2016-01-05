package hu.szaniszlaid.ulwila.notes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import hu.szaniszlaid.ulwila.midi.MidiFile;
import hu.szaniszlaid.ulwila.midi.MidiTrack;
import hu.szaniszlaid.ulwila.midi.TimeSignature;
import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.rest.EighthRest;
import hu.szaniszlaid.ulwila.notes.rest.HalfRest;
import hu.szaniszlaid.ulwila.notes.rest.QuarterRest;
import hu.szaniszlaid.ulwila.notes.rest.SixteenthRest;
import hu.szaniszlaid.ulwila.notes.rest.WholeRest;
import hu.szaniszlaid.ulwila.notes.semi.EighthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.HalfSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.QuarterSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.SixteenthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.WholeSemiNote;
import hu.szaniszlaid.ulwila.notes.whole.EighthNote;
import hu.szaniszlaid.ulwila.notes.whole.HalfNote;
import hu.szaniszlaid.ulwila.notes.whole.QuarterNote;
import hu.szaniszlaid.ulwila.notes.whole.SixteenthNote;
import hu.szaniszlaid.ulwila.notes.whole.WholeNote;
import hu.szaniszlaid.ulwila.view.ExportHelper;
import hu.szaniszlaid.ulwila.view.MusicTrack;
import hu.szaniszlaid.ulwila.view.UlwilaTrack;

public class Main extends JFrame {

	private JPanel mainPanel = new JPanel();
	private JScrollPane scrollPanel;

	public Main() {
		// set LookAndFeel to system default
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponents();

	}

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

					// List<UlwilaRow> rows = getRows(getMusicTrack(new File(selectedFile.getPath())).get(0));
					// TODO modify this to list (remove get(0))
					MusicTrack track = getMusicTrack(new File(selectedFile.getPath())).get(0);
					List<MusicComponent> components = track.getComponents();

					UlwilaTrack ulwilaTrack = new UlwilaTrack(components, track.getTimeSignature());

					scrollPanel.setViewportView(ulwilaTrack.getPanel());

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
		ArrayList<MusicComponent> components = new ArrayList<>();
		components.add(new EighthNote(Octave.THIRD, Tone.D));
		components.add(new EighthNote(Octave.THIRD, Tone.F));
		components.add(new QuarterNote(Octave.THIRD, Tone.G));
		components.add(new EighthNote(Octave.THIRD, Tone.A));
		components.add(new EighthNote(Octave.THIRD, Tone.D));
        components.add(new EighthNote(Octave.THIRD, Tone.F));
        components.add(new QuarterNote(Octave.THIRD, Tone.G));
        components.add(new EighthNote(Octave.THIRD, Tone.A));
		
		ExportHelper.exportComponents(components);
	}





	private static List<MusicTrack> getMusicTrack(File file) {

		MidiFile f = new MidiFile(file);
		List<MidiTrack> tracks = f.getTracks();
		TimeSignature timeSignature = f.getTimesig();

		List<MusicTrack> musicTracks = new ArrayList<>();

		for (MidiTrack midiTrack : tracks) {
			musicTracks.add(new MusicTrack(midiTrack.getNotes(), timeSignature));
		}

		return musicTracks;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

		// Main notes
		JPanel notesPanel = new JPanel();

		// rests
		notesPanel.add(new SixteenthRest());
		notesPanel.add(new EighthRest());
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
					notesPanel.add(new EighthSemiNote(octave, tone));
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
