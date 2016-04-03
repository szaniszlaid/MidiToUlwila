package hu.szaniszlaid.ulwila.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import hu.szaniszlaid.ulwila.export.HtmlExport;
import hu.szaniszlaid.ulwila.export.ImageExport;
import hu.szaniszlaid.ulwila.export.WordExport;
import hu.szaniszlaid.ulwila.midi.MidiFile;
import hu.szaniszlaid.ulwila.midi.MidiTrack;
import hu.szaniszlaid.ulwila.midi.TimeSignature;
import hu.szaniszlaid.ulwila.notes.rest.DottedEighthRest;
import hu.szaniszlaid.ulwila.notes.rest.DottedHalfRest;
import hu.szaniszlaid.ulwila.notes.rest.DottedQuarterRest;
import hu.szaniszlaid.ulwila.notes.rest.EighthRest;
import hu.szaniszlaid.ulwila.notes.rest.HalfRest;
import hu.szaniszlaid.ulwila.notes.rest.QuarterRest;
import hu.szaniszlaid.ulwila.notes.rest.SixteenthRest;
import hu.szaniszlaid.ulwila.notes.rest.WholeRest;
import hu.szaniszlaid.ulwila.notes.semi.DottedEighthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.DottedQuarterSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.DottedSemiHalfNote;
import hu.szaniszlaid.ulwila.notes.semi.EighthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.HalfSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.QuarterSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.SixteenthSemiNote;
import hu.szaniszlaid.ulwila.notes.semi.WholeSemiNote;
import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.DottedEighthNote;
import hu.szaniszlaid.ulwila.notes.whole.DottedHalfNote;
import hu.szaniszlaid.ulwila.notes.whole.DottedQuarterNote;
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
	private JButton exportImageBtn;
	private JButton playBtn;
	private JButton pauseBtn;
	private JButton stopBtn;
	private JButton paintStyleBtn;

	private UlwilaPlayer ulwilaPlayer;
	
	private static PaintStyle paintStyle = PaintStyle.COLORED;

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
		setSize(1000, 800);

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

		initPlayButton();
		menu.add(playBtn);

		initPauseButton();
		menu.add(pauseBtn);

		initStopButton();
		menu.add(stopBtn);
		
		initPaintStyleButton();
		menu.add(paintStyleBtn);
		
		initExportImageButton();
		menu.add(exportImageBtn);


		menu.setBackground(Color.WHITE);
		setButtonsEnabled(false);

		scrollPanel = new JScrollPane();
		scrollPanel.setColumnHeaderView(menu);
		scrollPanel.getViewport().setBackground(Color.WHITE);
		

		// set scroll speed TODO properties file
		scrollPanel.getVerticalScrollBar().setUnitIncrement(24);

		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(scrollPanel, BorderLayout.CENTER);

		JPanel ulwilaSheet = new JPanel();
		ulwilaSheet.setLayout(new BoxLayout(ulwilaSheet, BoxLayout.Y_AXIS));

		setContentPane(scrollPanel);
	}

	private void initPlayButton(){
		playBtn = new JButton("play");
		playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				pauseBtn.setEnabled(true);
				stopBtn.setEnabled(true);
				pauseBtn.setEnabled(true);
				ulwilaPlayer.play(ulwilaTrack);
			}
		});
	}

	private void initPauseButton(){
		pauseBtn = new JButton("pause");
		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				playBtn.setEnabled(true);
				pauseBtn.setEnabled(false);
				ulwilaPlayer.pause();
			}
		});
	}

	private void initStopButton(){
		stopBtn = new JButton("stop");
		stopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				playBtn.setEnabled(true);
				stopBtn.setEnabled(false);
				pauseBtn.setEnabled(false);
				ulwilaPlayer.stop();
			}
		});
	}
	
	private void initPaintStyleButton(){
		paintStyleBtn = new JButton(paintStyle.inverse().toString());
		//paintStyleBtn.setEnabled(false);
		paintStyleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				paintStyle = paintStyle.inverse();	
				ulwilaTrack.setPaintStyle(paintStyle);
				paintStyleBtn.setText(paintStyle.inverse().toString());
				scrollPanel.getViewport().repaint();
			}
		});
	}


	private void initSampleButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/sampleLogo_up.png")
				.imgRolloverURL("/images/sampleLogo_hower.png")
				.imgPressedURL("/images/sampleLogo_down.png")
				.toolTip("sample");

		sampleBtn = builder.create();

		sampleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ulwilaTrack = getSampleTrackFromMap(getTestNotes());
				scrollPanel.setViewportView(ulwilaTrack.getPanel());
			}
		});
	}

	private void initOpenButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/openLogo_up.png")
				.imgRolloverURL("/images/openLogo_hower.png")
				.imgPressedURL("/images/openLogo_down.png")
				.toolTip("open midi");

		openBtn = builder.create();

		openBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("MIDI files", "mid", "MID");
				fileChooser.setFileFilter(filter);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();

					// TODO modify this to list (remove get(0))
					MusicTrack musicTrack = getMusicTrack(new File(selectedFile.getPath())).get(0);
					ulwilaTrack = musicTrack.getUlwilaTrack();

					scrollPanel.setViewportView(ulwilaTrack.getPanel());
										

					setButtonsEnabled(true);

					ulwilaPlayer = UlwilaPlayer.getInstance();
				}
			}
		});
	}

	private void initExportHtmlButton() {

		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/htmlLogo_up.png")
				.imgRolloverURL("/images/htmlLogo_hower.png")
				.imgPressedURL("/images/htmlLogo_down.png")
				.toolTip("HTML export");

		exportHtmlBtn = builder.create();

		// open saveAs dialog on button click
		exportHtmlBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showSaveDialog(exportHtmlBtn);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					new HtmlExport(Main.this, ulwilaTrack, selectedFile).generate();
				}

			}
		});
	}
	
	private void initExportImageButton() {

//		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
//				.imgUrl("/images/htmlLogo_up.png")
//				.imgRolloverURL("/images/htmlLogo_hower.png")
//				.imgPressedURL("/images/htmlLogo_down.png")
//				.toolTip("HTML export");
//
//		exportHtmlBtn = builder.create();
		exportImageBtn = new JButton("ImageExport");

		// open saveAs dialog on button click
		exportImageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showSaveDialog(exportHtmlBtn);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					new ImageExport(Main.this, scrollPanel.getViewport(), selectedFile).generate();
				}

			}
		});
	}

	private void initExportWordButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/wordLogo_up.jpg")
				.imgRolloverURL("/images/wordLogo_hower.jpg")
				.imgPressedURL("/images/wordLogo_down.jpg")
				.toolTip("Word export");

		exportWordBtn = builder.create();

		// open saveAs dialog on button click
		exportWordBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showSaveDialog(exportHtmlBtn);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					// ExportHelper.exportToWord(ulwilaTrack, selectedFile);
					new WordExport(Main.this, ulwilaTrack, selectedFile).generate();
					;
				}
			}
		});
	}

	private void setButtonsEnabled(boolean enabled) {
		exportHtmlBtn.setEnabled(enabled);
		exportWordBtn.setEnabled(enabled);
		exportImageBtn.setEnabled(enabled);
		playBtn.setEnabled(enabled);
		pauseBtn.setEnabled(enabled);
		stopBtn.setEnabled(enabled);
		paintStyleBtn.setEnabled(enabled);
	}

	private static List<MusicTrack> getMusicTrack(File file) {

		MidiFile f = new MidiFile(file);
		List<MidiTrack> tracks = f.getTracks();
		TimeSignature timeSignature = f.getTimesig();

		List<MusicTrack> musicTracks = new ArrayList<>();

		for (MidiTrack midiTrack : tracks) {
			musicTracks.add(new MusicTrack(midiTrack.getNotes(), timeSignature, paintStyle));
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
		ulwilaComponents.add(new UlwilaComponent(new DottedEighthRest()));

		ulwilaComponents.add(new UlwilaComponent(new QuarterRest()));
		ulwilaComponents.add(new UlwilaComponent(new DottedQuarterRest()));
		ulwilaComponents.add(new UlwilaComponent(new HalfRest()));
		ulwilaComponents.add(new UlwilaComponent(new DottedHalfRest()));
		ulwilaComponents.add(new UlwilaComponent(new WholeRest()));

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new SixteenthSemiNote(octave, tone, paintStyle), "a"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new SixteenthNote(octave, tone, paintStyle), "s"));
				}
			}
		}

		//Eight
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new EighthSemiNote(octave, tone, paintStyle), "as"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new EighthNote(octave, tone, paintStyle), "as"));
				}
			}
		}

		//DottedEight
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new DottedEighthSemiNote(octave, tone, paintStyle), "as"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new DottedEighthNote(octave, tone, paintStyle), "as"));
				}
			}
		}

		//Quarter
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new QuarterSemiNote(octave, tone, paintStyle), "asd"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new QuarterNote(octave, tone, paintStyle), "asd"));
				}
			}
		}

		//DottedQuarter
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new DottedQuarterSemiNote(octave, tone, paintStyle), "as"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new DottedQuarterNote(octave, tone, paintStyle), "asd"));
				}
			}
		}

		//Half
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new HalfSemiNote(octave, tone, paintStyle), "asdf"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new HalfNote(octave, tone, paintStyle), "asdf"));
				}
			}
		}

		//DottedHalf
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new DottedSemiHalfNote(octave, tone, paintStyle), "asdf"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new DottedHalfNote(octave, tone, paintStyle), "asdf"));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new WholeSemiNote(octave, tone, paintStyle), "asdfjklé"));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new WholeNote(octave, tone, paintStyle), "asdfjklé"));
				}
			}
		}

		return new UlwilaTrack(ulwilaComponents, new TimeSignature(4, 4, 20, 120));
	}

}
