package hu.szaniszlaid.ulwila.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ProgressMonitor;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import hu.szaniszlaid.ulwila.midi.MidiFile;
import hu.szaniszlaid.ulwila.midi.MidiTrack;
import hu.szaniszlaid.ulwila.midi.TimeSignature;
import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.MusicComponent;
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
import hu.szaniszlaid.ulwila.view.CustomizedButton.CustomizedButtonBuilder;

public class Main extends JFrame {

	private JPanel mainPanel = new JPanel();
	private JScrollPane scrollPanel;
	private UlwilaTrack ulwilaTrack;

	private JButton openBtn;
	private JButton sampleBtn;
	private JButton exportHtmlBtn;
	private JButton exportWordBtn;
	
	private ProgressMonitor progressMonitor;

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
		setSize(1000, 500);

		setLocationRelativeTo(null);

		JPanel menu = new JPanel();

		initOpenButton();
		menu.add(openBtn);

		initSampleButton();
		menu.add(sampleBtn);

		initExportHtmlButton();
		menu.add(exportHtmlBtn);

		initExportWordButton();
		menu.add(exportWordBtn);

		// setExportButtonsEnabled(false);

		scrollPanel = new JScrollPane();
		scrollPanel.setColumnHeaderView(menu);

		// set scroll speed TODO properties file
		scrollPanel.getVerticalScrollBar().setUnitIncrement(24);

		mainPanel.add(scrollPanel, BorderLayout.CENTER);

		JPanel ulwilaSheet = new JPanel();
		ulwilaSheet.setLayout(new BoxLayout(ulwilaSheet, BoxLayout.Y_AXIS));

		setContentPane(scrollPanel);
	}

	private void initSampleButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/sampleLogo_up.png")
				.imgRolloverURL("/images/sampleLogo_hower.png")
				.imgPressedURL("/images/sampleLogo_down.png");

			sampleBtn = builder.create();

		sampleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				scrollPanel.setViewportView(getSampleTrackFromMap(getTestNotes()).getPanel());
			}
		});
	}

	private void initOpenButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/openLogo_up.png")
				.imgRolloverURL("/images/openLogo_hower.png")
				.imgPressedURL("/images/openLogo_down.png");

			openBtn = builder.create();

		openBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();

					// List<UlwilaRow> rows = getRows(getMusicTrack(new File(selectedFile.getPath())).get(0));
					// TODO modify this to list (remove get(0))
					MusicTrack track = getMusicTrack(new File(selectedFile.getPath())).get(0);
					List<MusicComponent> components = track.getComponents();
					List<UlwilaComponent> ulwilaComponents = new ArrayList<>();

					for (MusicComponent component : components) { // FIXME remove sample asd
						ulwilaComponents.add(new UlwilaComponent(component, "asd"));
					}

					ulwilaTrack = new UlwilaTrack(ulwilaComponents, track.getTimeSignature());
					exportHtmlBtn.setEnabled(true);

					scrollPanel.setViewportView(ulwilaTrack.getPanel());

					setExportButtonsEnabled(true);
				}
			}
		});
	}

	private void initExportHtmlButton() {

		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
			.imgUrl("/images/htmlLogo_up.png")
			.imgRolloverURL("/images/htmlLogo_hower.png")
			.imgPressedURL("/images/htmlLogo_down.png");

		exportHtmlBtn = builder.create();

		// open saveAs dialog on button click
		exportHtmlBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showSaveDialog(exportHtmlBtn);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					//new ExportHelper().exportToHtml(ulwilaTrack, selectedFile);
				}

			}
		});
	}

	private void initExportWordButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/wordLogo_up.jpg")
				.imgRolloverURL("/images/wordLogo_hower.jpg")
				.imgPressedURL("/images/wordLogo_down.jpg");

			exportWordBtn = builder.create();

		// open saveAs dialog on button click
		exportWordBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showSaveDialog(exportHtmlBtn);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					//ExportHelper.exportToWord(ulwilaTrack, selectedFile);
					new WordExportHelper(Main.this, ulwilaTrack, selectedFile).generate();;
				}
			}
		});
	}

	private void setExportButtonsEnabled(boolean enabled) {
		exportHtmlBtn.setEnabled(enabled);
		exportWordBtn.setEnabled(enabled);
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
			@Override
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

	private static UlwilaTrack getSampleTrackFromMap(Map<Octave, List<Tone>> notes) {

		List<UlwilaComponent> ulwilaComponents = new ArrayList<>();

		// rests
		ulwilaComponents.add(new UlwilaComponent(new SixteenthRest()));
		ulwilaComponents.add(new UlwilaComponent(new EighthRest()));
		ulwilaComponents.add(new UlwilaComponent(new QuarterRest()));
		ulwilaComponents.add(new UlwilaComponent(new HalfRest()));
		ulwilaComponents.add(new UlwilaComponent(new WholeRest()));

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new SixteenthSemiNote(octave, tone), "a"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new SixteenthNote(octave, tone), "s"));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new EighthSemiNote(octave, tone), "as"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new EighthNote(octave, tone), "as"));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new QuarterSemiNote(octave, tone), "asd"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new QuarterNote(octave, tone), "asd"));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new HalfSemiNote(octave, tone), "asdf"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new HalfNote(octave, tone), "asdf"));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new WholeSemiNote(octave, tone), "asdfjklé"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new WholeNote(octave, tone), "asdfjklé"));
				}
			}
		}

		return new UlwilaTrack(ulwilaComponents, new TimeSignature(4, 4, 20, 120));
	}

}
