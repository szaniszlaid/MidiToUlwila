package hu.szaniszlaid.ulwila.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
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
	private JButton settingsBtn;

	private JMenuBar menuBar;
	private JMenu fileMenu;

	private JMenuItem menuOpen;
	private JMenuItem menuSample;
	private JMenuItem menuPaintStyle;
	private JMenuItem menuSettings;

	private JMenu exportMenu;
	private JMenuItem menuHtmlExport;
	private JMenuItem menuWordExport;
	private JMenuItem menuImageExport;

	private JMenu playbackMenu;
	private JMenuItem menuPlay;
	private JMenuItem menuPause;
	private JMenuItem menuStop;

	JFileChooser fileChooser;

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

		JPanel controlPanel = new JPanel();

		initOpenButton();
		controlPanel.add(openBtn);

		initSampleButton();
		controlPanel.add(sampleBtn);

		initExportHtmlButton();
		controlPanel.add(exportHtmlBtn);

		initExportWordButton();
		controlPanel.add(exportWordBtn);

		initPlayButton();
		controlPanel.add(playBtn);

		initPauseButton();
		controlPanel.add(pauseBtn);

		initStopButton();
		controlPanel.add(stopBtn);

		initPaintStyleButton();
		controlPanel.add(paintStyleBtn);

		initExportImageButton();
		controlPanel.add(exportImageBtn);

		initSettingsButton();
		controlPanel.add(settingsBtn);

		initFileChooser();

		setJMenuBar(initMenu());
		
		controlPanel.setBorder(BorderFactory.createEtchedBorder());
		//controlPanel.setBackground(Color.WHITE);
		setButtonsEnabled(false);

		scrollPanel = new JScrollPane();
		scrollPanel.setColumnHeaderView(controlPanel);

		scrollPanel.getViewport().setBackground(Color.WHITE);

		// set scroll speed TODO properties file
		scrollPanel.getVerticalScrollBar().setUnitIncrement(24);

		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(scrollPanel, BorderLayout.CENTER);

		JPanel ulwilaSheet = new JPanel();
		ulwilaSheet.setLayout(new BoxLayout(ulwilaSheet, BoxLayout.Y_AXIS));

		setContentPane(scrollPanel);
	}

	private JMenuBar initMenu() {
		menuBar = new JMenuBar();

		fileMenu = new JMenu("Fájl");
		fileMenu.setMnemonic(KeyEvent.VK_A);

		//Open
		menuOpen = new JMenuItem("Megnyitás", KeyEvent.VK_T);
		menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		fileMenu.add(menuOpen);

		//Sample
		menuSample = new JMenuItem("Minta betöltése");
		menuSample.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadSample();
			}
		});
		fileMenu.add(menuSample);

		//paintStyle
		menuPaintStyle = new JMenuItem("Vázlatos/színes");
		menuPaintStyle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintStyleChange();
			}
		});
		fileMenu.add(menuPaintStyle);

		//Settings
		menuSettings = new JMenuItem("Beállítások");
		menuSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openSettings();
			}
		});
		fileMenu.add(menuSettings);

		menuBar.add(fileMenu);

		//export
		exportMenu = new JMenu("Export");
		menuBar.add(exportMenu);

		//html
		menuHtmlExport = new JMenuItem("HTML");
		menuHtmlExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportHtml();
			}
		});
		exportMenu.add(menuHtmlExport);

		//word
		menuWordExport = new JMenuItem("Word");
		menuWordExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportWord();
			}
		});
		exportMenu.add(menuWordExport);

		//image
		menuImageExport = new JMenuItem("Kép");
		menuImageExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportImage();
			}
		});
		exportMenu.add(menuImageExport);

		//playback
		playbackMenu = new JMenu("Lejátszás");
		menuBar.add(playbackMenu);

		//play
		menuPlay = new JMenuItem("Indítás");
		menuPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		playbackMenu.add(menuPlay);

		//stop
		menuStop = new JMenuItem("Megállítás");
		menuStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
		playbackMenu.add(menuStop);

		//pause
		menuPause = new JMenuItem("Szüneteltetés");
		menuPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		playbackMenu.add(menuPause);

		return menuBar;
	}

	private void initPlayButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/play_up.png")
				.imgRolloverURL("/images/play_hover.png")
				.imgPressedURL("/images/play_down.png")
				.toolTip("play");

		playBtn = builder.create();

		playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				play();
			}
		});
	}

	private void play() {
		pauseBtn.setEnabled(true);
		stopBtn.setEnabled(true);
		pauseBtn.setEnabled(true);
		ulwilaPlayer.play(ulwilaTrack);
	}

	private void initPauseButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/pause_up.png")
				.imgRolloverURL("/images/pause_hover.png")
				.imgPressedURL("/images/pause_down.png")
				.toolTip("pause");

		pauseBtn = builder.create();

		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				pause();
			}
		});
	}

	private void pause() {
		playBtn.setEnabled(true);
		pauseBtn.setEnabled(false);
		ulwilaPlayer.pause();
	}

	private void initStopButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/stop_up.png")
				.imgRolloverURL("/images/stop_hover.png")
				.imgPressedURL("/images/stop_down.png")
				.toolTip("stop");

		stopBtn = builder.create();
		stopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				stop();
			}
		});
	}

	private void stop() {
		playBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		pauseBtn.setEnabled(false);
		ulwilaPlayer.stop();
	}

	private void initPaintStyleButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/sketch_up.png")
				.imgRolloverURL("/images/sketch_hover.png")
				.imgPressedURL("/images/sketch_down.png")
				.toolTip("sketch");

		paintStyleBtn = builder.create();
		paintStyleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				paintStyleChange();
			}
		});
	}

	private void paintStyleChange() {
		paintStyle = paintStyle.inverse();
		ulwilaTrack.setPaintStyle(paintStyle);
		scrollPanel.getViewport().repaint();
	}

	private void initSampleButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/sample_up.png")
				.imgRolloverURL("/images/sample_hover.png")
				.imgPressedURL("/images/sample_down.png")
				.toolTip("sample");

		sampleBtn = builder.create();

		sampleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				loadSample();
			}
		});
	}

	private void loadSample() {
		ulwilaTrack = getSampleTrackFromMap(getTestNotes());
		scrollPanel.setViewportView(ulwilaTrack.getPanel());
	}

	private void initSettingsButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/settings_up.png")
				.imgRolloverURL("/images/settings_hover.png")
				.imgPressedURL("/images/settings_down.png")
				.toolTip("color settings");

		settingsBtn = builder.create();

		settingsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				openSettings();
			}
		});
	}

	private void openSettings() {
		new UlwilaColorSettings(scrollPanel.getViewport()).setVisible(true);
	}

	private void initOpenButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/open_up.png")
				.imgRolloverURL("/images/open_hover.png")
				.imgPressedURL("/images/open_down.png")
				.toolTip("open midi");

		openBtn = builder.create();

		openBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				open();
			}
		});
	}

	private void open() {
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			setTitle(selectedFile.getName());

			// TODO modify this to list (remove get(0))
			MusicTrack musicTrack = getMusicTrack(new File(selectedFile.getPath())).get(0);
			ulwilaTrack = musicTrack.getUlwilaTrack();

			scrollPanel.setViewportView(ulwilaTrack.getPanel());

			setButtonsEnabled(true);

			ulwilaPlayer = UlwilaPlayer.getInstance();
		}
	}

	private void initFileChooser() {
		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("MIDI files", "mid", "MID");
		fileChooser.setFileFilter(filter);
	}

	private void initExportHtmlButton() {

		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/html_up.png")
				.imgRolloverURL("/images/html_hover.png")
				.imgPressedURL("/images/html_down.png")
				.toolTip("HTML export");

		exportHtmlBtn = builder.create();

		// open saveAs dialog on button click
		exportHtmlBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				exportHtml();
			}
		});
	}

	private void exportHtml() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showSaveDialog(exportHtmlBtn);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			new HtmlExport(Main.this, ulwilaTrack, selectedFile).generate();
		}
	}

	private void initExportImageButton() {

		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/image_up.png")
				.imgRolloverURL("/images/image_hover.png")
				.imgPressedURL("/images/image_down.png")
				.toolTip("Image export");

		exportImageBtn = builder.create();

		// open saveAs dialog on button click
		exportImageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				exportImage();
			}
		});
	}

	private void exportImage() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showSaveDialog(exportHtmlBtn);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			new ImageExport(Main.this, scrollPanel.getViewport(), selectedFile).generate();
		}
	}

	private void initExportWordButton() {
		CustomizedButtonBuilder builder = new CustomizedButtonBuilder()
				.imgUrl("/images/word_up.png")
				.imgRolloverURL("/images/word_hover.png")
				.imgPressedURL("/images/word_down.png")
				.toolTip("Word export");

		exportWordBtn = builder.create();

		// open saveAs dialog on button click
		exportWordBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				exportWord();
			}
		});
	}

	private void exportWord() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showSaveDialog(exportHtmlBtn);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			// ExportHelper.exportToWord(ulwilaTrack, selectedFile);
			new WordExport(Main.this, ulwilaTrack, selectedFile).generate();
			;
		}
	}

	private void setButtonsEnabled(boolean enabled) {
		exportHtmlBtn.setEnabled(enabled);
		exportWordBtn.setEnabled(enabled);
		exportImageBtn.setEnabled(enabled);
		playBtn.setEnabled(enabled);
		pauseBtn.setEnabled(enabled);
		stopBtn.setEnabled(enabled);
		paintStyleBtn.setEnabled(enabled);

		menuHtmlExport.setEnabled(enabled);
		menuWordExport.setEnabled(enabled);
		menuImageExport.setEnabled(enabled);
		menuPlay.setEnabled(enabled);
		menuStop.setEnabled(enabled);
		menuPause.setEnabled(enabled);
		menuPaintStyle.setEnabled(enabled);
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
		for (Tone tone : Tone.values()) {
			notes.get(Octave.FOURTH).add(tone);
		}

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
					ulwilaComponents.add(new UlwilaComponent(new SixteenthSemiNote(octave, tone, paintStyle)));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new SixteenthNote(octave, tone, paintStyle)));
				}
			}
		}

		//Eight
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new EighthSemiNote(octave, tone, paintStyle)));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new EighthNote(octave, tone, paintStyle)));
				}
			}
		}

		//DottedEight
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new DottedEighthSemiNote(octave, tone, paintStyle)));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new DottedEighthNote(octave, tone, paintStyle)));
				}
			}
		}

		//Quarter
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new QuarterSemiNote(octave, tone, paintStyle)));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new QuarterNote(octave, tone, paintStyle)));
				}
			}
		}

		//DottedQuarter
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new DottedQuarterSemiNote(octave, tone, paintStyle)));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new DottedQuarterNote(octave, tone, paintStyle)));
				}
			}
		}

		//Half
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new HalfSemiNote(octave, tone, paintStyle)));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new HalfNote(octave, tone, paintStyle)));
				}
			}
		}

		//DottedHalf
		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new DottedSemiHalfNote(octave, tone, paintStyle)));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new DottedHalfNote(octave, tone, paintStyle)));
				}
			}
		}

		for (Octave octave : notes.keySet()) {
			for (Tone tone : notes.get(octave)) {
				if (tone.isSemiTone()) {
					ulwilaComponents.add(new UlwilaComponent(new WholeSemiNote(octave, tone, paintStyle)));
				} else {
					ulwilaComponents.add(new UlwilaComponent(new WholeNote(octave, tone, paintStyle)));
				}
			}
		}

		return new UlwilaTrack(ulwilaComponents, new TimeSignature(4, 4, 20, 120));
	}

}
